package org.zerock.day7.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.day7.domain.Board;

/**
 * SampleController
 */
@Controller
@RequestMapping("/sample")
public class SampleController {

    @GetMapping("/ex1")
    public void ex1(Model model) {

        model.addAttribute("msg", "Hello Thymeleaf");
    }

    @GetMapping("/ex2")
    public void ex2(Model model) {

        //mapToObj = i를 어떤값으로 mapping할것인지
        List<Board> list = IntStream.range(1, 101).mapToObj(i -> Board.builder()
        .bno(i)
        .title("title" + i)
        .content("content" + i)
        .writer("u" + (i % 10))
        .build())
        .collect(Collectors.toList());

        model.addAttribute("list", list);
    }

    @GetMapping("/exLayout")
    public void exLayout() {

        
    }
    
}