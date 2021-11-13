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
                                        <%--<form class="layui-form layui-form-pane" action="personServlet?action=querySomePersons" method="post">--%>
                                        <form class="layui-form layui-form-pane">

                                            <div class="layui-form-item">

                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">姓名</label>
                                                    <div class="layui-input-inline"  style="width: 150px">
                                                        <input type="text" name="name" placeholder="请输入"
                                                               class="layui-input">
                                                    </div>
                                                </div>

                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">性别</label>
                                                    <div class="layui-input-inline" style="width: 150px" >
                                                        <select name="sex" lay-filter="" lay-search>
                                                            <option value=""></option>
                                                            <%--mysql中为enum枚举类型，从1开始--%>
                                                            <option value="1">男</option>
                                                            <option value="2">女</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">联系方式</label>
                                                    <div class="layui-input-inline"  style="width: 150px">
                                                        <input type="tel" name="phone"
                                                               placeholder="请输入" autocomplete="off" class="layui-input">
                                                    </div>
                                                </div>

                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">民族</label>
                                                    <div class="layui-input-inline"  style="width: 150px">
                                                        <input type="tel" name="nation"
                                                               placeholder="请输入" autocomplete="off" class="layui-input">
                                                    </div>
                                                </div>

                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px;">出生日期</label>
                                                    <div class="layui-input-inline" style="width: 150px;">
                                                        <input type="text" name="birthDate" class="layui-input" id="birthDate">
                                                    </div>
                                                </div>

                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">工作单位</label>
                                                    <div class="layui-input-inline"  style="width: 150px" lay-search>
                                                        <select id="office" name="office" lay-search="">
                                                            <option value=""></option>
                                                        </select>
                                                    </div>
                                                </div>


                                            </div>

                                            <div class="layui-form-item">

                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">现任职务</label>
                                                    <div class="layui-input-inline"  style="width: 150px" lay-search>
                                                        <input type="text" name="post" placeholder="请输入"
                                                               class="layui-input">
                                                    </div>
                                                </div>

                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">职级</label>
                                                    <div class="layui-input-inline"  style="width: 150px" lay-search>
                                                        <select id="level" name="level" lay-search="">
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width:100px">所在类区</label>
                                                    <div class="layui-input-inline" style="width:150px" lay-search>
                                                        <select name="area_class">
                                                            <option value="">请选择</option>
                                                            <option value="二类区">二类区</option>
                                                            <option value="三类区">三类区</option>
                                                            <%--<option value="四类区" selected>四类区</option>--%>
                                                            <option value="四类区">四类区</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">本人籍贯</label>
                                                    <div class="layui-input-inline" style="width: 135px">
                                                        <select name="province" <%--data-area="西藏自治区"--%> lay-filter="province">
                                                            <option value="">选择省</option>
                                                        </select>
                                                    </div>
                                                    <div class="layui-input-inline" style="width: 135px">
                                                        <select name="city" <%--data-area="日喀则市"--%> lay-filter="city">
                                                            <option value="">选择市</option>
                                                        </select>
                                                    </div>
                                                    <div class="layui-input-inline" style="width: 135px">
                                                        <select name="district" <%--data-area="仲巴县" --%>lay-filter="district">
                                                            <option value="">选择区</option>
                                                        </select>
                                                    </div>
                                                </div>

                                            </div>

                                            <div class="layui-form-item" style="padding-left: 70%">
                                                <div class="layui-input-block">
                                                    <button type="submit" class="layui-btn" lay-submit lay-filter="person_info_query">查询</button>
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

            layui.config({
                base: 'static/js/' //存放拓展模块的根目录
            }).extend({ //设定模块别名
                common: 'common' //如果 common.js 是在根目录，也可以不用设定别名
            });

            layui.use(['table','upload','laydate','element','form',
                'layer', 'util','common'], function(){
                var element = layui.element;
                var layer = layui.layer;
                var util = layui.util;
                var $ = layui.jquery;
                var table = layui.table;
                var form = layui.form;
                var laydate = layui.laydate;
                var common = layui.common;

                //三级地址联动
                common.showCity('province', 'city', 'district');

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
                    ,limits:[5,10,15]
                    ,cols: [[
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

                table.on('toolbar(personinformation)', function(obj){
                    var checkStatus = table.checkStatus(obj.config.id);

                    //obj.event：对应的事件
                    switch(obj.event){
                        case 'addAPerson':
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
                                area: ['600px', '680px'],
                                content: "pages/service/personinfomation/_addPerson.jsp",
                                anim:2,
                                resize:false,
                                id:'LAY_layuipro',
                                btn:['提交','重置'],
                                yes:function (index, layero) {
                                    var body = layer.getChildFrame('body', index);
                                    // 找到隐藏的提交按钮模拟点击提交
                                    body.find('#submitbtn').click();
                                    //return false;
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
                    var deleteLayer;
                    var updateLayer;
                    var bindLeaderLayer;
                    var showRelatedLeaderLayer;

                    if(layEvent === 'update'){
                        //更新人员信息
                        updateLayer = layer.open({
                            type: 2,
                            title: '更新人员信息',
                            shadeClose: true,
                            shade: false,
                            maxmin: true, //开启最大化最小化按钮
                            area: ['600px', '600px'],
                            content: "pages/service/personinfomation/_updatePerson.jsp",
                            anim:2,
                            id:'LAY_layuipro',
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
                    }else if(layEvent === 'delete'){
                        //删除数据
                        // alert("[obj.tr]:" + obj.tr);
                        // alert("[obj.data]:" + obj.data);
                        // alert("[obj.event]:" + obj.event);
                       deleteLayer = layer.confirm('真的删除行么', function(index){
                            obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                            layer.close(index);

                            //向服务端发送删除指令
                            /*layer.alert(JSON.stringify(data), {
                                title: '当前行数据：'
                            });*/

                        });
                    } else if(layEvent === 'bindLeader'){ //绑定相关领导
                        //do something
                        bindLeaderLayer = layer.open({
                            type: 2,
                            title: '绑定相关领导',
                            shadeClose: true,
                            shade: false,
                            maxmin: true, //开启最大化最小化按钮
                            area: ['600px', '600px'],
                            content: "pages/service/personinfomation/_bindLeader.jsp",
                            anim:2,
                            id:'LAY_layuipro',
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
                        showRelatedLeaderLayer = layer.open({
                            type: 2,
                            title: '查看相关领导',
                            shadeClose: true,
                            shade: false,
                            maxmin: true, //开启最大化最小化按钮
                            area: ['600px', '600px'],
                            id:'LAY_layuipro',
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

                //为民族下拉框select绑定数据
                $.ajax({
                    url: 'systemDataServlet?action=bindOfficeSelectData',
                    dataType: 'json',
                    type: 'post',
                    success: function(data) {
                        if (data!== null) {
                            $("#office").empty();
                            $("#office").append("<option value=''>请选择</option>");
                            $.each(data, function(index, item) {
                                $('#office').append(new Option(item.office_name));
                            });
                        } else {
                            $("#office").append(new Option("暂无数据", ""));
                        }
                        //重新渲染
                        form.render("select");
                    }
                });

                //为工作单位下拉框select绑定后台数据
                $.ajax({
                    url: 'systemDataServlet?action=bindOfficeSelectData',
                    dataType: 'json',
                    type: 'post',
                    success: function(data) {
                        if (data!== null) {
                            $("#office").empty();
                            $("#office").append("<option value=''>请选择</option>");
                            $.each(data, function(index, item) {
                                $('#office').append(new Option(item.office_name));
                            });
                        } else {
                            $("#office").append(new Option("暂无数据", ""));
                        }
                        //重新渲染
                        form.render("select");
                    }
                });

                //为职级下拉框select绑定后台数据
                $.ajax({
                    url: 'systemDataServlet?action=bindLevelSelectData',
                    dataType: 'json',
                    type: 'post',
                    success: function(data) {
                        if (data!== null) {
                            $("#level").empty();
                            $("#level").append("<option value=''>请选择</option>");
                            $.each(data, function(index, item) {
                                $('#level').append(new Option(item.level_name));
                            });
                        } else {
                            $("#level").append(new Option("暂无数据", ""));
                        }
                        //重新渲染
                        form.render("select");
                    }
                });

                laydate.render({
                    elem: '#birthDate'//指定元素
                    ,type:'date'
                    ,format: 'yyyy-MM-dd'
                });

                //监听查询模块提交事件
                form.on('submit(person_info_query)', function(data){
                    const sourceData = data.field;
                    const area_class = sourceData.area_class;
                    const birthDate = sourceData.birthDate;
                    const level = sourceData.level;
                    const name = sourceData.name;
                    const nation = sourceData.nation;
                    const office = sourceData.office;
                    const phone = sourceData.phone;
                    const sex = sourceData.sex;
                    const post = sourceData.post;

                    //解析解析框中的地址内容
                    const city = sourceData.city;
                    const district = sourceData.district;
                    const province = sourceData.province;
                    // 通过地址code码获取地址名称
                    var address = common.getCity({
                        province,
                        city,
                        district
                    });
                    var provinceName = address.provinceName;
                    var cityName = address.cityName;
                    var districtName = address.districtName;
                    var nativePlace = provinceName+''+cityName+''+districtName;

                    table.reload('personinformation', {
                        url: 'personServlet?action=querySomePersons'
                        ,where: {
                            //设定异步数据接口的额外参数
                            name : name,
                            sex : sex,
                            nation : nation,
                            birthDate : birthDate,
                            nativePlace:nativePlace,
                            office : office,
                            post : post,
                            area_class : area_class,
                            level : level,
                            phone : phone
                        }
                        ,page: {
                            curr: 1 //重新从第 1 页开始
                        }
                        ,request: {
                            pageName: 'curr' //页码的参数名称，默认：page
                            ,limitName: 'nums' //每页数据量的参数名，默认：limit
                        }
                        //,height: 300
                    });

                   /* $.ajax({
                        type : 'POST',
                        url : 'personServlet?action=querySomePersons',
                        data :{
                            name : name,
                            sex : sex,
                            nation : nation,
                            birthDate : birthDate,
                            nativePlace:nativePlace,
                            office : office,
                            post : post,
                            area_class : area_class,
                            level : level,
                            phone : phone
                        },
                        dataType : 'json',
                        success : function(data) {

                            //查询成功时重载表格

                            // 成功提示框
                            const index = parent.layer.msg('添加成功', {
                                icon : 6,
                            });
                            console.log(data);
                            //关闭此页面
                            //parent.layer.close(index);
                            //关闭弹框
                            //parent.layer.closeAll('iframe');
                        },
                        error : function(data) {
                            // 异常提示
                            const index = parent.layer.msg('出现网络故障', {
                                icon : 5
                            });
                            //关闭此页面
                            //parent.layer.close(index);
                            //关闭弹框
                            //parent.layer.closeAll('iframe');
                        }
                    });*/
                    return false;
                });

            });
        </script>

    </body>

</html>
