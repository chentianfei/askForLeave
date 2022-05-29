<%--
  Created by IntelliJ IDEA.
  User: tianfeichen
  Date: 2021/8/21
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--请假审批页面--%>
<head>
    <%--基础引入--%>
    <%@include file="/pages/common/baseinfo.jsp"%>

    <style>
        .layui-table th{
            font-size: 5px;
        }
    </style>

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
            <div class="layui-bg-gray" style="padding: 10px;">
                <div class="layui-row">
                    <div class="layui-col-md12">
                        <div class="layui-card">
                            <div class="layui-card-header layui-bg-blue">已请假信息查询</div>
                            <div class="layui-card-body">
                                <%--查询表单开始--%>
                                <form class="layui-form layui-form-pane">

                                    <div class="layui-form-item">
                                        <%--姓名--%>
                                        <div class="layui-inline">
                                            <label class="layui-form-label" style="width: 100px">姓名</label>
                                            <div class="layui-input-inline"  style="width: 150px">
                                                <input type="text" name="name"
                                                       id="name"
                                                       placeholder="请输入"
                                                       class="layui-input" autocomplete=“off”>
                                            </div>
                                        </div>
                                        <%--工作单位--%>
                                        <div class="layui-inline">
                                            <label class="layui-form-label" style="width: 100px">工作单位</label>
                                            <div class="layui-input-inline"  style="width: 150px" >
                                                <select id="office" name="office" lay-search>
                                                    <option value=""></option>
                                                </select>
                                            </div>
                                        </div>
                                        <%--职级--%>
                                        <div class="layui-inline">
                                            <label class="layui-form-label" style="width: 100px">职级</label>
                                            <div class="layui-input-inline"  style="width: 150px">
                                                <select id="level" name="level" lay-search >
                                                </select>
                                            </div>
                                        </div>
                                        <%--所在类区--%>
                                        <div class="layui-inline">
                                            <label class="layui-form-label" style="width:100px">所在类区</label>
                                            <div class="layui-input-inline" style="width:150px">
                                                <select name="area_class" id="area_class" lay-search >
                                                    <option value="">请选择</option>
                                                    <option value="二类区">二类区</option>
                                                    <option value="三类区">三类区</option>
                                                    <option value="四类区">四类区</option>
                                                </select>
                                            </div>
                                        </div>
                                        <%--联系方式--%>
                                        <div class="layui-inline">
                                            <label class="layui-form-label" style="width: 100px">联系电话</label>
                                            <div class="layui-input-inline"  style="width: 150px">
                                                <input type="tel" name="phone" id="phone"
                                                       placeholder="请输入" autocomplete="off" class="layui-input">
                                            </div>
                                        </div>

                                    </div>

                                    <div class="layui-form-item">
                                        <%--本人籍贯--%>
                                        <div class="layui-inline">
                                            <label class="layui-form-label" style="width: 100px">本人籍贯</label>
                                            <div class="layui-input-inline" style="width: 135px">
                                                <select name="province" <%--data-area="西藏自治区"--%> lay-filter="province">
                                                    <option value="">选择省</option>
                                                </select>
                                            </div>
                                            <div class="layui-input-inline" style="width: 135px">
                                                <select name="city" <%--data-area="日喀则市"--%> lay-filter="city">
                                                    <option value="">选择市</option>
                                                </select>
                                            </div>
                                            <div class="layui-input-inline" style="width: 135px">
                                                <select name="district" <%--data-area="仲巴县" --%>lay-filter="district">
                                                    <option value="">选择区</option>
                                                </select>
                                            </div>
                                        </div>

                                        <%--出发地--%>
                                        <div class="layui-inline">
                                            <label class="layui-form-label" style="width: 100px">出发地</label>
                                            <div class="layui-input-inline" style="width: 150px">
                                                <input type="text" name="depart_location"
                                                       id="depart_location"
                                                       placeholder=""
                                                       autocomplete="off"
                                                       class="layui-input">
                                            </div>
                                        </div>

                                        <%--到达地--%>
                                        <div class="layui-inline">
                                            <label class="layui-form-label" style="width: 100px">到达地</label>
                                            <div class="layui-input-inline" style="width: 150px">
                                                <input type="text" name="arrive_location"
                                                       id="arrive_location"
                                                       placeholder=""
                                                       autocomplete="off"
                                                       class="layui-input">
                                            </div>
                                        </div>

                                        <%--批准人--%>
                                        <div class="layui-inline">
                                            <label class="layui-form-label"
                                                   style="width: 100px">批准人</label>
                                            <div class="layui-input-inline" style="width: 150px">
                                                <input type="text" name="approver"
                                                       autocomplete="off"
                                                       id="approver" placeholder=""
                                                       class="layui-input">
                                            </div>
                                        </div>

                                    </div>

                                    <div class="layui-form-item">

                                        <%--请假种类--%>
                                        <div class="layui-inline">
                                            <label class="layui-form-label">请假种类</label>
                                            <div class="layui-input-block" id="leave_type" ></div>
                                        </div>

                                    </div>

                                    <%--带范围的选择区域--%>
                                    <div class="layui-form-item">

                                        <%--开始时间 start_date--%>
                                        <div class="layui-inline">
                                            <label class="layui-form-label"
                                                   style="width: 145px">请假开始时间</label>
                                            <div class="layui-inline"  id="start_date">
                                                <div class="layui-input-inline" style="width: 170px">
                                                    <input type="text" autocomplete="off" id="start_date_min"
                                                           name="start_date_min"
                                                           class="layui-input" placeholder="开始日期">
                                                </div>
                                                <div class="layui-form-mid">至</div>
                                                <div class="layui-input-inline" style="width: 170px">
                                                    <input type="text" autocomplete="off" id="start_date_max"
                                                           name="start_date_max"
                                                           class="layui-input" placeholder="结束日期">
                                                </div>
                                            </div>
                                        </div>

                                        <%--预计到岗时间 end_date_maybe--%>
                                        <div class="layui-inline">
                                            <label class="layui-form-label"
                                                   style="width: 145px">预计到岗时间</label>
                                            <div class="layui-inline"  id="end_date_maybe">
                                                <div class="layui-input-inline" style="width: 170px">
                                                    <input type="text" autocomplete="off" id="end_date_maybe_min"
                                                           name="end_date_maybe_min"
                                                           class="layui-input" placeholder="开始日期">
                                                </div>
                                                <div class="layui-form-mid">至</div>
                                                <div class="layui-input-inline" style="width: 170px">
                                                    <input type="text" autocomplete="off" id="end_date_maybe_max"
                                                           name="end_date_maybe_max"
                                                           class="layui-input" placeholder="结束日期">
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                                    <div class="layui-form-item" style="padding-left: 70%">
                                        <div class="layui-input-block">
                                            <button type="submit" class="layui-btn" lay-submit
                                                    lay-filter="leave_info_query">查询</button>
                                            <button type="reset" class="layui-btn layui-btn-normal" >重置</button>
                                        </div>
                                    </div>

                                </form>
                                <%--查询表单结束--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%--信息管理--%>
        <div class="layui-row">
            <div class="layui-col-md12">
                <div class="layui-bg-gray" style="padding: 10px;">
                    <div class="layui-row">
                        <div class="layui-col-md12">
                            <div class="layui-card">
                                <div class="layui-card-header layui-bg-blue">已请假信息</div>
                                <div class="layui-card-body">

                                    <%--数据展示--%>
                                    <table class="layui-hide" id="approval" lay-filter="approval"></table>

                                </div>
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

