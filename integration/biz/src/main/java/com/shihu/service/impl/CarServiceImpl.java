package com.shihu.service.impl;

import com.shihu.exception.PagePromptException;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService {
    private Map<Long,CarVO> idCarVOMap;
    @Autowired
    private CarTypeService carTypeService;
    @Autowired
    private CarDao carDao;
    @Autowired
    private CarProductDao carProductDao;


    public CarVO getCarVOByIdCache(Long id) {
        CarVO carVO=getIdCarVOMap().get(id);
        if(null==carVO){
            carVO=carDao.getCarVOById(id);
            if(null!=carVO){
                getIdCarVOMap().put(id,carVO);
            }
        }
        return carVO;
    }

    public List<CarVO> getCarVOListByCarTypeId(Long carTypeId) {
        return carDao.getCarVOListByCarTypeId(carTypeId);
    }

    @Transactional
    public void deleteCarById(Long id) throws  PagePromptException {
        int count=carProductDao.getCountByCarId(id);
        if(count>0){
            throw new PagePromptException(PagePromptException.CAR_NOT_EMPTY);
        }else {
            carDao.deleteCarById(id);
            getIdCarVOMap().remove(id);
        }
    }

    @Transactional
    public void addCarVO(CarVO carVO) throws  PagePromptException{
        CarVO c=carDao.getCarVOByName(carVO.getName());
        if(null==c){
            carDao.addCarVO(carVO);
            getIdCarVOMap().put(carVO.getId(),carVO);
        }else {
            throw new PagePromptException(PagePromptException.ADD_INFO_REPART);
        }
    }

    public List<CarVO> listCarVOWithCarTypeNameIncludeStr(String str) {
        List<CarVO> list=new ArrayList<CarVO>();
        for(Map.Entry<Long,CarVO> e:getIdCarVOMap ().entrySet()){
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
    private void setIdCarVOMap(){
        idCarVOMap=new HashMap<Long,CarVO>();
        List<CarVO> list=carDao.getCarVO();
        for(CarVO c:list){
            idCarVOMap.put(c.getId(),c);
        }
    }
    private Map<Long,CarVO> getIdCarVOMap(){
        if (null==idCarVOMap){
            setIdCarVOMap();
        }
        return idCarVOMap;
    }
}
