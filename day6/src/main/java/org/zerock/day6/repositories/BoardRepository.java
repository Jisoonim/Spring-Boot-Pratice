package org.zerock.day6.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.day6.domain.Board;

/**
 * BoardRepository
 */
public interface BoardRepository extends JpaRepository<Board, Integer> {

    @Query(value = "SELECT b, w FROM Board b JOIN b.writer w",
    countQuery = "select count(b) from Board b")
    public Page<Object[]> getList1(Pageable page);

    @Query(value = "SELECT b, w, max(concat(f.ano, '_', f.name)) FROM Board b JOIN b.writer w LEFT JOIN b.files f",
        countQuery = "select count(b) from Board b")
    public Page<Object[]> getList2(Pageable page);

    @Query(value ="SELECT new org.zerock.day6.dto.BoardListDTO (b, w, f, count(r) FROM Board b.bno , b.title, w.mid, f.fname, JOIN b.writer w LEFT JOIN b.files f LEFT JOIN b.replyList r where f.current = true OR f is null GROUP by b ",
    countQuery = "select count(b) from Board b")
    public Page<Object[]> getList3(Pageable page);
    
}