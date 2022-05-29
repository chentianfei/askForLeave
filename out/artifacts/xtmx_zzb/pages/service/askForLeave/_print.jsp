<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%--查看请假信息详情--%>
    <head>
        <%--基础引入--%>
        <%@include file="/pages/common/baseinfo.jsp"%>
    </head>
    <body>
        <!--startprint-->
        <div style="padding: 10px">
            <div id="responsive" style="text-align: left;">
                <p class="title1"
                   style="margin: 5px 0; text-align: center; font-size: 25px;">准假批复</p>
                <p class="no bum"
                   style="text-align: right; font-weight: bold; margin-right: 70px; margin-bottom: 5px;">编号:<span id="serialnumber"></span></p>
                <p><u id="office_name"></u>：</p>
                <p>&nbsp;&nbsp;经我部批准同意你单位<u id="name"></u>同志请<u id="leave_type"></u>假
                    <u id="leave_days_projected"></u>天
                    ，该同志离岗时间为<u id="start_date1"></u>，假期自<u id="start_date2"></u>起至<u id="end_date_maybe"></u>止
                    。</p>
                <p>&nbsp;&nbsp;特此批复！</p>
                <div style="width:800px;height:100px;">
                    <div style="float:left;width:500px;height:100px;">
                        <p style="display:inline;">备注：此批复拿回单位备案</p>
                      <%--  <img  src="images/pizhun.jpg" style="margin-left: 50px;margin-bottom:18px;">
                        <img  src="images/erweima.jpg" width="100" height="100" style="margin-left: 20px;"/>--%>
                    </div>
                    <div style="float:right;width:300px;height:100px;">
                        <p style="margin-left:110px;margin-top:45px;">中共谢通门县委组织部</p>
                        <p class="date2" style="text-align: right; margin-bottom: 0; margin-right: 30px;"><span id="operate_date"></span></p></p>
                    </div>
                </div>
            </div>
        </div>
        <!--endprint-->
        <button type="submit" class="layui-btn" style="display:none"
                id="print" lay-submit lay-filter="print"></button>

        </div>

        <script type="text/javascript">
            layui.use(['laydate','form','common','table'], function() {
                var laydate = layui.laydate;
                var form = layui.form;
                var $ = layui.jquery;
                var index = parent.layer.getFrameIndex(window.name);
                var common = layui.common;
                var table = layui.table;

                form.render();

                function dayin(){
                    bdhtml=window.document.body.innerHTML;//获取当前页的html代码
                    sprnstr="<!--startprint-->";//设置打印开始区域
                    eprnstr="<!--endprint-->";//设置打印结束区域
                    prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html
                    prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html
                    window.document.body.innerHTML=prnhtml;
                    window.print();
                }

                form.on('submit(print)', function(data){
                    const sourceData = data.field;

                    dayin();
                    //关闭此页面
                    parent.layer.close(index);
                    return false;
                });
            });
        </script>

    </body>
</html>
