package com.shihu.service.impl;

import com.shihu.model.common.OrderProduct;
import com.shihu.mybatis.dao.OrderProductDao;
import com.shihu.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProductServiceImpl implements OrderProductService {
    @Autowired
    private OrderProductDao orderProductDao;
    public void addOrderProduct(OrderProduct orderProduct) {

    }
}
