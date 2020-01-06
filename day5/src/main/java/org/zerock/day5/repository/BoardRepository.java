package org.zerock.day5.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.day5.domain.Board;

/**
 * BoardRepository
 */
public interface BoardRepository extends JpaRepository<Board, Integer>, QuerydslPredicateExecutor {

    @Query("SELECT b, count(r) FROM Board b LEFT JOIN b.replyList r WHERE b.bno = :bno GROUP BY b")
    public Object[] getBoard(Integer bno);

    @Query(value = "SELECT b, count(r) FROM Board b LEFT JOIN b.replyList r GROUP BY b", countQuery = "SELECT count(b) FROM Board b")
    public Page<Object[]> getBoardList(Pageable page);

    @Query(value = "SELECT b, r FROM Board b LEFT JOIN b.replyList r WHERE b.bno = :bno ORDER BY r", countQuery = "SELECT count(r) FROM Reply r") // WHERE r.board.bno = :bno
    public Page<Object[]> getBoardWithReplyPage(Integer bno, Pageable page);
    
    // @Query("SELECT b FROM Board b LEFT JOIN FETCH b.replyList WHERE b.bno = :bno")
    // public Board getBoard(Integer bno);

    // @Query("SELECT b FROM Board b LEFT JOIN FETCH b.replyList GROUP BY b")
    // public List<Board> getBoardList(Pageable page);

    // @Query(value = "SELECT b, count(r) FROM Board b LEFT JOIN b.replyList r GROUP BY b", countQuery = "SELECT count(b) FROM Board b")
    // public Page<Object[]> getBoardList2(Pageable page);

    
}