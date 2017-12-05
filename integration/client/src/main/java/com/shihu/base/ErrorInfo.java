package com.shihu.base;

public class ErrorInfo {
    private Integer id;
    private String Info;

    public Integer getId() {
        return id;
    }

    public String getInfo() {
        return Info;
    }

    public ErrorInfo(Integer id, String info) {
        this.id = id;
        Info = info;
    }
}
