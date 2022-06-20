package com.ctf.web.Servlet;

import com.aliyun.dysmsapi20170525.models.QuerySendStatisticsResponse;
import com.aliyun.dysmsapi20170525.models.QuerySendStatisticsResponseBody;
import com.ctf.utils.DateUtils;
import com.ctf.utils.PropertiedUtils;
import com.ctf.utils.SendMsg;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/smsServlet")
public class SmsServlet extends BaseServlet{

    //向阿里云发起发送数据统计获取请求
    public void querySendStaticsBeforeToday(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException, ClassNotFoundException {
        //解决post请求方式获取请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");

        String curr_str = request.getParameter("curr");
        String nums_str = request.getParameter("nums");
        Integer pageNo = null;
        Integer pageSize = null;
        if(curr_str!=null){
            if(!curr_str.trim().equals("")){
                //获取当前页码
                pageNo =Integer.valueOf(curr_str);
            }
        }
        if(nums_str!=null){
            if(!nums_str.trim().equals("")){
                //获取每页显示数量
                pageSize = Integer.valueOf(nums_str);
            }
        }

        String msg = "OK";
        String start_date = "20170804";
        String end_date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        QuerySendStatisticsResponse querySendStatisticsResponse = null;
        List<QuerySendStatisticsResponseBody.QuerySendStatisticsResponseBodyDataTargetList> targetList = null;
        try {
            querySendStatisticsResponse  = SendMsg.querySendStaticsBeforeToday(start_date,
                    end_date,
                    pageNo,
                    pageSize);
            targetList = querySendStatisticsResponse.getBody().getData().getTargetList();
        } catch (Exception e) {
            msg = e.getMessage();
        }

        SimpleDateFormat simpleDateFormatYYYYMMDD = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat simpleDateFormatYYYYMMDDCHN = new SimpleDateFormat("yyyy年MM月dd日");
        List<HashMap<String,Object>> hashMapList = new ArrayList<>();

        String count_str = PropertiedUtils.getValue("sms.properties", "count", String.class);
        String start_date_str = PropertiedUtils.getValue("sms.properties", "time", String.class);
        int total = Integer.parseInt(count_str);
        int count = 0;
        for(QuerySendStatisticsResponseBody.QuerySendStatisticsResponseBodyDataTargetList target : targetList){
            HashMap<String,Object> hashMap = new HashMap<>();
            String sendDateStr = simpleDateFormatYYYYMMDDCHN.format(simpleDateFormatYYYYMMDD.parse(target.getSendDate()));
            hashMap.put("sendDate",sendDateStr);
            Date sendDate = new SimpleDateFormat("yyyyMMdd").parse(target.getSendDate());
            Date startDate = new SimpleDateFormat("yyyyMMdd").parse(start_date_str);
            if(sendDate.compareTo(startDate)>=0){
                //在上次购买之后的日期，需要记录使用条数
                count+=target.getRespondedSuccessCount();
            }
            hashMap.put("noRespondedCount",target.getNoRespondedCount());
            hashMap.put("totalCount",target.getTotalCount());
            hashMap.put("respondedSuccessCount",target.getRespondedSuccessCount());
            hashMap.put("respondedFailCount",target.getRespondedFailCount());
            hashMap.put("residue",total-count);
            hashMapList.add(hashMap);
        }

        //封装成json字符串，通过getWriter().write()返回给页面
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",querySendStatisticsResponse.getBody().getData().getTotalSize());
        map.put("data",hashMapList);

        //以json格式返回给前端
        String result_json = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result_json);

    }

    public void getReceiveMessageFromOperation(HttpServletRequest request, HttpServletResponse response){
        BufferedReader br = null;
        String line = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(sb);
    }

}
