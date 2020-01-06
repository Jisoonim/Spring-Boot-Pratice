package org.zerock.b1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SamplerController
 */
@RestController
@RequestMapping("/sample")
public class SamplerController {

    @GetMapping("/hello")
    public String Hello() {

        return "Hello World";
    }

}