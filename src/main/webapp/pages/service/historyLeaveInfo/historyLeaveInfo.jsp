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
                                    <div class="layui-card-header layui-bg-blue">历史请假记录查询</div>
                                    <div class="layui-card-body">
                                        <%--查询表单开始--%>
                                        <form class="layui-form layui-form-pane" action="#" method="post">

                                            <div class="layui-form-item">
                                                <%--姓名--%>
                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">姓名</label>
                                                    <div class="layui-input-inline" style="width: 150px">
                                                        <input type="text" name="username" placeholder="请输入"
                                                               class="layui-input">
                                                    </div>
                                                </div>

                                                <%--性别--%>
                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">性别</label>
                                                    <div class="layui-input-inline" style="width: 150px">
                                                        <select name="modules" lay-search="">
                                                            <option value=""></option>
                                                            <option value="1">男</option>
                                                            <option value="2">女</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <%--工作单位--%>
                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">工作单位</label>
                                                    <div class="layui-input-inline" style="width: 150px">
                                                        <select name="modules" lay-search="">
                                                            <option value=""></option>
                                                            <option value="1">layer</option>
                                                            <option value="2">form</option>
                                                        </select>
                                                    </div>
                                                </div>


                                                <%--联系方式--%>
                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">联系方式</label>
                                                    <div class="layui-input-inline" style="width: 150px">
                                                        <input type="tel" name="phone" lay-verify="phone"
                                                               placeholder="请输入" autocomplete="off" class="layui-input">
                                                    </div>
                                                </div>


                                            </div>
                                            <div class="layui-form-item">


                                                <%--假期类型--%>
                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">假期类型</label>
                                                    <div class="layui-input-inline" style="width: 150px">
                                                        <select name="modules" lay-search="">
                                                            <option value=""></option>
                                                            <option value="1">事假</option>
                                                            <option value="2">休假</option>
                                                            <option value="2">婚假</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <%--起始地--%>
                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">起始地</label>
                                                    <div class="layui-input-inline" style="width: 150px">
                                                        <input type="text" name="username" placeholder="请输入"
                                                               class="layui-input">
                                                    </div>
                                                </div>

                                                <%--到达点--%>
                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">到达点</label>
                                                    <div class="layui-input-inline" style="width: 150px">
                                                        <input type="text" name="username" placeholder="请输入"
                                                               class="layui-input">
                                                    </div>
                                                </div>

                                                <%--预计开始时间--%>
                                                <div class="layui-inline">
                                                    <label class="layui-form-label"
                                                           style="width: 100px;">开始时间</label>
                                                    <div class="layui-input-inline" style="width: 150px;">
                                                        <input type="text" class="layui-input" id="startDate">
                                                    </div>
                                                </div>

                                                <%--预计到岗时间--%>
                                                <div class="layui-inline">
                                                    <label class="layui-form-label"
                                                           style="width: 100px;">到岗时间</label>
                                                    <div class="layui-input-inline" style="width: 150px;">
                                                        <input type="text" class="layui-input" id="endDate">
                                                    </div>
                                                </div>
                                            </div>


                                            <%--按钮区域--%>
                                            <div class="layui-form-item" style="padding-left: 70%">
                                                <div class="layui-input-block">
                                                    <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">提交</button>
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
                                        <div class="layui-card-header layui-bg-blue">历史请假记录</div>
                                        <div class="layui-card-body">

                                            <%--数据展示--%>
                                            <table class="layui-hide" id="auditInfo" lay-filter="auditInfo"></table>

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

        <%--表格内部工具栏--%>
        <script type="text/html" id="toolbar">
            <a class="layui-btn layui-btn-xs" lay-event="detail">详情</a>
            <a class="layui-btn layui-btn-xs" lay-event="delete">删除</a>
        </script>

        <script>
            layui.use(['table','upload','laydate','element','form', 'layer', 'util'], function(){
                var element = layui.element
                var layer = layui.layer
                var util = layui.util
                var $ = layui.jquery;
                var table = layui.table;
                var form = layui.form;
                var laydate = layui.laydate;

                layer.config({
                    skin:'layui-layer-molv'
                })

                laydate.render({
                    elem: '#startDate'//指定元素
                    ,type:'date'
                });

                laydate.render({
                    elem: '#endDate'//指定元素
                    ,type:'date'
                });

                table.render({
                    elem: '#auditInfo'
                    ,url:'infoServlet?action=queryAllHistoryLeaveInfo'
                    ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                        layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                        ,curr: 5 //设定初始在第 5 页
                        ,groups: 5 //只显示 1 个连续页码
                        ,first: "首页" //不显示首页
                        ,last: "末页" //不显示尾页
                    }
                    ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                    ,defaultToolbar: ['filter', 'exports', 'print']
                    ,title: '历史请假记录数据'
                    ,cols: [[
                        {type: 'checkbox'}
                        , {field: 'info_id', title: '流水号', width: 100}
                        , {field: 'person_name', title: '姓名', width: "8%"}
                        , {field: 'phoneNum', title: '联系电话', width: "11%"}
                        , {field: 'office', title: '工作单位', width: "12%"}
                        , {field: 'job', title: '现任职务', width: "10%"}
                        , {field: 'leaveType', title: '请假类型', width: "8%"}
                        , {field: 'reason', title: '请假事由', width: "8%"}
                        , {field: 'startDate', title: '开始日期', width: "12%"}
                        , {field: 'leaveComment', title: '请假备注'}
                        , {field: 'permitPerson', title: '批准人', width: "8%"}
                        , {field: 'startLocation', title: '出发地', width: "10%"}
                        , {field: 'endLocation', title: '到达地', width: "10%"}
                        , {field: "actualEndDate", title: '实际到岗日期', width: "10%"}
                        , {field: 'actualDays', title: '实际请假天数', width: "8%"}
                        , {field: 'endLeaveComment', title: '销假备注'}
                        , {fixed: 'right', title: '操作', toolbar: '#toolbar', width: "11%",id:"toolbar"}
                    ]]
                });

                table.on('tool(auditInfo)', function(obj){
                    var data = obj.data;

                    var person_name;//用于保存人员姓名
                    var phoneNum;//用于保存人员联系电话
                    var person_id;//人员编号
                    var sex; //性别
                    var birthDate;//出生年月
                    var nativePlace; //本人籍贯
                    var office; //工作单位
                    var job;//现任职务
                    var area;//所在类区
                    var level;//职级
                    var leaveDays;//允许休假天数
                    var leaderID; //相关领导
                    var nationality;//民族
                    var leaveType;//请假类型
                    var reason;//请假事由
                    var startDate;//开始日期
                    var days;//请假天数
                    var permitPerson;//批准人
                    var startLocation;//出发地
                    var endLocation;//到达地
                    var endDate;//预计到岗日期
                    var leaveComment;//请假备注

                    if(obj.event === "detail"){

                        //发起查询人员详细基本信息的ajax请求
                        $.ajax({
                            //请求方式
                            type: "get",
                            //请求的媒体类型
                            contentType: "application/json;charset=UTF-8",
                            //请求地址
                            url: "personServlet?action=queryPersonDetail",
                            //数据，json字符串
                            //dataType:"JSON",
                            data:{"person_name":data.person_name,"phoneNum":data.phoneNum},
                            //请求成功
                            success: function (result) {
                                //查到人员信息后再弹窗
                                //result包含的person数量
                                var count = result.split("person_name").length-1;
                                //console.log(count);
                                if(count == 1){
                                    //传过来的result是String，需要转为json对象才可实现遍历
                                    //解析result数据并赋值变量
                                    office = queryAndBindInfo_obj(JSON.parse(result),"office");
                                    person_name = queryAndBindInfo_obj(JSON.parse(result),"person_name");
                                    phoneNum = queryAndBindInfo_obj(JSON.parse(result),"phoneNum");
                                    person_id = queryAndBindInfo_obj(JSON.parse(result),"person_id");
                                    sex = queryAndBindInfo_obj(JSON.parse(result),"sex");
                                    birthDate = queryAndBindInfo_obj(JSON.parse(result),"birthDate");
                                    nativePlace = queryAndBindInfo_obj(JSON.parse(result),"nativePlace");
                                    job = queryAndBindInfo_obj(JSON.parse(result),"job");
                                    area = queryAndBindInfo_obj(JSON.parse(result),"area");
                                    level = queryAndBindInfo_obj(JSON.parse(result),"level");
                                    leaveDays = queryAndBindInfo_obj(JSON.parse(result),"leaveDays");
                                    leaderID = queryAndBindInfo_obj(JSON.parse(result),"leaderID");
                                    nationality = queryAndBindInfo_obj(JSON.parse(result),"nationality");

                                    //查看详情弹窗
                                    layer.open({
                                        type: 2,
                                        title: '请假者具体信息',
                                        shadeClose: true,
                                        shade: false,
                                        maxmin: true, //开启最大化最小化按钮
                                        area: ['600px', '600px'],
                                        content: "pages/service/historyLeaveInfo/_leaveInfoDetail.jsp",
                                        anim:2,
                                        resize:false,
                                        btn:['返回'],
                                        success: function(layero, index){
                                            var body = layer.getChildFrame('body', index);
                                            body.find('#person_name').val(person_name);
                                            body.find('#office').val(office);
                                            body.find('#phoneNum').val(phoneNum);
                                            body.find('#person_id').val(person_id);
                                            body.find('#sex').val(sex);
                                            body.find('#birthDate').val(birthDate);
                                            body.find('#nativePlace').val(nativePlace);
                                            body.find('#job').val(job);
                                            body.find('#area').val(area);
                                            body.find('#level').val(level);
                                            body.find('#leaveDays').val(leaveDays);
                                            body.find('#leaderID').val(leaderID);
                                            body.find('#nationality').val(nationality);
                                            body.find('#leaveType').val(data.leaveType);
                                            body.find('#reason').val(data.reason);
                                            body.find('#startDate').val(data.startDate);
                                            body.find('#days').val(data.days);
                                            body.find('#permitPerson').val(data.permitPerson);
                                            body.find('#startLocation').val(data.startLocation);
                                            body.find('#endLocation').val(data.endLocation);
                                            body.find('#endDate').val(data.endDate);
                                            body.find('#leaveComment').val(data.leaveComment);
                                        },
                                        yes:function (index, layero) {
                                            //关闭该弹窗
                                            layer.close(index);
                                            // return false;
                                        },
                                        cancel: function () {
                                            //右上角关闭回调
                                            //return false 开启该代码可禁止点击该按钮关闭
                                        }
                                    });

                                }else if(count<1){
                                    layer.msg("查无此人");
                                }else if (count>1){
                                    layer.msg("此人信息出现重复，请核实人员信息库");
                                }


                            },
                            //请求失败，包含具体的错误信息
                            error: function (e) {
                                console.log(e.status);
                                console.log(e.responseText);
                            }
                        });


                    }else if(obj.event === "delete"){
                        //点击删除按钮
                        /*
                        * 1.获取姓名和电话信息
                        * 2.向后台发送ajax请求，
                        *       后台处理内容：
                        *           （1）改变审批状态
                        *           （2）向请假待审批表备份表中插入该记录，保存操作时间
                        *           （3）向待销假表中插入该记录，保存操作时间
                        *           （4）删除请假待审批表中该记录
                        *           （5）向请假者与请假者相关领导发送信息
                        *           （6）记录请假者提醒短信触发日期，供定时任务调用
                        * 3.根据返回结果
                        *       0：操作成功
                        *           备份与删除成功，调用obj.del()方法删除此行数据，调用table.render（）刷新表格数据
                        *       -1:操作失败
                        *           备份与删除失败，调用layer.msg（）提示错误信息*/

                        //获取传值参数信息
                        person_name = queryAndBindInfo_array(data,"person_name");
                        alert()
                        phoneNum = queryAndBindInfo_array(data,"phoneNum");

                        /*ajax开始*/
                        $.ajax({
                            //请求方式
                            type: "get",
                            //请求的媒体类型
                            contentType: "application/json;charset=UTF-8",
                            //请求地址
                            url: "infoServlet?action=deleteHistoryLeaveInfo",
                            //数据，json字符串
                            //dataType:"JSON",
                            data:{"person_name":person_name,"phoneNum":phoneNum},
                            //请求成功
                            success: function (result) {
                                console.log("执行成功"+result);
                            },
                            //请求失败，包含具体的错误信息
                            error: function (e) {
                                console.log(e.status);
                                console.log(e.responseText);
                            }
                        });
                        /*ajax结束*/

                    } else if(obj.event === "notagree"){
                        //点击不同意按钮
                        /*
                        * 1.向后台发送ajax请求，
                        *       后台处理内容：
                        *           （1）改变审批状态
                        *           （2）向请假待审批表备案表中插入该记录，保存操作时间
                    *               （3）删除请假待审批表中该记录
                        * 2.根据返回结果
                        *       0：操作成功
                        *           备份与删除成功，调用obj.del()方法删除此行数据，调用table.render（）刷新表格数据
                        *       -1:操作失败
                        *           备份与删除失败，调用layer.msg（）提示错误信息*/

                        //获取传值参数信息
                        person_name = queryAndBindInfo_array(data,"person_name");
                        phoneNum = queryAndBindInfo_array(data,"phoneNum");

                        /*ajax开始*/
                        $.ajax({
                            //请求方式
                            type: "get",
                            //请求的媒体类型
                            contentType: "application/json;charset=UTF-8",
                            //请求地址
                            url: "infoServlet?action=notAgreeLeave",
                            //数据，json字符串
                            //dataType:"JSON",
                            data:{"person_name":person_name,"phoneNum":phoneNum},
                            //请求成功
                            success: function (result) {
                                console.log("执行成功"+result);
                            },
                            //请求失败，包含具体的错误信息
                            error: function (e) {
                                console.log(e.status);
                                console.log(e.responseText);
                            }
                        });
                        /*ajax结束*/
                    }
                });

            });

        </script>

    </body>

</html>
