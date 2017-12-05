package com.shihu.controller;

import com.shihu.model.common.VO.CarTypeVO;
import com.shihu.mybatis.dao.CarTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/test")
public class Test {

    @RequestMapping("test1")
    public ModelAndView test1(){
        ModelAndView modelAndView=new ModelAndView("hello");
        modelAndView.addObject("message","messagtestok");
        return modelAndView;
    }

}
