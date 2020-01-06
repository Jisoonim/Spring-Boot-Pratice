
package org.zerock.day03;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zerock.day03.domain.Board;
import org.zerock.day03.dto.SearchDTO;
import org.zerock.day03.repositories.BoardRepository;
import org.zerock.day03.repositories.BoardSearchPredicate;

import lombok.extern.slf4j.Slf4j;

/**
 * BoardRepoTests
 */
@SpringBootTest
@Slf4j
public class BoardRepoTests {

    @Autowired
    private BoardRepository repo;
    private BoardRepository BoardRepository;

    @Test
    public void testInsert() {
        IntStream.range(1, 101).forEach(i ->{

            Board board = new Board();
            board.setTitle("Title....." + (i%10));
            board.setContent("Content....." + (i%10));
            Board savedBoard = repo.save(board);

            log.info(savedBoard.toString());
        });
    }
    @Test
    public void testSearchTitle() {

        SearchDTO dto = new SearchDTO();
        dto.setType("t");
        dto.setKeyword("5");

        Pageable page = PageRequest.of(0,10);

        Page<Board> result = BoardRepository.findAll(BoardSearchPredicate.searchSimple(dto),page);
    }

    
}