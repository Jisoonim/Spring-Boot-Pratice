package org.zerock.day2.repositorties;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.zerock.day2.domain.Board;
import org.zerock.day2.repositories.BoardRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * BoardTests
 */
@SpringBootTest
@Slf4j
public class BoardTests {

    @Autowired
    private BoardRepository repo;

    @Test
    public void testInsert() {
        IntStream.range(1, 2)
        .forEach(i ->{

            Board board = new Board();
            board.setTitle("Sample Title"+i);
            board.setContent("Sample Title"+i);
            Board savedBoard = repo.save(board);

            log.info(savedBoard.toString());
        });
    }
    
    @Test
    public void testReadAndUpdate(){
        Optional<Board> result = repo.findById(99);
        
        Board board = null;
        if(result.isPresent()) {
            board = result.get();
            log.info(board.toString());

            board.setTitle("updated Title");
            Board updatedBoard = repo.save(board);
            log.info(updatedBoard.toString());
            
        }
    }

    @Test
    public void testPaging(){
        Pageable page = PageRequest.of(0, 10, Direction.DESC, "bno");

        Page<Board> result = repo.findAll(page);

        log.info(result.toString());

        log.info(result.getPageable().toString());

        Stream<Board> stream = result.get();
        List<Board> boardList = stream.collect(Collectors.toList());
        log.info(boardList.toString());
    }

    @Test
    public void testFindBy(){
        Pageable pageable = PageRequest.of(1, 10, Direction.DESC, "bno");
        Page<Board> result = repo.findByTitleContaining("3", pageable);

        log.info(result.toString());
   }
}