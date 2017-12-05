package com.shihu.base;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Base {
    public static Gson gson=new Gson();
    public static Map<Integer,ErrorInfo> errorInfoMap;
    public final  static ErrorInfo USER_PASSWORD_ERROR=new ErrorInfo(1,"用户名密码错误");
    public final  static ErrorInfo ADD_INFO_NOT_EMPTY=new ErrorInfo(2,"添加信息不能为空");
    public final  static ErrorInfo ADD_INFO_REPART=new ErrorInfo(3,"添加信息重复");
    public final  static ErrorInfo CARTYPE_NOT_EMPTY=new ErrorInfo(4,"该车型含有车信息不为空，无法删除");
    public final  static ErrorInfo CAR_NOT_EMPTY=new ErrorInfo(5,"该车含有商品信息不为空，无法删除");

    public static ErrorInfo getErrorInfo(Integer i){
        return errorInfoMap.get(i);
    }
    static {
        errorInfoMap=new HashMap<Integer, ErrorInfo>(8);
        errorInfoMap.put(USER_PASSWORD_ERROR.getId(),USER_PASSWORD_ERROR);
        errorInfoMap.put(ADD_INFO_NOT_EMPTY.getId(),ADD_INFO_NOT_EMPTY);
        errorInfoMap.put(ADD_INFO_REPART.getId(),ADD_INFO_REPART);
        errorInfoMap.put(CARTYPE_NOT_EMPTY.getId(),CARTYPE_NOT_EMPTY);
        errorInfoMap.put(CAR_NOT_EMPTY.getId(),CAR_NOT_EMPTY);
    }

}
