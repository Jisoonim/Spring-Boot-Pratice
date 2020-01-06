package org.zerock.day3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.day3.repository.BoardRepository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * BoardController
 */
@RestController
@RequestMapping("/board")
@Slf4j
public class BoardController {

    @Setter(onMethod_ = {@Autowired})
    private BoardRepository boardRepository;
    
    @GetMapping("/list")
    public ResponseEntity<Page<Object[]>> getList(
        @PageableDefault(direction = Sort.Direction.DESC, sort = "bno") Pageable page) {

            Page<Object[]> result = boardRepository.getBoardList2(page);

            log.info("" + result);
            
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}