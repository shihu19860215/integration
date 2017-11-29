package com.shihu.server.impl;

import com.shihu.model.common.CarType;
import com.shihu.model.common.VO.CarTypeVO;
import com.shihu.mybatis.dao.CarTypeDao;
import com.shihu.server.ICarTypeServer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CarTypeServerImpl implements ICarTypeServer{
    @Autowired
    private CarTypeDao carTypeDao;
    public List<CarTypeVO> getAllCarTypeVO() {
        List<CarTypeVO> list=carTypeDao.getAllCarTypeVO();
        return list;
    }

}
