package com.shihu.mybatis.dao;

import com.shihu.model.common.VO.CarProductVO;

public interface CarProductDao {
    public void addCarProductVO(CarProductVO carProductVO);
    public void delCarProductVO(CarProductVO carProductVO);
    public int getCountByCarId(Long carId);
}
