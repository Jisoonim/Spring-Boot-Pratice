package org.zerock.day5;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;
import org.zerock.day5.domain.Board;
import org.zerock.day5.domain.Reply;
import org.zerock.day5.repository.BoardRepository;
import org.zerock.day5.repository.ReplyRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * BoardTests
 */
@Slf4j
@SpringBootTest
public class BoardTests {

    @Autowired
    BoardRepository boardRepo;

    @Autowired
    ReplyRepository replyRepo;

    @Test
    public void testcaseInput(){

        for(int i=100; i<301; i++){
            Board b = new Board();
            b.setTitle("Title"+(char)('A'+(i % 45)));
            b.setContent("content"+i);

            
            boardRepo.save(b);
        }

    }

    @Test
    @Transactional
    @Commit
    public void testcaseInput2(){

        Board b = boardRepo.getOne(600);
        for(int i=0; i<29; i++){
           Reply r = b.mackReply();
           r.setReply("reply"+(char)('A'+(i%20)));
           
           replyRepo.save(r);
        }
    }

    @Test
    public void testList(){
        Pageable page = PageRequest.of(0, 10, Direction.DESC, "bno");

        Page<Object[]> result = boardRepo.getBoardList(page);

        result.forEach(arr -> {
            log.info(arr[0]+"\t arr[0]");
            log.info(arr[1]+"\t arr[1]");
            log.info("=========================");
        });

    }

    @Test
    public void test1(){
        boardRepo.getBoard(600);
    }

    @Test
    public void readWithReplyPage(){
        Pageable page = PageRequest.of(4, 10);
        Page<Object[]> result = boardRepo.getBoardWithReplyPage(600, page);

        log.info(result.getTotalPages()+"\t total page");
        log.info(result.getNumber()+"\t number");

        result.forEach( arr -> {
            log.info(arr[0]+"\t arr0");
            log.info(arr[1]+"\t arr1");
        });

    }
    
}