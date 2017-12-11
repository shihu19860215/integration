package com.shihu.service.impl;

import com.shihu.model.common.VO.CustomerVO;
import com.shihu.mybatis.dao.CustomerDao;
import com.shihu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;
    public List<CustomerVO> getCustomerVOAll() {
        return customerDao.getCustomerVOAll();
    }

    public void addCustomerVO(CustomerVO customerVO) {
        customerDao.addCustomerVO(customerVO);
    }

    public void updateCustomerVO(CustomerVO customerVO) {
        customerDao.updateCustomerVO(customerVO);
    }

    public void delCustomerVO(Long id) {
        customerDao.delCustomerById(id);
    }

    public CustomerVO getCustomerVOById(Long id) {
        return customerDao.getCustomerVOById(id);
    }

    public List<CustomerVO> getCustomerVOIdNameListLikeName(String name) {
        return customerDao.getCustomerVOIdNameListLikeName("%"+name+"%");
    }
}
