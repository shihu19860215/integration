package com.shihu.model;

public class HomePageBean {
    private int index;
    private String includePage;

    public HomePageBean(int index, String includePage) {
        this.index = index;
        this.includePage = includePage;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getIncludePage() {
        return includePage;
    }

    public void setIncludePage(String includePage) {
        this.includePage = includePage;
    }
}
