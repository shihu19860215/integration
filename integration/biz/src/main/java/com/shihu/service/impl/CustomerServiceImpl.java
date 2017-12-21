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
        getIdCustomerVOMap().put(customerVO.getId(),customerVO);
    }

    @Transactional
    public void updateCustomerVO(CustomerVO customerVO) {
        customerDao.updateCustomerVO(customerVO);
        getIdCustomerVOMap().put(customerVO.getId(),customerVO);
    }

    @Transactional
    public void delCustomerVO(Long id) {
        customerDao.unDisplayCustomerVO(id);
    }

    public CustomerVO getCustomerVOByIdCache(Long id) {
        CustomerVO customerVO=getIdCustomerVOMap().get(id);
        if(null==customerVO){
            customerVO=customerDao.getCustomerVOById(id);
            if(null!=customerVO){
                getIdCustomerVOMap().put(id,customerVO);
            }
        }
        return customerVO;
    }

    public List<CustomerVO> getCustomerVOListLikeNameOrTel(String customerNameOrTel) {
        return customerDao.getCustomerVOListLikeNameOrTel("%"+customerNameOrTel+"%");
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
