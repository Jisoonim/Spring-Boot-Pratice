package org.zerock.day05_1202.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.day05_1202.domain.Board;
import org.zerock.day05_1202.domain.Reply;

/**
 * ReplyRepository
 */
public interface ReplyRepository extends JpaRepository<Reply,Integer> {

    public Page<Reply> findByBoard(Board board, Pageable page);

    
}