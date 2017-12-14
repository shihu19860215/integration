package com.shihu.service.impl;

import com.shihu.model.common.VO.CarVO;
import com.shihu.model.common.VO.CustomerVO;
import com.shihu.mybatis.dao.CustomerDao;
import com.shihu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    private Map<Long,CustomerVO> idCustomerVOMap;
    @Autowired
    private CustomerDao customerDao;
    public List<CustomerVO> getCustomerVOAll() {
        return customerDao.getCustomerVOAll();
    }

    @Transactional
    public void addCustomerVO(CustomerVO customerVO) {
        customerDao.addCustomerVO(customerVO);
        getIdCustomerVOMap().put(customerVO.getId(),customerVO);
    }

    @Transactional
    public void updateCustomerVO(CustomerVO customerVO) {
        customerDao.updateCustomerVO(customerVO);
        getIdCustomerVOMap().put(customerVO.getId(),customerVO);
    }

    @Transactional
    public void delCustomerVO(Long id) {
        customerDao.delCustomerById(id);
        getIdCustomerVOMap().remove(id);
    }

    public CustomerVO getCustomerVOByIdCache(Long id) {
        return getIdCustomerVOMap().get(id);
    }

    public List<CustomerVO> getCustomerVOIdNameListLikeName(String name) {
        return customerDao.getCustomerVOIdNameListLikeName("%"+name+"%");
    }

    /**
     * 设置idNameMap
     */
    private void setIdCustomerVOMap(){
        idCustomerVOMap=new HashMap<Long,CustomerVO>();
        List<CustomerVO> list=customerDao.getCustomerVOAll();
        for(CustomerVO c:list){
            idCustomerVOMap.put(c.getId(),c);
        }
    }
    private Map<Long,CustomerVO> getIdCustomerVOMap(){
        if (null==idCustomerVOMap){
            setIdCustomerVOMap();
        }
        return idCustomerVOMap;
    }
}
