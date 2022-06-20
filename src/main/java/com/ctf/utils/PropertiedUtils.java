package com.ctf.utils;

import com.ctf.web.Servlet.PersonServlet;
import org.apache.poi.ss.formula.functions.T;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

public class PropertiedUtils {
    public static final String SYSTEMPROPERTIES = "system.properties";
    public static final String DRUIDPROPERTIES = "druid.properties";
    public static final String LOG4JPROPERTIES = "log4j.properties";
    public static final String QUARTZPROPERTIES = "quartz.properties";

    //读取properties，返回指定key的value
    public static <T> T getValue(String propertiesName,String keyName,Class<T> type)
            throws ClassNotFoundException, IOException {
        Properties properties = new Properties();
        InputStream inputStream = PropertiedUtils.class
                .getClassLoader()
                .getResourceAsStream(propertiesName);
        // 从流中加载数据
        properties.load(inputStream);
        return (T) properties.get(keyName);
    }

}
