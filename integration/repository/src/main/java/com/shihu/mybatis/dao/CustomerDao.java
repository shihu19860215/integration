package com.shihu.mybatis.dao;

import com.shihu.model.common.VO.CustomerVO;

import java.util.List;

public interface CustomerDao {
    public List<CustomerVO> getCustomerVOAll();
    public void addCustomerVO(CustomerVO customerVO);
    public void updateCustomerVO(CustomerVO customerVO);
    public void delCustomerById(Long id);
    public CustomerVO getCustomerVOById(Long id);
    public List<CustomerVO> getCustomerVOIdNameListLikeName(String name);
}
