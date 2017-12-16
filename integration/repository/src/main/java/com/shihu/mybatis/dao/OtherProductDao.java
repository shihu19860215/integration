package com.shihu.mybatis.dao;

import com.shihu.model.common.VO.OrderProductAndProductVO;
import com.shihu.model.common.VO.OrderProductVO;
import com.shihu.model.common.VO.OtherProductVO;

import java.util.List;

public interface OtherProductDao {
    public List<OtherProductVO> getOtherProductVOListByOrderId(Long orderId);
    public void addOtherProductVO(OtherProductVO otherProductVO);
}
