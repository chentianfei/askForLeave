<%--
  Created by IntelliJ IDEA.
  User: tianfeichen
  Date: 2021/8/21
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%--基础引入--%>
        <%@include file="/pages/common/baseinfo.jsp"%>
        <style>
            #relatedLeader{
                margin-top: 5px;
            }
        </style>
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
                                    <div class="layui-card-header layui-bg-blue">人员信息查询</div>
                                    <div class="layui-card-body">
                                        <%--查询表单开始--%>
                                        <form class="layui-form layui-form-pane" action="#" method="post">

                                            <div class="layui-form-item">

                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">姓名</label>
                                                    <div class="layui-input-inline"  style="width: 150px">
                                                        <input type="text" name="username" placeholder="请输入"
                                                               class="layui-input">
                                                    </div>
                                                </div>

                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">性别</label>
                                                    <div class="layui-input-inline" style="width: 150px" >
                                                        <select name="sex" lay-filter="">
                                                            <option value=""></option>
                                                            <option value="0">男</option>
                                                            <option value="2">女</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">联系方式</label>
                                                    <div class="layui-input-inline"  style="width: 150px">
                                                        <input type="tel" name="phone" lay-verify="phone"
                                                               placeholder="请输入" autocomplete="off" class="layui-input">
                                                    </div>
                                                </div>

                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">现任职务</label>
                                                    <div class="layui-input-inline"  style="width: 150px">
                                                        <input type="text" name="username" placeholder="请输入"
                                                               class="layui-input">
                                                    </div>
                                                </div>

                                            </div>

                                            <div class="layui-form-item">
                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">工作单位</label>
                                                    <div class="layui-input-inline"  style="width: 150px">
                                                        <select name="modules" lay-search="">
                                                            <option value=""></option>
                                                            <option value="1">layer</option>
                                                            <option value="2">form</option>
                                                        </select>
                                                    </div>
                                                </div>


                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">职级</label>
                                                    <div class="layui-input-inline"  style="width: 150px">
                                                        <select name="modules" lay-search="">
                                                            <option value=""></option>
                                                            <option value="1">layer</option>
                                                            <option value="2">form</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px;">出生年月</label>
                                                    <div class="layui-input-inline" style="width: 150px;">
                                                        <input type="text" class="layui-input" id="startDate">
                                                    </div>
                                                </div>
                                            </div>

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
                                                            <option value="宁波" disabled="">宁波</option>
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
                                        <div class="layui-card-header layui-bg-blue">人员信息管理</div>
                                        <div class="layui-card-body">

                                            <%--数据展示--%>
                                                <table class="layui-hide" id="personinformation" lay-filter="personinformation"></table>

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

        <%--表格上方工具栏--%>
        <script type="text/html" id="toolbar">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm" lay-event="addAPerson" id="addAPerson">新增单个人员</button>
                <button class="layui-btn layui-btn-sm" lay-event="batchAddPerson" id="batchAddPerson" >批量新增人员</button>
                <button class="layui-btn layui-btn-xs" lay-event="uploadBtn" id="uploadBtn" ><i class="layui-icon">&#xe67c;</i>上传</button>
            </div>
        </script>

        <%--表格内部工具栏1--%>
        <script type="text/html" id="baseInfo">
            <a class="layui-btn layui-btn-xs" lay-event="update" >修改</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>

            {{# if(d.leader.length === 0){ }}
            <a type="button"
               class="layui-btn  layui-btn-xs layui-btn-primary layui-border-red layui-btn-radius"
               lay-event="bindLeader">
                绑定相关领导
            </a>
            <%--<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="unPass">未审批</a>--%>
            {{#  } }}

            {{#  if(d.leader.length >0 ){ }}
            <a type="button"
               class="layui-btn  layui-btn-xs layui-btn-primary layui-btn-radius"
               lay-event="showRelatedLeader">
                查看相关领导
            </a>
            <%--<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="pass">已审批</a>--%>
            {{#  } }}

        </script>
        <%--表格内部工具栏2--%>
        <%--<script type="text/html" id="relatedLeader">

            &lt;%&ndash;<a layui-btn-normal layui-btn-radius lay-event="edit">绑定相关领导</a>&ndash;%&gt;
        </script>--%>

        <script>
            layui.use(['table','upload','laydate','element','form', 'layer', 'util'], function(){
                var element = layui.element;
                var layer = layui.layer;
                var util = layui.util;
                var $ = layui.jquery;
                var table = layui.table;
                var form = layui.form;
                var laydate = layui.laydate;


                //全局取消回车默认事件
                document.onkeydown = function(e){
                    if(e.keyCode==13){e.preventDefault();//禁用回车的默认事件
                    }
                }

                layer.config({
                    skin:'layui-layer-molv'
                })


                //表格数据读取参数
                table.render({
                    elem: '#personinformation'
                    ,url:'personServlet?action=queryAllPerson'
                    ,toolbar: '#toolbar' //开启头部工具栏，并为其绑定左侧模板
                    ,title: '人员信息表'
                    ,request: {
                        pageName: 'curr' //页码的参数名称，默认：page
                        ,limitName: 'nums' //每页数据量的参数名，默认：limit
                    }
                    ,limit:5
                    ,limits:[5,10,15,20]
                    ,cols: [[
                        //{type: 'checkbox', fixed: 'left'}
                        {field:'person_id', title:'人员编号',width: 120}
                        ,{field:'name', title:'姓名',width: 100}
                        ,{field:'sex', title:'性别',width: 60}
                        ,{field:'birthDate', title:'出生年月',width: 120}
                        ,{field:'area_class', title:'所在类区',width: 100}
                        ,{field:'nation', title:'民族',width: 100}
                        ,{field:'nativePlace', title:'本人籍贯'}
                        ,{field:'office', title:'工作单位'}
                        ,{field:'post', title:'现任职务'}
                        ,{field:'level', title:'职级',width: 150}
                        ,{field:'phone', title:'联系电话',width: 150}
                        ,{field:'allow_Leave_Days', title:'允许休假天数',width: 120}
                        ,{field:'leader', title:'相关领导',hide:true,width: 120}
                        ,{fixed: 'right', title:'操作', toolbar: '#baseInfo',width:220}
                    ]]
                    ,page: true
                });

                /*,defaultToolbar: [
                        'exports',
                        'filter',
                        'print',
                        { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                            title: '提示'
                            ,layEvent: 'LAYTABLE_TIPS'
                            ,icon: 'layui-icon-tips'}
                    ]*/

                table.on('toolbar(personinformation)', function(obj){
                    var checkStatus = table.checkStatus(obj.config.id);
                    switch(obj.event){
                        case 'addAPerson':
                            //obj.event：对应的事件
                            //选择的行的具体内容：checkStatus.data;
                            //选中的行数：checkStatus.data.length
                            //是否全选的状态：checkStatus.isAll

                            /*点击新增单个人员按钮后的弹窗*/
                            layer.open({
                                type: 2,
                                title: '新增人员',
                                shadeClose: true,
                                shade: false,
                                maxmin: true, //开启最大化最小化按钮
                                area: ['600px', '600px'],
                                content: "pages/service/personinfomation/_addPerson.jsp",
                                anim:2,
                                resize:false,
                                btn:['提交','重置'],
                                yes:function (index, layero) {
                                    var body = layer.getChildFrame('body', index);
                                    // 找到隐藏的提交按钮模拟点击提交
                                    body.find('#submitbtn').click();
                                    return false;
                                    //按钮【按钮一】的回调
                                },
                                btn2: function (index, layero) {
                                    layer.msg("重置表单");
                                    //按钮【按钮二】的回调

                                    //return false 开启该代码可禁止点击该按钮关闭
                                },
                                cancel: function () {
                                    //右上角关闭回调
                                    //return false 开启该代码可禁止点击该按钮关闭
                                }
                            });

                            break;
                        case 'batchAddPerson':
                            /*点击批量添加人员按钮后的操作*/
                            break;
                        case 'uploadBtn':
                            break;

                       /* //自定义头工具栏右侧图标 - 提示
                        case 'LAYTABLE_TIPS':
                            layer.alert('这是工具栏右侧自定义的一个图标按钮');
                            break;*/
                    };
                });

                /*设定行工具事件*/
                table.on('tool(personinformation)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                    var data = obj.data; //获得当前行数据
                    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                    var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

                    if(layEvent === 'update'){
                        //更新人员信息
                        layer.open({
                            type: 2,
                            title: '更新人员信息',
                            shadeClose: true,
                            shade: false,
                            maxmin: true, //开启最大化最小化按钮
                            area: ['600px', '600px'],
                            content: "pages/service/personinfomation/_updatePerson.jsp",
                            anim:2,
                            resize:false,
                            btn:['提交','重置'],
                            yes:function (index, layero) {
                                var body = layer.getChildFrame('body', index);
                                // 找到隐藏的提交按钮模拟点击提交
                                body.find('#submitbtn').click();
                                return false;
                                //按钮【按钮一】的回调
                            },
                            btn2: function (index, layero) {
                                layer.msg("重置表单");
                                //按钮【按钮二】的回调

                                //return false 开启该代码可禁止点击该按钮关闭
                            },
                            cancel: function () {
                                //右上角关闭回调
                                //return false 开启该代码可禁止点击该按钮关闭
                            }
                        });
                    } else if(layEvent === 'delete'){
                        //删除数据
                        // alert("[obj.tr]:" + obj.tr);
                        // alert("[obj.data]:" + obj.data);
                        // alert("[obj.event]:" + obj.event);
                        layer.confirm('真的删除行么', function(index){
                            obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                            layer.close(index);

                            //向服务端发送删除指令
                            /*layer.alert(JSON.stringify(data), {
                                title: '当前行数据：'
                            });*/

                        });
                    } else if(layEvent === 'bindLeader'){ //绑定相关领导
                        //do something
                        layer.open({
                            type: 2,
                            title: '绑定相关领导',
                            shadeClose: true,
                            shade: false,
                            maxmin: true, //开启最大化最小化按钮
                            area: ['600px', '600px'],
                            content: "pages/service/personinfomation/_bindLeader.jsp",
                            anim:2,
                            resize:false,
                            btn:['提交','重置'],
                            yes:function (index, layero) {
                                var body = layer.getChildFrame('body', index);
                                // 找到隐藏的提交按钮模拟点击提交
                                body.find('#submitbtn').click();
                                return false;
                                //按钮【按钮一】的回调
                            },
                            btn2: function (index, layero) {
                                layer.msg("重置表单");
                                //按钮【按钮二】的回调

                                //return false 开启该代码可禁止点击该按钮关闭
                            },
                            cancel: function () {
                                //右上角关闭回调
                                //return false 开启该代码可禁止点击该按钮关闭
                            }
                        });

                        //同步更新缓存对应的值
                        obj.update({
                            username: '123'
                            ,title: 'xxx'
                        });
                    } else if(layEvent === 'showRelatedLeader'){ //查看相关领导
                        //do something
                        layer.open({
                            type: 2,
                            title: '查看相关领导',
                            shadeClose: true,
                            shade: false,
                            maxmin: true, //开启最大化最小化按钮
                            area: ['600px', '600px'],
                            content: "pages/service/personinfomation/_bindLeader.jsp",
                            anim:2,
                            resize:false,
                            btn:['提交','重置'],
                            yes:function (index, layero) {
                                var body = layer.getChildFrame('body', index);
                                // 找到隐藏的提交按钮模拟点击提交
                                body.find('#submitbtn').click();
                                return false;
                                //按钮【按钮一】的回调
                            },
                            btn2: function (index, layero) {
                                layer.msg("重置表单");
                                //按钮【按钮二】的回调

                                //return false 开启该代码可禁止点击该按钮关闭
                            },
                            cancel: function () {
                                //右上角关闭回调
                                //return false 开启该代码可禁止点击该按钮关闭
                            }
                        });

                        //同步更新缓存对应的值
                        obj.update({
                            username: '123'
                            ,title: 'xxx'
                        });
                    }
                });

                laydate.render({
                    elem: '#startDate'//指定元素
                    ,type:'month'
                });

                //上传功能js
                var upload = layui.upload;
                upload.render({
                    elem: '#batchAddPerson' //绑定元素
                    ,url: '/upload/' //上传接口
                    ,auto: false //选择文件后不自动上传
                    ,accept:'file'
                    ,bindAction: '#uploadBtn'
                    ,done: function(res){
                        //上传完毕回调
                    }
                    ,error: function(){
                        //请求异常回调
                    }
                    ,progress: function(n, elem, e){
                        element.progress('demo', n + '%'); //可配合 layui 进度条元素使用
                        if(n == 100){
                            layer.msg('上传完毕', {icon: 1});
                        }
                    }
                });

            });
        </script>

    </body>

</html>
