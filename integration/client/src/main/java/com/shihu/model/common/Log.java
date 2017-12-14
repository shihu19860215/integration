package com.shihu.model.common;

import com.shihu.base.Base;
import com.shihu.model.common.VO.LogVO;
import com.shihu.util.DateUtil;

import java.util.Date;

public class Log {
    private Long id;
    private String info;
    private String createTime;

    public Log(LogVO logVO) {
        this.info = logVO.getInfo();
        this.createTime = DateUtil.format(logVO.getCreateTime(), Base.DATE_PARSE_YYYYMMddHHmmss);
    }

    public Long getId() {
        return id;
    }

    public Log setId(Long id) {
        this.id = id;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public Log setInfo(String info) {
        this.info = info;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public Log setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }
}
