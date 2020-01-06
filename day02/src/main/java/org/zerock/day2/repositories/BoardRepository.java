package org.zerock.day2.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.day2.domain.Board;

/**
 * BoardRepository
 */
public interface BoardRepository extends JpaRepository<Board, Integer> {

    public Page<Board> findByTitleContaining(String keyword, Pageable pageable);
    
}