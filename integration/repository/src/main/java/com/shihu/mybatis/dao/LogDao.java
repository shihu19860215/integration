package com.shihu.mybatis.dao;

import com.shihu.model.common.LogSearch;
import com.shihu.model.common.VO.LogVO;

import java.util.List;

public interface LogDao {
    public void addLog(LogVO logVO);
    public List<LogVO> listLogVOListByLogSearch(LogSearch logSearch);
    public int getCountLogVOListByLogSearch(LogSearch logSearch);
}
