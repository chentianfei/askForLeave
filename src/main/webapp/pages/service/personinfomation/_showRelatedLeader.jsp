<%--
  Created by IntelliJ IDEA.
  User: tianfeichen
  Date: 2021/8/21
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%--查看相关领导信息--%>
    <head>
        <%--基础引入--%>
        <%@include file="/pages/common/baseinfo.jsp"%>
    </head>
    <body>
        <form class="layui-form">
            <div style="padding: 10px">
                    <%--下属信息--%>
                    <div class="layui-row">
                        <div class="layui-col-md12">
                            <div class="layui-row">
                                <div class="layui-col-md12">
                                    <%--数据展示--%>
                                    <table class="layui-hide" id="subordinate_info" lay-filter="subordinate_info"></table>
                                </div>
                            </div>
                        </div>
                    </div>
                        <%--领导信息--%>
                        <div class="layui-row">
                            <div class="layui-col-md12">
                                <div class="layui-bg-gray" style="padding: 10px;">
                                    <div class="layui-row">
                                        <div class="layui-col-md12">
                                            <div class="layui-card">
                                                <div class="layui-card-header layui-bg-blue" >领导信息</div>
                                                <div class="layui-card-body">
                                                    <%--数据展示--%>
                                                    <table class="layui-hide" id="leader_info" lay-filter="leader_info"></table>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

            </div>

            <%--隐藏域--%>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <input  type="hidden" class="layui-input" name="subordinate_id" style="display:none" id="subordinate_id" >
                </div>
            </div>

        </form>

        <%--头部工具--%>
        <script type="text/html" id="toolbar_header">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm" lay-event="bindLeader" id="bindLeader">绑定领导</button>
            </div>
        </script>


        <%--行工具--%>
        <script type="text/html" id="toolbar_line">
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="deleteLeader">删除</a>
        </script>

        <script type="text/javascript">

            layui.use(['laydate','form','table','util','element','layer'], function() {
                var laydate = layui.laydate;
                var form = layui.form;
                var table = layui.table;
                var $ = layui.jquery;
                var element = layui.element;
                var util = layui.util;
                var layer = layui.layer;
                var index = parent.layer.getFrameIndex(window.name);
                var layero_ctf;
                /*不要用body.find("#subordinate_id").val(subordinate_id)获取下属id值
                会出现父页面将值赋给子页面有延迟，子页面获取值时，父页面还没赋值完，导致获取到空值
                这里直接采用获取父页面隐藏域的值的方式，父页面的值随时都有，子页面难以读取就能读取出来
                */
                var subordinate_id = parent.$("#person_id:hidden").val();
                //给本页面subordinate_id赋值
                $("#subordinate_id").val(subordinate_id);

                //初始化下属信息表
                var subordinate_info_table =table.render({
                    elem: '#subordinate_info'
                    ,url:'personServlet?action=queryPersonInfoById'
                    ,title: '下属信息表'
                    ,where: {
                        person_id: subordinate_id
                    }
                    ,cols: [
                        [
                            {colspan:"5",title:"下属信息 ",align:"center"}
                        ],
                        [
                            {field:'name', title:'姓名',align:"center"}
                            ,{field:'sex', title:'性别',align:"center"}
                            ,{field:'office', title:'工作单位',align:"center"}
                            ,{field:'post', title:'现任职务',align:"center"}
                            ,{field:'phone', title:'联系电话',align:"center"}
                        ]
                    ]
                });

                //初始化领导信息表
                var leader_info_table = table.render({
                    elem: '#leader_info'
                    ,url:'personServlet?action=queryRelatedLeader'
                    ,toolbar: '#toolbar_header' //开启头部工具栏，并为其绑定左侧模板
                    ,title: '领导信息表'
                    ,where: {
                        subordinate_id: subordinate_id
                    }
                    ,cols: [
                        [
                            {field:'name', title:'姓名',align:"center"}
                            ,{field:'sex', title:'性别',align:"center"}
                            ,{field:'office', title:'工作单位',align:"center"}
                            ,{field:'post', title:'现任职务',align:"center"}
                            ,{field:'phone', title:'联系电话',align:"center"}
                            ,{fixed: 'right', title:'操作', toolbar: '#toolbar_line',
                            align:"center",
                            width:80}
                        ]
                    ]
                    ,defaultToolbar: []
                    ,text: {
                        none: '暂未绑定领导，请点击左上角按钮进行绑定' //默认：无数据。
                    }
                });

                //头工具栏事件
                table.on('toolbar(leader_info)', function(obj){
                    var currentPage = parent.$(".layui-laypage-skip .layui-input").val();
                    switch(obj.event){
                        case 'bindLeader':
                            parent.layer.open({
                                type: 2,
                                title: '绑定相关领导',
                                area: ['600px', '400px'],
                                fixed: true,
                                maxmin: true,
                                btn:['提交'],
                                anim:2,
                                content: "pages/service/personinfomation/_showRelatedLeader_bindLeader.jsp",
                                success: function (layero, index) {
                                    var body = parent.layer.getChildFrame('body', index);
                                    //给隐藏域传值
                                    body.find("#subordinate_id").val(subordinate_id);
                                },
                                yes:function (index, layero) {
                                    var body = parent.layer.getChildFrame('body', index);

                                    // 找到隐藏的提交按钮模拟点击提交
                                    body.find('#bindLeaderSubmit').click();
                                    layer.load(0, {
                                        shade: false,
                                        time:2000
                                    });
                                    //刷新本页面
                                    self.location.reload();

                                    //关闭此页面
                                    parent.layer.close(index);
                                },
                                cancel: function (index, layero) {
                                    //关闭此页面
                                    parent.layer.close(index);
                                }
                            });
                        break;
                    };

                });

                //监听行工具事件
                table.on('tool(leader_info)', function(obj){
                    var data = obj.data;
                    var currentPage = parent.$(".layui-laypage-skip .layui-input").val();
                    if(obj.event === 'deleteLeader'){

                        layer.confirm('确定删除该领导吗？', function(index){
                            obj.del();

                            $.ajax({
                                type : 'POST',
                                url : 'personServlet?action=deleteTheLeader',
                                data : {
                                    leader_id : data.person_id,
                                    subordinate_id : subordinate_id
                                },
                                dataType : 'json',
                                success : function(data) {
                                    // 成功提示框
                                    parent.layer.msg('已删除该领导', {
                                        icon : 6,
                                    });

                                    //重载表格
                                    parent.layui.table.reload('personinformation', {
                                        url: 'personServlet?action=queryAllPerson'
                                        ,page: {
                                            curr: currentPage //重新从第 1 页开始
                                        }
                                        ,request: {
                                            pageName: 'curr' //页码的参数名称，默认：page
                                            ,limitName: 'nums' //每页数据量的参数名，默认：limit
                                        }
                                    });

                                    //parent.layer.close(index);
                                },
                                error : function(data) {
                                    // 异常提示
                                    parent.layer.msg('出现网络故障', {
                                        icon : 5
                                    });
                                    //关闭此页面
                                    //parent.layer.close(index);
                                }
                            });

                            layer.close(index);
                        });
                    }
                });


            })

        </script>
    </body>
</html>
