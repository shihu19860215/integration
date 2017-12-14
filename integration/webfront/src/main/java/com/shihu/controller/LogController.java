package com.shihu.controller;

import com.shihu.model.common.HomePageBean;
import com.shihu.model.common.Log;
import com.shihu.model.common.LogSearch;
import com.shihu.model.common.VO.LogVO;
import com.shihu.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/log")
public class LogController {
    private final int logIndex=13;
    @Autowired
    private LogService logService;

    @RequestMapping("/search")
    public ModelAndView search(LogSearch logSearch){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=new HomePageBean(logIndex,"log");
        modelAndView.addObject("page",homePageBean);

        List<Log> logList=logService.listLogVOListByLogSearch(logSearch);
        modelAndView.addObject("logList",logList);
        return modelAndView;
    }
}
