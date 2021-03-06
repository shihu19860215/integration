package com.shihu.mybatis.dao;

import com.shihu.model.common.VO.CarProductUpdateVO;
import com.shihu.model.common.VO.CarProductVO;

import java.util.Map;

public interface CarProductDao {
    public void addCarProductVO(CarProductVO carProductVO);
    public void delCarProductVO(CarProductVO carProductVO);
    public int getCountByCarId(Long carId);
    public void updateCarProduct(CarProductUpdateVO carProductUpdateVO);
    public void delCarProductVOByProductId(Long id);
}
