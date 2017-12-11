package com.shihu.mybatis.dao;

import com.shihu.model.common.OrderProduct;
import com.shihu.model.common.VO.OrderProductAndProductVO;
import com.shihu.model.common.VO.OrderProductVO;

import java.util.List;

public interface OrderProductDao {
    public List<OrderProductAndProductVO> getOrderProductAndProductVOListByOrderId(Long orderId);
    public void addOrderProductVO(OrderProductVO orderProductVO);
}
