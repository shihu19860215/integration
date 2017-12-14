package com.shihu.service;

import com.shihu.exception.PagePromptException;
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
    public void deleteCarTypeById(Long id) throws PagePromptException;
    public void addCarTypeVO(CarTypeVO carTypeVO) throws PagePromptException;
    public CarType getCarTypeById(Long id);
}