<%--表格上方工具栏--%>
<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="batchDelete" id="batchDelete">批量删除</button>
        <button class="layui-btn layui-btn-primary layui-border-green layui-btn-sm" lay-event="export" id="export" >导出当前查询数据</button>
    </div>
</script>

<%--表格内部工具栏--%>
<script type="text/html" id="audit">
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看详情</a>
    <a class="layui-btn layui-btn-xs" lay-event="update">修改请假数据</a>
    <a class="layui-btn layui-btn-xs" lay-event="delete">删除</a>
</script>

<script>
    layui.use(['table','upload','laydate','element','common'
        ,'form', 'layer', 'util','laytpl'], function(){
        var element = layui.element;
        var common = layui.common;
        var layer = layui.layer;
        var util = layui.util;
        var $ = layui.jquery;
        var table = layui.table;
        var form = layui.form;
        var laydate = layui.laydate;
        var laytpl = layui.laytpl;
        var active = {
            getCheckData: function(){ //获取选中数据
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }
            ,getCheckLength: function(){ //获取选中数目
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
            }
            ,isAll: function(){ //验证是否全选
                var checkStatus = table.checkStatus('idTest');
                layer.msg(checkStatus.isAll ? '全选': '未全选')
            }
        };

        bindLevelSelectData();
        bindNationSelectData();
        bindOfficeSelectData();
        //初始化请假种类下拉框
        //bindLeaveTypeSelectData();
        // 初始化请假种类复选框数据
        bindLeaveTypeCheckboxData();

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

        //定义导出报表的数据
        let exportData = {};

        var approvalInfo_query = table.render({
            elem: '#approval'
            ,url:'askForLeaveServlet?action=queryAllLeaveInfo'
            ,toolbar: '#toolbar'
            ,defaultToolbar: []
            ,title: '请假信息审核数据表'+new Date().getTime()
            ,request: {
                pageName: 'curr' //页码的参数名称，默认：page
                ,limitName: 'nums' //每页数据量的参数名，默认：limit
            }
            ,limit:5
            ,limits:[5,10,15]
            ,cols: [[
                {type:'checkbox'}
                ,{field: 'serialnumber', title: '流水号', unresize:true,align:'center',width: 110}
                ,{field:'name', title:'姓名', align:'center',width:110}
                ,{field:'office', title:'工作单位', align:'center',width:210}
                ,{field:'post', title:'现任职务', align:'center',width:210}
                ,{field:'phone', title:'联系电话', align:'center',width:130}
                ,{field:'leave_type', title:'请假类型', align:'center',width:110}
                ,{field:'start_date',title:'开始日期',align:"center",width:140}
                ,{field:'leave_days_projected', title:'请假天数', align:'center',width:90}
                ,{field:'work_leader', title:'不在岗期间主持工作领导', align:'center',width:200}
                ,{field:'leave_reason', title:'请假事由', align:'center',width:160}
                ,{field:'approver', title:'批准人', align:'center',width:100}
                ,{field:'depart_location', title:'出发地', align:'center',width:130}
                ,{field:'arrive_location', title:'到达地', align:'center',width:130}
                ,{field:'end_date_maybe',title:'预计到岗日期',align:'center',width:140}
                ,{field:'start_leave_remark', title:'请假备注', align:'center',width:190}
                ,{field:'start_leave_operator', title:'请假操作者', align:'center',width:170}
                ,{fixed: 'right', title:'操作', align:'center', toolbar: '#audit',width: 250}
            ]]
            ,page:true
            ,parseData: function(res) { //res 即为原始返回的数据
                //将本次查询的数据赋值给导出数据指定的变量
                exportData = res.count;
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.count.length, //解析数据长度
                    "data": res.data //解析数据列表
                };
            }
        });

        //行工具栏事件
        table.on('tool(approval)', function(obj){
            var data = obj.data;
            //获取当前页码
            var currentPage = $(".layui-laypage-skip .layui-input").val();
            if(obj.event === "detail"){
                //发起查询人员详细基本信息的ajax请求
                $.ajax({
                    type: "post",
                    url: "askForLeaveServlet?action=queryALeaveInfoForPrintBySerialnumber",
                    data:{
                        serialnumber : data.serialnumber
                    },
                    dataType: 'json',
                    success: function (result) {
                        let sourceData = result;
                        //result包含的person数量
                        layer.open({
                            type: 2,
                            title: '请假详细信息',
                            maxmin: true,
                            area: ['600px', '600px'],
                            content: "pages/service/approvalLeave/_detail.jsp",
                            anim:2,
                            resize:false,
                            btn:['返回'],
                            success: function(layero, index){
                                var body = layer.getChildFrame('body', index);
                                body.find('#person_id').val(sourceData.person_id);
                                body.find('#name').val(sourceData.name);
                                body.find('#sex').val(sourceData.sex);
                                body.find('#birthDate').val(sourceData.birthDate);
                                body.find('#nation').val(sourceData.nation);
                                body.find('#nativePlace').val(sourceData.nativePlace);
                                body.find('#phone').val(sourceData.phone);
                                body.find('#office').val(sourceData.office);
                                body.find('#post').val(sourceData.post);
                                body.find('#level').val(sourceData.level);
                                body.find('#area_class').val(sourceData.area_class);

                                body.find('#marriage_status').val(sourceData.marriage_status);
                                body.find('#name_spouse').val(sourceData.name_spouse);
                                body.find('#nativeplace_spouse').val(sourceData.nativeplace_spouse);

                                body.find('#allow_Leave_Days').val(sourceData.allow_Leave_Days);
                                body.find('#leave_type').val(sourceData.leave_type);
                                body.find('#start_date').val(sourceData.start_date);
                                body.find('#leave_days_projected').val(sourceData.leave_days_projected);
                                body.find('#work_leader').val(sourceData.work_leader);
                                body.find('#leave_reason').val(sourceData.leave_reason);
                                body.find('#approver').val(sourceData.approver);
                                body.find('#depart_location').val(sourceData.depart_location);
                                body.find('#arrive_location').val(sourceData.arrive_location);
                                body.find('#end_date_maybe').val(sourceData.end_date_maybe);
                                body.find('#start_leave_remark').val(sourceData.start_leave_remark);
                                body.find('#start_leave_operator').val(sourceData.start_leave_operator);
                            },
                            yes:function (index, layero) {
                                //关闭该弹窗
                                layer.close(index);
                                // return false;
                            },
                            cancel: function (index, layero) {
                                //右上角关闭回调
                                //关闭该弹窗
                                layer.close(index);
                                //return false 开启该代码可禁止点击该按钮关闭
                            }
                        });
                    },
                    //请求失败，包含具体的错误信息
                    error: function (e) {
                        // 异常提示
                        parent.layer.msg('网络故障', {
                            icon : 5
                        });
                    }
                });
            }
            else if(obj.event === "update"){
                var updateLeaveInfoLayer = layer.open({
                    type: 2,
                    title: '修改请假数据',
                    maxmin: true, //开启最大化最小化按钮
                    area: ['600px', '600px'],
                    content: "pages/service/approvalLeave/_updateLeaveInfoLayer.jsp",
                    anim:2,
                    id:'LAY_layuipro',
                    resize:false,
                    btn:['修改','取消'],
                    success: function (layero, index) {
                        var body = layer.getChildFrame('body', index);
                        //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                        var iframeWin = window[layero.find('iframe')[0]['name']];
                        //初始化表单数据的值
                        body.find("#serialnumber").val(data.serialnumber);
                        body.find("#nameInput").val(data.name);
                        body.find("#office").val(data.office);
                        body.find("#post").val(data.post);
                        body.find("#phone").val(data.phone);
                        body.find("#start_date").val(replaceYMDChinese(data.start_date));
                        body.find("#leave_days_projected").val(data.leave_days_projected);
                        body.find("#work_leader").val(data.work_leader);
                        body.find("#leave_reason").val(data.leave_reason);
                        body.find("#approver").val(data.approver);
                        body.find("#depart_location").val(data.depart_location);
                        body.find("#arrive_location").val(data.arrive_location);
                        body.find("#start_leave_remark").val(data.start_leave_remark);

                        //通过ajax为弹框页面职级下拉框拉取当前页面数据并绑定数据库中其他数据
                        $.ajax({
                            url: 'systemDataServlet?action=queryLeaveType',
                            dataType: 'json',
                            type: 'post',
                            success: function (result) {
                                var sourceData = result.data;
                                if (sourceData !== null) {
                                    $.each(sourceData, function (index, item) {
                                        if (data.leave_type == item.leave_type) {
                                            body.find("#leave_type").append(
                                                "<option selected>"+data.leave_type+"</option>>"
                                            );
                                        } else {
                                            body.find("#leave_type").append($("<option>").attr("value", item.leave_type).text(item.leave_type));
                                        }
                                    });
                                } else {
                                    $("#leave_type").append(new Option("暂无数据", ""));
                                }
                                //重新渲染，特别重要，不然写的不起作用
                                iframeWin.layui.form.render("select");
                            }
                        });

                    },
                    yes:function (index, layero) {
                        //更新按钮的回调
                        var body = layer.getChildFrame('body', index);
                        // 找到隐藏的提交按钮模拟点击提交
                        body.find('#updateLeaveInfoSubmit').click();
                        //return false 开启该代码可禁止点击该按钮关闭
                    },
                    btn2: function (index, layero) {
                        //取消按钮的回调
                        layer.close(updateLeaveInfoLayer);
                        //return false 开启该代码可禁止点击该按钮关闭
                    },
                    cancel: function () {
                        layer.close(updateLeaveInfoLayer);
                        //右上角关闭回调
                        //return false 开启该代码可禁止点击该按钮关闭
                    }
                });
            }
            else if(obj.event === "delete"){
                layer.prompt(
                    {title:"请填写删除原因"
                        ,formType: 2
                        ,btn:["您的行为将会写入日志，确认删除吗？","取消"]
                    },
                    function (val,index2) {
                        // val 输入值；index2 该窗口索引
                        /*ajax开始*/
                        $.ajax({
                            type: "post",
                            url: "askForLeaveServlet?action=deleteALeaveInfoBySerialnumber",
                            data:{
                                serialnumber: data.serialnumber,
                                delete_reason: val,
                                delete_operator:"${sessionScope.user.operator}"
                            },
                            dataType: 'json',
                            success: function (result) {
                                if(result.code == 1){
                                    layer.msg('删除成功', {
                                        icon : 6
                                    });
                                    //重载表格
                                    table.reload('approval', {
                                        url: 'askForLeaveServlet?action=queryAllLeaveInfo'
                                        ,page: {
                                            curr: currentPage//重新从第 1 页开始
                                        }
                                        ,request: {
                                            pageName: 'curr' //页码的参数名称，默认：page
                                            ,limitName: 'nums' //每页数据量的参数名，默认：limit
                                        }
                                    });
                                }else {
                                    layer.msg('业务提交失败:'+result.message, {
                                        icon : 5
                                    });
                                }
                            },
                            error: function (e) {
                                // 异常提示
                                layer.msg('网络故障'+e.status, {
                                    icon : 5
                                });
                            }
                        });
                        /*ajax结束*/
                        layer.close(index2);
                    }
                    )
            }
        });

        //头工具栏事件
        table.on('toolbar(approval)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            //获取当前页码
            var currentPage = $(".layui-laypage-skip .layui-input").val();
            switch(obj.event){
                case 'batchDelete':
                    var data = checkStatus.data;
                    var serialnumber_set = new Array();
                    $.each(data,function (index,element) {
                        var serialnumber = element.serialnumber;
                        serialnumber_set.push(serialnumber);
                    })
                    layer.prompt(
                        {title:"请填写批量删除原因"
                            ,formType: 2
                            ,btn:["您的行为将会写入日志，确认删除吗？","取消"]
                        },
                        function (val,index2) {
                            // val 输入值；index2 该窗口索引
                            /*ajax开始*/
                            $.ajax({
                                type: "post",
                                url: "askForLeaveServlet?action=batchDeleteALeaveInfoBySerialnumber",
                                data:{
                                    serialnumber_set: JSON.stringify(serialnumber_set),
                                    delete_reason: val,
                                    delete_operator:"${sessionScope.user.operator}"
                                },
                                dataType: 'json',
                                success: function (result) {
                                    layer.msg('完成，'+result.message, {
                                        icon : 6
                                    });
                                    //重载表格
                                    table.reload('approval', {
                                        url: 'askForLeaveServlet?action=queryAllLeaveInfo'
                                        ,page: {
                                            curr: currentPage//重新从第 1 页开始
                                        }
                                        ,request: {
                                            pageName: 'curr' //页码的参数名称，默认：page
                                            ,limitName: 'nums' //每页数据量的参数名，默认：limit
                                        }
                                    });
                                },
                                error: function (e) {
                                    // 异常提示
                                    layer.msg('网络故障'+e.status, {
                                        icon : 5
                                    });
                                }
                            });
                            /*ajax结束*/
                            layer.close(index2);
                        }
                    )
                    break;
                //导出数据
                case 'export':
                    table.exportFile(approvalInfo_query.config.id, exportData, 'xls');
                    break;
            };
        });

        //监听查询区域的提交按钮事件
        form.on('submit(leave_info_query)',function (data) {
            //获取数据
            const sourceData = data.field;
            console.log(sourceData);
            //人员基本信息
            const name = sourceData.name;
            const level = sourceData.level;
            const office = sourceData.office;
            const phone = sourceData.phone;
            const area_class = sourceData.area_class;
            //解析解析框中的地址内容
            const city = sourceData.city;
            const district = sourceData.district;
            const province = sourceData.province;
            // 通过地址code码获取地址名称
            var address = common.getCity({
                province,
                city,
                district
            });
            let provinceName = address.provinceName;
            let cityName = address.cityName;
            let districtName = address.districtName;
            //解析解析框中的地址内容
            const nativePlace = provinceName + ' ' + cityName + ' ' + districtName;

            //请假信息
            const depart_location = sourceData.depart_location;
            const arrive_location = sourceData.arrive_location;
            const approver = sourceData.approver;
            //const leave_type = sourceData.leave_type;

            //处理请假类型复选框数据并封装
            var leave_typeSource = [];
            $("#leave_type>div.layui-form-checked").each(function(index,ele){
                leave_typeSource.push($(ele).find("span").html());
            })

            const end_date_maybe_max = sourceData.end_date_maybe_max;
            const end_date_maybe_min = sourceData.end_date_maybe_min;
            const start_date_max = sourceData.start_date_max;
            const start_date_min = sourceData.start_date_min;

            //使用layui直接重载表格（兼有数据查询）：
            table.reload('approval', {
                url: 'askForLeaveServlet?action=querySomeLeaveInfos'
                ,where: {
                    //设定异步数据接口的额外参数
                    //人员信息
                    name : name,
                    nativePlace:nativePlace,
                    office : office,
                    area_class : area_class,
                    level : level,
                    phone : phone,
                    //请假信息
                    depart_location : depart_location,
                    arrive_location : arrive_location,
                    approver : approver,
                    leave_type : leave_typeSource.toLocaleString(),
                    end_date_maybe_max : end_date_maybe_max,
                    end_date_maybe_min : end_date_maybe_min,
                    start_date_max : start_date_max,
                    start_date_min : start_date_min
                }
                ,page:true
                ,request: {
                    pageName: 'curr' //页码的参数名称，默认：page
                    ,limitName: 'nums' //每页数据量的参数名，默认：limit
                }
                ,page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,parseData: function(res) { //res 即为原始返回的数据
                    //将本次查询的数据赋值给导出数据指定的变量
                    exportData = res.count;
                    return {
                        "code": res.code, //解析接口状态
                        "msg": res.msg, //解析提示文本
                        "count": res.count.length, //解析数据长度
                        "data": res.data //解析数据列表
                    };
                }
            });
            return false;
        });

    });
</script>

</body>

</html>
