package com.shihu.mybatis.dao;

import com.shihu.model.common.Car;
import com.shihu.model.common.CarType;
import com.shihu.model.common.VO.CarTypeVO;
import com.shihu.model.common.VO.CarVO;

import java.util.List;
import java.util.Map;

public interface CarDao {
    public List<CarVO> getCarVOListByCarTypeId(Long carTypeId);
    public void deleteCarById(Long id);
    public void addCarVO(CarVO carVO);
    public CarVO getCarVOById(Long id);
    public List<CarVO> getCarVO();
    public CarVO getCarVOByName(String name);
    public List<CarVO> getCarVOListLikeName(Map<String,Object> map);
}
