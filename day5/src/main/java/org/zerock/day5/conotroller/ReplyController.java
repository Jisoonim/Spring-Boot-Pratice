package org.zerock.day5.conotroller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.day5.domain.Reply;
import org.zerock.day5.repository.ReplyRepository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * ReplyController
 */
@Slf4j
@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Setter(onMethod_ = @Autowired)
    private ReplyRepository repo;

    @Transactional
    @PostMapping("")
    public void addReply(@RequestBody Reply reply){
        log.info(reply+"\t - add reply");

        repo.save(reply);

    }
    
}