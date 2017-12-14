package com.shihu.service;

import com.shihu.exception.PagePromptException;
import com.shihu.model.common.Order;
import com.shihu.model.common.OrderSearch;
import com.shihu.model.common.VO.OrderVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {
    public void addOrder(Order order) throws PagePromptException;
    public Order getOrderById(Long id);
    public void updateOrderRemarksById(OrderVO orderVO);
    public List<Order> getOrderWithCustomerByOrderSearch(OrderSearch orderSearch);
}
