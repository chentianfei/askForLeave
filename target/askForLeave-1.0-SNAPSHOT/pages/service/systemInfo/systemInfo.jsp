<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%--请假审批页面--%>
    <head>
        <%--基础引入--%>
        <%@include file="/pages/common/baseinfo.jsp"%>
    </head>
    <body>
        <div class="layui-layout layui-layout-admin">
            <%--头部信息--%>
            <%@include file="/pages/common/header.jsp"%>

            <%--左侧导航--%>
            <%@include file="/pages/common/menu.jsp"%>

            <%--主体信息--%>
                <div class="layui-body layui-bg-gray" style="padding: 10px">
                    <%--信息查询--%>
                    <div class="layui-row">
                        <div class="layui-bg-gray" style="padding: 30px;">
                            <div class="layui-row layui-col-space15">

                                <div class="layui-col-md6">
                                    <div class="layui-card">
                                        <div class="layui-card-header" style="background: #1E9FFF;color: whitesmoke">设置到假提醒短信发送日期</div>
                                        <div class="layui-card-body" style="height: 200px">

                                            <blockquote class="layui-elem-quote">
                                                此处可以设置在请假者应到岗日前多少天，发送短信提醒请假者到岗。
                                                当前参数为<strong class="layui-font-20 layui-font-orange" id="currentSmsAlertDays"></strong>天
                                            </blockquote>

                                            <hr class="layui-border-green">

                                            <form class="layui-form layui-form-pane">
                                                <div class="layui-form-item">
                                                    <div class="layui-inline">
                                                        <label class="layui-form-label" style="width: 300px">提前几日为请假者发送到岗提醒短信</label>
                                                        <div class="layui-input-inline" style="width: 200px">
                                                            <input type="text" name="smsAlertDays" placeholder="请输入参数"
                                                                   autocomplete="off" class="layui-input">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="layui-form-item" style="margin: 5% 5% 0px 30%">
                                                    <div class="layui-input-block">
                                                        <button class="layui-btn" lay-submit lay-filter="updateSmsAlert">修改</button>
                                                    </div>
                                                </div>
                                            </form>



                                        </div>
                                    </div>
                                </div>

                                <div class="layui-col-md6">
                                    <div class="layui-card">
                                        <div class="layui-card-header" style="background: #1E9FFF;color: whitesmoke">短信发送对象</div>
                                        <div class="layui-card-body" style="height: 200px">

                                            <blockquote class="layui-elem-quote">
                                                此处可以设置是否向请假者本人或领导发送短信
                                            </blockquote>

                                            <hr class="layui-border-green">

                                            <form class="layui-form layui-form-pane">

                                                <div class="layui-form-item">
                                                    <label class="layui-form-label" style="width: 200px">是否给本人发送短信</label>
                                                    <div class="layui-input-block">
                                                        <input type="checkbox" name="doesSendSelf" checked=""
                                                               id="doesSendSelf"
                                                               lay-skin="switch"
                                                               lay-filter="doesSendSelf" lay-text="是|否">
                                                    </div>
                                                </div>

                                                <div class="layui-form-item">
                                                    <label class="layui-form-label" style="width: 200px">是否给领导发送短信</label>
                                                    <div class="layui-input-block">
                                                        <input type="checkbox" name="doesSendLeader" checked=""
                                                               id="doesSendLeader"
                                                               lay-skin="switch"
                                                               lay-filter="doesSendLeader" lay-text="是|否">
                                                    </div>
                                                </div>

                                                <div class="layui-form-item" style="margin: -120px 5% 0px 30%">
                                                    <div class="layui-input-block">
                                                        <button class="layui-btn" lay-submit
                                                                lay-filter="updateSendObject">修改</button>
                                                    </div>
                                                </div>

                                            </form>
                                        </div>
                                    </div>
                                </div>

                            </div>

                        </div>
                    </div>

                </div>

            <%--底部信息--%>
            <div class="layui-footer">
                <%@include file="/pages/common/footer.jsp" %>
            </div>

        </div>


        <script>

            layui.use(['element', 'layer', 'util','form'], function(){
                var element = layui.element
                    ,layer = layui.layer
                    ,util = layui.util
                    ,$ = layui.$;
                var form = layui.form;

                bindSendObjSwitchStatus();
                bindCurrentSmsAlertDays();

                form.on('submit(updateSmsAlert)', function(data){
                    var sourceData = data.field;
                    var smsAlertDays = sourceData.smsAlertDays;

                    $.ajax({
                        type : 'POST',
                        url : 'systemDataServlet?action=updateSmsAlertDays',
                        data : {
                            smsAlertDays:smsAlertDays
                        },
                        dataType : 'json',
                        success : function(data) {
                            var souceData = data.data;
                            // 成功提示框
                            layer.msg('已修改', {
                                icon : 6,
                            });
                        },
                        error : function(data) {
                            // 异常提示
                            layer.msg('出现网络故障', {
                                icon : 5
                            });
                        }
                    });
                    window.location.reload();
                    return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
                });

                form.on('submit(updateSendObject)', function(data){
                    var sourceData = data.field;
                    var doesSendSelf = sourceData.doesSendSelf;
                    var doesSendLeader = sourceData.doesSendLeader;

                    $.ajax({
                        type : 'POST',
                        url : 'systemDataServlet?action=updateSendObj',
                        data : {
                            doesSendSelf:doesSendSelf,
                            doesSendLeader:doesSendLeader
                        },
                        dataType : 'json',
                        success : function(data) {
                            var souceData = data.data;
                            // 成功提示框
                            layer.msg('已修改', {
                                icon : 6,
                            });
                        },
                        error : function(data) {
                            // 异常提示
                            layer.msg('出现网络故障', {
                                icon : 5
                            });
                        }
                    });

                    return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
                });


            });
        </script>

    </body>
</html>
