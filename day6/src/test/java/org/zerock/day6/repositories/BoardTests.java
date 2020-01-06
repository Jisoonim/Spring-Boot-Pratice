package org.zerock.day6.repositories;

import java.util.Arrays;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.zerock.day6.domain.Attach;
import org.zerock.day6.domain.Board;
import org.zerock.day6.domain.Member;
import org.zerock.day6.domain.Reply;

import lombok.extern.slf4j.Slf4j;

/**
 * BoardTests
 */
@SpringBootTest
@Slf4j
public class BoardTests {

    @Autowired
    private MemberRepository memberRepository;//회원

    @Autowired
    private BoardRepository boardRepository;//게시판

    @Autowired
    private ReplyRepository replyRepositoy;//댓글
    
    @Test
    public void addMembers() {

        IntStream.range(100, 201).forEach(i -> {

            Member member = new Member();
            member.setMid("user" + i);
            member.setMpw("pw" + i);
            member.setMname("사용자" + i);
            member.setNickname("GUEST" + i);

            memberRepository.save(member);
        });
    }

    @Test
    public void insertBoards() {

        IntStream.range(1, 101).forEach(i -> {

            Board board = new Board();
            board.setWriter(memberRepository.getOne("user" + (i + 99)));
            board.setTitle("Title....." + i);
            board.setContent("Content....." + i);

            boardRepository.save(board);
        });
    }

    @Test
    public void getList1() {

        Pageable pvalue = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");

        boardRepository.getList1(pvalue);
    }

    @Test
    public void addBoardWithAttach() {

        Attach a1 = new Attach();
        a1.setFname("imgA.jpg");
        Attach a2 = new Attach();
        a2.setFname("imgB.jpg");
        a2.setCurrent(true);

        Board board = new Board();
        board.setTitle("Sample.....");
        board.setContent("Content.....");
        board.setWriter(memberRepository.getOne("user100"));

        board.addAttach(a1);
        board.addAttach(a2);

        boardRepository.save(board);

    }

    @Test
    @Transactional
    @Commit
    public void addAttach() {//첨부파일 있는 게시물에서 추가하기(트랜잭션과 커밋 / cascade 추가)

        Board board = boardRepository.getOne(105);

        Attach attach = new Attach();
        attach.setFname("newImg.jpg");

        board.addAttach(attach);

        boardRepository.save(board);
    }

    @Test
    @Transactional
    @Commit
    public void replaceAttach() { //첨부파일 수정(삭제하고 추가하기)

        Board board = boardRepository.getOne(104);

        Attach attach = new Attach();
        attach.setFname("Replace.jpg");
        board.replaceAttach(attach);

        boardRepository.save(board);

    }

    @Test
    public void getList2() {

        Pageable pvalue = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");

       Page<Object[]> result =  boardRepository.getList2(pvalue);
       log(result);
    }

    private void log(Page<Object[]> page) {

        log.info("TOTAL: " + page.getTotalElements());

        page.forEach(arr -> {
            log.info("" + Arrays.toString(arr));
            log.info("+++++++++++++++++++++++++++++++++++++++++++++++");
        });
    }

    @Test
    public void addReply() {
        
        IntStream.range(1, 106).forEach(bno -> {
            int count = (int)(Math.random() * 20);

            Board board = boardRepository.getOne(bno);

            for(int i = 0; i < count; i++) {

            Reply reply = new Reply();
            reply.setBoard(board);
            reply.setReply("Test reply...." + i);

            replyRepositoy.save(reply);
            }
        });
    }

    @Test
    public void testList3() {

        Pageable pvalue = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");

        boardRepository.getList3(pvalue);
    }
}