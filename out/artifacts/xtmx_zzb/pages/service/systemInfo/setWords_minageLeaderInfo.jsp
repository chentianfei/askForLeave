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
            <%--单位信息--%>

            <div class="layui-row">
                <div class="layui-col-md12">
                    <div class="layui-row">
                        <div class="layui-col-md12">
                            <%--数据展示--%>
                            <table class="layui-hide" id="office_info" lay-filter="office_info"></table>
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
                                        <table class="layui-hide" id="leader_info"
                                               lay-filter="leader_info"></table>

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
                <input  type="hidden" class="layui-input" name="office_id"
                        style="display:none" id="office_id" >
            </div>
        </div>


        <%--隐藏域--%>
        <div class="layui-form-item">
            <div class="layui-inline">
                <input  type="hidden" class="layui-input" name="leader_id"
                        style="display:none" id="leader_id" >
            </div>
        </div>

    </form>

    <%--头部工具--%>
    <script type="text/html" id="toolbar_header">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="bindLeader" id="bindLeader">新增领导</button>
        </div>
    </script>

    <%--行工具--%>
    <script type="text/html" id="toolbar_line">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="updateLeaderInfo">修改领导信息</a>
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
            var office_id = parent.$("#office_id:hidden").val();
            //给隐藏域传值
            $("#office_id:hidden").val(office_id);

            //初始化单位信息表
            var office_info_table =table.render({
                elem: '#office_info'
                ,url:'systemDataServlet?action=queryOfficeByOfficeId'
                ,title: '单位信息表'
                ,where: {
                    office_id:  office_id
                }
                ,cols: [
                   [
                       {field:'id', title:'单位id',align:"center",width:150}
                       ,{field:'office_name', title:'单位名称',align:"center"}
                   ]
                ]
                ,defaultToolbar: []
                ,text: {
                    none: '暂未绑定领导，请点击左上角按钮进行绑定' //默认：无数据。
                }
            });

            //初始化领导信息表
            var leader_info_table = table.render({
                elem: '#leader_info'
                ,url:'systemDataServlet?action=queryOfficeLeaderByOfficeId'
                ,toolbar: '#toolbar_header' //开启头部工具栏，并为其绑定左侧模板
                ,title: '领导信息表'
                ,where: {
                    office_id: office_id
                }
                ,cols: [
                    [
                        {field:'office_leader_name', title:'单位领导姓名',align:"center"}
                        ,{field:'office_leader_type', title:'领导属性',align:"center", width:150}
                        ,{field:'office_leader_phone', title:'联系电话',align:"center", width:120}
                        ,{fixed: 'right', title:'操作', toolbar: '#toolbar_line',align:"center", width:200}
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
                            content: "pages/service/systemInfo/setWords_minageLeaderInfo_addLeaderInfo.jsp",
                            yes:function (index, layero) {
                                var body = parent.layer.getChildFrame('body', index);

                                // 找到隐藏的提交按钮模拟点击提交
                                body.find('#addLeaderInfoSubmit').click();

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
                            url : 'systemDataServlet?action=deleteOfficeLeaderByLeaderId',
                            data : {
                                id : data.id
                            },
                            dataType : 'json',
                            success : function(data) {
                                // 成功提示框
                                parent.layer.msg('已删除该领导', {
                                    icon : 6,
                                });

                                //重载表格
                                parent.layui.table.reload('leader_info_table', {
                                    url: 'systemDataServlet?action=queryOfficeLeaderByOfficeId'
                                    ,page: {
                                        curr: currentPage //重新从第 1 页开始
                                    }
                                    ,where: {
                                        office_id: office_id
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
                else if(obj.event === 'updateLeaderInfo'){
                    var updateOfficeLeaderInfoLayer = layer.open({
                        type: 2,
                        title: '更新领导信息',
                        maxmin: true, //开启最大化最小化按钮
                        area: ['600px', '400px'],
                        anim:2,
                        id:'LAY_layuipro',
                        resize:false,
                        content: "pages/service/systemInfo/setWords_minageLeaderInfo_updateLeaderInfo.jsp",
                        btn:['更新','取消'],
                        success: function (layero, index) {
                            var body = layer.getChildFrame('body', index);
                            //赋值，以便子页面取值
                            $("#leader_id").val(data.id);
                            //初始化表单数据的值
                            body.find("#office_leader_name").val(data.office_leader_name);
                            body.find("#office_leader_phone").val(data.office_leader_phone);
                            body.find("input[name=office_leader_type][value=正职]").attr("checked", data.office_leader_type == "正职" ? true : false);
                            body.find("input[name=office_leader_type][value=副职]").attr("checked", data.office_leader_type == "副职" ? true : false);
                            body.find("input[name=office_leader_type][value=主持工作的副职]").attr("checked", data.office_leader_type == "主持工作的副职" ? true : false);
                        },
                        yes:function (index, layero) {
                            var body = layer.getChildFrame('body', index);
                            body.find('#updateLeaderInfoSubmit').click();
                        },
                        btn2: function (index, layero) {
                            layer.close(updateOfficeLeaderInfoLayer);
                        },
                        cancel: function () {
                            layer.close(updateOfficeLeaderInfoLayer);
                        }
                    })
                }
            });


        })

    </script>
    </body>
</html>
