package com.shihu.controller;

import com.shihu.model.common.HomePageBean;
import com.shihu.model.common.Order;
import com.shihu.model.common.OrderSearch;
import com.shihu.model.common.Product;
import com.shihu.model.common.VO.OrderProductVO;
import com.shihu.service.CustomerService;
import com.shihu.service.OrderService;
import com.shihu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final int nowOrderIndex=4;
    private final int manageOrderIndex=11;
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @RequestMapping("/toaddpage")
    public ModelAndView toAddPage(HttpSession session){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=new HomePageBean(nowOrderIndex,"order_add");
        modelAndView.addObject("page",homePageBean);
        Object o=session.getAttribute("order");
        if(o!=null){
            Order order=(Order)o;
            modelAndView.addObject("order",order);
            if(null!=order.getOrderProductVOList()&&order.getOrderProductVOList().size()>0){
                List<Product> productList=new ArrayList<Product>(order.getOrderProductVOList().size());
                Iterator<OrderProductVO> it = order.getOrderProductVOList().iterator();
                while(it.hasNext()){
                    OrderProductVO orderProductVO= it.next();
                    Product product=productService.getDisplayProductById(orderProductVO.getProductId());
                    if(null==product){
                        it.remove();
                    }else {
                        productList.add(product);
                    }
                }
                modelAndView.addObject("productList",productList);
            }
            if(null!=order.getCustomerVO()&&null!=order.getCustomerVO().getId()){
                order.setCustomerVO(customerService.getCustomerVOByIdCache(order.getCustomerVO().getId()));
            }
        }

        return modelAndView;
    }

    @RequestMapping("/{id}")
    public ModelAndView display(@PathVariable("id") Long id){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=new HomePageBean(manageOrderIndex,"order_display");
        modelAndView.addObject("page",homePageBean);
        Order order=orderService.getOrderById(id);
        modelAndView.addObject("order",order);
        return modelAndView;
    }

    @RequestMapping("/search")
    public ModelAndView search(OrderSearch orderSearch){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=new HomePageBean(manageOrderIndex,"order");
        modelAndView.addObject("page",homePageBean);
        List<Order> orderList=orderService.getOrderWithCustomerByOrderSearch(orderSearch);
        modelAndView.addObject("orderList",orderList);

        return modelAndView;
    }

}
