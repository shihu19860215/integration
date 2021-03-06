package com.shihu.service;

import com.shihu.model.common.VO.CustomerVO;

import java.util.List;

public interface CustomerService {
    public List<CustomerVO> getCustomerVODisplayAll();
    public void addCustomerVO(CustomerVO customerVO);
    public void updateCustomerVO(CustomerVO customerVO);
    public void delCustomerVO(Long id);
    public CustomerVO getCustomerVOByIdCache(Long id);
    public List<CustomerVO> getCustomerVOListLikeNameOrTel(String customerNameOrTel);
}
