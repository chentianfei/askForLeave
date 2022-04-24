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
                    <%--表格展示--%>
                    <div class="layui-row">
                        <div class="layui-col-md12">
                            <div class="layui-bg-gray" style="padding: 10px;">
                                <div class="layui-row">
                                    <div class="layui-col-md12">
                                        <div class="layui-card">
                                            <div class="layui-card-header layui-bg-blue">单位信息维护</div>
                                            <div class="layui-card-body">

                                                <%--数据展示--%>
                                                <table class="layui-hide" id="officeInfo" lay-filter="officeInfo"></table>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <hr class="layui-border-green">

                    <%--表格展示--%>
                    <div class="layui-row">
                        <div class="layui-col-md12">
                            <div class="layui-bg-gray" style="padding: 10px;">
                                <div class="layui-row">
                                    <div class="layui-col-md12">
                                        <div class="layui-card">
                                            <div class="layui-card-header layui-bg-blue">职级信息维护</div>
                                            <div class="layui-card-body">

                                                <%--数据展示--%>
                                                <table class="layui-hide" id="levelInfo" lay-filter="levelInfo"></table>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <hr class="layui-border-green">

                    <%--表格展示--%>
                    <div class="layui-row">
                        <div class="layui-col-md12">
                            <div class="layui-bg-gray" style="padding: 10px;">
                                <div class="layui-row">
                                    <div class="layui-col-md12">
                                        <div class="layui-card">
                                            <div class="layui-card-header layui-bg-blue">假期类型维护</div>
                                            <div class="layui-card-body">

                                                <%--数据展示--%>
                                                <table class="layui-hide" id="leaveType" lay-filter="leaveType"></table>

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

        <%--单位信息表格上方工具栏--%>
        <script type="text/html" id="officeInfoTableToolbar">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm" lay-event="addAOfficeInfo" id="addAOfficeInfo">新增单位</button>
            </div>
        </script>

        <%--单位信息表格内部工具栏--%>
        <script type="text/html" id="officeInfoTableBaseInfo">
            {{# if(d.office_name !== "县教育局"){ }}
            <a class="layui-btn layui-btn-xs" lay-event="updateOfficeInfo" >修改单位信息</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="deleteAOfficeInfo">删除该单位</a>
            {{#  } }}
        </script>

        <%--职级信息表格上方工具栏--%>
        <script type="text/html" id="levelInfoTableToolbar">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm" lay-event="addALevelInfo" id="addALevelInfo">新增职级信息</button>
            </div>
        </script>

        <%--职级信息表格内部工具栏--%>
        <script type="text/html" id="levelInfoTableBaseInfo">
            <a class="layui-btn layui-btn-xs" lay-event="updateLevelInfo" >修改职级信息</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="deleteALevelInfo">删除该职级</a>
        </script>

        <%--假期类型表格上方工具栏--%>
        <script type="text/html" id="leaveTypeTableToolbar">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm" lay-event="addALeaveType" id="addALeaveType">新增假期类型</button>
            </div>
        </script>

        <%--假期类型表格内部工具栏--%>
        <script type="text/html" id="leaveTypeTableBaseInfo">
            <a class="layui-btn layui-btn-xs" lay-event="updateLeaveType" >修改假期名称</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="deleteLeaveType">删除该假期类型</a>
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
                //初始化
                table.render({
                    elem: '#officeInfo'
                    ,url:'systemDataServlet?action=queryOffice'
                    ,toolbar: '#officeInfoTableToolbar'
                    ,defaultToolbar: []
                    ,title: '单位信息表'
                    ,request: {
                        pageName: 'curr' //页码的参数名称，默认：page
                        ,limitName: 'nums' //每页数据量的参数名，默认：limit
                    }
                    ,limit:3
                    ,limits:[3,5,7]
                    ,cols: [[
                        {field:'id', title:'单位id',align:"center",width:150}
                        ,{field:'office_name', title:'单位名称',align:"center"}
                        ,{fixed: 'right', title:'操作',align:"center", toolbar: '#officeInfoTableBaseInfo',width:300}
                    ]]
                    ,page: true
                });
                //表格工具栏功能绑定
                table.on('toolbar(officeInfo)', function(obj){
                    switch(obj.event){
                        case 'addAOfficeInfo':
                            layer.open({
                                type: 2,
                                title: '新增单位',
                                maxmin: true, //开启最大化最小化按钮
                                area: ['500px', '200px'],
                                anim:2,
                                id:'LAY_layuipro',
                                resize:false,
                                content: "pages/service/systemInfo/setWords_addAOfficeInfo.jsp",
                                btn:['提交'],
                                yes:function (index, layero) {
                                    var body = layer.getChildFrame('body', index);
                                    body.find('#addOfficeInfoSubmit').click();
                                },
                                cancel: function () {

                                }
                            });
                            break;
                    };
                });
                //行工具栏功能绑定
                table.on('tool(officeInfo)', function(obj){
                    var data = obj.data; //获得当前行数据
                    var layEvent = obj.event;
                    //更新单位信息
                    if(layEvent === 'updateOfficeInfo'){
                        var updateOfficeInfoLayer = layer.open({
                            type: 2,
                            title: '更新单位信息',
                            maxmin: true, //开启最大化最小化按钮
                            area: ['600px', '600px'],
                            anim:2,
                            id:'LAY_layuipro',
                            resize:false,
                            content: "pages/service/systemInfo/setWords_updateOfficeInfo.jsp",
                            btn:['更新','取消'],
                            success: function (layero, index) {
                                var body = layer.getChildFrame('body', index);
                                //初始化表单数据的值
                                body.find("#id").val(data.id);
                                body.find("#office_name").val(data.office_name);
                            },
                            yes:function (index, layero) {
                                var body = layer.getChildFrame('body', index);
                                body.find('#updateOfficeInfoSubmit').click();
                            },
                            btn2: function (index, layero) {
                                layer.close(updateOfficeInfoLayer);
                            },
                            cancel: function () {
                                layer.close(updateOfficeInfoLayer);
                            }
                        });
                    }
                    //删除该单位
                    else if(layEvent === 'deleteAOfficeInfo'){
                        var currentPage = $(".layui-laypage-skip .layui-input").val();
                        var deleteAOfficeInfoLayer = layer.confirm('若删除该单位，原属于本单位的人将无单位信息，确认删除吗？', function(index){
                            //向服务端发送删除指令
                            $.ajax({
                                type : 'POST',
                                url : 'systemDataServlet?action=deleteAOfficeByOfficeId',
                                data : {
                                    id : data.id,
                                },
                                dataType : 'json',
                                success : function(data) {
                                    if(data==1){
                                        layer.msg('已删除', {
                                            icon : 6,
                                        });
                                        obj.del();
                                        //重载表格
                                        table.reload('officeInfo', {
                                            url: 'systemDataServlet?action=queryOffice'
                                            ,page: {
                                                curr: currentPage //重新从第 1 页开始
                                            }
                                            ,request: {
                                                pageName: 'curr' //页码的参数名称，默认：page
                                                ,limitName: 'nums' //每页数据量的参数名，默认：limit
                                            }
                                        });
                                    }else {
                                        // 异常提示
                                        layer.msg('删除失败', {
                                            icon : 5
                                        });
                                    }
                                    //关闭此页面
                                    layer.close(deleteAOfficeInfoLayer);
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

                /*职级信息维护开始*/
                //初始化
                table.render({
                    elem: '#levelInfo'
                    ,url:'systemDataServlet?action=queryLevelInfo'
                    ,toolbar: '#levelInfoTableToolbar'
                    ,defaultToolbar: []
                    ,title: '职级信息表'
                    ,request: {
                        pageName: 'curr' //页码的参数名称，默认：page
                        ,limitName: 'nums' //每页数据量的参数名，默认：limit
                    }
                    ,limit:3
                    ,limits:[3,5,7]
                    ,cols: [[
                        {field:'id', title:'职级编号',align:"center",width:150}
                        ,{field:'level_name', title:'职级名称',align:"center"}
                        ,{fixed: 'right', title:'操作',align:"center", toolbar: '#levelInfoTableBaseInfo',width:300}
                    ]]
                    ,page: true
                });
                //表格工具栏功能绑定
                table.on('toolbar(levelInfo)', function(obj){
                    switch(obj.event){
                        case 'addALevelInfo':
                            layer.open({
                                type: 2,
                                title: '新增职级',
                                maxmin: true,
                                area: ['500px', '200px'],
                                anim:2,
                                id:'LAY_layuipro',
                                resize:false,
                                content: "pages/service/systemInfo/setWords_addALevelInfo.jsp",
                                btn:['提交'],
                                yes:function (index, layero) {
                                    var body = layer.getChildFrame('body', index);
                                    body.find('#addLevelInfoSubmit').click();
                                },
                                cancel: function () {

                                }
                            });
                            break;
                    };
                });
                //行工具栏功能绑定
                table.on('tool(levelInfo)', function(obj){
                    var data = obj.data; //获得当前行数据
                    var layEvent = obj.event;
                    //更新单位信息
                    if(layEvent === 'updateLevelInfo'){
                        var updateLevelInfoLayer = layer.open({
                            type: 2,
                            title: '更新职级信息',
                            maxmin: true, //开启最大化最小化按钮
                            area: ['600px', '600px'],
                            anim:2,
                            id:'LAY_layuipro',
                            resize:false,
                            content: "pages/service/systemInfo/setWords_updateLevelInfo.jsp",
                            btn:['更新','取消'],
                            success: function (layero, index) {
                                var body = layer.getChildFrame('body', index);
                                //初始化表单数据的值
                                body.find("#id").val(data.id);
                                body.find("#level_name").val(data.level_name);
                            },
                            yes:function (index, layero) {
                                var body = layer.getChildFrame('body', index);
                                body.find('#updateLevelInfoSubmit').click();
                            },
                            btn2: function (index, layero) {
                                layer.close(updateLevelInfoLayer);
                            },
                            cancel: function () {
                                layer.close(updateLevelInfoLayer);
                            }
                        });
                    }
                    //删除该单位
                    else if(layEvent === 'deleteALevelInfo'){
                        var currentPage = $(".layui-laypage-skip .layui-input:eq(1)").val();
                        var deleteALevelInfoLayer = layer.confirm('若删除该职级，原属于本职级的人将无职级信息，确认删除吗？', function(index){
                            //向服务端发送删除指令
                            $.ajax({
                                type : 'POST',
                                url : 'systemDataServlet?action=deleteALevelInfoByLevelInfoId',
                                data : {
                                    id : data.id,
                                },
                                dataType : 'json',
                                success : function(data) {
                                    if(data==1){
                                        layer.msg('已删除', {
                                            icon : 6,
                                        });
                                        obj.del();
                                        //重载表格
                                        table.reload('levelInfo', {
                                            url: 'systemDataServlet?action=queryLevelInfo'
                                            ,page: {
                                                curr: currentPage //重新从第 1 页开始
                                            }
                                            ,request: {
                                                pageName: 'curr' //页码的参数名称，默认：page
                                                ,limitName: 'nums' //每页数据量的参数名，默认：limit
                                            }
                                        });
                                    }else {
                                        // 异常提示
                                        layer.msg('删除失败', {
                                            icon : 5
                                        });
                                    }
                                    //关闭此页面
                                    layer.close(deleteALevelInfoLayer);
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
                /*职级信息维护结束*/

                /*假期类型信息维护开始*/
                //初始化
                table.render({
                    elem: '#leaveType'
                    ,url:'systemDataServlet?action=queryLeaveType'
                    ,toolbar: '#leaveTypeTableToolbar'
                    ,defaultToolbar: []
                    ,title: '假期类型信息表'
                    ,request: {
                        pageName: 'curr'
                        ,limitName: 'nums'
                    }
                    ,limit:3
                    ,limits:[3,5,7]
                    ,cols: [[
                        {field:'id', title:'假期类型编号',align:"center",width:150}
                        ,{field:'leave_type', title:'假期类型名称',align:"center"}
                        ,{fixed: 'right', title:'操作',align:"center", toolbar: '#leaveTypeTableBaseInfo',width:300}
                    ]]
                    ,page: true
                });
                //表格工具栏功能绑定
                table.on('toolbar(leaveType)', function(obj){
                    switch(obj.event){
                        case 'addALeaveType':
                            layer.open({
                                type: 2,
                                title: '新增假期类型',
                                maxmin: true,
                                area: ['500px', '200px'],
                                anim:2,
                                id:'LAY_layuipro',
                                resize:false,
                                content: "pages/service/systemInfo/setWords_addALeaveType.jsp",
                                btn:['提交'],
                                yes:function (index, layero) {
                                    var body = layer.getChildFrame('body', index);
                                    body.find('#addLeaveTypeSubmit').click();
                                },
                                cancel: function () {

                                }
                            });
                            break;
                    };
                });
                //行工具栏功能绑定
                table.on('tool(leaveType)', function(obj){
                    var data = obj.data; //获得当前行数据
                    var layEvent = obj.event;
                    //更新单位信息
                    if(layEvent === 'updateLeaveType'){
                        var updateLeaveTypeLayer = layer.open({
                            type: 2,
                            title: '更新假期类型信息',
                            maxmin: true, //开启最大化最小化按钮
                            area: ['600px', '600px'],
                            anim:2,
                            id:'LAY_layuipro',
                            resize:false,
                            content: "pages/service/systemInfo/setWords_updateLeaveTypeInfo.jsp",
                            btn:['更新','取消'],
                            success: function (layero, index) {
                                var body = layer.getChildFrame('body', index);
                                //初始化表单数据的值
                                body.find("#id").val(data.id);
                                body.find("#leave_type").val(data.leave_type);
                            },
                            yes:function (index, layero) {
                                var body = layer.getChildFrame('body', index);
                                body.find('#updateLeaveTypeSubmit').click();
                            },
                            btn2: function (index, layero) {
                                layer.close(updateLeaveTypeLayer);
                            },
                            cancel: function () {
                                layer.close(updateLeaveTypeLayer);
                            }
                        });
                    }
                    //删除该单位
                    else if(layEvent === 'deleteLeaveType'){
                        var currentPage = $(".layui-laypage-skip .layui-input:eq(2)").val();
                        var deleteLeaveTypeLayer = layer.confirm('若删除该假期类型，已请该假期类型的人将无假期类型信息，确认删除吗？', function(index){
                            //向服务端发送删除指令
                            $.ajax({
                                type : 'POST',
                                url : 'systemDataServlet?action=deleteALeaveTypeByLeaveTypeId',
                                data : {
                                    id : data.id,
                                },
                                dataType : 'json',
                                success : function(data) {
                                    if(data==1){
                                        layer.msg('已删除', {
                                            icon : 6,
                                        });
                                        obj.del();
                                        //重载表格
                                        table.reload('leaveType', {
                                            url: 'systemDataServlet?action=queryLeaveType'
                                            ,page: {
                                                curr: currentPage //重新从第 1 页开始
                                            }
                                            ,request: {
                                                pageName: 'curr' //页码的参数名称，默认：page
                                                ,limitName: 'nums' //每页数据量的参数名，默认：limit
                                            }
                                        });
                                    }else {
                                        // 异常提示
                                        layer.msg('删除失败', {
                                            icon : 5
                                        });
                                    }
                                    //关闭此页面
                                    layer.close(deleteLeaveTypeLayer);
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
                /*假期类型信息维护结束*/

            });
        </script>

    </body>
</html>
