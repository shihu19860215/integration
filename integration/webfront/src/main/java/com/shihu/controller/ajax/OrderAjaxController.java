package com.shihu.controller.ajax;

import com.google.gson.Gson;
import com.shihu.model.common.Order;
import com.shihu.model.common.OrderProduct;
import com.shihu.model.common.VO.OrderProductVO;
import com.shihu.model.common.VO.OrderVO;
import com.shihu.service.OrderService;
import com.shihu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Controller
@RequestMapping("/ajax/order")
public class OrderAjaxController {
    public static Gson gson=new Gson();
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/addproduct/{id}")
    public void addProduct(HttpSession session,HttpServletResponse response,@PathVariable("id") Long productId){
        Object o=session.getAttribute("order");
        Order order;
        if(o!=null){
            order=(Order)o;
        }else {
            order=new Order();
            order.setOrderProductVOList(new ArrayList<OrderProductVO>());
            session.setAttribute("order",order);
        }
        boolean isExist=false;
        if(null==order.getOrderProductVOList()){
            order.setOrderProductVOList(new ArrayList<OrderProductVO>());
        }else {
            for(OrderProductVO orderProductVO:order.getOrderProductVOList()){
                if(orderProductVO.getProductId().equals(productId)){
                    isExist=true;
                    break;
                }
            }
        }
        if(!isExist){
            OrderProductVO orderProductVO=new OrderProductVO();
            orderProductVO.setNum(1);
            orderProductVO.setProductId(productId);
            orderProductVO.setSell(true);
            order.getOrderProductVOList().add(orderProductVO);
        }

        try {
            //设置页面不缓存
            response.setContentType("application/json");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out= null;
            out = response.getWriter();
            out.print(gson.toJson("success"));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/save")
    public void save(HttpSession session,HttpServletResponse response,Order order){
        session.setAttribute("order",order);
        try {
            //设置页面不缓存
            response.setContentType("application/json");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out= null;
            out = response.getWriter();
            out.print(gson.toJson("success"));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/add")
    public void add(HttpServletResponse response,Order order){
        orderService.addOrder(order);
        try {
            //设置页面不缓存
            response.setContentType("application/json");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out= null;
            out = response.getWriter();
            out.print(gson.toJson("success"));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/saveremarks")
    public void updateRemarks(HttpServletResponse response,OrderVO orderVO){
        orderService.updateOrderRemarksById(orderVO);
        try {
            //设置页面不缓存
            response.setContentType("application/json");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out= null;
            out = response.getWriter();
            out.print(gson.toJson("success"));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
