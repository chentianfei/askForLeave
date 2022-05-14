package com.ctf.utils;

import com.ctf.bean.Person;
import com.ctf.dao.AskForLeaveDao;
import com.ctf.dao.PersonDao;
import com.ctf.dao.SystemDataDao;

import com.tencentcloudapi.sms.v20210111.models.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

//导入可选配置类
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

// 导入对应SMS模块的client
import com.tencentcloudapi.sms.v20210111.SmsClient;

// 导入要请求接口对应的request response类

// 导入要请求接口对应的request response类


/**
*@Description :
*@ClassName WebUtils
*@Author tianfeichen
*@Date 2021/8/21 16:12
*@Version v1.0
*/
public class WebUtils {
    private static final String FORMAT = "yyyy-MM-dd" ;
    private static SystemDataDao systemDataDao = new SystemDataDao();
    private static AskForLeaveDao askForLeaveDao = new AskForLeaveDao();
    private static PersonDao personDao = new PersonDao();

    public static Integer parseInt(String str,Integer defaultValue){
        Integer value = Integer.parseInt(str);
        if(value != null){
            return value;
        }else {
            return defaultValue;
        }
    }

    //打印hashMap的list
    public static void printHashMapList(List<HashMap<String, Object>> hashMaps){
        for(HashMap<String, Object> hashMap:hashMaps){
            for(Map.Entry<String, Object> map : hashMap.entrySet()){
                System.out.println("key="+map.getKey()+";  value="+map.getValue());
            }
        }
    }

    /**
     * @Name: fillBean
     * @Description: 将Map集合中的数据封装到JavaBean
     * 说明：
     *   Map的key：与属性名称保持一致；
     *   Map的value：设置为属性值；
     * @Author: XXX
     * @Version: V1.0
     * @CreateDate: XXX
     * @Parameters: @param map
     * @Parameters: @param clazz
     * @Return: T
     */
    public static <T> T fillBean(Map<String, String[]> map, Class<T> clazz) {
        T bean = null ;
        try {
            bean = clazz.newInstance() ;

            ConvertUtils.register(new Converter() {
                //修改第三方jar引入方法的参数时候，可以关联源码，ctrl选择该类，点进去，选择Attach source--->external file
                @Override
                public Object convert(Class type, Object value) {
                    //判断
                    if (value ==null ||"".equals(value.toString().trim())){
                        return null;
                    }
                    if (type !=Date.class){
                        return null;
                    }
                    try {
                        //字符串转换为日期
                        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                        return sdf.parse(value.toString());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            },Date.class);

            //将Map集合中的数据封装到JavaBean
            BeanUtils.populate(bean, map) ;
        } catch (Exception e) {
            throw new RuntimeException(e) ;
        }
        return bean ;
    }



    //将String类型的List转换为String[]
    public static String[] stringListTostringArray(List<String> stringList){
        String[] strings = new String[stringList.size()];
        int index = 0;
        for(String templateParam : stringList){
            strings[index++] = templateParam;
        }
        return strings;
    }

    //数据导出到excel
    public static void exportToExcel(){}

}
