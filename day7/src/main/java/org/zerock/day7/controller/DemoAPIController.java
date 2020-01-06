package org.zerock.day7.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.day7.domain.Board;

/**
 * DemoAPIController
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class DemoAPIController {


    @GetMapping("/{bno}")
    public ResponseEntity<Board> get1(@PathVariable("bno") Integer bno) {

        System.out.println("BNO: " + bno);

        Board board =  Board.builder()
                        .bno(bno)
                        .title("AAA")
                        .content("AAAAContent")
                        .writer("user00")
                        .build();
    
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    
}