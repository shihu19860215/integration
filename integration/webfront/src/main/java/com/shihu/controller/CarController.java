package com.shihu.controller;

import com.shihu.base.Base;
import com.shihu.model.HomePageBean;
import com.shihu.model.common.Car;
import com.shihu.model.common.CarType;
import com.shihu.model.common.VO.CarTypeVO;
import com.shihu.model.common.VO.CarVO;
import com.shihu.service.CarService;
import com.shihu.service.CarTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    private CarTypeService carTypeService;


    @RequestMapping("/list")
    public ModelAndView listByCarTypeId(Long carTypeId,Integer errorInfoId) {
        ModelAndView modelAndView = new ModelAndView("home");
        HomePageBean homePageBean = new HomePageBean(1, "car");
        modelAndView.addObject("page", homePageBean);

        CarType carType = carTypeService.getCarTypeById(carTypeId);
        modelAndView.addObject("carType", carType);


        if(null!=errorInfoId){
            modelAndView.addObject("errorInfo",Base.getErrorInfo(errorInfoId).getInfo());
        }
        return modelAndView;
    }
    @RequestMapping("/search")
    public ModelAndView listByCarTypeId(Long carTypeId,String searchStr) {
        ModelAndView modelAndView = new ModelAndView("home");
        HomePageBean homePageBean = new HomePageBean(1, "car");
        modelAndView.addObject("page", homePageBean);
        CarType carType;
        if(null!=searchStr&&searchStr.length()>0){
            CarTypeVO carTypeVO=carTypeService.getCarTypeVOByIdCache(carTypeId);
            List<CarVO> carVOs= carService.getCarVOListLikeName(carTypeId,searchStr);
            carType=new CarType(carTypeVO,carVOs);
            modelAndView.addObject("searchStr",searchStr);
        }else {
            carType = carTypeService.getCarTypeById(carTypeId);
        }
        modelAndView.addObject("carType", carType);

        return modelAndView;
    }

    @RequestMapping(value = "/del/{id}")
    public ModelAndView delete(@PathVariable("id") Long id, Long carTypeId) {
        ModelAndView modelAndView=new ModelAndView("redirect:/car/list?carTypeId=" + carTypeId);
        boolean result=carService.deleteCarById(id);
        if (!result){
            modelAndView.addObject("errorInfoId",Base.CAR_NOT_EMPTY.getId());
        }
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add(CarVO carVO) {
        ModelAndView modelAndView=new ModelAndView("redirect:/car/list?carTypeId=" + carVO.getCarTypeId());
        if(null!=carVO.getName()&&carVO.getName().length()>0){
            boolean result=carService.addCarVO(carVO);
            if(!result){
                modelAndView.addObject("errorInfoId", Base.ADD_INFO_REPART.getId());
            }
        }else {
            modelAndView.addObject("errorInfoId",Base.ADD_INFO_NOT_EMPTY.getId());
        }
        return modelAndView;
    }
}
