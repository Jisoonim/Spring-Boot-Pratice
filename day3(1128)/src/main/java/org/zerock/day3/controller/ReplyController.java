package org.zerock.day3.controller;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.day3.domain.Board;
import org.zerock.day3.domain.ex1.Reply;
import org.zerock.day3.repository.ReplyRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ReplyController
 */
@RestController
@RequestMapping("/reply")
@AllArgsConstructor
@Slf4j
public class ReplyController {

    private ReplyRepository repo;

    @GetMapping("/list/{bno}")
    public ResponseEntity<Page<Reply>> getListByBoard(
        @PathVariable("bno") Integer bno,
        Pageable page){

            log.info("bno : " + bno);

            Board board = new Board();
            board.setBno(bno);

            Page<Reply> result = repo.findByBoard(board, page);

            log.info("" + result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    
}