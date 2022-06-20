package com.ctf.web.Listener;

import com.alicom.mns.tools.DefaultAlicomMessagePuller;
import com.alicom.mns.tools.MessageListener;
import com.aliyun.mns.model.Message;
import com.aliyuncs.exceptions.ClientException;
import com.ctf.web.Servlet.ReceiveDemo;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description :
 * @ClassName AliyunSmsListener
 * @Author tianfeichen
 * @Date 2022/6/19 20:54
 * @Version v1.0
 */
public class AliyunSmsListener implements ServletContextListener {
    private ReceiveDemo receiveDemo = new ReceiveDemo();

    public void contextInitialized(ServletContextEvent e) {
        System.out.println("开始监听");
        try {
            receiveDemo.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        /*
        String str = null;
        if (str == null && receiveDemo == null) {
            receiveDemo = new ReceiveDemo();
            receiveDemo.start(); // servlet 上下文初始化时启动 socket
        }*/
    }

    public void contextDestroyed(ServletContextEvent e) {
        System.out.println("监听结束");
        try {
            receiveDemo.stop();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        /*if (receiveDemo != null && receiveDemo.isInterrupted()) {
            receiveDemo.interrupt();
        }*/
    }

}
