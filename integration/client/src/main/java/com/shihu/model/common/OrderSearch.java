package com.shihu.model.common;

import com.shihu.base.Base;
import com.shihu.util.DateUtil;

import java.util.Date;

public class OrderSearch {
    private Long customerId;
    private String customerName;
    private String productName;
    private String startDate;
    private String endDate;
    private Date start;
    private Date end;

    public void conversionDate(){
        if(validateDateString(startDate)){
            start=DateUtil.parse(startDate, Base.DATE_PARSE_YYYYMMdd);
        }
        if(validateDateString(endDate)){
            end=DateUtil.parse(endDate, Base.DATE_PARSE_YYYYMMdd);
        }
    }
    private boolean validateDateString(String str){
        if(null==startDate||startDate.length()!=8){
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
            tmp=d%100/100;
            if(tmp<1||tmp>31){
                return false;
            }
        }catch (Exception e){
            return false;
        }
        return  true;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public OrderSearch setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public OrderSearch setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public OrderSearch setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getStartDate() {
        return startDate;
    }

    public OrderSearch setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getEndDate() {
        return endDate;
    }

    public OrderSearch setEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public Date getStart() {
        return start;
    }

    public OrderSearch setStart(Date start) {
        this.start = start;
        return this;
    }

    public Date getEnd() {
        return end;
    }

    public OrderSearch setEnd(Date end) {
        this.end = end;
        return this;
    }
}
