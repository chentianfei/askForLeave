package com.ctf.utils;

import com.ctf.bean.Person;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
*@Description :
*@ClassName WebUtils
*@Author tianfeichen
*@Date 2021/8/21 16:12
*@Version v1.0
*/
public class WebUtils {
    private static final String FORMAT = "yyyy-MM-dd" ;

    public static Integer parseInt(String str,Integer defaultValue){
        Integer value = Integer.parseInt(str);
        if(value != null){
            return value;
        }else {
            return defaultValue;
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
            //注册日期类型转换器
           /* ConvertUtils.register(new Converter() {

                public Object convert(Class type, Object value) {
                    if(type != Date.class) {
                        return null ;
                    }
                    if(value == null || "".equals(value.toString().trim())) {
                        return null ;
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd" ) ;
                    Date date = null ;
                    try {
                        date = sdf.parse((String) value) ;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(date);
                    return date;
                }

            }, Date.class);*/

            //2、指定一个类型转换器（String转换为Date），birthday传过来是String
            /*ConvertUtils.register(new Converter() {
                public Object convert(Class clazz, Object value) {
                    //将String转换为Date
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date parse = null;
                    try {
                        parse = format.parse(value.toString());
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return parse;
                }
            }, Date.class);*/

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


    /**
     * 获取中文全拼
     *
     * @param name 需要转换的中文
     * @return 全拼结果
     **/
    public static String getFullPinyin(String name) {
        // 创建格式化对象
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        //设置大小写格式
        outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        //设置声调格式
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        // 放置输入结果
        StringBuilder result = new StringBuilder();
        // 字符数组
        char[] charArray = name.toCharArray();
        // 遍历字符
        for (char c : charArray) {
            // 中文会被变成全拼，非中文会被直接拼接在结果字符串中
            if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                String[] pinyinArray = new String[0];
                try {
                    pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, outputFormat);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
                if (pinyinArray != null) {
                    result.append(pinyinArray[0]);
                }
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * 获取中文首字母
     *
     * @param name 需要转换的中文
     * @return 首字母结果
     **/
    public static String getPinyinInitial(String name) {
        // 创建格式化对象
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        //设置大小写格式
        outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        //设置声调格式
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        // 放置输入结果
        StringBuilder result = new StringBuilder();
        // 字符数组
        char[] charArray = name.toCharArray();
        // 遍历字符
        for (char c : charArray) {
            // 中文会被变成拼音首字母，非中文会被直接拼接在结果字符串中
            if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                String[] pinyinArray = new String[0];
                try {
                    pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, outputFormat);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
                if (pinyinArray != null) {
                    result.append(pinyinArray[0].charAt(0));
                }
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }


    public static String generateAPersonId(Person person){
        String name = person.getName();
        String name_PYIitial = getPinyinInitial(name).toUpperCase();

        String phone = person.getPhone().substring(7);

        Long randomLong = Math.abs(new Random().nextLong());
        String code = Long.toString(randomLong).substring(0,9);

        return name_PYIitial+""+code+""+phone;
    }

    //短信发送功能
    public static void sendMsg(){ }

    //数据导出到excel
    public static void exportToExcel(){}

}
