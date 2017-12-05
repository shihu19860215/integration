package com.shihu.service;

import com.shihu.model.common.Car;
import com.shihu.model.common.CarType;
import com.shihu.model.common.VO.CarTypeVO;
import com.shihu.model.common.VO.CarVO;

import java.util.List;

public interface CarService {
    public CarVO getCarVOByIdCache(Long id);
    public List<CarVO> getCarVOListByCarTypeId(Long carTypeId);
    public List<CarVO> getCarVOListLikeName(Long carTypeId,String name);
    public boolean deleteCarById(Long id);
    public boolean addCarVO(CarVO carVO);
    public List<CarVO> setCarNameWithCarType(List<CarVO> list);
    /**
     * 获取名字中含有str的车列表,车名 中带有车型名称
     * @param str
     * @return
     */
    public List<CarVO> listCarVOWithCarTypeNameIncludeStr(String str);
}
