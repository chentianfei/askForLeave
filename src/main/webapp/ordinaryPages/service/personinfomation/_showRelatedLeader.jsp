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
        <%@include file="/ordinaryPages/common/baseinfo.jsp"%>
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
                var subordinate_id = parent.$("#person_id").val();
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
                        ]
                    ]
                    ,defaultToolbar: []
                    ,text: {
                        none: '暂未绑定领导，请联系县委组织部工作人员进行相关操作'
                    }
                });

                //头工具栏事件
                table.on('toolbar(leader_info)', function(obj){
                    switch(obj.event){
                        case 'bindLeader':
                            parent.layer.msg('您暂无操作权限', {
                                icon : 5
                            });
                        break;
                    };

                });

                //监听行工具事件
                table.on('tool(leader_info)', function(obj){
                    var data = obj.data;
                    console.log(data);
                    if(obj.event === 'deleteLeader'){
                        parent.layer.msg('您暂无操作权限', {
                            icon : 5
                        });
                    }
                });


            })

        </script>
    </body>
</html>
