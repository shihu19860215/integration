package com.shihu.base;

import com.google.gson.Gson;

public class AjaxResult {
    private int state;
    private String info;

    public String toJsonStr(){
        return Base.gson.toJson(this);
    }

    public int getState() {
        return state;
    }

    public AjaxResult setState(int state) {
        this.state = state;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public AjaxResult setInfo(String info) {
        this.info = info;
        return this;
    }
}
