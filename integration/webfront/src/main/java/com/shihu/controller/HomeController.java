package com.shihu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @RequestMapping("/home")
    public String home(HttpSession session){
        return "forward:/cartype/list";
    }
    @RequestMapping("/")
    public String base(HttpSession session){
        return "forward:/cartype/list";
    }
}
