package com.shihu.server;

import com.shihu.model.common.CarType;
import com.shihu.model.common.VO.CarTypeVO;

import java.util.List;

public interface ICarTypeServer {
    public List<CarTypeVO> getAllCarTypeVO();
}
