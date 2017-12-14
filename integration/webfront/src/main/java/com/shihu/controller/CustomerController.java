package com.shihu.controller;

import com.shihu.model.common.HomePageBean;
import com.shihu.model.common.VO.CustomerVO;
import com.shihu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final int index=12;
    @Autowired
    private CustomerService customerService;
    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=new HomePageBean(index,"customer");
        modelAndView.addObject("page",homePageBean);

        List<CustomerVO> list=customerService.getCustomerVOAll();
        modelAndView.addObject("list",list);

        return  modelAndView;
    }

    @RequestMapping("/toaddpage")
    public ModelAndView toAddPage(){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=new HomePageBean(index,"customer_add");
        modelAndView.addObject("page",homePageBean);
        return  modelAndView;

    }

    @RequestMapping("/toupdatepage/{id}")
    public ModelAndView toUpdatePage(@PathVariable("id")Long id){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=new HomePageBean(index,"customer_update");
        modelAndView.addObject("page",homePageBean);

        CustomerVO customerVO=customerService.getCustomerVOByIdCache(id);
        modelAndView.addObject("customerVO",customerVO);
        return  modelAndView;
    }
    @RequestMapping("/del/{id}")
    public String del(@PathVariable("id")Long id){
        customerService.delCustomerVO(id);
        return  "redirect:/customer/list";
    }
    @RequestMapping("/add")
    public String add(CustomerVO customerVO){
        if(null!=customerVO&&null!=customerVO.getName()&&customerVO.getName().length()>0){
            customerService.addCustomerVO(customerVO);

        }
        return  "redirect:/customer/list";
    }

    @RequestMapping("/update")
    public String update(CustomerVO customerVO){
        if(null!=customerVO&&null!=customerVO.getName()&&customerVO.getName().length()>0){
            customerService.updateCustomerVO(customerVO);
        }

        return  "redirect:/customer/list";
    }
}
