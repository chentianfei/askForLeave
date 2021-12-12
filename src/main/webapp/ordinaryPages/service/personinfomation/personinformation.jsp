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
        <%@include file="/ordinaryPages/common/baseinfo.jsp"%>
        <style>
            #relatedLeader{
                margin-top: 5px;
            }
        </style>

    </head>
    <body>
        <div class="layui-layout layui-layout-admin">
            <%--头部信息--%>
            <%@include file="/ordinaryPages/common/header.jsp"%>

            <%--左侧导航--%>
            <%@include file="/ordinaryPages/common/menu.jsp"%>

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
                                                               class="layui-input" autocomplete=“off”>
                                                    </div>
                                                </div>

                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">性别</label>
                                                    <div class="layui-input-inline" style="width: 150px" >
                                                        <select name="sex" lay-search autocomplete=“off”>
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
                                                    <div class="layui-input-inline"  style="width: 150px" >
                                                        <select id="nation" name="nation" lay-search >
                                                            <option value=""></option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px;">出生日期</label>
                                                    <div class="layui-input-inline" style="width: 150px;" >
                                                        <input type="text" name="birthDate" autocomplete=“off”
                                                               class="layui-input" id="birthDate">
                                                    </div>
                                                </div>

                                                <%--<div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">工作单位</label>
                                                    <div class="layui-input-inline"  style="width: 150px" >
                                                        <select id="office" name="office" lay-search>
                                                            <option value="${sessionScope.user.office}" selected>${sessionScope.user.office}</option>
                                                        </select>
                                                    </div>
                                                </div>--%>


                                            </div>

                                            <div class="layui-form-item">

                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">现任职务</label>
                                                    <div class="layui-input-inline"  style="width: 150px">
                                                        <input type="text" name="post" placeholder="请输入" autocomplete=“off”
                                                               class="layui-input">
                                                    </div>
                                                </div>

                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width: 100px">职级</label>
                                                    <div class="layui-input-inline"  style="width: 150px">
                                                        <select id="level" name="level" lay-search >
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="layui-inline">
                                                    <label class="layui-form-label" style="width:100px">所在类区</label>
                                                    <div class="layui-input-inline" style="width:150px">
                                                        <select name="area_class" lay-search >
                                                            <option value="">请选择</option>
                                                            <option value="二类区">二类区</option>
                                                            <option value="三类区">三类区</option>
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

                <%--表格展示--%>
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
                <%@include file="/ordinaryPages/common/footer.jsp" %>
            </div>

            <%--接受person_id的值，以便子页面获取--%>
            <input  type="hidden" class="layui-input"
                    name="person_id"
                    style="display:none"
                    id="person_id"  />

        </div>

        <%--表格上方工具栏--%>
        <script type="text/html" id="toolbar">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm" lay-event="addAPerson" id="addAPerson">新增单个人员</button>
                <%--<button class="layui-btn layui-btn-sm" lay-event="batchAddPerson" id="batchAddPerson" >批量新增人员</button>--%>
                <%--<button class="layui-btn layui-btn-xs" lay-event="uploadBtn" id="uploadBtn" ><i class="layui-icon">&#xe67c;</i>上传</button>--%>
            </div>
        </script>

        <%--表格内部工具栏--%>
        <script type="text/html" id="baseInfo">
            <a class="layui-btn layui-btn-xs" lay-event="update" >修改</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>

            {{# if(d.leader.length === 0){ }}
            <a type="button"
               class="layui-btn  layui-btn-xs layui-btn-primary layui-border-red layui-btn-radius"
               lay-event="bindLeader">
                暂未绑定领导
            </a>
            <%--<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="unPass">未审批</a>--%>
            {{#  } }}

            {{#  if(d.leader.length >0 ){ }}
            <a type="button"
               class="layui-btn  layui-btn-xs layui-btn-primary layui-border-green  layui-btn-radius"
               lay-event="showRelatedLeader">
                已绑定{{ d.leader.length }}位领导
            </a>
            <%--<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="pass">已审批</a>--%>
            {{#  } }}

        </script>

        <script>

            let username = "${sessionScope.username}";

            layui.use(['table','upload','laydate','common','element','form',
                'layer', 'util','laypage'], function(){
                var common = layui.common;
                var element = layui.element;
                var layer = layui.layer;
                var util = layui.util;
                var $ = layui.jquery;
                var table = layui.table;
                var form = layui.form;
                var laydate = layui.laydate;
                var laypage = layui.laypage;

                bindLevelSelectData();
                bindNationSelectData();

                //表格数据读取参数
                table.render({
                    elem: '#personinformation'
                    ,url:'personServlet?action=queryAllPerson'
                    ,where:{
                        user_office:"${sessionScope.user.office}"
                    }
                    ,toolbar: '#toolbar' //开启头部工具栏，并为其绑定左侧模板
                    ,title: '人员信息表'
                    ,request: {
                        pageName: 'curr' //页码的参数名称，默认：page
                        ,limitName: 'nums' //每页数据量的参数名，默认：limit
                    }
                    ,limit:5
                    ,limits:[5,10,15]
                    ,cols: [[
                        {field:'name', title:'姓名',align:"center",width: 100}
                        ,{field:'sex', title:'性别',align:"center",width: 60}
                        ,{field:'birthDate',title:'出生日期',width: 140,align:"center"}
                        ,{field:'area_class', title:'所在类区',align:"center",width: 85}
                        ,{field:'nation', title:'民族',align:"center",width: 80}
                        ,{field:'nativePlace', title:'本人籍贯',align:"center",width: 240}
                        ,{field:'office', title:'工作单位',align:"center"}
                        ,{field:'post', title:'现任职务',align:"center",width: 160}
                        ,{field:'level', title:'职级',align:"center",width: 120}
                        ,{field:'phone', title:'联系电话',align:"center",width: 125}
                        ,{field:'allow_Leave_Days', title:'允许休假天数',align:"center",width: 120}
                        ,{fixed: 'right', title:'操作',align:"center", toolbar: '#baseInfo',width:220}
                    ]]
                    ,page: true
                });

                /*设定表格工具事件*/
                table.on('toolbar(personinformation)', function(obj){
                    switch(obj.event){
                        case 'addAPerson':
                            /*点击新增单个人员按钮后的弹窗*/
                            layer.open({
                                type: 2,
                                title: '新增人员',
                                maxmin: true, //开启最大化最小化按钮
                                area: ['600px', '680px'],
                                content: "ordinaryPages/service/personinfomation/_addPerson.jsp",
                                anim:2,
                                id:'LAY_layuipro',
                                btn:['提交','重置'],
                                yes:function (index, layero) {
                                    var body = layer.getChildFrame('body', index);
                                    body.find('#addPersonSubmit').click();
                                },
                                btn2: function (index, layero) {
                                    var body = layer.getChildFrame('body', index);
                                    body.find('#addPersonReset').click();
                                    return false;// 开启该代码可禁止点击该按钮关闭
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

                    };
                });

                /*设定行工具事件*/
                table.on('tool(personinformation)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                    var data = obj.data; //获得当前行数据
                    //解析当前行数据

                    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                    var deleteLayer;
                    var updateLayer;
                    var bindLeaderLayer;
                    var showRelatedLeaderLayer;

                    var person_id = data.person_id;
                    $("#person_id").val(person_id);

                    //更新人员信息
                    if(layEvent === 'update'){
                        updateLayer = layer.open({
                            type: 2,
                            title: '更新人员信息',
                            maxmin: true, //开启最大化最小化按钮
                            area: ['600px', '600px'],
                            content: "ordinaryPages/service/personinfomation/_updatePerson.jsp",
                            anim:2,
                            id:'LAY_layuipro',
                            resize:false,
                            btn:['更新','取消'],
                            success: function (layero, index) {
                                var body = layer.getChildFrame('body', index);
                                var iframeWin = window[layero.find('iframe')[0]['name']];

                                //初始化表单数据的值
                                body.find("input[name=sex][value=男]").attr("checked", data.sex == "男" ? true : false);
                                body.find("input[name=sex][value=女]").attr("checked", data.sex == "女" ? true : false);
                                body.find("#name").val(data.name);
                                body.find("#birthDate").val(data.birthDate);
                                body.find("#post").val(data.post);
                                body.find("#phone").val(data.phone);
                                body.find("#allow_Leave_Days").val(data.allow_Leave_Days);
                                //为工作类区绑定下拉框
                                body.find("#area_class option[value='" + data.area_class+"']")
                                    .attr("selected", "selected");
                                body.find("#person_id").val(data.person_id)

                                //通过ajax为弹框页面职级下拉框拉取当前页面数据并绑定数据库中其他数据
                                $.ajax({
                                    url: 'systemDataServlet?action=queryLevelInfo',
                                    type : 'POST',
                                    dataType : 'json',
                                    contentType : "text/html;charset=utf-8",
                                    success: function (sourceData) {
                                        var SourceData = sourceData.data;
                                        $.each(SourceData, function (index, item) {
                                            if (data.level == item.level_name) {
                                                body.find('[id=level]').append(
                                                    "<option selected>"+item.level_name+"</option>>"
                                                );
                                            } else {
                                                body.find('[id=level]').append($("<option>").attr("value", item.level_name).text(item.level_name));
                                            }
                                        });

                                        //重新渲染，特别重要，不然写的不起作用
                                        iframeWin.layui.form.render("select");

                                    }
                                })

                                //通过ajax为弹框页面民族下拉框拉取当前页面数据并绑定数据库中其他数据
                                $.ajax({
                                    url: 'systemDataServlet?action=bindNationSelectData',
                                    type : 'POST',
                                    dataType : 'json',
                                    contentType : "text/html;charset=utf-8",
                                    success: function (SourceData) {

                                        $.each(SourceData, function (index, item) {
                                            if (data.nation == item.nation_name) {
                                                body.find('[id=nation]').append(
                                                    "<option selected>"+item.nation_name+"</option>>"
                                                );
                                            } else {
                                                body.find('[id=nation]').append($("<option>").attr("value", item.nation_name).text(item.nation_name));
                                            }
                                        });

                                        //重新渲染，特别重要，不然写的不起作用
                                        iframeWin.layui.form.render("select");

                                    }
                                })

                            },
                            yes:function (index, layero) {
                                //更新按钮的回调
                                var body = layer.getChildFrame('body', index);
                                // 找到隐藏的提交按钮模拟点击提交
                                body.find('#updatePersonSubmit').click();
                                //return false 开启该代码可禁止点击该按钮关闭
                            },
                            btn2: function (index, layero) {
                                //取消按钮的回调
                                layer.close(updateLayer);
                                //return false 开启该代码可禁止点击该按钮关闭
                            },
                            cancel: function () {
                                layer.close(updateLayer);
                                //右上角关闭回调
                                //return false 开启该代码可禁止点击该按钮关闭
                            }
                        });
                    }
                    //删除人员
                    else if(layEvent === 'delete'){
                       deleteLayer = layer.confirm('真的删除行么', function(index){
                            obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                            layer.close(index);
                            //向服务端发送删除指令
                           $.ajax({
                               type : 'POST',
                               url : 'personServlet?action=deleteThePerson',
                               data : {
                                   person_id : data.person_id,
                               },
                               dataType : 'json',
                               success : function(data) {
                                   // 成功提示框
                                   layer.msg('已删除', {
                                       icon : 6,
                                   });
                                   //重载表格
                                   table.reload('personinformation', {
                                       url: 'personServlet?action=queryAllPerson'
                                       ,page: {
                                           curr: 1 //重新从第 1 页开始
                                       }
                                       ,request: {
                                           pageName: 'curr' //页码的参数名称，默认：page
                                           ,limitName: 'nums' //每页数据量的参数名，默认：limit
                                       }
                                   });
                                   //关闭此页面
                                   layer.close(deleteLayer);
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
                    //绑定相关领导
                    else if(layEvent === 'bindLeader'){
                        layer.msg('您暂无操作权限', {
                            icon : 5
                        });
                    }
                    //查看相关领导
                    else if(layEvent === 'showRelatedLeader'){
                        //do something
                        showRelatedLeaderLayer = layer.open({
                            type: 2,
                            title: '查看相关领导',
                            fixed: true,
                            maxmin: true, //开启最大化最小化按钮
                            area: ['1200px', '600px'],
                            id: "LAY_layuipro",
                            content: "ordinaryPages/service/personinfomation/_showRelatedLeader.jsp",
                            anim:2,
                            resize:false,
                            success: function (layero, index) {
                                /*不要用body.find("#subordinate_id").val(subordinate_id)获取下属id值
                                  会出现父页面将值赋给子页面有延迟，子页面获取值时，父页面还没赋值完，导致获取到空值
                                  这里直接采用获取父页面隐藏域的值的方式，父页面的值随时都有，子页面难以读取就能读取出来
                                  */
                            },
                            cancel: function () {
                                //关闭此页面
                                parent.layer.close(showRelatedLeaderLayer);
                                //右上角关闭回调
                                //return false 开启该代码可禁止点击该按钮关闭
                            }
                        });
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
                    console.log(sourceData);
                    const area_class = sourceData.area_class;
                    const birthDate = sourceData.birthDate;
                    const level = sourceData.level;
                    const name = sourceData.name;
                    const nation = sourceData.nation;

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
                    let provinceName = address.provinceName;
                    let cityName = address.cityName;
                    let districtName = address.districtName;

                    //解析解析框中的地址内容
                    const nativePlace = provinceName + '' + cityName + '' + districtName;

                    //重载表格
                    table.reload('personinformation', {
                        url: 'personServlet?action=querySomePersons'
                        ,where: {
                            //设定异步数据接口的额外参数
                            name : name,
                            sex : sex,
                            nation : nation,
                            birthDate : birthDate,
                            nativePlace:nativePlace,
                            office : "${sessionScope.user.office}",
                            post : post,
                            area_class : area_class,
                            level : level,
                            phone : phone
                        }
                        ,page:true
                        ,request: {
                            pageName: 'curr' //页码的参数名称，默认：page
                            ,limitName: 'nums' //每页数据量的参数名，默认：limit
                        }
                        ,page: {
                            curr: 1 //重新从第 1 页开始
                        }
                    });
                    return false;
                });


            });
        </script>

    </body>

</html>
