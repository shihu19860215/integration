package com.shihu.service.impl;

import com.shihu.model.common.VO.CarTypeVO;
import com.shihu.mybatis.dao.CarTypeDao;
import com.shihu.service.CarTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarTypeServiceImpl implements CarTypeService {
    @Autowired
    private CarTypeDao carTypeDao;
    public List<CarTypeVO> getAllCarTypeVO() {
        List<CarTypeVO> list=carTypeDao.getAllCarTypeVO();
        return list;
    }

}
