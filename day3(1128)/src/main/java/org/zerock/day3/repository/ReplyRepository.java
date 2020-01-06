package org.zerock.day3.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.day3.domain.Board;
import org.zerock.day3.domain.ex1.Reply;

/**
 * ReplyRepository
 */
public interface ReplyRepository extends JpaRepository<Reply,Integer> {

    public Page<Reply> findByBoard(Board board, Pageable page);

    
}