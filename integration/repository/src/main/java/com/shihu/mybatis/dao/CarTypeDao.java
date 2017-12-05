package com.shihu.mybatis.dao;

import com.shihu.model.common.CarType;
import com.shihu.model.common.VO.CarTypeVO;

import java.util.List;

public interface CarTypeDao {
    public List<CarTypeVO> getAllCarTypeVO();
    public List<CarTypeVO> getCarTypeVOLikeName(String name);
    public void deleteCarTypeById(Long id);
    public Long addCarTypeVO(CarTypeVO carTypeVO);
    public CarTypeVO getCarTypeVOById(Long id);
    public CarTypeVO getCarTypeVOByName(String name);
}
