<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%--销假审批页面--%>
    <head>
        <%--基础引入--%>
        <%@include file="/pages/common/baseinfo.jsp" %>
        <style>
            .layui-input-date{
                height: 25px;
                margin-top: 3px;
            }
            #endOfLeave {
              margin-top:3px;
            }
            #endOfLeave_quickly{
                margin-top:3px;
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
                <%--信息查询--%>
                <div class="layui-row">
                    <div class="layui-bg-gray" style="padding: 10px;">
                        <div class="layui-row">
                            <div class="layui-col-md12">
                                <div class="layui-card">
                                    <div class="layui-card-header layui-bg-blue">待销假信息查询</div>
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
                                                           style="width: 100px;">预计开始时间</label>
                                                    <div class="layui-input-inline" style="width: 150px;">
                                                        <input type="text" class="layui-input" id="startDate">
                                                    </div>
                                                </div>
                                                <%--预计到岗时间--%>
                                                <div class="layui-inline">
                                                    <label class="layui-form-label"
                                                           style="width: 100px;">预计到岗时间</label>
                                                    <div class="layui-input-inline" style="width: 150px;">
                                                        <input type="text" class="layui-input" id="endDate">
                                                    </div>
                                                </div>
                                            </div>

                                            <%--本人籍贯--%>
                                            <div class="layui-form-item">
                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">本人籍贯</label>
                                                    <div class="layui-input-inline" style="width: 100px">
                                                        <select name="quiz1">
                                                            <option value="">请选择省</option>
                                                            <option value="浙江">浙江省</option>
                                                            <option value="你的工号">江西省</option>
                                                            <option value="你最喜欢的老师">福建省</option>
                                                        </select>
                                                    </div>
                                                    <div class="layui-input-inline" style="width: 100px">
                                                        <select name="quiz2">
                                                            <option value="">请选择市</option>
                                                            <option value="杭州">杭州</option>
                                                            <option value="宁波">宁波</option>
                                                            <option value="温州">温州</option>
                                                            <option value="温州">台州</option>
                                                            <option value="温州">绍兴</option>
                                                        </select>
                                                    </div>
                                                    <div class="layui-input-inline" style="width: 130px">
                                                        <select name="quiz3">
                                                            <option value="">请选择县/区</option>
                                                            <option value="西湖区">西湖区</option>
                                                            <option value="余杭区">余杭区</option>
                                                            <option value="拱墅区">临安市</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <%--按钮区域--%>
                                            <div class="layui-form-item" style="padding-left: 70%">
                                                <div class="layui-input-block">
                                                    <button type="submit" class="layui-btn" lay-submit=""
                                                            lay-filter="demo1">提交
                                                    </button>
                                                    <button type="reset" class="layui-btn layui-btn-normal">重置</button>
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
                                        <div class="layui-card-header layui-bg-blue">待销假信息</div>
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
        <script type="text/html" id="audit">
            <button type="button" class="layui-btn layui-btn-xs" lay-event="endOfLeave" id="endOfLeave">销假</button>
            <button type="button" class="layui-btn layui-btn-xs" lay-event="endOfLeave_quickly" id="endOfLeave_quickly">快速销假</button>
        </script>

        <%--表格日期控件--%>
       <%-- <script type="text/html" id="dateTpl">
            <input type="text" class="layui-input" id="getEndLeaveDate">
        </script>--%>

        <script type="text/html" id="dateTpl">
            {{#  if(d.s_time !=''){ }}
            <input type="text" class="layui-input layui-input-date" value="{{d.s_time}}">
            {{#  } else { }}
            <input type="text" class="layui-input layui-input-date" placeholder="HH:mm">
            {{#  } }}
        </script>

        <input type="hidden" id = "endLeaveComment">

        <script>
            layui.use(['table', 'upload', 'laydate',  'form', 'layer'], function () {
                var layer = layui.layer
                var $ = layui.jquery;
                var table = layui.table;
                var form = layui.form;
                var laydate = layui.laydate;

                layer.config({
                    skin: 'layui-layer-molv'
                })

                laydate.render({
                    elem: '#startDate'//指定元素
                    , type: 'date'
                });

                laydate.render({
                    elem: '#endDate'//指定元素
                    , type: 'date'
                });

                /*需要发送给后台的数据*/
                var person_name;//用于保存人员姓名
                var phoneNum;//用于保存人员联系电话
                var getEndDate;//用于保存人员销假日期
                var endLeaveComment;//销假备注

                table.render({
                    elem: '#auditInfo'
                    , url: 'infoServlet?action=queryNotArrive'
                    , page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                        layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                        , curr: 5 //设定初始在第 5 页
                        , groups: 5 //只显示 1 个连续页码
                        , first: "首页" //不显示首页
                        , last: "末页" //不显示尾页
                    }
                    , cols: [[
                        {type: 'checkbox'}
                        , {field: 'info_id', title: '流水号', width: 100}
                        , {field: "getEndDate", title: '销假日期', width: "12%", templet: "#dateTpl"}
                        , {field: 'person_name', title: '姓名', width: "8%"}
                        , {field: 'office', title: '工作单位', width: "15%"}
                        , {field: 'job', title: '现任职务', width: "10%"}
                        , {field: 'leaveType', title: '请假类型', width: "8%"}
                        , {field: 'reason', title: '请假事由', width: "8%"}
                        , {field: 'startDate', title: '开始日期', width: "12%"}
                        , {field: 'days', title: '请假天数', width: "8%"}
                        , {field: 'permitPerson', title: '批准人', width: "8%"}
                        , {field: 'startLocation', title: '出发地', width: "10%"}
                        , {field: 'endLocation', title: '到达地', width: "10%"}
                        , {field: 'endDate', title: '预计到岗日期', width: "12%"}
                        , {field: 'phoneNum', title: '联系电话', width: "15%"}
                        , {field: 'leaveComment', title: '请假备注', width: "10%"}
                        , {fixed: 'right', title: '操作', toolbar: '#audit', width: "15%",id:"toolbar"}
                    ]]
                    , done: function (res, curr, count) {
                        //日期控件
                        $(".layui-input-date").each(function () {
                            laydate.render({
                                elem: this,
                                type: 'date',
                                value: new Date(),
                                format: "yyyy-MM-dd",
                                theme: 'grid',
                                btns: ['clear', 'confirm'],
                            });
                        });
                    }
                });

                table.on('tool(auditInfo)', function (obj) {
                    var data = obj.data;

                    if (obj.event === "endOfLeave") {
                        //点击销假按钮
                        /*
                        * 1.获取姓名、电话信息
                        * 2.弹出提示框，提示填写销假备注，并核实销假日期是否正确
                        * 3.向后台发送ajax请求，
                        *       后台处理内容：
                        *           （1）获取前端传来的姓名、电话、销假日期
                        *           （2）向待销假表备份表中插入该记录，保存操作时间
                        *           （3）向历史请假记录表中插入该记录，保存操作时间
                        *           （4）删除待销假表中该记录
                        *           （5）向销假者与销假者相关领导发送信息
                        * 4.根据返回结果
                        *       0：操作成功
                        *           备份与删除成功，调用obj.del()方法删除此行数据，调用table.render（）刷新表格数据
                        *       -1:操作失败
                        *           备份与删除失败，调用layer.msg（）提示错误信息*/

                        //1.获取姓名、电话信息
                        person_name = queryAndBindInfo_array(data,"person_name");
                        phoneNum = queryAndBindInfo_array(data,"phoneNum");
                        getEndDate = $(obj.tr).find(".layui-input-date").val();

                        //2.弹出提示框，提示填写销假备注，并核实销假日期是否正确
                      layer.msg('请确认销假时间：【'+getEndDate+"】是否正确？", {
                            time: 0 //不自动关闭
                            ,btn: ['正确', '错误']
                            ,yes: function(index){
                                layer.close(index);
                                layer.prompt(
                                    {title:"请填写销假备注"
                                    ,value: '正常到岗'
                                    ,formType: 2
                                        ,btn:["提交","返回"]}
                                    ,function (val,index2) {
                                        /*向后台发起ajax请求*/
                                        $.ajax({
                                            //请求方式
                                            type: "get",
                                            //请求的媒体类型
                                            contentType: "application/json;charset=UTF-8",
                                            //请求地址
                                            url: "infoServlet?action=manageArrive",
                                            //数据，json字符串
                                            //dataType:"JSON",
                                            data:{"person_name":data.person_name,
                                                "phoneNum":data.phoneNum,
                                                "getEndDate":getEndDate,
                                                "endLeaveComment":val},
                                            //请求成功
                                            success: function (result) {
                                                //备份与删除成功，调用obj.del()方法删除此行数据，调用table.render（）刷新表格数据
                                                obj.del();
                                                layer.msg('销假成功', {icon: 1,time: 1000});
                                                table.render();
                                            },
                                            //请求失败，包含具体的错误信息
                                            error: function (e) {
                                                console.log(e.status);
                                                console.log(e.responseText);
                                            }
                                        });
                                        layer.close(index2);
                                })
                            }
                          ,btn2:function (index) {
                              layer.close(index);
                              layer.open({
                                  type: 2,
                                  title: '请输入正确参数',
                                  shadeClose: true,
                                  shade: false,
                                  area: ['400px', '450px'],
                                  content: 'pages/service/endOfLeave/_endLeaveDate.jsp'
                              });

                          }
                        });


                    }
                    else if(obj.event === "endOfLeave_quickly") {
                        //点击快速销假按钮
                        /*
                        * 1.获取姓名、电话信息
                        * 3.向后台发送ajax请求，
                        *       后台处理内容：
                        *           （1）获取前端传来的姓名、电话、销假日期
                        *           （2）向待销假表备份表中插入该记录，保存操作时间
                        *           （3）向历史请假记录表中插入该记录，保存操作时间
                        *           （4）删除待销假表中该记录
                        *           （5）向销假者与销假者相关领导发送信息
                        * 4.根据返回结果
                        *       0：操作成功
                        *           备份与删除成功，调用obj.del()方法删除此行数据，调用table.render（）刷新表格数据
                        *       -1:操作失败
                        *           备份与删除失败，调用layer.msg（）提示错误信息*/

                        //1.获取姓名、电话信息
                        person_name = queryAndBindInfo_array(data,"person_name");
                        phoneNum = queryAndBindInfo_array(data,"phoneNum");
                        getEndDate = $(obj.tr).find(".layui-input-date").val();
                        endLeaveComment = "正常到岗";
                        //3.向后台发送ajax请求，
                        $.ajax({
                            //请求方式
                            type: "get",
                            //请求的媒体类型
                            contentType: "application/json;charset=UTF-8",
                            //请求地址
                            url: "infoServlet?action=manageArrive",
                            //数据，json字符串
                            //dataType:"JSON",
                            data:{"person_name":data.person_name,
                                "phoneNum":data.phoneNum,
                                "getEndDate":getEndDate,
                                "endLeaveComment":endLeaveComment},
                            //请求成功
                            success: function (result) {
                                //备份与删除成功，调用obj.del()方法删除此行数据，调用table.render（）刷新表格数据
                                obj.del();
                                layer.msg('销假成功', {icon: 1,time: 1000});
                                table.render();
                            },
                            //请求失败，包含具体的错误信息
                            error: function (e) {
                                console.log(e.status);
                                console.log(e.responseText);
                            }
                        });


                    }
                });
            });

        </script>

    </body>


</html>
