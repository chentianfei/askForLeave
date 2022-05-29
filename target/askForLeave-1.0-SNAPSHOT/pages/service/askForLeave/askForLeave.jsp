<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%--请假操作页面--%>
    <head>
        <%--基础引入--%>
        <%@include file="/pages/common/baseinfo.jsp" %>

    </head>
    <body>
        <div class="layui-layout layui-layout-admin">
            <%--头部信息--%>
            <%@include file="/pages/common/header.jsp" %>

            <%--左侧导航--%>
            <%@include file="/pages/common/menu.jsp" %>

            <%--主体信息--%>
            <div class="layui-body layui-bg-gray" style="padding: 10px">
                <div class="layui-fluid">
                    <div class="layui-row">

                        <div class="layui-col-md7">
                            <div class="layui-bg-gray" style="padding: 20px 10px;">
                                <div class="layui-row">
                                    <div class="layui-col-md12">
                                        <div class="layui-card">
                                            <div class="layui-card-header  layui-bg-blue">请假信息登记</div>
                                            <div class="layui-card-body">

                                                <form class="layui-form layui-form-pane">

                                                    <div class="layui-form-item">
                                                        <div class="layui-inline">
                                                            <label class="layui-form-label" style="width: 100px">姓名</label>
                                                            <div class="layui-input-inline" style="width: 300px">
                                                                <input type="text" name="nameInput" placeholder=""
                                                                       autocomplete="off" lay-verify="required"
                                                                       class="layui-input" id="nameInput">
                                                            </div>
                                                        </div>

                                                        <div class="layui-inline">
                                                            <div class="layui-input-inline" style="width:350px">
                                                                <select name="person_info" id="person_info"
                                                                        lay-filter="person_info"
                                                                        lay-verify="required">
                                                                    <option value="">请先输入请假者姓名，敲回车查询信息</option>
                                                                </select>
                                                            </div>
                                                        </div>

                                                        <div class="layui-inline" id="showPersonDetailDiv"> </div>

                                                    </div>


                                                    <div class="layui-form-item">
                                                        <label class="layui-form-label"  style="width: 100px">请假种类</label>
                                                        <div class="layui-input-inline" style="width: 400px">
                                                            <select id="leave_type" name="leave_type" lay-verify="required" lay-search="">
                                                                <option value=""></option>
                                                            </select>
                                                        </div>
                                                    </div>


                                                    <div class="layui-form-item">
                                                        <label class="layui-form-label"
                                                               style="width: 100px;">开始日期</label>
                                                        <div class="layui-input-inline" style="width: 400px">
                                                            <input type="text" class="layui-input"
                                                                   autocomplete="off" lay-verify="required"
                                                                   name="start_date"
                                                                   id="start_date">
                                                        </div>
                                                    </div>

                                                    <div class="layui-form-item">
                                                            <label class="layui-form-label"
                                                                   style="width: 100px">请假天数</label>
                                                            <div class="layui-input-inline" style="width: 400px">
                                                                <input type="text" name="leave_days_projected"
                                                                       id="leave_days_projected"
                                                                       lay-verify="number|required|integer"
                                                                       autocomplete="off"
                                                                       class="layui-input">
                                                            </div>
                                                    </div>


                                                    <div class="layui-form-item">
                                                        <label class="layui-form-label"
                                                               style="width: 200px">不在岗期间主持工作领导</label>
                                                        <div class="layui-input-inline" style="width: 300px">
                                                            <input type="text" name="work_leader"
                                                                   autocomplete="off" lay-verify="required"
                                                                   id="work_leader" placeholder=""
                                                                   class="layui-input">
                                                        </div>
                                                    </div>

                                                    <div class="layui-form-item">
                                                        <label class="layui-form-label"
                                                               style="width: 100px">请假事由</label>
                                                        <div class="layui-input-inline" style="width: 400px">
                                                            <input type="text" name="leave_reason"
                                                                   autocomplete="off" lay-verify="required"
                                                                   id="leave_reason" placeholder=""
                                                                   class="layui-input">
                                                        </div>
                                                    </div>

                                                    <div class="layui-form-item">
                                                        <label class="layui-form-label"
                                                               style="width: 100px">批准人</label>
                                                        <div class="layui-input-inline" style="width: 400px">
                                                            <input type="text" name="approver"
                                                                   autocomplete="off" lay-verify="required"
                                                                   id="approver" placeholder=""
                                                                   class="layui-input">
                                                        </div>
                                                    </div>

                                                    <div class="layui-form-item">
                                                        <label class="layui-form-label" style="width: 100px">出发地</label>
                                                        <div class="layui-input-inline" style="width: 400px">
                                                            <input type="text" name="depart_location" value="未知"
                                                                   autocomplete="off" lay-verify="required"
                                                                   id="depart_location" placeholder=""
                                                                   class="layui-input">
                                                        </div>
                                                    </div>

                                                    <div class="layui-form-item">
                                                        <label class="layui-form-label"
                                                               style="width: 100px">到达地</label>
                                                        <div class="layui-input-inline" style="width: 400px">
                                                            <input type="text" name="arrive_location" value="未知"
                                                                   autocomplete="off" lay-verify="required"
                                                                   id="arrive_location" placeholder=""
                                                                   class="layui-input">
                                                        </div>
                                                    </div>

                                                    <div class="layui-form-item">
                                                        <label class="layui-form-label" style="width: 100px">备注</label>
                                                        <div class="layui-input-inline" style="width: 400px">
                                                            <textarea placeholder="" name="start_leave_remark"
                                                                      id="start_leave_remark"
                                                                      autocomplete="off"
                                                                      class="layui-textarea"></textarea>
                                                        </div>
                                                    </div>

                                                    <div class="layui-form-item" style="padding-left: 60%">
                                                        <div class="layui-input-block">
                                                            <button type="submit" class="layui-btn" lay-submit id="addALeaveInfoSubmit"
                                                                    lay-filter="submit">提交
                                                            </button>
                                                            <button type="reset" id="resetBtn" class="layui-btn layui-btn-normal" >
                                                                重置
                                                            </button>

                                                        </div>
                                                    </div>

                                                </form>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="layui-col-md5">

                            <%--本年度请假信息统计表格--%>
                            <div class="layui-row">
                                <div class="layui-bg-gray"  style="padding:20px 10px;">
                                    <div class="layui-row">
                                        <div class="layui-col-md12">
                                            <div class="layui-card">
                                                <div class="layui-card-header  layui-bg-blue">本年度请假信息统计</div>
                                                <div class="layui-card-body">
                                                    <table class="layui-hide" lay-filter="LeaveInfoCount"
                                                           id="LeaveInfoCount"></table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <fieldset class="layui-elem-field layui-field-title"></fieldset>

                            <%--本年度请假信息详情表格--%>
                            <div class="layui-row">
                                <div class="layui-bg-gray" >
                                    <div class="layui-row">
                                        <div class="layui-col-md12">
                                            <div class="layui-card">
                                                <div class="layui-card-header layui-bg-blue">本年度请假信息详情</div>
                                                <div class="layui-card-body">
                                                    <table class="layui-hide" id="LeaveInfoDetail"></table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <fieldset class="layui-elem-field layui-field-title"></fieldset>

                            <%--本单位当前请假情况--%>
                            <div class="layui-row">
                                    <div class="layui-bg-gray" >
                                        <div class="layui-row">
                                            <div class="layui-col-md12">
                                                <div class="layui-card">
                                                    <div class="layui-card-header layui-bg-blue">本单位当前请假情况</div>
                                                    <div class="layui-card-body">
                                                        <table class="layui-hide" id="currentLeaveInfoOfThisOffice"></table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                        </div>


                    </div>

                </div>
            </div>

            <%--接受person_id的值，获取需要向父页面赋值的元素名称--%>
            <input  type="hidden" class="layui-input"
                    name="person_id"
                    style="display:none"
                    id="person_id"  />

            <%--表格上方工具栏--%>
            <script type="text/html" id="toolbar">
                <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-primary layui-border-green layui-btn-sm"
                            lay-event="showCurrentLeaveInfoOfThisOffice"
                            id="showCurrentLeaveInfoOfThisOffice" onclick="openCurrentLeaveInfoOfThisOfficeLayer()" >查看本单位请假详情</button>
                </div>
            </script>

            <script id="showPersonDetailScript" type="text/html">
                {{#  if(d.person_id_value == ""){ }}
                {{#  } }}
                {{#  if(d.person_id_value == null){ }}
                {{#  } }}
                {{#  if(d.person_id_value != ""){ }}
                <button type="button"
                        id="showPersonDetail"
                        onclick="openPersonDetailPage(person_id_value)"
                        class="layui-btn layui-btn-primary layui-border-blue">
                    查看详情
                </button>
                {{#  } }}
            </script>

                <%--底部信息--%>
            <div class="layui-footer">
                <%@include file="/pages/common/footer.jsp" %>
            </div>

        </div>

        <script>
            //全局使用的人员编号
            var person_id_value;
            var office;
            var phone;
            var operatorId = "${sessionScope.user.id}";

            //全局取消回车默认事件
            document.onkeydown = function (e) {
                if (e.keyCode == 13) {
                    e.preventDefault();//禁用回车的默认事件
                }
            }

            function openPersonDetailPage(person_id_value){
                $.ajax({
                    type: "post",
                    url: "personServlet?action=queryPersonInfoById",
                    data:{
                        person_id : person_id_value
                    },
                    dataType: 'json',
                    success: function (result) {
                        let peopleBaseInfo = result.peopleBase;
                        let start_work_date = result.start_work_date;
                        let birthDate = result.birthDate;
                        //result包含的person数量
                        layer.open({
                            type: 2,
                            title: '请假者具体信息',
                            maxmin: true,
                            area: ['600px', '600px'],
                            content: "pages/service/askForLeave/_personInfoDetail.jsp",
                            anim:2,
                            resize:false,
                            btn:['返回'],
                            success: function(layero, index){
                                var body = layer.getChildFrame('body', index);
                                body.find('#person_id').val(peopleBaseInfo.person_id);
                                body.find('#name').val(peopleBaseInfo.name);
                                body.find('#sex').val(peopleBaseInfo.sex);
                                body.find('#birthDate').val(birthDate);
                                body.find('#nation').val(peopleBaseInfo.nation);
                                body.find('#nativePlace').val(peopleBaseInfo.nativePlace);
                                body.find('#phone').val(peopleBaseInfo.phone);
                                body.find('#office').val(peopleBaseInfo.office);
                                body.find('#post').val(peopleBaseInfo.post);
                                body.find('#level').val(peopleBaseInfo.level);
                                body.find('#area_class').val(peopleBaseInfo.area_class);
                                body.find('#allow_Leave_Days').val(peopleBaseInfo.allow_Leave_Days);
                                body.find('#marriage_status').val(peopleBaseInfo.marriage_status);
                                body.find('#name_spouse').val(peopleBaseInfo.name_spouse);
                                body.find('#nativeplace_spouse').val(peopleBaseInfo.nativeplace_spouse);
                                body.find('#start_work_date').val(start_work_date);
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

            function openCurrentLeaveInfoOfThisOfficeLayer(){
                var showCurrentLeaveInfoOfThisOfficeLayer = layer.open({
                    type: 2,
                    title: '查看本单位当前请假情况',
                    maxmin: true, //开启最大化最小化按钮
                    area: ['1300px', '600px'],
                    content: "pages/service/askForLeave/_showCurrentLeaveInfoOfThisOffice.jsp",
                    anim:2,
                    id:'LAY_layuipro',
                    resize:false,
                    btn:['返回'],
                    yes:function (index, layero) {
                        layer.close(index);
                    },
                    cancel: function () {
                        layer.close(showCurrentLeaveInfoOfThisOfficeLayer);
                        //右上角关闭回调
                        //return false 开启该代码可禁止点击该按钮关闭
                    }
                });
            }

            layui.use(['laytpl','laydate', 'element', 'layer', 'util', 'table',"form"], function () {
                var element = layui.element;
                var layer = layui.layer;
                var util = layui.util;
                var $ = layui.$;
                var table = layui.table;
                var form = layui.form;
                //日期选择框
                var laydate = layui.laydate;
                var laytpl=layui.laytpl;

                //定义变量用于补充表单提交时时无法自动获取的参数
                var person_name;//人员姓名
                var startDate;//开始日期

                //laytpl模板内容
                var getTemplate = $('#showPersonDetailScript').html();

                //初始化日期框，并获取选中的值
                laydate.render({
                    elem: '#start_date' //指定元素
                    ,done: function(value, date){
                        startDate = value;
                    }
                });

                //数字验证
                form.verify({
                    integer: [
                        /^[1-9]\d*$/
                        , '只能输入正整数'
                    ]
                });

                //请假者名字输入框完成输入后敲回车后发起数据查询——开始 person_info nameInput
                $("#nameInput").keydown(function (e) {
                    switch (e.keyCode) {
                        case 13:
                            $.ajax({
                                url: 'personServlet?action=queryPersonInfoByName',
                                dataType: 'json',
                                data:{
                                    person_name : $("#nameInput").val()
                                },
                                type: 'post',
                                success: function (result) {
                                    if (result.data.length > 0) {
                                        $("#person_info").empty();
                                        $("#person_info").append("<option value=''>已查询出人员信息，请选择</option>");
                                        $.each(result, function (key, value) {
                                            if(key == "data"){
                                                $.each(result.data,function (infoIndex,personInfo) {
                                                    var person = personInfo.name+":"
                                                        +personInfo.office+"-"
                                                        +personInfo.post+"-"
                                                        +personInfo.phone;
                                                    var person_id_result = personInfo.person_id;
                                                    $('#person_info').append(new Option(person,person_id_result));
                                                })
                                            }
                                        });
                                    } else {
                                        $("#person_info").empty();
                                        $("#person_info").append("<option value=''>无该人员信息，请核实输入是否正确</option>");
                                        //将laytpl的person_id置空，使得按钮消失
                                        var data = {
                                            person_id_value : ""
                                        }
                                        //初始化“查看详情”按钮
                                        laytpl(getTemplate).render(data, function (html) {
                                            $('#showPersonDetailDiv').html(html);
                                        });
                                    }
                                    //重新渲染
                                    form.render("select");
                                }
                            });
                            break;
                    }
                });
                //名字输入框onblur后发起数据查询——结束

                //下拉框选中后给person_id和其他基本信息表格赋值
                form.on('select(person_info)', function(data){
                    //给person_id赋值
                    person_id_value = data.value;
                    $("#person_id:hidden").val(data.value);
                    $.ajax({
                        type: "post",
                        url: "personServlet?action=queryPersonInfoById",
                        data:{
                            person_id : person_id_value
                        },
                        dataType: 'json',
                        success: function (result) {
                            let sourceData = result.peopleBase;
                            office = sourceData.office;
                            phone = sourceData.phone;
                        },
                        //请求失败，包含具体的错误信息
                        error: function (e) {
                            // 异常提示
                            parent.layer.msg('网络故障', {
                                icon : 5
                            });
                        }
                    });

                    //初始化“查看详情”按钮
                    laytpl(getTemplate).render(person_id_value, function (html) {
                        $('#showPersonDetailDiv').html(html);
                    });

                    /*----------重载表格---------------*/
                    //本年度请假信息详情表格数据初始化
                    table.render({
                        elem: '#LeaveInfoDetail'
                        ,url:'askForLeaveServlet?action=queryThisYearLeaveInfo'
                        ,where:{
                            person_id:person_id_value
                        }
                        ,title: '本年度请假信息详情表'
                        ,request: {
                            pageName: 'curr' //页码的参数名称，默认：page
                            ,limitName: 'nums' //每页数据量的参数名，默认：limit
                        }
                        ,limit:5
                        ,limits:[5,10,15]
                        , cols: [[
                            {field: 'leave_type', width: '20%', title: '假期类型', align:'center'}
                            , {field: 'actualDays', width: '20%', title: '实际天数', align:'center'}
                            , {field: 'startDate', width: '30%', title: '开始日期', align:'center'}
                            , {field: 'endDate', width: '30%', title: '销假日期', align:'center'}
                        ]]
                        ,page : true
                    });

                    //本年度请假信息统计表格数据初始化
                    table.render({
                        elem: '#LeaveInfoCount'
                        , url: "askForLeaveServlet?action=queryThisYearLeaveInfoCount"
                        ,where:{
                            person_id:person_id_value
                        }
                        ,title: '本年度请假信息统计表'
                        ,request: {
                            pageName: 'curr'
                            ,limitName: 'nums'
                        }
                        ,limit:5
                        ,limits:[5,10,15]
                        , cols: [[
                            {field: 'leave_type', width: '40%', title: '假期类型', align:'center'}
                            , {field: 'time', width: '30%', title: '累计次数', align:'center'}
                            , {field: 'totalDays', width: '30%', title: '累计天数', align:'center'}
                        ]]
                        ,page : true
                    });

                    //初始化本单位当前请假情况（详情，总人数，请假人数，在岗率）
                    table.render({
                        elem: '#currentLeaveInfoOfThisOffice'
                        , url: "askForLeaveServlet?action=queryOfficeLeaveInfoByPersonId"
                        ,where:{
                            person_id:person_id_value
                        }
                        ,toolbar: '#toolbar'
                        ,defaultToolbar: []
                        ,title: '本单位当前请假情况表'
                        ,request: {
                            pageName: 'curr'
                            ,limitName: 'nums'
                        }
                        ,limit:5
                        ,limits:[5,10,15]
                        , cols: [[
                            {field: 'personCounts', width: '40%', title: '总人数', align:'center'}
                            , {field: 'leavingCounts', width: '30%', title: '请假人数', align:'center'}
                            , {field: 'workingPercent', width: '30%', title: '在岗率', align:'center'}
                        ]]
                        ,page : true
                    });
                    /*----------重载表格---------------*/
                });

                //初始化请假种类下拉框
                $.ajax({
                    url: 'systemDataServlet?action=queryLeaveType',
                    dataType: 'json',
                    type: 'post',
                    success: function (data) {
                        var sourceData = data.data;
                        if (sourceData !== null) {
                            $("#leave_type").empty();
                            $("#leave_type").append("<option value=''>请选择</option>");
                            $.each(sourceData, function (index, item) {
                                $('#leave_type').append(new Option(item.leave_type,item.leave_type));
                            });
                        } else {
                            $("#leave_type").append(new Option("暂无数据", ""));
                        }
                        //重新渲染
                        form.render("select");
                    }
                });

                //表单提交事件
                form.on('submit(submit)', function(data){
                    const sourceData = data.field;
                    var DISABLED = 'layui-btn-disabled';
                    $('#addALeaveInfoSubmit').addClass(DISABLED);
                    $('#addALeaveInfoSubmit').attr('disabled', 'disabled');

                    var confirmLayer = layer.confirm('确认提交？', {
                        btn: ['确定','返回'] //按钮
                    }, function(){
                        $.ajax({
                            url: 'askForLeaveServlet?action=addLeaveInfo',
                            dataType: 'json',
                            data: {
                                name:sourceData.nameInput,
                                person_id:person_id_value,
                                office:office,
                                phone:phone,
                                leave_type:sourceData.leave_type,
                                start_date:sourceData.start_date,
                                leave_days_projected:sourceData.leave_days_projected,
                                work_leader:sourceData.work_leader,
                                leave_reason:sourceData.leave_reason,
                                approver:sourceData.approver,
                                depart_location:sourceData.depart_location,
                                arrive_location:sourceData.arrive_location,
                                start_leave_remark:sourceData.start_leave_remark,
                                start_leave_operator:"${sessionScope.user.operator}"
                            },
                            type: 'post',
                            success: function (data) {
                                console.log("data: " + data);
                                var leaveInfo = data.leave_info;
                                if (data.code == 1) {
                                    var printLayer = layer.msg('操作成功!', {
                                        icon: 1
                                        , time: 0
                                        , id: 'LAY_layuipro'
                                        , btn: ['前往打印', '返回继续添加']
                                        , yes: function (index) {
                                            $.ajax({
                                                url: 'askForLeaveServlet?action=queryALeaveInfoForPrintBySerialnumber',
                                                dataType: 'json',
                                                data: {
                                                    serialnumber: leaveInfo.serialnumber
                                                },
                                                type: 'post',
                                                success: function (data) {
                                                    //跳转到打印页面
                                                    var printPage = layer.open({
                                                        type: 2,
                                                        title: '请假者具体信息',
                                                        maxmin: true,
                                                        area: ['800px', '500px'],
                                                        content: "pages/service/askForLeave/_print.jsp",
                                                        anim: 2,
                                                        resize: false,
                                                        btn: ['打印', '前往待销假页面'],
                                                        success: function (layero, index) {
                                                            var body = layer.getChildFrame('body', index);
                                                            //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                                                            var iframeWin = window[layero.find('iframe')[0]['name']];
                                                            //初始化表单数据的值
                                                            body.find("#serialnumber").html(data.serialnumber);
                                                            body.find("#office_name").html(data.office);
                                                            body.find("#name").html(data.name);
                                                            body.find("#leave_type").html(data.leave_type);
                                                            body.find("#leave_days_projected").html(data.leave_days_projected);
                                                            body.find("#start_date1").html(data.start_date);
                                                            body.find("#start_date2").html(data.start_date);
                                                            body.find("#end_date_maybe").html(data.end_date_maybe);
                                                            body.find("#operate_date").html(data.operate_date);
                                                        },
                                                        yes: function (index, layero) {
                                                            //更新按钮的回调
                                                            var body = layer.getChildFrame('body', index);
                                                            // 找到隐藏的提交按钮模拟点击提交
                                                            body.find('#print').click();
                                                            //return false 开启该代码可禁止点击该按钮关闭
                                                        },
                                                        btn2: function (index, layero) {
                                                            //取消按钮的回调
                                                            layer.close(printPage);
                                                            window.location.href = "pages/service/approvalLeave/approvalLeave.jsp";
                                                            //return false 开启该代码可禁止点击该按钮关闭
                                                        },
                                                        cancel: function () {
                                                            layer.close(printPage);
                                                            //右上角关闭回调
                                                            //return false 开启该代码可禁止点击该按钮关闭
                                                        }
                                                    });
                                                },
                                                error: function (e) {
                                                    // 异常提示
                                                    layer.msg('网络故障' + e.status, {
                                                        icon: 5
                                                    });
                                                }

                                            })
                                            //关闭消息提示
                                            layer.close(printLayer);
                                        }
                                        , btn2: function (index) {
                                            //点击了继续添加
                                            //关闭消息提示
                                            layer.close(index);
                                            //撤销样式
                                            $('#addALeaveInfoSubmit').removeClass(DISABLED);
                                            $('#addALeaveInfoSubmit').removeAttr('disabled');

                                        }
                                    });
                                }
                                else {
                                    layer.msg('业务提交失败，原因：' + data.msg, {
                                        icon: 5
                                    });
                                    $('#addALeaveInfoSubmit').removeClass(DISABLED);
                                    $('#addALeaveInfoSubmit').removeAttr('disabled');
                                }
                            },
                            error:function(e){
                                // 异常提示
                                layer.msg('网络故障'+e.status, {
                                    icon : 5
                                });
                            }
                        });
                    }, function(){
                        var DISABLED = 'layui-btn-disabled';
                        $('#addALeaveInfoSubmit').removeClass(DISABLED);
                        $('#addALeaveInfoSubmit').removeAttr('disabled');
                        layer.close(confirmLayer);
                    });


                    return false;
                });

            });
        </script>


    </body>


</html>
