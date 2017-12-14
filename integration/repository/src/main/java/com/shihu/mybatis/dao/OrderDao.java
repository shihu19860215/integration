package com.shihu.mybatis.dao;

import com.shihu.model.common.Order;
import com.shihu.model.common.OrderSearch;
import com.shihu.model.common.VO.OrderVO;

import java.util.Date;
import java.util.List;

public interface OrderDao {
    public void addOrderVO(OrderVO orderVO);
    public OrderVO getOrderVOById(Long id);
    public void updateOrderRemarks(OrderVO orderVO);
    public List<OrderVO> getOrderVOListLikeLikeCustomerNameByDate(String customerName, Date startDate, Date endDate);

    public List<OrderVO> getOrderVOAll();
    public List<OrderVO> getOrderVOByOrderSearch(OrderSearch orderSearch);
    public int getCountOrderVOByOrderSearch(OrderSearch orderSearch);
}
