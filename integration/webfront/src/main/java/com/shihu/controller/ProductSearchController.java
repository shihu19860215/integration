package com.shihu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/search")
public class ProductSearchController {

    @RequestMapping("/product")
    public ModelAndView productSearch(String carName,String productName,String version,Integer sort){
        ModelAndView modelAndView=new ModelAndView("product_search");
        return  modelAndView;
    }
}
