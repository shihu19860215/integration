package com.shihu.controller;

import com.shihu.exception.PagePromptException;
import com.shihu.base.Base;
import com.shihu.model.common.HomePageBean;
import com.shihu.model.common.VO.CarTypeVO;
import com.shihu.service.CarTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/cartype")
public class CarTypeController {
    private final int index=1;
    @Autowired
    private CarTypeService carTypeServer;

    @RequestMapping("/list")
    public ModelAndView list(Integer pagePromptId){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=new HomePageBean(index,"cartype");
        modelAndView.addObject("page",homePageBean);
        List<CarTypeVO> carTypeList=  carTypeServer.getAllCarTypeVO();
        modelAndView.addObject("carTypeList",carTypeList);
        if(null!=pagePromptId){
            homePageBean.setPagePromptException(PagePromptException.getPagePromptInfo(pagePromptId));
        }

        return modelAndView;
    }

    @RequestMapping("/search")
    public ModelAndView search(String searchStr){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=new HomePageBean(index,"cartype");
        modelAndView.addObject("page",homePageBean);
        List<CarTypeVO> carTypeList;
        if(null!=searchStr&&searchStr.length()>0) {
            carTypeList = carTypeServer.getCarTypeVOLikeName(searchStr);
            modelAndView.addObject("searchStr",searchStr);
        }else {
            carTypeList=  carTypeServer.getAllCarTypeVO();
        }
        modelAndView.addObject("carTypeList",carTypeList);

        return modelAndView;

    }

    @RequestMapping(value="/del/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        ModelAndView modelAndView=new ModelAndView("redirect:/cartype/list");
        boolean result= false;
        try {
            carTypeServer.deleteCarTypeById(id);
        } catch (PagePromptException e) {
            modelAndView.addObject("pagePromptId", e.getId());
        }
        if(!result){
        }
        return modelAndView;
    }
    @RequestMapping("/add")
    public ModelAndView add(CarTypeVO carTypeVO){
        ModelAndView modelAndView=new ModelAndView("redirect:/cartype/list");
        if(null!=carTypeVO.getName()&&carTypeVO.getName().length()>0){
            boolean result= false;
            try {
                carTypeServer.addCarTypeVO(carTypeVO);
            } catch (PagePromptException e) {
                modelAndView.addObject("pagePromptId", e.getId());
            }
        }else {
            modelAndView.addObject("pagePromptId",PagePromptException.ADD_INFO_NOT_EMPTY);
        }
        return modelAndView;
    }
}
