package com.shihu.service;

import com.shihu.model.common.Log;
import com.shihu.model.common.LogSearch;
import com.shihu.model.common.VO.LogVO;

import java.util.List;

public interface LogService {
    public List<Log> listLogVOListByLogSearch(LogSearch logSearch);
}
