package org.zerock.day3.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.day3.domain.Board;

/**
 * BoardRepository
 */
public interface BoardRepository extends JpaRepository<Board, Integer>, QuerydslPredicateExecutor {

    @Query("SELECT Board b LEFT JOIN FETCH b.replyList WHERE b.bno = :bno")
    public Board getBoard(Integer bno);

    @Query("SELECT Board b LEFT JOIN FETCH b.replyList GROUP BY b")
    public List<Board> getBoardList(Pageable page);

    @Query( value =  "SELECT b, count(r) FROM Board b LEFT JOIN b.replyList r GROUP BY b",
    countQuery ="select count(b) FROM Board b")
    public Page<Object[]> getBoardList2(Pageable page);


    
}