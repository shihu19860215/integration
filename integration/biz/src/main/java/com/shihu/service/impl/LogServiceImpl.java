package com.shihu.service.impl;

import com.shihu.model.common.Log;
import com.shihu.model.common.LogSearch;
import com.shihu.model.common.VO.LogVO;
import com.shihu.mybatis.dao.LogDao;
import com.shihu.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;
    public List<Log> listLogVOListByLogSearch(LogSearch logSearch){
        logSearch.init();
        List<LogVO> logVOList=logDao.listLogVOListByLogSearch(logSearch);
        logSearch.setCount(logDao.getCountLogVOListByLogSearch(logSearch));
        List<Log> logList=new ArrayList<Log>();
        if(null!=logVOList&&logVOList.size()>0){
            for(LogVO logVO:logVOList){
                logList.add(new Log(logVO));
            }
        }
        return logList;
    }
}
