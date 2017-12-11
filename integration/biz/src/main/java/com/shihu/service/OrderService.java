package com.shihu.service;

import com.shihu.model.common.Order;
import com.shihu.model.common.OrderSearch;
import com.shihu.model.common.VO.OrderVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {
    @Transactional
    public void addOrder(Order order);
    public Order getOrderById(Long id);
    public void updateOrderRemarksById(OrderVO orderVO);
    public List<Order> getOrderWithCustomerLikeCustomerNameByDate(String customerName,String startDate,String endDate);
    public List<Order> getOrderWithCustomer();
    public List<Order> getOrderWithCustomerByOrderSearch(OrderSearch orderSearch);
}
