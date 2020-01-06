package org.zerock.day01.repositories;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.zerock.day01.domain.Member;

import lombok.extern.slf4j.Slf4j;

/**
 * MemberRepoTests
 */
@SpringBootTest
@Slf4j
public class MemberRepoTests {

    @Autowired
    private MemberRepository repo;

    @Test
    public void testPaging() {

       Pageable page = PageRequest.of(0, 10, Direction.DESC, "mid");

       Page<Member> result = repo.findAll(page);

       log.info("" + result.getTotalPages());

       result.forEach(m -> log.info("" + m));
    }

    @Test
    public void testRead() {

        Optional<Member> result = repo.findById("user74");

        log.info("" + result.get());
    }

    @Test
    public void testInsert() {

       IntStream.range(1,101).forEach(i -> {

            Member member = 
            Member.builder()
            .mid("user" + i)
            .mpw("pw")
            .mname("사용자" + i)
            .build();
            log.info("==========================================");
            log.info("" + repo.save(member) );

       }); 
    }

    @Test
    public void test1() {
        log.info("test1..............."); //로그찍을시 문자열밖에못찍음(객체 못들어감)
        log.info("test1...............");
        log.info("test1...............");
        log.info("test1...............");
        log.info("test1...............");
    }
    
}