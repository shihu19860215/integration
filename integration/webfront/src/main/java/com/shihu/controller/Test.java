package com.shihu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class Test {
    @RequestMapping("test1")
    public String test1(){
        System.out.println("fgh");
        return "hello";
    }
}
