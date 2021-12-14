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

                        <div class="layui-col-md8">
                            <div class="layui-bg-gray" style="padding: 20px 10px;">
                                <div class="layui-row">
                                    <div class="layui-col-md12">
                                        <div class="layui-card">
                                            <div class="layui-card-header  layui-bg-blue">调休信息登记</div>
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


                                                    <div class="layui-form-item" style="display: none">
                                                            <label class="layui-form-label"
                                                                   style="width: 100px">请假种类</label>
                                                            <div class="layui-input-inline" style="width: 400px">
                                                                <select id="leave_type" name="leave_type" lay-verify="required" lay-search="">
                                                                    <option value="调休" selected>调休</option>
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
                                                                   style="width: 150px">此次调休天数</label>
                                                            <div class="layui-input-inline" style="width: 350px">
                                                                <input type="text" name="leave_days_projected"
                                                                       id="leave_days_projected"
                                                                       lay-verify="number|required|integer"
                                                                       autocomplete="off"
                                                                       class="layui-input">
                                                            </div>
                                                    </div>


                                                    <div class="layui-form-item" >
                                                        <label class="layui-form-label"
                                                               style="width: 200px">不在岗期间主持工作领导</label>
                                                        <div class="layui-input-inline" style="width: 300px">
                                                            <input type="text" name="work_leader"
                                                                   autocomplete="off" lay-verify="required"
                                                                   id="work_leader" placeholder=""
                                                                   class="layui-input">
                                                        </div>
                                                    </div>

                                                    <div class="layui-form-item" style="display: none">
                                                        <label class="layui-form-label"
                                                               style="width: 100px">请假事由</label>
                                                        <div class="layui-input-inline" style="width: 400px">
                                                            <input type="text" name="leave_reason"
                                                                   autocomplete="off" lay-verify="required"
                                                                   id="leave_reason" placeholder=""
                                                                   class="layui-input" value="调休">
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
                                                            <input type="text" name="depart_location"
                                                                   autocomplete="off" lay-verify="required"
                                                                   id="depart_location" placeholder=""
                                                                   class="layui-input">
                                                        </div>
                                                    </div>

                                                    <div class="layui-form-item">
                                                        <label class="layui-form-label"
                                                               style="width: 100px">到达地</label>
                                                        <div class="layui-input-inline" style="width: 400px">
                                                            <input type="text" name="arrive_location"
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
                                                            <button type="submit" class="layui-btn" lay-submit
                                                                    lay-filter="submit">提交
                                                            </button>
                                                            <button type="reset" id="resetBtn" class="layui-btn layui-btn-normal" >
                                                                重置
                                                            </button>

                                                        </div>
                                                    </div>

                                                    <%--人员编号数据域
                                                    <div class="layui-form-item">
                                                        <div class="layui-input-inline" style="display: none ">
                                                            <input type="text" name="person_id" placeholder=""
                                                                   class="layui-input" id="person_id">
                                                        </div>
                                                    </div>
                                                    --%>
                                                </form>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="layui-col-md4">

                            <%--已累计在岗天数--%>
                            <div class="layui-row">
                                <div class="layui-bg-gray" style="padding:20px 10px;">
                                    <div class="layui-row">
                                        <div class="layui-col-md12">
                                            <div class="layui-card">
                                                <div class="layui-card-header layui-bg-blue">累计在岗天数（系统数据）</div>

                                                <div class="layui-card-body" style="text-align: center" >
                                                    <div class="layui-bg-gray">
                                                        <div class="layui-row layui-col-space5">
                                                            <div class="layui-col-md4">
                                                                <div class="layui-panel">
                                                                    <div style="padding: 50px 30px;">
                                                                        <span style="font-size: 20px">
                                                                             累计在岗
                                                                        </span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="layui-col-md4">
                                                                <div class="layui-panel">
                                                                    <div style="padding: 50px 30px;">
                                                                        <span id="totalWorkDays" style="font-size: 80px;color: darkred">
                                                                            --
                                                                        </span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="layui-col-md4">
                                                                <div class="layui-panel">
                                                                    <div style="padding: 50px 30px">
                                                                         <span style="font-size: 20px">
                                                                             天
                                                                        </span>
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
                            </div>


                            <%--本年度累计调休天数--%>
                            <div class="layui-row">
                                <div class="layui-bg-gray" style="padding:20px 10px;">
                                    <div class="layui-row">
                                        <div class="layui-col-md12">
                                            <div class="layui-card">
                                                <div class="layui-card-header layui-bg-blue">本年度调休统计数据</div>
                                                <div class="layui-card-body" style="height:100px;line-height:100px;text-align: center" >
                                                    <form class="layui-form layui-form-pane">
                                                        <div class="layui-form-item">
                                                            <label class="layui-form-label" style="width: 150px">累计调休次数</label>
                                                            <div class="layui-input-inline">
                                                                <input type="text" id="totalRestTimes" disabled
                                                                       placeholder="当前暂未获取到人员信息"
                                                                       autocomplete="off" class="layui-input">
                                                            </div>
                                                            <div class="layui-form-mid layui-word-aux">次</div>
                                                        </div>

                                                        <div class="layui-form-item">
                                                            <label class="layui-form-label" style="width: 150px">累计调休天数</label>
                                                            <div class="layui-input-inline">
                                                                <input type="text" id="totalRestDays" disabled
                                                                       placeholder="当前暂未获取到人员信息"
                                                                       autocomplete="off" class="layui-input">
                                                            </div>
                                                            <div class="layui-form-mid layui-word-aux">天</div>
                                                        </div>
                                                    </form>
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

            //全局取消回车默认事件
            document.onkeydown = function (e) {
                if (e.keyCode == 13) {
                    e.preventDefault();//禁用回车的默认事件
                }
            }

            function openPersonDetailPage(person_id_value){
                $.ajax({
                    type: "post",
                    url: "personServlet?action=queryPersonInfoByIdRTNList",
                    data:{
                        person_id : person_id_value
                    },
                    dataType: 'json',
                    success: function (result) {
                        console.log(result.data[0]);
                        let sourceData = result.data[0];
                        //result包含的person数量
                        layer.open({
                            type: 2,
                            title: '请假者具体信息',
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
                                body.find('#allow_Leave_Days').val(sourceData.allow_Leave_Days);
                                //获取领导姓名
                                let leadersName = new Array();
                                $.each(sourceData.leader,function (index,ele) {
                                    leadersName.push(ele.name);
                                })
                                if(leadersName.length <= 0){
                                    body.find('#leader').val("暂未绑定领导");
                                }else {
                                    body.find('#leader').val(leadersName);
                                }

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

            layui.use(['laytpl','laydate', 'element', 'layer', 'util', 'table',"form"], function () {
                var element = layui.element;
                var layer = layui.layer;
                var util = layui.util;
                var $ = layui.$;
                var table = layui.table;
                var form = layui.form;
                var laydate = layui.laydate;
                var laytpl=layui.laytpl;

                //数字验证
                form.verify({
                    integer: [
                        /^[1-9]\d*$/
                        , '只能输入正整数'
                    ]
                });

                //laytpl模板内容
                var getTemplate = $('#showPersonDetailScript').html();

                //初始化日期框，并获取选中的值
                laydate.render({
                    elem: '#start_date'
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
                                                    //加入和本人id不同的领导信息
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
                    //初始化“查看详情”按钮
                    laytpl(getTemplate).render(person_id_value, function (html) {
                        $('#showPersonDetailDiv').html(html);
                    });

                    //查询调休情况统计数据
                    $.ajax({
                        url:"askForLeaveServlet?action=queryThisYearOneLeaveInfoCountByPersonID",
                        type:"post",
                        data:{
                            person_id:person_id_value,
                            leave_type:"调休"
                        },
                        dataType:"json",
                        success:function (result) {
                            if(result==null){
                                $("#totalRestTimes").val(0);
                                $("#totalRestDays").val(0);
                            }else {
                                $("#totalRestTimes").val(result.time);
                                $("#totalRestDays").val(result.totalDays);
                            }
                        },
                        error:function (data) {

                        }
                    })

                    //查询累计在岗时间
                    $.ajax({
                        url:"askForLeaveServlet?action=queryRecentWorkDaysByPersonIDqueryAHistoryInfo",
                        type:"post",
                        data:{
                            person_id:person_id_value
                        },
                        dataType:"json",
                        success:function (result) {
                            if(result<=0){
                                $("#totalWorkDays").html(0);
                            }else {
                                $("#totalWorkDays").html(result);
                            }

                        },
                        error:function (data) {

                        }
                    })

                });

                //本年度请假信息详情表格数据初始化
                var table_LeaveInfo = table.render({
                    elem: '#LeaveInfoDetail'
                    , url: "askForLeaveServlet?action=queryThisYearLeaveInfo"
                    , cellMinWidth: 80
                    , cols: [[
                        {field: 'type', width: '25%', title: '假期类型'}
                        , {field: 'actualDays', width: '25%', title: '实际天数'}
                        , {field: 'startDate', width: '25%', title: '开始日期'}
                        , {field: 'endDate', width: '25%', title: '销假日期'}
                    ]]
                    ,page: {
                        layout: ['prev', 'page', 'next'] //自定义分页布局
                        //,curr: 5 //设定初始在第 5 页
                        ,groups: 3 //只显示 1 个连续页码
                        ,limit:3
                        ,first: false //不显示首页
                        ,last: false //不显示尾页
                    }
                    ,parseData:function(res){ //res 即为原始返回的数据
                        //console.log(res);
                        var current_pages;
                        //第一次显示的时候this.page=true，把这种情况单独列出
                        if(this.page===true){
                            current_pages=1;
                        } else {
                            current_pages=this.page.curr;
                        }
                        //根据分页要求选出需要显示的数据
                        var data= res.data.slice(
                            this.limit*(current_pages-1), this.limit*current_pages
                        );
                        return {
                            "code": res.code,
                            "msg":res.msg,
                            "count": res.data.length,
                            "data": data
                        }
                    }
                });

                //本年度请假信息统计表格数据初始化
                var table_LeaveInfoCount = table.render({
                    elem: '#LeaveInfoCount'
                    , url: "askForLeaveServlet?action=queryThisYearLeaveInfoCount"
                    , cellMinWidth: 80
                    , cols: [[
                        {field: 'leave_type', width: '40%', title: '假期类型'}
                        , {field: 'time', width: '30%', title: '累计次数'}
                        , {field: 'totalDays', width: '30%', title: '累计天数'}
                    ]]
                    ,page: {
                        layout: ['prev', 'page', 'next'] //自定义分页布局
                        //,curr: 5 //设定初始在第 5 页
                        ,groups: 3 //只显示 1 个连续页码
                        ,limit:3
                        ,first: false //不显示首页
                        ,last: false //不显示尾页
                    }
                    ,parseData:function(res){ //res 即为原始返回的数据
                        //console.log(res);
                        var current_pages;
                        //第一次显示的时候this.page=true，把这种情况单独列出
                        if(this.page===true)current_pages=1;
                        else current_pages=this.page.curr;
                        //根据分页要求选出需要显示的数据
                        var data= res.data.slice(this.limit*(current_pages-1),this.limit*current_pages);
                        return {
                            "code": res.code,
                            "msg":res.msg,
                            "count": res.data.length,
                            "data": data
                        }
                    }
                });

                //表单提交事件
                form.on('submit(submit)', function(data){
                    const sourceData = data.field;

                    $.ajax({
                        url: 'askForLeaveServlet?action=addLeaveInfo',
                        dataType: 'json',
                        data: {
                            name:sourceData.nameInput,
                            person_id:person_id_value,
                            leave_type:"调休",
                            start_date:sourceData.start_date,
                            leave_days_projected:sourceData.leave_days_projected,
                            work_leader:sourceData.work_leader,
                            leave_reason:"调休",
                            approver:sourceData.approver,
                            depart_location:sourceData.depart_location,
                            arrive_location:sourceData.arrive_location,
                            start_leave_remark:sourceData.start_leave_remark,
                            start_leave_operator:"${sessionScope.user.operator}"
                        },
                        type: 'post',
                        success: function (data) {
                            console.log("data: "+data);
                            if(data == 1){
                                layer.msg('操作成功!是否前往审核页面？', {
                                    icon: 1
                                    ,time: 0 //不自动关闭
                                    ,btn: ['前往审核', '继续添加']
                                    ,yes: function(index){
                                        //点击了前往审核
                                        //关闭消息提示
                                        layer.close(index);
                                        //点击重置按钮实现重置
                                        $("#resetBtn").click();
                                        //跳转到审核页面
                                        window.location.href = "pages/service/approvalLeave/approvalLeave.jsp"
                                    }
                                    ,btn2:function(index){
                                        //点击了继续添加
                                        //关闭消息提示
                                        layer.close(index);
                                        //点击重置按钮实现重置
                                        $("#resetBtn").click();
                                    }
                                });
                            }else {
                                layer.msg('业务提交失败', {
                                    icon : 5
                                });
                            }
                        },
                        error:function(e){
                            // 异常提示
                            layer.msg('网络故障'+e.status, {
                                icon : 5
                            });
                        }
                    });

                    return false;
                });

            });
        </script>


    </body>


</html>
