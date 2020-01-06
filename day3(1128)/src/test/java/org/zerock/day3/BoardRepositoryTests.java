package org.zerock.day3;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.zerock.day3.domain.Board;
import org.zerock.day3.domain.ex1.Reply;
import org.zerock.day3.dto.SearchDTO;
import org.zerock.day3.repository.BoardRepository;
import org.zerock.day3.repository.BoardSearchPredicate;
import org.zerock.day3.repository.ReplyRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * BoardRepositoryTests
 */
@SpringBootTest
@Slf4j
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testInsert() {
        IntStream.range(1, 101).forEach(i ->{

            Board board = new Board();
            board.setTitle("Sample Title" + (i % 10));
            board.setContent("Sample Content" + (i % 10));
            Board savedBoard = boardRepository.save(board);

            log.info(savedBoard.toString());
        });
    }
    
    @Test
    public void testSearchTitle(){
        SearchDTO dto = new SearchDTO();
        dto.setType("c");
        dto.setKeyword("5");

        Pageable page = PageRequest.of(0, 10);

        Page<Board> resultPage = boardRepository.findAll(BoardSearchPredicate.searchSimple(dto), page);

        resultPage.forEach(result -> log.info(result.toString()));
    }

    @Test
    public void testReadAndUpdate(){
        Optional<Board> result = boardRepository.findById(99);
        
        Board board = null;
        if(result.isPresent()) {
            board = result.get();
            log.info(board.toString());

            board.setTitle("updated Title");
            Board updatedBoard = boardRepository.save(board);
            log.info(updatedBoard.toString());
            
        }
    }

    @Test
    public void testPaging(){
        Pageable page = PageRequest.of(0, 10, Direction.DESC, "bno");

        Page<Board> result = boardRepository.findAll(page);

        log.info(result.toString());

        log.info(result.getPageable().toString());

        Stream<Board> stream = result.get();
        List<Board> boardList = stream.collect(Collectors.toList());
        log.info(boardList.toString());
    }

//     @Test
//     public void testFindBy(){
//         Pageable pageable = PageRequest.of(1, 10, Direction.DESC, "bno");
//         Page<Board> result = repo.findByTitleContaining("3", pageable);

//         log.info(result.toString());
//    }

          @Test
         public void addReply() {
            Reply reply = new Reply();
            reply.setReply("Sample Reply..........."); 
            reply.setBoard(boardRepository.getOne(100));

            replyRepository.save(reply);         
          }

          @Test
          public void testReadBoard() {

            Board board = boardRepository.findById(100).get();

            log.info(""+board);
          }

          @Test
          @Transactional
          public void testList1() {
              PageRequest.of(0,10,Sort.Direction.DESC,"bno");

             Page<Board> result =  boardRepository.findAll(page);

             result.forEach(b -> {
                 log.info("BNO : " + b.getBno());
                 log.info("Title : " + b.getTitle());
                 log.info("" + b.getReplyList());
                 log.info("++++++++++++++++++++++++++++++++++++++++++++");
             });
          }

          public void testReadFetch() {
              Board board = boardRepository.getBoard(100);

              log.info("" + board.getBno());
              log.info("" + board);
          }

          @Test
          public void testFetch2() {
            
            Pageable page = 
            PageRequest.of(0, 5, Sort.Direction.DESC, "bno");

            List<Board> list = boardRepository.getBoardList(page);

            list.forEach(b -> {
                log.info("BNO : " + b.getBno());
                log.info("Title : " + b.getTitle());
                log.info("" + b.getReplyList());
                log.info("++++++++++++++++++++++++++++++++++++++++++++");
            });
        }

            @Test
            public void testJOIN() {
              
              Pageable page = 
              PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
  
              Page<Object[]> result = boardRepository.getBoardList2(page);

              log.info("" + result.getTotalElements());
              log.info("" + result.getTotalPages());
              log.info("+++++++++++++++++++++++++++++++++++++++++++++++");
  
              result.forEach(arr -> {
                log.info("" + arr[0]);
                log.info("" + arr[1]);
                log.info("++++++++++++++++++++++++++++++++++++++++++++++++");
                  
              });

          }

          @Test
          public void testReplyByBoard() {

            Pageable page = PageRequest.of(0,10);

            Board board = boardRepository.getOne(100);

            Page<Reply> result = replyRepository.findByBoard(board, page);

            log.info("++++++++++++++++++++++++++++++++++");
            log.info("" + result);

            result.forEach(r -> {
                log.info("REPLY : " + r);
            });

          }
        
}