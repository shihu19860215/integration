package com.shihu.controller;

import com.shihu.model.HomePageBean;
import com.shihu.model.common.Car;
import com.shihu.model.common.CarType;
import com.shihu.model.common.Product;
import com.shihu.model.common.VO.CarTypeVO;
import com.shihu.model.common.VO.CarVO;
import com.shihu.model.common.VO.ProductVO;
import com.shihu.service.CarService;
import com.shihu.service.CarTypeService;
import com.shihu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final int productIndex=1;
    private final int quickProductIndex=3;
    @Autowired
    private ProductService productService;
    @Autowired
    private CarTypeService carTypeService;
    @Autowired
    private CarService carService;

    @RequestMapping("/list")
    public ModelAndView list(Long carId,Long carTypeId){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=new HomePageBean(productIndex,"product");
        modelAndView.addObject("page",homePageBean);

        List<Product> productList=productService.getProductListByCarId(carId);
        modelAndView.addObject("productList",productList);
        CarTypeVO carTypeVO=carTypeService.getCarTypeVOByIdCache(carTypeId);
        modelAndView.addObject("carType",carTypeVO);
        CarVO carVO=carService.getCarVOByIdCache(carId);
        modelAndView.addObject("car",carVO);

        return modelAndView;
    }
    @RequestMapping("/toaddpage/{id}")
    public ModelAndView toAddPage(@PathVariable("id")Long carId){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=new HomePageBean(productIndex,"product_add");
        modelAndView.addObject("page",homePageBean);

        CarVO carVO=carService.getCarVOByIdCache(carId);
        modelAndView.addObject("car",carVO);
        return modelAndView;
    }
    @RequestMapping("/toupdatepage/{id}")
    public ModelAndView toUpdatePage(@PathVariable("id")Long id,Long carId){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=new HomePageBean(productIndex,"product_update");
        modelAndView.addObject("page",homePageBean);

        CarVO carVO=carService.getCarVOByIdCache(carId);
        modelAndView.addObject("car",carVO);

        Product product=productService.getProductByIdWithCarTypeName(id);
        modelAndView.addObject("product",product);
        return modelAndView;
    }

    @RequestMapping("/add")
    public String add(ProductVO productVO, Long[] carIds,Long carId){
        List<CarVO> cars=new ArrayList<CarVO>();
        CarVO carVO=carService.getCarVOByIdCache(carId);
        cars.add(carVO);
        if(null!=carIds&&carIds.length>0){
            for (Long cId:carIds){
                cars.add(carService.getCarVOByIdCache(cId));
            }
        }
        productVO.setCarStr(cars);
        productService.addProduct(productVO,cars);

        return "redirect:/product/list?carId="+carId+"&carTypeId="+cars.get(0).getCarTypeId();
    }
    @RequestMapping("/update")
    public String update(ProductVO productVO, Long[] carIds,Long carId){
        productService.updateProduct(productVO,carIds,carId);
        CarVO carVO=carService.getCarVOByIdCache(carId);

        return "redirect:/product/list?carId="+carId+"&carTypeId="+carVO.getCarTypeId();
    }
    @RequestMapping("/del/{id}")
    public String del(@PathVariable("id")Long id,Long carId,Long carTypeId){
        productService.delProduct(id);
        return "redirect:/product/list?carId="+carId+"&carTypeId="+carTypeId;
    }

    @RequestMapping("/toquickaddpage")
    public ModelAndView toQuickAddPage(){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=new HomePageBean(quickProductIndex,"product_quick_add");
        modelAndView.addObject("page",homePageBean);

        return modelAndView;
    }


    @RequestMapping("/quickadd")
    public ModelAndView quickadd(ProductVO productVO, Long[] carIds){
        ModelAndView modelAndView=new ModelAndView("forward:/product/toquickaddpage");
        HomePageBean homePageBean=new HomePageBean(quickProductIndex,"product_quick_add");
        modelAndView.addObject("page",homePageBean);

        if(null!=carIds&&carIds.length>0){
            List<CarVO> cars=new ArrayList<CarVO>();
            for (Long cId:carIds){
                cars.add(carService.getCarVOByIdCache(cId));
            }
            productVO.setCarStr(cars);
            productService.addProduct(productVO,cars);
        }

        return modelAndView;
    }

    /*修改数据库增加carstr字段*/
    @RequestMapping("/changesql")
    public String changeSql(){
        productService.changesql();
        return "redirect:/cartype/list";
    }

}
