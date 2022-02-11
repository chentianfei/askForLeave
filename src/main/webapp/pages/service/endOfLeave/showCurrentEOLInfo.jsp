<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%--应到岗人员--%>
    <head>
        <%--基础引入--%>
        <%@include file="/pages/common/baseinfo.jsp" %>
        <style>
            .layui-input-date{
                height: 25px;
                margin-top: 3px;
            }
        </style>
    </head>
    <body>
        <div class="layui-layout layui-layout-admin">
            <%--头部信息--%>
            <%@include file="/pages/common/header.jsp" %>

            <%--左侧导航--%>
            <%@include file="/pages/common/menu.jsp" %>

            <%--主体信息--%>
            <div class="layui-body layui-bg-gray" style="padding: 10px">
                    <div class="layui-tab layui-tab-brief">
                        <ul class="layui-tab-title">
                            <li class="layui-this">今日到假未到岗人员</li>
                            <li>所有到假未到岗人员</li>
                        </ul>
                        <div class="layui-tab-content">
                            <div class="layui-tab-item layui-show">
                                <%--今日到岗未销假人员表格--%>
                                <table class="layui-hide" id="currentEOLInfo_today" lay-filter="currentEOLInfo_today"></table>
                            </div>

                            <div class="layui-tab-item">
                                <%--所有到岗未销假人员表格--%>
                                <table class="layui-hide" id="currentEOLInfo_all" lay-filter="currentEOLInfo_all"></table>
                            </div>

                        </div>
                    </div>

            </div>

            <%--底部信息--%>
            <div class="layui-footer">
                <%@include file="/pages/common/footer.jsp" %>
            </div>

            <%--接受person_id的值，以便子页面获取--%>
            <input  type="hidden" class="layui-input"
                    name="start_date"
                    style="display:none"
                    id="start_date"  />

        </div>

        <%--今日到假未到岗人员表格内部工具栏--%>
        <script type="text/html" id="toolbar_currentEOLInfo_today">
            <button type="button" class="layui-btn layui-btn-xs layui-bg-cyan" lay-event="endOfLeave_today" id="endOfLeave_today">销假</button>
            <button type="button" class="layui-btn layui-btn-xs layui-bg-cyan" lay-event="endOfLeave_quickly_today" id="endOfLeave_quickly_today">快速销假</button>
            <button type="button" class="layui-btn layui-btn-xs layui-bg-cyan" lay-event="sendAlertSMS_today" id="sendAlertSMS_today">发送提醒短信</button>
        </script>

        <%--所有到假未到岗人员表格内部工具栏--%>
        <script type="text/html" id="toolbar_currentEOLInfo_all">
            <button type="button" class="layui-btn layui-btn-xs layui-bg-cyan" lay-event="endOfLeave" id="endOfLeave">销假</button>
            <button type="button" class="layui-btn layui-btn-xs layui-bg-cyan" lay-event="endOfLeave_quickly" id="endOfLeave_quickly">快速销假</button>
            <button type="button" class="layui-btn layui-btn-xs layui-bg-cyan" lay-event="sendAlertSMS" id="sendAlertSMS">发送提醒短信</button>
        </script>

        <script>

            layui.use(['table', 'upload', 'laydate',  'form', 'layer','common'], function () {
                var layer = layui.layer
                var $ = layui.jquery;
                var table = layui.table;
                var form = layui.form;
                var laydate = layui.laydate;
                var common = layui.common;

                layer.config({
                    skin: 'layui-layer-molv'
                })

                //初始化请假开始时间
                laydate.render({
                    elem: '#start_date'
                    ,range: ['#start_date_min', '#start_date_max']
                });

                //初始化预计到岗时间
                laydate.render({
                    elem: '#end_date_maybe'
                    ,range: ['#end_date_maybe_min', '#end_date_maybe_max']
                });

                table.render({
                    elem: '#currentEOLInfo_today'
                    ,url:'askForLeaveServlet?action=queryCurrentEOLPerson'
                    //,toolbar: '#toolbar' //开启头部工具栏，并为其绑定左侧模板
                    ,title: '待销假信息表'
                    ,request: {
                        pageName: 'curr' //页码的参数名称，默认：page
                        ,limitName: 'nums' //每页数据量的参数名，默认：limit
                    }
                    ,limit:10
                    ,limits:[5,10,15]
                    , cols: [[
                        {field: 'serialnumber', title: '流水号', unresize:true,align:'center',width: 80}
                        ,{field:'name', title:'姓名', align:'center',width:110}
                        ,{field:'office', title:'工作单位', align:'center',width:210}
                        ,{field:'post', title:'现任职务', align:'center',width:210}
                        ,{field:'phone', title:'联系电话', align:'center',width:130}
                        ,{field:'leave_type', title:'请假类型', align:'center',width:110}
                        ,{field:'start_date',title:'开始日期',align:'center',width:140}
                        ,{field:'leave_days_projected', title:'请假天数', align:'center',width:90}
                        ,{field:'end_date_maybe',title:'预计到岗日期',align:'center',width:140}
                        ,{field:'work_leader', title:'不在岗期间主持工作领导', align:'center',width:200}
                        ,{field:'leave_reason', title:'请假事由', align:'center',width:160}
                        ,{field:'approver', title:'批准人', align:'center',width:100}
                        ,{field:'depart_location', title:'出发地', align:'center',width:130}
                        ,{field:'arrive_location', title:'到达地', align:'center',width:130}
                        ,{field:'start_leave_remark', title:'请假备注', align:'center',width:190}
                        ,{field:'start_leave_operator', title:'请假操作者', align:'center',width:170}
                        ,{fixed: 'right', title:'操作', align:'center',toolbar: '#toolbar_currentEOLInfo_today',width: 250}
                    ]]
                    ,page : true
                   /* ,done : function(res, curr, count){
                        tableList=res.data;
                        $('th').css({'background-color': '#FF5722',color:"#FAFAFA"})
                    }*/
                    });

                table.on('tool(currentEOLInfo_today)', function (obj) {
                    var data = obj.data;

                    //给隐藏域赋值，方便子页面取值
                    var start_date = data.start_date;
                    $("#start_date").val(start_date);

                    if (obj.event === "endOfLeave_today") {
                        //获取当前页码
                        let currentPage = $(".layui-laypage-skip .layui-input").val();
                        //获取当前日期
                        let currentDate = jutils.formatDate(new Date(),"yyyy年MM月dd日");
                        //2.弹出提示框，提示填写销假备注，并核实销假日期是否正确
                        var msgIndex = layer.msg('请确认到岗日期【'+currentDate+"】是否正确？", {
                            time: 0 //不自动关闭
                            ,btn: ['正确', '错误']
                            ,yes: function(index){
                                //关闭上一层弹框
                                layer.close(index);
                                layer.prompt(
                                    {title:"请填写销假备注"
                                        ,formType: 2
                                        ,value: '正常到岗'
                                        ,btn:["提交","返回"]
                                    },
                                    function (val,index2) {
                                        // val 输入值；index2 该窗口索引
                                        /*向后台发起ajax请求*/
                                        $.ajax({
                                            type: "post",
                                            url: "askForLeaveServlet?action=resumeWork",
                                            dataType:"JSON",
                                            data: {
                                                serialnumber: data.serialnumber,
                                                end_leave_remark: val,
                                                end_date: currentDate,
                                                end_leave_operator:"${sessionScope.user.operator}"
                                            },
                                            success: function (result) {
                                                let code = result.data;
                                                if(code == 1){
                                                    //备份与删除成功，调用obj.del()方法删除此行数据，调用table.render（）刷新表格数据
                                                    obj.del();
                                                    layer.msg('销假成功', {icon: 1,time: 1000});
                                                    //重载表格
                                                    table.reload('resumeWorkInfo', {
                                                        url: 'askForLeaveServlet?action=queryALLResumeWorkInfo'
                                                        ,page: {
                                                            curr: currentPage//重新从第 1 页开始
                                                        }
                                                        ,request: {
                                                            pageName: 'curr' //页码的参数名称，默认：page
                                                            ,limitName: 'nums' //每页数据量的参数名，默认：limit
                                                        }
                                                    });

                                                }

                                            },
                                            //请求失败，包含具体的错误信息
                                            error: function (e) {
                                                layer.msg('业务提交失败'+e.status, {
                                                    icon : 5
                                                });
                                            }
                                        });
                                        layer.close(index2);
                                })
                            }
                            ,btn2:function (index) {
                                layer.close(index);
                                //弹窗选择正确数据
                                layer.open({
                                    type: 2,
                                    title: '请输入正确参数',
                                    maxmin: true, //开启最大化最小化按钮
                                    area:['500px', '500px'],
                                    content: "pages/service/endOfLeave/_endLeaveDate.jsp",
                                    anim:2,
                                    resize:false,
                                    id:'LAY_layuipro',
                                    btn:['提交','取消'],
                                    success: function (layero, index) {
                                        //提交按钮的回调
                                        var body = layer.getChildFrame('body', index);
                                        body.find("#serialnumber").val(data.serialnumber);
                                        body.find("#currentPage").val(currentPage);
                                    },
                                    yes:function (index, layero) {
                                        //提交按钮的回调
                                        var body = layer.getChildFrame('body', index);
                                        // 找到隐藏的提交按钮模拟点击提交
                                        body.find('#resumeWorkSubmit').click();
                                    },
                                    btn2: function (index, layero) {
                                        //return false;// 开启该代码可禁止点击该按钮关闭
                                    },
                                    cancel: function () {
                                        //右上角关闭回调
                                        //return false 开启该代码可禁止点击该按钮关闭
                                    }
                                });

                            }
                        });

                    }
                    else if(obj.event === "endOfLeave_quickly_today") {
                        //获取当前页码
                        let currentPage = $(".layui-laypage-skip .layui-input").val();
                        //获取当前日期
                        let currentDate = jutils.formatDate(new Date(),"yyyy年MM月dd日");
                        if(compareDateStrRTNBoolean(currentDate,start_date)){
                            $.ajax({
                                type: "post",
                                url: "askForLeaveServlet?action=resumeWork",
                                dataType:"JSON",
                                data: {
                                    serialnumber: data.serialnumber,
                                    end_leave_remark: "正常到岗",
                                    end_date: currentDate,
                                    end_leave_operator:"${sessionScope.user.operator}"
                                },
                                success: function (result) {
                                    let code = result.data;
                                    if(code == 1){
                                        //备份与删除成功，调用obj.del()方法删除此行数据，调用table.render（）刷新表格数据
                                        obj.del();
                                        layer.msg('销假成功', {icon: 1,time: 1000});
                                        //重载表格
                                        table.reload('resumeWorkInfo', {
                                            url: 'askForLeaveServlet?action=queryALLResumeWorkInfo'
                                            ,page: {
                                                curr: currentPage//重新从第 1 页开始
                                            }
                                            ,request: {
                                                pageName: 'curr' //页码的参数名称，默认：page
                                                ,limitName: 'nums' //每页数据量的参数名，默认：limit
                                            }
                                        });
                                    }
                                },
                                //请求失败，包含具体的错误信息
                                error: function (e) {
                                    layer.msg('业务提交失败'+e.status, {
                                        icon : 5
                                    });
                                }
                            });
                        }else {
                            layer.alert("当前日期小于起始日期，不能销假");
                        }
                    }
                    else if(obj.event === "sendAlertSMS_today") {
                        //将按钮设置为不可点击,1分钟后可点：短信发送频率不能低于1分钟1条
                        $(this).addClass("layui-btn-disabled").attr("disabled",true);
                        //弹出提示框
                        layer.load(1);
                        //发送提醒短信
                        $.ajax({
                            type: "post",
                            url: "askForLeaveServlet?action=sendAlertSMS",
                            dataType:"JSON",
                            data: {
                                serialnumber: data.serialnumber
                            },
                            success: function (result) {
                                layer.closeAll('loading');
                                layer.msg('发送成功', {icon: 1,time: 1000});
                            },
                            //请求失败，包含具体的错误信息
                            error: function (e) {
                                layer.msg('发送失败，请重试'+e.status, {
                                    icon : 5
                                });
                                layer.closeAll('loading');
                            }
                        });
                    }
                });

                table.render({
                    elem: '#currentEOLInfo_all'
                    ,url:'askForLeaveServlet?action=queryAllCurrentEOLPerson'
                    //,toolbar: '#toolbar' //开启头部工具栏，并为其绑定左侧模板
                    ,title: '待销假信息表'
                    ,request: {
                        pageName: 'curr' //页码的参数名称，默认：page
                        ,limitName: 'nums' //每页数据量的参数名，默认：limit
                    }
                    ,limit:10
                    ,limits:[5,10,15]
                    , cols: [[
                        {field: 'serialnumber', title: '流水号', unresize:true,align:'center',width: 80}
                        ,{field:'name', title:'姓名', align:'center',width:110}
                        ,{field:'office', title:'工作单位', align:'center',width:210}
                        ,{field:'post', title:'现任职务', align:'center',width:210}
                        ,{field:'phone', title:'联系电话', align:'center',width:130}
                        ,{field:'leave_type', title:'请假类型', align:'center',width:110}
                        ,{field:'start_date',title:'开始日期',align:'center',width:140}
                        ,{field:'leave_days_projected', title:'请假天数', align:'center',width:90}
                        ,{field:'end_date_maybe',title:'预计到岗日期',align:'center',width:140}
                        ,{field:'work_leader', title:'不在岗期间主持工作领导', align:'center',width:200}
                        ,{field:'leave_reason', title:'请假事由', align:'center',width:160}
                        ,{field:'approver', title:'批准人', align:'center',width:100}
                        ,{field:'depart_location', title:'出发地', align:'center',width:130}
                        ,{field:'arrive_location', title:'到达地', align:'center',width:130}
                        ,{field:'start_leave_remark', title:'请假备注', align:'center',width:190}
                        ,{field:'start_leave_operator', title:'请假操作者', align:'center',width:170}
                        ,{fixed: 'right', title:'操作', align:'center',toolbar: '#toolbar_currentEOLInfo_all',width: 250}
                    ]]
                    ,page : true
                    /* ,done : function(res, curr, count){
                         tableList=res.data;
                         $('th').css({'background-color': '#FF5722',color:"#FAFAFA"})
                     }*/
                });

                table.on('tool(currentEOLInfo_all)', function (obj) {
                    var data = obj.data;

                    //给隐藏域赋值，方便子页面取值
                    var start_date = data.start_date;
                    $("#start_date").val(start_date);

                    if (obj.event === "endOfLeave") {
                        //获取当前页码
                        let currentPage = $(".layui-laypage-skip .layui-input").val();
                        //获取当前日期
                        let currentDate = jutils.formatDate(new Date(),"yyyy年MM月dd日");
                        //2.弹出提示框，提示填写销假备注，并核实销假日期是否正确
                        var msgIndex = layer.msg('请确认到岗日期【'+currentDate+"】是否正确？", {
                            time: 0 //不自动关闭
                            ,btn: ['正确', '错误']
                            ,yes: function(index){
                                //关闭上一层弹框
                                layer.close(index);
                                layer.prompt(
                                    {title:"请填写销假备注"
                                        ,formType: 2
                                        ,value: '正常到岗'
                                        ,btn:["提交","返回"]
                                    },
                                    function (val,index2) {
                                        // val 输入值；index2 该窗口索引
                                        /*向后台发起ajax请求*/
                                        $.ajax({
                                            type: "post",
                                            url: "askForLeaveServlet?action=resumeWork",
                                            dataType:"JSON",
                                            data: {
                                                serialnumber: data.serialnumber,
                                                end_leave_remark: val,
                                                end_date: currentDate,
                                                end_leave_operator:"${sessionScope.user.operator}"
                                            },
                                            success: function (result) {
                                                let code = result.data;
                                                if(code == 1){
                                                    //备份与删除成功，调用obj.del()方法删除此行数据，调用table.render（）刷新表格数据
                                                    obj.del();
                                                    layer.msg('销假成功', {icon: 1,time: 1000});
                                                    //重载表格
                                                    table.reload('resumeWorkInfo', {
                                                        url: 'askForLeaveServlet?action=queryALLResumeWorkInfo'
                                                        ,page: {
                                                            curr: currentPage//重新从第 1 页开始
                                                        }
                                                        ,request: {
                                                            pageName: 'curr' //页码的参数名称，默认：page
                                                            ,limitName: 'nums' //每页数据量的参数名，默认：limit
                                                        }
                                                    });

                                                }

                                            },
                                            //请求失败，包含具体的错误信息
                                            error: function (e) {
                                                layer.msg('业务提交失败'+e.status, {
                                                    icon : 5
                                                });
                                            }
                                        });
                                        layer.close(index2);
                                    })
                            }
                            ,btn2:function (index) {
                                layer.close(index);
                                //弹窗选择正确数据
                                layer.open({
                                    type: 2,
                                    title: '请输入正确参数',
                                    maxmin: true, //开启最大化最小化按钮
                                    area:['500px', '500px'],
                                    content: "pages/service/endOfLeave/_endLeaveDate.jsp",
                                    anim:2,
                                    resize:false,
                                    id:'LAY_layuipro',
                                    btn:['提交','取消'],
                                    success: function (layero, index) {
                                        //提交按钮的回调
                                        var body = layer.getChildFrame('body', index);
                                        body.find("#serialnumber").val(data.serialnumber);
                                        body.find("#currentPage").val(currentPage);
                                    },
                                    yes:function (index, layero) {
                                        //提交按钮的回调
                                        var body = layer.getChildFrame('body', index);
                                        // 找到隐藏的提交按钮模拟点击提交
                                        body.find('#resumeWorkSubmit').click();
                                    },
                                    btn2: function (index, layero) {
                                        //return false;// 开启该代码可禁止点击该按钮关闭
                                    },
                                    cancel: function () {
                                        //右上角关闭回调
                                        //return false 开启该代码可禁止点击该按钮关闭
                                    }
                                });

                            }
                        });

                    }
                    else if(obj.event === "endOfLeave_quickly") {
                        //获取当前页码
                        let currentPage = $(".layui-laypage-skip .layui-input").val();
                        //获取当前日期
                        let currentDate = jutils.formatDate(new Date(),"yyyy年MM月dd日");
                        if(compareDateStrRTNBoolean(currentDate,start_date)){
                            $.ajax({
                                type: "post",
                                url: "askForLeaveServlet?action=resumeWork",
                                dataType:"JSON",
                                data: {
                                    serialnumber: data.serialnumber,
                                    end_leave_remark: "正常到岗",
                                    end_date: currentDate,
                                    end_leave_operator:"${sessionScope.user.operator}"
                                },
                                success: function (result) {
                                    let code = result.data;
                                    if(code == 1){
                                        //备份与删除成功，调用obj.del()方法删除此行数据，调用table.render（）刷新表格数据
                                        obj.del();
                                        layer.msg('销假成功', {icon: 1,time: 1000});
                                        //重载表格
                                        table.reload('resumeWorkInfo', {
                                            url: 'askForLeaveServlet?action=queryALLResumeWorkInfo'
                                            ,page: {
                                                curr: currentPage//重新从第 1 页开始
                                            }
                                            ,request: {
                                                pageName: 'curr' //页码的参数名称，默认：page
                                                ,limitName: 'nums' //每页数据量的参数名，默认：limit
                                            }
                                        });
                                    }
                                },
                                //请求失败，包含具体的错误信息
                                error: function (e) {
                                    layer.msg('业务提交失败'+e.status, {
                                        icon : 5
                                    });
                                }
                            });
                        }else {
                            layer.alert("当前日期小于起始日期，不能销假");
                        }
                    }
                    else if(obj.event === "sendAlertSMS") {
                        //将按钮设置为不可点击,1分钟后可点：短信发送频率不能低于1分钟1条
                        $(this).addClass("layui-btn-disabled").attr("disabled",true);
                        //弹出提示框
                        layer.load(1);
                        //发送提醒短信
                        $.ajax({
                            type: "post",
                            url: "askForLeaveServlet?action=sendAlertSMS",
                            dataType:"JSON",
                            data: {
                                serialnumber: data.serialnumber
                            },
                            success: function (result) {
                                layer.closeAll('loading');
                                layer.msg('发送成功', {icon: 1,time: 1000});
                            },
                            //请求失败，包含具体的错误信息
                            error: function (e) {
                                layer.msg('发送失败，请重试'+e.status, {
                                    icon : 5
                                });
                                layer.closeAll('loading');
                            }
                        });
                    }
                });

            });

        </script>

    </body>


</html>
