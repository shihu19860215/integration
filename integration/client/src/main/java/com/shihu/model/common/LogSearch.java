package com.shihu.model.common;

import com.shihu.base.Base;
import com.shihu.util.DateUtil;

import java.util.Calendar;
import java.util.Date;

public class LogSearch {
    private Integer pageNum;
    private Integer pageStart;
    private Integer pageSize=100;
    private Integer count;
    private String startDate;
    private String endDate;
    private Date start;
    private Date end;

    public void init(){
        conversionDate();
        if(null==pageNum){
            pageNum=1;
            pageStart=0;
        }else {
            pageStart=(pageNum-1)*pageSize;
        }
    }
    public void conversionDate(){
        if(validateDateString(startDate)){
            start= DateUtil.parse(startDate, Base.DATE_PARSE_yyyyMMdd);
        }
        if(validateDateString(endDate)){
            end=DateUtil.parse(endDate, Base.DATE_PARSE_yyyyMMdd);
            Calendar c = Calendar.getInstance();
            c.setTime(end);
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            end=c.getTime();
        }
    }
    private boolean validateDateString(String str){
        if(null==str||str.length()!=8){
            return false;
        }
        try {
            int d=Integer.valueOf(str);
            int tmp;
            tmp=d/10000;
            if(tmp<2017||tmp>2100){
                return false;
            }
            tmp=d%10000/100;
            if(tmp<1||tmp>13){
                return false;
            }
            tmp=d%100;
            if(tmp<1||tmp>31){
                return false;
            }
        }catch (Exception e){
            return false;
        }
        return  true;
    }




    public Integer getPageNum() {
        return pageNum;
    }

    public LogSearch setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public Integer getPageStart() {
        return pageStart;
    }

    public LogSearch setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public LogSearch setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public LogSearch setCount(Integer count) {
        this.count = count;
        return this;
    }

    public String getStartDate() {
        return startDate;
    }

    public LogSearch setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getEndDate() {
        return endDate;
    }

    public LogSearch setEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public Date getStart() {
        return start;
    }

    public LogSearch setStart(Date start) {
        this.start = start;
        return this;
    }

    public Date getEnd() {
        return end;
    }

    public LogSearch setEnd(Date end) {
        this.end = end;
        return this;
    }

}
