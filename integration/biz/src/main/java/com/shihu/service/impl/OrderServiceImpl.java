package com.shihu.service.impl;

import com.shihu.base.Base;
import com.shihu.exception.PagePromptException;
import com.shihu.model.common.Order;
import com.shihu.model.common.OrderSearch;
import com.shihu.model.common.Product;
import com.shihu.model.common.VO.*;
import com.shihu.mybatis.dao.OrderDao;
import com.shihu.mybatis.dao.OrderProductDao;
import com.shihu.mybatis.dao.OtherProductDao;
import com.shihu.mybatis.dao.ProductDao;
import com.shihu.service.CustomerService;
import com.shihu.service.OrderService;
import com.shihu.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderProductDao orderProductDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OtherProductDao otherProductDao;

    @Transactional
    public void addOrder(Order order) throws PagePromptException{
        if(null!=order.getCustomerVO()
                &&null!=order.getCustomerVO().getId()
                &&((null!=order.getOrderProductVOList()&&order.getOrderProductVOList().size()>0)
                ||(null!=order.getOtherProductVOList()&&order.getOtherProductVOList().size()>0))
                ){
            List<OrderProductVO> orderProductVOList=order.getOrderProductVOList();
            List<OtherProductVO> otherProductVOList=order.getOtherProductVOList();
            Boolean retreat=false;
            if(null!=orderProductVOList&&orderProductVOList.size()>0){
                for(int i=0;i<orderProductVOList.size();i++){
                    if(!orderProductVOList.get(i).isSell()){
                        retreat=true;
                        break;
                    }
                }
            }
            if(!retreat&&null!=otherProductVOList&&otherProductVOList.size()>0){
                for(int i=0;i<otherProductVOList.size();i++){
                    if(!otherProductVOList.get(i).isSell()){
                        retreat=true;
                        break;
                    }
                }
            }
            OrderVO orderVO=new OrderVO(order.getCustomerVO().getId(),order.getTotal(),order.getProductNames(),order.getRemarks(),retreat);
            orderDao.addOrderVO(orderVO);
            if(null!=orderProductVOList&&orderProductVOList.size()>0){
                for(int i=0;i<orderProductVOList.size();i++){
                    OrderProductVO orderProductVO=orderProductVOList.get(i);
                    ProductVO productVO=productDao.getDisplayProductVOById(orderProductVO.getProductId());
                    if(orderProductVO.isSell()) {
                        if (null == productVO) {
                            throw new PagePromptException(PagePromptException.ADD_ORDER_ERROR_PRODCUT_INVAILD);
                        }
                        if (orderProductVO.isSell() && orderProductVO.getNum() > productVO.getNum()) {
                            throw new PagePromptException(PagePromptException.ADD_ORDER_ERROR_PRODCUT_NUM_LESS);
                        }
                        productVO.setNum(productVO.getNum() - orderProductVO.getNum());
                    }else {
                        productVO.setNum(productVO.getNum()+ orderProductVO.getNum());
                    }
                    productDao.updateProdectNumById(productVO);
                    orderProductVO.setOrderId(orderVO.getId());
                    orderProductDao.addOrderProductVO(orderProductVO);
                }
            }
            if(null!=otherProductVOList&&otherProductVOList.size()>0){
                for(int i=0;i<otherProductVOList.size();i++){
                    otherProductVOList.get(i).setOrderId(orderVO.getId());
                    otherProductDao.addOtherProductVO(otherProductVOList.get(i));
                }
            }
        };
    }

    public Order getOrderById(Long id) {
        OrderVO orderVO=orderDao.getOrderVOById(id);
        CustomerVO customerVO=customerService.getCustomerVOByIdCache(orderVO.getCustomerId());
        List<OrderProductAndProductVO> orderProductAndProductVOList= orderProductDao.getOrderProductAndProductVOListByOrderId(id);
        List<OtherProductVO> otherProductVOList=otherProductDao.getOtherProductVOListByOrderId(id);
        Order order=new Order(orderVO,customerVO,orderProductAndProductVOList,otherProductVOList);
        return order;
    }

    public void updateOrderRemarksById(OrderVO orderVO) {
        orderDao.updateOrderRemarks(orderVO);
    }


    public List<Order> getOrderWithCustomerByOrderSearch(OrderSearch orderSearch){
        orderSearch.init();
        List<OrderVO> orderVOList= orderDao.getOrderVOByOrderSearch(orderSearch);
        orderSearch.setCount(orderDao.getCountOrderVOByOrderSearch(orderSearch));
        List<Order> orderList=new ArrayList<Order>();
        for(OrderVO orderVO:orderVOList){
            Order order=new Order();
            order.setId(orderVO.getId());
            order.setCreateTime(DateUtil.format(orderVO.getCreateTime(), Base.DATE_PARSE_YYYYMMddHHmmss));
            order.setProductNames(orderVO.getProductNames());
            order.setRemarks(orderVO.getRemarks());
            order.setCustomerVO(customerService.getCustomerVOByIdCache(orderVO.getCustomerId()));
            orderList.add(order);
        }
        return orderList;
    }
}
