package com.shihu.mybatis.dao;

import com.shihu.model.common.VO.CustomerVO;

import java.util.List;

public interface CustomerDao {
    public List<CustomerVO> getCustomerVOAll();
    public List<CustomerVO> getCustomerVODisplayAll();
    public void addCustomerVO(CustomerVO customerVO);
    public void updateCustomerVO(CustomerVO customerVO);
    public void unDisplayCustomerVO(Long id);
    public CustomerVO getCustomerVOById(Long id);
    public List<CustomerVO> getCustomerVOListLikeNameOrTel(String customerNameOrTel);
}
