package com.ctf.test;

import java.io.UnsupportedEncodingException;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;


/**
 * @Description :
 * @ClassName Test
 * @Author tianfeichen
 * @Date 2021/10/14 19:04
 * @Version v1.0
 */
public class Test {
    public static void test(String[] args)throws Exception
    {

        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
        NameValuePair[] data ={
                new NameValuePair("Uid", "ctf001"),
                new NameValuePair("Key", "5ca42f7364fa83a6efdf"),
                new NameValuePair("smsMob","18780292551"),
                new NameValuePair("smsText","这是飞哥用程序向你发来的第一条短信，加油")};
        post.setRequestBody(data);

        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:"+statusCode);
        for(Header h : headers)
        {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
        System.out.println(result); //打印返回消息状态
        post.releaseConnection();

    }
}
