package com.ctf.utils;

import java.util.List;

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

    //根据条件（可变参数）查询查询指定表中的数据，并返回结果集
    public static List queryDataByConfidition(){
        return null;
    }


    //短信发送功能
    public static void sendMsg(){ }

    //数据导出到excel
    public static void exportToExcel(){}

}
