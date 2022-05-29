<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%--账号管理页面--%>
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
                    <%@include file="/pages/common/generating.jsp"%>
                </div>

                <%--底部信息--%>
                <div class="layui-footer">
                    <%@include file="/pages/common/footer.jsp" %>
                </div>

            </div>

            <%--用户信息表上方工具栏--%>
            <script type="text/html" id="accountInfoTableToolbar">
                <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-sm" lay-event="addAAccountInfo" id="addAAccountInfo">创建用户</button>
                </div>
            </script>

            <%--用户信息表内部工具栏--%>
            <script type="text/html" id="accountInfoTableBaseInfo">
                {{# if(d.role_id !== 1){ }}
                <a class="layui-btn layui-btn-xs" lay-event="updateAccountInfo" >修改用户信息</a>
                <a class="layui-btn layui-btn-xs" lay-event="updatePassword" >修改密码</a>
                <a class="layui-btn layui-btn-xs" lay-event="resetPassword" >重置密码</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="deleteAccountInfo">删除该用户</a>
                {{#  } }}
            </script>

            <script>
                //JS
                layui.use(['element', 'layer', 'util','table'], function(){
                    var element = layui.element
                    var layer = layui.layer
                    var util = layui.util
                    var $ = layui.$;
                    var table = layui.table;

                    /*单位信息维护开始*/
                    table.render({
                        elem: '#accountInfo'
                        ,url:'systemDataServlet?action=queryAccountInfo'
                        ,toolbar: '#accountInfoTableToolbar'
                        ,defaultToolbar: []
                        ,title: '单位信息表'
                        ,request: {
                            pageName: 'curr'
                            ,limitName: 'nums'
                        }
                        ,limit:3
                        ,limits:[3,5,7]
                        ,cols: [[
                            //{field:'id', title:'用户id',align:"center",width:150}
                            {field:'user_name', title:'用户名',align:"center"}
                            ,{field:'office', title:'所属单位',align:"center"}
                            ,{field:'operator', title:'操作人姓名',align:"center"}
                            ,{field:'operator_phone', title:'操作人联系方式',align:"center"}
                            ,{field:'role_id', title:'角色类型',align:"center"}
                            ,{fixed: 'right', title:'操作',align:"center",
                                toolbar: '#accountInfoTableBaseInfo',width:400}
                        ]]
                        ,page: true
                    });
                    //表格工具栏功能绑定
                    table.on('toolbar(accountInfo)', function(obj){
                        switch(obj.event){
                            case 'addAAccountInfo':
                                layer.open({
                                    type: 2,
                                    title: '新增用户',
                                    maxmin: true,
                                    area: ['500px', '500px'],
                                    anim:2,
                                    id:'LAY_layuipro',
                                    resize:false,
                                    content: "pages/service/systemInfo/systemInfo_addAccountInfo.jsp",
                                    btn:['提交'],
                                    yes:function (index, layero) {
                                        var body = layer.getChildFrame('body', index);
                                        body.find('#addAAccountSubmit').click();
                                    }
                                });
                                break;
                        };
                    });
                    //行工具栏功能绑定
                    table.on('tool(accountInfo)', function(obj){
                        var data = obj.data; //获得当前行数据
                        var layEvent = obj.event;
                        var user_id = $("#user_id").val(data.id);

                        if(layEvent === 'updatePassword'){
                            var updatePasswordLayer = layer.open({
                                type: 2,
                                title: '修改密码',
                                maxmin: true, //开启最大化最小化按钮
                                area: ['600px', '400px'],
                                anim:2,
                                id:'LAY_layuipro',
                                resize:false,
                                content: "pages/service/systemInfo/systemInfo_updatePWD.jsp",
                                btn:['更新','取消'],
                                yes:function (index, layero) {
                                    var body = layer.getChildFrame('body', index);
                                    body.find('#updatePWDSubmit').click();
                                },
                                btn2: function (index, layero) {
                                    layer.close(updatePasswordLayer);
                                },
                                cancel: function () {
                                    layer.close(updatePasswordLayer);
                                }
                            });
                        }
                        else if(layEvent === 'updateAccountInfo'){
                            var updateAccountInfoLayer = layer.open({
                                type: 2,
                                title: '更新用户信息',
                                maxmin: true, //开启最大化最小化按钮
                                area: ['600px', '600px'],
                                content: "pages/service/systemInfo/systemInfo_updateAccountInfo.jsp",
                                anim:2,
                                id:'LAY_layuipro',
                                resize:false,
                                btn:['更新'],
                                success: function (layero, index) {
                                    var body = layer.getChildFrame('body', index);
                                    var iframeWin = window[layero.find('iframe')[0]['name']];
                                    //初始化表单数据的值

                                    body.find("#operator").val(data.operator);
                                    body.find("#user_name").val(data.user_name);
                                    body.find("#operator_phone").val(data.operator_phone);

                                    //通过ajax为弹框页面工作单位下拉框拉取当前页面数据并绑定数据库中其他数据
                                    $.ajax({
                                        url: 'systemDataServlet?action=queryOffice',
                                        type : 'POST',
                                        dataType : 'json',
                                        contentType : "text/html;charset=utf-8",
                                        success: function (sourceData) {
                                            var SourceData = sourceData.data;
                                            $.each(SourceData, function (index, item) {
                                                if (data.office == item.office_name) {
                                                    body.find('[id=office]').append(
                                                        "<option selected>"+item.office_name+"</option>>"
                                                    );
                                                } else {
                                                    body.find('[id=office]').append($("<option>").attr("value", item.office_name).text(item.office_name));
                                                }
                                            });

                                            //重新渲染，特别重要，不然写的不起作用
                                            iframeWin.layui.form.render("select");
                                        }
                                    })

                                    //通过ajax为弹框页面角色下拉框拉取当前页面数据并绑定数据库中其他数据
                                    $.ajax({
                                        url: 'systemDataServlet?action=queryRoleInfo',
                                        type : 'POST',
                                        dataType : 'json',
                                        contentType : "text/html;charset=utf-8",
                                        success: function (Data) {
                                            var sourceData = Data.data;
                                            $.each(sourceData, function (index, item) {
                                                if (data.role_id == item.id) {
                                                    body.find('[id=role]').append(
                                                        "<option selected value="+item.id+">"+item.role_name+"-"+item.role_description+"</option>>"
                                                    );
                                                } else {
                                                    body.find('[id=role]').append($("<option>").attr("value", item.id).text(item.role_name+"-"+item.role_description));
                                                }
                                            });

                                            //重新渲染，特别重要，不然写的不起作用
                                            iframeWin.layui.form.render("select");

                                        }
                                    })

                                },
                                yes:function (index, layero) {
                                    var body = layer.getChildFrame('body', index);
                                    body.find('#updateAccountInfoSubmit').click();
                                },
                                cancel: function () {
                                    layer.close(updateAccountInfoLayer);
                                }
                            });

                        }
                        else if(layEvent === 'resetPassword'){
                            //向服务端发送删除指令
                            $.ajax({
                                type : 'POST',
                                url : 'systemDataServlet?action=resetPasswordByUserID',
                                data : {
                                    id : data.id
                                },
                                dataType : 'json',
                                success : function(data) {
                                    if(data==1){
                                        layer.msg('已重置，登录密码为123456', {
                                            icon : 6,
                                        });
                                    }else {
                                        layer.msg('重置失败', {
                                            icon : 5
                                        });
                                    }
                                },
                                error : function(data) {
                                    // 异常提示
                                    layer.msg('出现网络故障', {
                                        icon : 5
                                    });
                                }
                            });
                        }
                        else if(layEvent === 'deleteAccountInfo'){
                            var currentPage = $(".layui-laypage-skip .layui-input").val();
                            var deleteAccountInfoLayer = layer.confirm('确认删除该用户吗？', function(index){
                                //向服务端发送删除指令
                                $.ajax({
                                    type : 'POST',
                                    url : 'systemDataServlet?action=deleteAAccount',
                                    data : {
                                        id : data.id
                                    },
                                    dataType : 'json',
                                    success : function(data) {
                                        if(data==1){
                                            layer.msg('已删除', {
                                                icon : 6,
                                            });
                                            obj.del();
                                            table.reload('accountInfo', {
                                                url: 'systemDataServlet?action=queryAccountInfo'
                                                ,page: {
                                                    curr: currentPage
                                                }
                                                ,request: {
                                                    pageName: 'curr'
                                                    ,limitName: 'nums'
                                                }
                                            });
                                        }else {
                                            layer.msg('删除失败', {
                                                icon : 5
                                            });
                                        }
                                        layer.close(deleteAccountInfoLayer);
                                    },
                                    error : function(data) {
                                        // 异常提示
                                        layer.msg('出现网络故障', {
                                            icon : 5
                                        });
                                    }
                                });
                            });
                        }
                    });
                    /*单位信息维护结束*/

                });
            </script>

        </body>
</html>
