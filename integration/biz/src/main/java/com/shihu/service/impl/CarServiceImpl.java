package com.shihu.service.impl;

import com.shihu.model.common.Car;
import com.shihu.model.common.CarType;
import com.shihu.model.common.VO.CarTypeVO;
import com.shihu.model.common.VO.CarVO;
import com.shihu.mybatis.dao.CarDao;
import com.shihu.mybatis.dao.CarProductDao;
import com.shihu.mybatis.dao.CarTypeDao;
import com.shihu.service.CarService;
import com.shihu.service.CarTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService {
    private Map<Long,CarVO> idNameMap;
    @Autowired
    private CarTypeService carTypeService;
    @Autowired
    private CarDao carDao;
    @Autowired
    private CarProductDao carProductDao;


    public CarVO getCarVOByIdCache(Long id) {
        return getIdNameMap().get(id);
    }

    public List<CarVO> getCarVOListByCarTypeId(Long carTypeId) {
        return carDao.getCarVOListByCarTypeId(carTypeId);
    }

    public boolean deleteCarById(Long id) {
        int count=carProductDao.getCountByCarId(id);
        if(count>0){
            return false;
        }else {
            carDao.deleteCarById(id);
            getIdNameMap().remove(id);
            return  true;
        }
    }

    public boolean addCarVO(CarVO carVO) {
        CarVO c=carDao.getCarVOByName(carVO.getName());
        if(null==c){
            carDao.addCarVO(carVO);
            getIdNameMap().put(carVO.getId(),carVO);
            return true;
        }else {
            return false;
        }
    }

    public List<CarVO> listCarVOWithCarTypeNameIncludeStr(String str) {
        List<CarVO> list=new ArrayList<CarVO>();
        for(Map.Entry<Long,CarVO> e:getIdNameMap ().entrySet()){
            String carName=e.getValue().getName();
            if(carName.indexOf(str)>=0){
                list.add(e.getValue());
            }
        }
        setCarNameWithCarType(list);
        return list;
    }

    public List<CarVO> getCarVOListLikeName(Long carTypeId,String name){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("carTypeId",carTypeId);
        map.put("name","%"+name+"%");
        List<CarVO> list=carDao.getCarVOListLikeName(map);
        return list;

    }

    public Map<Long,CarVO> getIdNameMap(){
        if (null==idNameMap){
            setIdNameMap();
        }
        return idNameMap;
    }

    public List<CarVO> setCarNameWithCarType(List<CarVO> list){
        List<CarVO> carVOs=new ArrayList<CarVO>(list.size());
        for(CarVO c:list){
            CarVO carVO=new CarVO();
            CarTypeVO carTypeVO=carTypeService.getCarTypeVOByIdCache(c.getCarTypeId());
            carVO.setId(c.getId());
            carVO.setName(carTypeVO.getName()+":"+c.getName());
            carVOs.add(carVO);
        }
        return carVOs;
    }
    /**
     * 设置idNameMap
     */
    private void setIdNameMap(){
        idNameMap=new HashMap<Long,CarVO>();
        List<CarVO> list=carDao.getCarVO();
        for(CarVO c:list){
            idNameMap.put(c.getId(),c);
        }
    }
}
