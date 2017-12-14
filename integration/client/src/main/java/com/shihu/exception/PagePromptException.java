package com.shihu.exception;


import java.util.HashMap;
import java.util.Map;

public class PagePromptException extends  RuntimeException{
    private Integer id;
    private String promptInfo;

    public PagePromptException(Integer id) {
        this.id=id;
        promptInfo=errorInfoMap.get(id);
    }

    public Integer getId() {
        return id;
    }

    public PagePromptException setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getPromptInfo() {
        return promptInfo;
    }

    public PagePromptException setPromptInfo(String promptInfo) {
        this.promptInfo = promptInfo;
        return this;
    }

    public static PagePromptException getPagePromptInfo(Integer key){
        return new PagePromptException(key);
    }

    private static Map<Integer,String> errorInfoMap;
    public final static Integer USER_PASSWORD_ERROR=1;
    public final static Integer ADD_INFO_NOT_EMPTY=2;
    public final static Integer ADD_INFO_REPART=3;
    public final static Integer CARTYPE_NOT_EMPTY=4;
    public final static Integer CAR_NOT_EMPTY=5;
    public final static Integer ADD_ORDER_ERROR_PRODCUT_INVAILD=6;
    public final static Integer ADD_ORDER_ERROR_PRODCUT_NUM_LESS=7;
    static {
        errorInfoMap=new HashMap<Integer, String>();
        errorInfoMap.put(USER_PASSWORD_ERROR,"用户名密码错误");
        errorInfoMap.put(ADD_INFO_NOT_EMPTY,"添加信息不能为空");
        errorInfoMap.put(ADD_INFO_REPART,"添加信息重复");
        errorInfoMap.put(CARTYPE_NOT_EMPTY,"该车型含有车信息不为空，无法删除");
        errorInfoMap.put(CAR_NOT_EMPTY,"该车含有商品信息不为空，无法删除");
        errorInfoMap.put(ADD_ORDER_ERROR_PRODCUT_INVAILD,"增加订单失败，订单含有失效商品");
        errorInfoMap.put(ADD_ORDER_ERROR_PRODCUT_NUM_LESS,"增加订单失败，订单商品数大于实际商品数");


    }
}
