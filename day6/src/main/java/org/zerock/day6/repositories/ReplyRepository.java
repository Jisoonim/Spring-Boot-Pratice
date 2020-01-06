package org.zerock.day6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.day6.domain.Reply;

/**
 * ReplyRepository
 */
public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    
}