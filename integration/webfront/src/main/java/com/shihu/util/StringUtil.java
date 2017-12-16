package com.shihu.util;

public class StringUtil {
    public String toCutString(String str,int maxlength){
        maxlength=maxlength;
        char[] chars=str.toCharArray();
        int n=0;
        for(int i=0;i<chars.length;i++){
            if(Integer.valueOf(chars[i])>255){
                n=n+2;
            }else{
                n=n+1;
            }
            if(n>maxlength){
                return str.substring(0,i)+"....";
            }
        }
        return str;
    }
}
