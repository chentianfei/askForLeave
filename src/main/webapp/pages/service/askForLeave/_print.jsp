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
                <p style="margin: 5px 0; text-align: center; font-size: 30px;font-family: FZXiaoBiaoSong-B05">准假批复</p>
                <p style="text-align: right; font-weight: bold; margin-right: 70px; margin-bottom: 5px;font-family: KaiTi_GB2312;font-size: 15px">编号:<span id="serialnumber"></span></p>
                <div style="font-family: FangSong_GB2312;font-size: 18px;line-height: 24px">
                    <p><u id="office_name" style="font-family: FangSong_GB2312"></u>：</p>
                    <p>&nbsp;&nbsp;经我局批准同意你单位<u id="name"></u>同志请<u id="leave_type"></u>假
                        <u id="leave_days_projected"></u>天
                        ，该同志离岗时间为<u id="start_date1"></u>，假期自<u id="start_date2"></u>起至<u id="end_date_maybe"></u>止
                        。</p>
                    <p>&nbsp;&nbsp;特此批复！</p>
                </div>

                <div style="text-align:right;width:800px;height:60px;font-family: FangSong_GB2312;font-size: 18px;line-height: 24px">
                   <div>
                        <p style="text-align: right;margin-right:40px;margin-top:45px;">谢通门县人社局</p>
                        <p class="date2" style="text-align: right; margin-bottom: 0; margin-right: 70px;"><span id="operate_date"></span></p>
                    </div>
                </div>

                <div id="qrcode" style="position:fixed;top:220px;left:200px;">
                </div>

                <div >
                    <p style="display:inline;"></p>
                    <p style="display:inline;"></p>
                    <p style="display:inline;">备注：此批复拿回单位备案</p>
                </div>

            </div>
        </div>
        <!--endprint-->
        <button type="submit" class="layui-btn" style="display:none"
                id="print" onclick="print()"></button>

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
                var serialnumber = parent.$("#serialnumber:hidden").val();

                // 带背景色二维码
                var qrcode = new QRCode(document.getElementById("qrcode"), {
                    text: "http://139.186.143.253:8080/askForLeave37f22ccdb90f3fb8/askForLeaveServlet?action=queryALeaveInfoForPrintBySerialnumberForQRCode&serialnumber="+serialnumber,
                    width: 90,
                    height: 90,
                    colorDark: "#424242",
                    colorLight: "#ffffff",
                    correctLevel: 1 // 二维码结构复杂性 0~3
                });

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
                    bdhtml=window.document.body.innerHTML;//获取当前页的html代码
                    sprnstr="<!--startprint-->";//设置打印开始区域
                    eprnstr="<!--endprint-->";//设置打印结束区域
                    prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html
                    prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html
                    window.document.body.innerHTML=prnhtml;
                    window.print();
                    //关闭此页面
                    parent.layer.close(index);
                    return false;
                });
            });
        </script>

    </body>
</html>
