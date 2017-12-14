package com.shihu.model.common.VO;

import java.util.Date;

public class LogVO {
    private Long id;
    private String info;
    private Date createTime;

    public LogVO() {
    }

    public LogVO(String info) {
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public LogVO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public LogVO setInfo(String info) {
        this.info = info;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public LogVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
