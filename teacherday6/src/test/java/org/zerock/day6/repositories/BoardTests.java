package org.zerock.day6.repositories;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.day6.domain.Attach;
import org.zerock.day6.domain.Board;
import org.zerock.day6.domain.Member;

import lombok.extern.slf4j.Slf4j;

/**
 * BoardTests
 */
@SpringBootTest
@Slf4j
public class BoardTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void getList2() {
     
        Pageable pvalue = PageRequest.of(0,10, Sort.Direction.DESC,"bno");

        Page<Object[]> result = boardRepository.getList2(pvalue);
        log(result);
    }
    
    private void log(Page<Object[]> page){

        log.info("TOTAL: " + page.getTotalElements());

        page.forEach(arr -> {
            log.info("" + Arrays.toString(arr));
            log.info("---------------------------");
            
        });

    }


    @Commit
    @Transactional
    @Test
    public void addAttach(){

        Board board = boardRepository.getOne(104);

        Attach attach = new Attach();
        attach.setFname("RRRnewImg.jpg");

        board.addAttach(attach);

        boardRepository.save(board);

    }

    @Commit
    @Transactional
    @Test
    public void replaceAttach() {

        Board board = boardRepository.getOne(104);

        Attach attach = new Attach();
        attach.setFname("Replace.jpg"); 
        board.replaceAttach(attach);
        
        boardRepository.save(board);

    }

    @Test
    public void addBoardWithAttach() {

        Attach a1 = new Attach();
        a1.setFname("imgA.jpg");
        Attach a2 = new Attach();
        a2.setFname("imgB.jpg");
        a2.setCurrent(true);
        
        Board board = new Board();
        board.setTitle("Sample........");
        board.setContent("Content........");
        board.setWriter(memberRepository.getOne("user100"));

        board.addAttach(a1);
        board.addAttach(a2);

        boardRepository.save(board);
        
    }

    @Test
    public void getList1(){

        Pageable pvalue = PageRequest.of(0,10, Sort.Direction.DESC,"bno");

        boardRepository.getList1(pvalue);

    }



    @Test
    public void insertBoards() {

        IntStream.range(1, 101).forEach(i -> {

            Board board = new Board();
            board.setWriter(memberRepository.getOne("user"+ (i+99)));
            board.setTitle("Title...." + i);
            board.setContent("Content....." + i);

            boardRepository.save(board);

        });

    }

    
    @Test
    public void addMembers(){

        IntStream.range(100, 201).forEach(i -> {

            Member member = new Member();
            member.setMid("user" + i);
            member.setMpw("pw"+i);
            member.setMname("사용자"+ i);
            member.setNickname("GUEST"+i);

            memberRepository.save(member);
        });

    }

}