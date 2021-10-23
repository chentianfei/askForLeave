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
                                            <div class="layui-card-header  layui-bg-blue">请假信息登记</div>
                                            <div class="layui-card-body">
                                                <form class="layui-form layui-form-pane">

                                                    <div class="layui-form-item">
                                                        <label class="layui-form-label" style="width: 100px">姓名</label>
                                                        <div class="layui-input-inline" style="width: 300px">
                                                            <input type="text" name="person_name" placeholder="输入后请敲回车，若有重名，将会选择正确信息"
                                                                   class="layui-input" id="person_name">
                                                        </div>
                                                    </div>

                                                    <div class="layui-form-item">
                                                        <%--人员基本信息展示--%>
                                                        <table class="layui-table">
                                                            <colgroup>
                                                                <col width="8%">
                                                                <col width="8%">
                                                                <col width="15%">
                                                                <col width="">
                                                                <col width="">
                                                                <col width="15%">
                                                                <col width="15%">
                                                            </colgroup>
                                                            <thead>
                                                                <tr>
                                                                    <th style="text-align: center">性别</th>
                                                                    <th style="text-align: center">民族</th>
                                                                    <th style="text-align: center">出生年月</th>
                                                                    <th style="text-align: center">本人籍贯</th>
                                                                    <th style="text-align: center">工作单位</th>
                                                                    <th style="text-align: center">现任职务</th>
                                                                    <th style="text-align: center">联系电话</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td style="text-align: center" id="td_sex" name="td_sex">-</td>
                                                                    <td style="text-align: center" id="td_nationality" name="td_nationality'">-</td>
                                                                    <td style="text-align: center" id="td_birthDate" name="td_birthDate">-</td>
                                                                    <td style="text-align: center" id="td_nativePlace" name="td_nativePlace">-</td>
                                                                    <td style="text-align: center" id="td_office" name="td_office">-</td>
                                                                    <td style="text-align: center" id="td_job" name="td_job">-</td>
                                                                    <td style="text-align: center" id="td_phoneNum" name="td_phoneNum">-</td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>

                                                    <div class="layui-form-item">
                                                        <div class="layui-inline">
                                                            <label class="layui-form-label"
                                                                   style="width: 100px">请假种类</label>
                                                            <div class="layui-input-inline" style="width: 100px">
                                                                <select name="leaveType" lay-search="">
                                                                    <option value=""></option>
                                                                    <option value="1">事假</option>
                                                                    <option value="2">休假</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="layui-inline">
                                                            <label class="layui-form-label"
                                                                   style="width: 100px;">开始日期</label>
                                                            <div class="layui-input-inline" style="width: 150px;">
                                                                <input type="text" class="layui-input" name="startDate"
                                                                       id="startDate">
                                                            </div>
                                                        </div>
                                                        <div class="layui-inline">
                                                            <label class="layui-form-label"
                                                                   style="width: 100px">请假天数</label>
                                                            <div class="layui-input-inline" style="width: 100px">
                                                                <input type="text" name="leaveDays" placeholder="请输入"
                                                                       class="layui-input">
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="layui-form-item">
                                                        <label class="layui-form-label"
                                                               style="width: 200px">不在岗期间主持工作领导</label>
                                                        <div class="layui-input-inline" style="width: 300px">
                                                            <input type="text" name="workLeader" placeholder="请输入"
                                                                   class="layui-input">
                                                        </div>
                                                    </div>

                                                    <div class="layui-form-item">
                                                        <label class="layui-form-label"
                                                               style="width: 100px">请假事由</label>
                                                        <div class="layui-input-inline" style="width: 400px">
                                                            <input type="text" name="reason" placeholder="请输入"
                                                                   class="layui-input">
                                                        </div>
                                                    </div>

                                                    <div class="layui-form-item">
                                                        <label class="layui-form-label"
                                                               style="width: 100px">批准人</label>
                                                        <div class="layui-input-inline" style="width: 400px">
                                                            <input type="text" name="permmitPerson" placeholder="请输入"
                                                                   class="layui-input">
                                                        </div>
                                                    </div>

                                                    <div class="layui-form-item">
                                                        <label class="layui-form-label" style="width: 100px">出发地</label>
                                                        <div class="layui-input-inline" style="width: 400px">
                                                            <input type="text" name="startLocation" placeholder="请输入"
                                                                   class="layui-input">
                                                        </div>
                                                    </div>

                                                    <div class="layui-form-item">
                                                        <label class="layui-form-label"
                                                               style="width: 100px">到达地</label>
                                                        <div class="layui-input-inline" style="width: 400px">
                                                            <input type="text" name="endLocation" placeholder="请输入"
                                                                   class="layui-input">
                                                        </div>
                                                    </div>

                                                    <div class="layui-form-item">
                                                        <label class="layui-form-label" style="width: 100px">备注</label>
                                                        <div class="layui-input-inline" style="width: 400px">
                                                            <textarea placeholder="请输入内容" name="leaveRemark"
                                                                      class="layui-textarea"></textarea>
                                                        </div>
                                                    </div>

                                                    <div class="layui-form-item" style="padding-left: 60%">
                                                        <div class="layui-input-block">
                                                            <button type="submit" class="layui-btn" lay-submit
                                                                    lay-filter="submit">提交
                                                            </button>
                                                            <button type="button" class="layui-btn layui-btn-normal">
                                                                取消
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


                        <div class="layui-col-md4">

                            <%--已累计在岗天数--%>
                            <div class="layui-row">
                                <div class="layui-bg-gray" style="padding: 10px;">
                                    <div class="layui-row">
                                        <div class="layui-col-md12">
                                            <div class="layui-card">
                                                <div class="layui-card-header layui-bg-blue">已累计在岗天数</div>
                                                <div class="layui-card-body" style="height:100px;line-height:100px;text-align: center" >
                                                    <p style="font-size: 50px;color: #333333" id="totalworkdays"></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <fieldset class="layui-elem-field layui-field-title" ></fieldset>

                            <%--本年度请假信息统计表格--%>
                            <div class="layui-row">
                                <div class="layui-bg-gray" <%--style="padding:20px 10px;"--%>>
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
                                                    <table class="layui-hide" id="LeaveInfo"></table>
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

                <%--底部信息--%>
            <div class="layui-footer">
                <%@include file="/pages/common/footer.jsp" %>
            </div>

        </div>


        <script>
            //全局取消回车默认事件
            document.onkeydown = function (e) {
                if (e.keyCode == 13) {
                    e.preventDefault();//禁用回车的默认事件
                }
            }

            layui.use(['laydate', 'element', 'layer', 'util', 'table',"form"], function () {
                var element = layui.element;
                var layer = layui.layer;
                var util = layui.util;
                var $ = layui.$;
                var table = layui.table;
                var form = layui.form;
                //日期选择框
                var laydate = layui.laydate;

                //定义变量用于补充表单提交时时无法自动获取的参数
                var person_name;//人员姓名
                var startDate;//开始日期
                var phoneNum;//联系电话

                //初始化日期框，并获取选中的值
                laydate.render({
                    elem: '#startDate' //指定元素
                    ,done: function(value, date){
                        startDate = value;
                    }
                });

                //本年度请假信息详情表格数据初始化
                var table_LeaveInfo = table.render({
                    elem: '#LeaveInfo'
                    , url: "infoServlet?action=queryThisYearLeaveInfo"
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
                        console.log(res);
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

                //本年度请假信息统计表格数据初始化
                var table_LeaveInfoCount = table.render({
                    elem: '#LeaveInfoCount'
                    , url: "infoServlet?action=queryThisYearLeaveInfoCount"
                    , cellMinWidth: 80
                    , cols: [[
                        {field: 'type', width: '40%', title: '假期类型'}
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
                        console.log(res);
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

                //名字输入框onblur后发起数据查询——开始
                $("#person_name").keydown(function (e) {
                    switch (e.keyCode) {
                        case 13:
                            person_name = $("#person_name").val();
                            //发起ajax请求后台数据查询
                            $.ajax({
                                type: "get",
                                contentType: "application/json;charset=UTF-8",
                                url: "personServlet?action=queryMultipleName",
                                dataType: "JSON",
                                data: {"person_name": person_name},
                                //请求成功
                                success: function (result) {
                                    //判断结果集是否有多人
                                    //结果集记录数>1：弹窗，显示具体信息
                                    //结果集记录数=1，直接显示表格
                                    if (result.data.length > 1) {
                                        layer.open({
                                            type: 2,
                                            title: '请选择正确人员信息',
                                            shadeClose: false,
                                            shade: [0.5, '#393D49'],
                                            area: ["1000px", "400px"],
                                            fixed: true,
                                            content: "pages/service/askForLeave/_multipleName.jsp",
                                            anim: 2,
                                            btn:["确认","返回"],
                                            yes: function(index, layero){
                                                //获取弹窗里表格的数据
                                                var ifameWin = parent["layui-layer-iframe"+index];
                                                var data = ifameWin.layui.table.checkStatus("multipleName").data;
                                                //遍历弹窗里的选中行的数据，为本页面的表格赋值
                                                for (var key in data) {
                                                    //data本身就是个json对象，是行数据对象，相当于java里的list
                                                    //一行表示data里有一条数据，多行多个，故如果要取得每一个里面的数据
                                                    //需要进行两次遍历，先取得行数据，在取得该行里的人员信息的对应信息
                                                    for (var key_in in data[key]) {
                                                        // console.log("key_in=" + key_in);
                                                        // console.log("data[key][key_in]=" + data[key][key_in]);
                                                        if (key_in === "sex") {
                                                            $("#td_sex").html(data[key][key_in]);
                                                        } else if (key_in === "nationality") {
                                                            $("#td_nationality").html(data[key][key_in]);
                                                        } else if (key_in === "birthDate") {
                                                            $("#td_birthDate").html(data[key][key_in]);
                                                        } else if (key_in === "nativePlace") {
                                                            $("#td_nativePlace").html(data[key][key_in]);
                                                        } else if (key_in === "office") {
                                                            $("#td_office").html(data[key][key_in]);
                                                        } else if (key_in === "job") {
                                                            $("#td_job").html(data[key][key_in]);
                                                        } else if (key_in === "phoneNum") {
                                                            $("#td_phoneNum").html(data[key][key_in]);
                                                            phoneNum = data[key][key_in];
                                                        }
                                                    }
                                                }

                                                //根据选择好的人员，通过table.reload（人员姓名、联系电话）进行表格分别查询本年度请假统计信息、本年度请假信息详情并作回显
                                                //查询当前已经累计在岗天数：计算当前日期与上次系统记录日起的时间差,并作回显
                                                $.ajax({
                                                    url:"infoServlet?action=queryTotalWorkDays",
                                                    method:"post",
                                                    data:{
                                                        "person_name": person_name,
                                                        "phoneNum":phoneNum
                                                    },
                                                    dataType: "json",
                                                    success:function () {
                                                        $("#totalworkdays").html("28天")

                                                    },
                                                    error:function(){
                                                        $("#totalworkdays").html("28天");
                                                        // $("#totalworkdays").html("查询失败")
                                                    }

                                                })


                                                //处理本年度请假信息统计显示功能
                                                table_LeaveInfoCount.reload({
                                                    where: {
                                                        person_name: person_name
                                                        , phoneNum: phoneNum
                                                    }
                                                });
                                                //处理本年度请假详细信息显示功能
                                                table_LeaveInfo.reload({
                                                    where: {
                                                        person_name: person_name
                                                        ,phoneNum: phoneNum
                                                    }
                                                });

                                                //关闭弹窗
                                                layer.close(index);
                                            },
                                            cancel: function () {

                                            }
                                        });
                                    } else if (result.data.length == 1) {
                                        alert("result.length == 1");
                                    }
                                },
                                error: function (e) {
                                    console.log(e.status);
                                    console.log(e.responseText);
                                }
                            });
                            break;
                        default:
                            break;
                    }
                });
                //名字输入框onblur后发起数据查询——结束

                //表单提交事件
                form.on('submit(submit)', function(data){
                    $.ajax({
                        // url:"infoServlet?action=askForLeave&startDate="+startDate+"&phoneNum="+phoneNum,
                        url:"infoServlet?action=askForLeave&phoneNum="+phoneNum,
                        method:"post",
                        data:data.field,
                        dataType: "json",
                        success:function () {
                            layer.msg('操作成功',{icon: 1});
                            //window.location.href = "pages/service/approvalLeave/approvalLeave.jsp"
                        },
                        error:function(){
                            //window.location.href = "pages/service/approvalLeave/approvalLeave.jsp"
                            layer.msg('操作成功???',{icon: 1});
                            //layer.msg('操作失败',{icon: 2});
                        }

                    })
                    return false;
                });

            });
        </script>


    </body>


</html>
