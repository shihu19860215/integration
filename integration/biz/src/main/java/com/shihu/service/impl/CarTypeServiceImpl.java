package com.shihu.service.impl;

import com.shihu.model.common.CarType;
import com.shihu.model.common.VO.CarTypeVO;
import com.shihu.model.common.VO.CarVO;
import com.shihu.mybatis.dao.CarDao;
import com.shihu.mybatis.dao.CarTypeDao;
import com.shihu.service.CarTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarTypeServiceImpl implements CarTypeService {
    private Map<Long,CarTypeVO> idNameMap;
    @Autowired
    private CarTypeDao carTypeDao;
    @Autowired
    private CarDao carDao;


    public CarTypeVO getCarTypeVOByIdCache(Long id) {
        return getIdNameMap().get(id);
    }

    public List<CarTypeVO> getAllCarTypeVO() {
        List<CarTypeVO> list=carTypeDao.getAllCarTypeVO();
        return list;
    }
    public List<CarTypeVO> getCarTypeVOLikeName(String name){
        List<CarTypeVO> list=carTypeDao.getCarTypeVOLikeName("%"+name+"%");
        return list;
    }

    public boolean deleteCarTypeById(Long id) {
        List<CarVO> carVOs= carDao.getCarVOListByCarTypeId(id);
        if(null!=carVOs&&carVOs.size()>0){
            return false;
        }else {
            carTypeDao.deleteCarTypeById(id);
            getIdNameMap().remove(id);
            return true;
        }
    }

    public boolean addCarTypeVO(CarTypeVO carTypeVO) {
        CarTypeVO c=carTypeDao.getCarTypeVOByName(carTypeVO.getName());
        if(null==c){
            carTypeDao.addCarTypeVO(carTypeVO);
            getIdNameMap().put(carTypeVO.getId(),carTypeVO);
            return true;
        }else {
            return false;
        }
    }

    public CarType getCarTypeById(Long id) {
        CarTypeVO carTypeVO=this.getCarTypeVOByIdCache(id);
        List<CarVO> carVOs=carDao.getCarVOListByCarTypeId(id);
        CarType carType=new CarType(carTypeVO,carVOs);
        return carType;
    }

    public Map<Long,CarTypeVO> getIdNameMap(){
        if (null==idNameMap){
            setIdNameMap();
        }
        return idNameMap;
    }

    /**
     * 设置idNameMap
     */
    private void setIdNameMap(){
        idNameMap=new HashMap<Long, CarTypeVO>();
        List<CarTypeVO> list=getAllCarTypeVO();
        for(CarTypeVO c:list){
            idNameMap.put(c.getId(),c);
        }
    }
}
