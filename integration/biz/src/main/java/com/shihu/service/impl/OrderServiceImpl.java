package com.shihu.service.impl;

import com.shihu.model.common.Order;
import com.shihu.model.common.OrderSearch;
import com.shihu.model.common.Product;
import com.shihu.model.common.VO.CustomerVO;
import com.shihu.model.common.VO.OrderProductAndProductVO;
import com.shihu.model.common.VO.OrderProductVO;
import com.shihu.model.common.VO.OrderVO;
import com.shihu.mybatis.dao.OrderDao;
import com.shihu.mybatis.dao.OrderProductDao;
import com.shihu.service.CustomerService;
import com.shihu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYYMMdd");
    private SimpleDateFormat sdfTime=new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderProductDao productDao;
    @Autowired
    private CustomerService customerService;

    public void addOrder(Order order) {
        if(null!=order.getCustomerVO()
                &&null!=order.getCustomerVO().getId()
                &&null!=order.getOrderProductVOList()
                &&order.getOrderProductVOList().size()>0
                ){
            OrderVO orderVO=new OrderVO(order.getCustomerVO().getId(),order.getTotal(),order.getProductNames(),order.getRemarks());
            orderDao.addOrderVO(orderVO);
            List<OrderProductVO> orderProductVOList=order.getOrderProductVOList();
            for(int i=0;i<orderProductVOList.size();i++){
                OrderProductVO orderProductVO=orderProductVOList.get(i);
                orderProductVO.setOrderId(orderVO.getId());
                productDao.addOrderProductVO(orderProductVO);
            }
        };
    }

    public Order getOrderById(Long id) {
        OrderVO orderVO=orderDao.getOrderVOById(id);
        CustomerVO customerVO=customerService.getCustomerVOById(orderVO.getId());
        List<OrderProductAndProductVO> orderProductAndProductVOList= productDao.getOrderProductAndProductVOListByOrderId(id);
        Order order=new Order(orderVO,customerVO,orderProductAndProductVOList);
        return order;
    }

    public void updateOrderRemarksById(OrderVO orderVO) {
        orderDao.updateOrderRemarks(orderVO);
    }

    public List<Order> getOrderWithCustomerLikeCustomerNameByDate(String customerName,String startDate,String endDate){
        Date start=null,end=null;
        try {
            start=simpleDateFormat.parse(startDate);
            end=simpleDateFormat.parse(endDate);
        }catch (Exception e){
        }
        List<OrderVO> orderVOList= orderDao.getOrderVOListLikeLikeCustomerNameByDate("%"+customerName+"%",start,end);
        List<Order> orderList=new ArrayList<Order>();
        for(OrderVO orderVO:orderVOList){
            Order order=new Order();
            order.setId(orderVO.getId());
            order.setCreateTime(sdfTime.format(orderVO.getCreateTime()));
            order.setProductNames(orderVO.getProductNames());
            order.setRemarks(orderVO.getRemarks());
            order.setCustomerVO(customerService.getCustomerVOById(orderVO.getCustomerId()));
            orderList.add(order);
        }
        return orderList;
    }
    public List<Order> getOrderWithCustomer(){
        List<OrderVO> orderVOList= orderDao.getOrderVOAll();
        List<Order> orderList=new ArrayList<Order>();
        for(OrderVO orderVO:orderVOList){
            Order order=new Order();
            order.setId(orderVO.getId());
            order.setCreateTime(sdfTime.format(orderVO.getCreateTime()));
            order.setProductNames(orderVO.getProductNames());
            order.setRemarks(orderVO.getRemarks());
            order.setCustomerVO(customerService.getCustomerVOById(orderVO.getCustomerId()));
            orderList.add(order);
        }
        return orderList;
    }

    public List<Order> getOrderWithCustomerByOrderSearch(OrderSearch orderSearch){
        List<OrderVO> orderVOList= orderDao.getOrderVOByOrderSearch(orderSearch);
        List<Order> orderList=new ArrayList<Order>();
        for(OrderVO orderVO:orderVOList){
            Order order=new Order();
            order.setId(orderVO.getId());
            order.setCreateTime(sdfTime.format(orderVO.getCreateTime()));
            order.setProductNames(orderVO.getProductNames());
            order.setRemarks(orderVO.getRemarks());
            order.setCustomerVO(customerService.getCustomerVOById(orderVO.getCustomerId()));
            orderList.add(order);
        }
        return orderList;
    }
}
