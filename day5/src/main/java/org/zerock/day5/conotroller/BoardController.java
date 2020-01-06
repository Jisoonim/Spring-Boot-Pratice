package org.zerock.day5.conotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.day5.repository.BoardRepository;

import lombok.Setter;

/**
 * BoardController
 */
@RestController
@RequestMapping("board/*")
public class BoardController {

    @Setter(onMethod_ = @Autowired)
    private BoardRepository boardRepo;

    @GetMapping("/list")
    public ResponseEntity<Page<Object[]>> getList(
        @PageableDefault(
            direction = Direction.DESC, sort = "bno"
        )
        Pageable page){
        
        Page<Object[]> result = boardRepo.getBoardList(page);


        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
}