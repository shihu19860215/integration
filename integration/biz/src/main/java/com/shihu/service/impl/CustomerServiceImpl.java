package com.shihu.service.impl;

import com.shihu.model.common.VO.CarVO;
import com.shihu.model.common.VO.CustomerVO;
import com.shihu.mybatis.dao.CustomerDao;
import com.shihu.redis.CustomerRedis;
import com.shihu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private CustomerRedis customerRedis;
    public List<CustomerVO> getCustomerVODisplayAll() {
        return customerDao.getCustomerVODisplayAll();
    }

    @Transactional
    public void addCustomerVO(CustomerVO customerVO) {
        if(null!=customerVO.getTelephone()&&customerVO.getTelephone().trim().length()==0){
            customerVO.setTelephone(null);
        }
        if(null!=customerVO.getTelephone2()&&customerVO.getTelephone2().trim().length()==0){
            customerVO.setTelephone2(null);
        }
        if(null!=customerVO.getArea()&&customerVO.getArea().trim().length()==0){
            customerVO.setArea(null);
        }
        if(null!=customerVO.getAddress()&&customerVO.getAddress().trim().length()==0){
            customerVO.setAddress(null);
        }
        customerDao.addCustomerVO(customerVO);
        customerRedis.putCustomerVO(customerVO.getId(),customerVO);
    }

    @Transactional
    public void updateCustomerVO(CustomerVO customerVO) {
        customerDao.updateCustomerVO(customerVO);
        customerRedis.putCustomerVO(customerVO.getId(),customerVO);
    }

    @Transactional
    public void delCustomerVO(Long id) {
        customerDao.unDisplayCustomerVO(id);
    }

    public CustomerVO getCustomerVOByIdCache(Long id) {
        CustomerVO customerVO=customerRedis.getCustomerVO(id);
        if(null==customerVO){
            customerVO=customerDao.getCustomerVOById(id);
            if(null!=customerVO){
                customerRedis.putCustomerVO(id,customerVO);
            }
        }
        return customerVO;
    }

    public List<CustomerVO> getCustomerVOListLikeNameOrTel(String customerNameOrTel) {
        return customerDao.getCustomerVOListLikeNameOrTel("%"+customerNameOrTel+"%");
    }

}
