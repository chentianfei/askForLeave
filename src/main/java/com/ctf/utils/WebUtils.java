package com.ctf.utils;
/**
*@Description :
*@ClassName WebUtils
*@Author tianfeichen
*@Date 2021/8/21 16:12
*@Version v1.0
*/
public class WebUtils {

    public static Integer parseInt(String str,Integer defaultValue){
        Integer value = Integer.parseInt(str);
        if(value != null){
            return value;
        }else {
            return defaultValue;
        }
    }

}
