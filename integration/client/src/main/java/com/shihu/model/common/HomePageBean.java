package com.shihu.model.common;

import com.shihu.exception.PagePromptException;

public class HomePageBean {
    private int index;
    private String includePage;
    private PagePromptException pagePromptException;

    public HomePageBean(int index, String includePage) {
        this.index = index;
        this.includePage = includePage;
    }

    public PagePromptException getPagePromptException() {
        return pagePromptException;
    }

    public HomePageBean setPagePromptException(PagePromptException pagePromptException) {
        this.pagePromptException = pagePromptException;
        return this;
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
