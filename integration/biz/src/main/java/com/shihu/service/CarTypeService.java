package com.shihu.service;

import com.shihu.model.common.CarType;
import com.shihu.model.common.VO.CarTypeVO;

import java.util.List;

public interface CarTypeService {
    /**
     * 获取carTypeVO
     * @param id
     * @return
     */
    public CarTypeVO getCarTypeVOByIdCache(Long id);
    public List<CarTypeVO> getAllCarTypeVO();
    public List<CarTypeVO> getCarTypeVOLikeName(String name);
    public boolean deleteCarTypeById(Long id);
    public boolean addCarTypeVO(CarTypeVO carTypeVO);
    public CarType getCarTypeById(Long id);
}
