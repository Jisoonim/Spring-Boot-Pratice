package org.zerock.day03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.day03.domain.Board;

/**
 * BoardRepository
 */
public interface BoardRepository extends JpaRepository<Board, Integer>, QuerydslPredicateExecutor{
   

    
}