package com.shihu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {
    private static Map<String,SimpleDateFormat> simpleDateFormatMap=new HashMap<String,SimpleDateFormat>();

    public static Date parse(String date,String parseStr){
        SimpleDateFormat simpleDateFormat=simpleDateFormatMap.get(parseStr);
        if(null==simpleDateFormat){
            simpleDateFormat=new SimpleDateFormat(parseStr);
            simpleDateFormatMap.put(parseStr,simpleDateFormat);
        }
        try {
            return  simpleDateFormat.parse(date);
        } catch (ParseException e) {
        }
        return null;
    }

    public static String format(Date date,String parseStr){
        SimpleDateFormat simpleDateFormat=simpleDateFormatMap.get(parseStr);
        if(null==simpleDateFormat){
            simpleDateFormat=new SimpleDateFormat(parseStr);
            simpleDateFormatMap.put(parseStr,simpleDateFormat);
        }
        return  simpleDateFormat.format(date);
    }
}
