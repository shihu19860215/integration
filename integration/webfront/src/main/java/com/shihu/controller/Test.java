package com.shihu.controller;

import com.shihu.model.common.VO.CarTypeVO;
import com.shihu.mybatis.dao.CarTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/test")
public class Test {
    @Autowired
    private CarTypeDao carTypeDao;

    @RequestMapping("test1")
    public String test1(){
        List<CarTypeVO> list=carTypeDao.getAllCarTypeVO();
        for(CarTypeVO c:list){
            System.out.println(c.getName());
        }
        return "hello";
    }

}
