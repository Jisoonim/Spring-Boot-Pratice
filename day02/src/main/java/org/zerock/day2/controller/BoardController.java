package org.zerock.day2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.day2.domain.Board;
import org.zerock.day2.dto.SearchDTO;
import org.zerock.day2.dto.StringMsg;
import org.zerock.day2.repositories.BoardRepository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * BoardController
 */
@RestController
@RequestMapping("/board/*")
@Slf4j
public class BoardController {

    @Setter(onMethod_ = @Autowired)
    BoardRepository boardRepo;

    @GetMapping("/{bno}")
    public ResponseEntity<Board> read(
        @PathVariable("bno") Integer bno){
        
        log.info(""+bno);

        return new ResponseEntity<>(boardRepo.findById(bno).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<StringMsg> register(@RequestBody Board board){

        log.info(board.toString());
        return new ResponseEntity<>(StringMsg.SUCCESS, HttpStatus.OK);
    }

    /*
    @GetMapping("/list")
    public ResponseEntity<Page<Board>> getList(
    @SortDefault(sort = "bno", direction = Direction.DESC)    
    Pageable page,
    SearchDTO searchS){

        log.info(""+page);

        return new ResponseEntity<>(boardRepo.findAll(page), HttpStatus.OK);
    }
    */

    @GetMapping(value = "/list", produces = "application/json;charset-utf-8")
    public ResponseEntity<MultiValueMap<String, Object>> getList(
    @SortDefault(sort = "bno", direction = Direction.DESC)    
    Pageable page,
    SearchDTO searchDTO){

        MultiValueMap<String, Object> result = new LinkedMultiValueMap<>();
        result.add("page", boardRepo.findAll(page));
        result.add("searchDTO", searchDTO);
        
        log.info(""+page);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}