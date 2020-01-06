package org.zerock.day3.repository.ex1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.day3.domain.ex1.Player;

/**
 * PlayerRepository
 */
public interface PlayerRepository extends JpaRepository<Player, Integer>{


    
}