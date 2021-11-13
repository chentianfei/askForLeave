<%--
  Created by IntelliJ IDEA.
  User: tianfeichen
  Date: 2021/8/21
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%--添加个人--%>
    <head>
        <%--基础引入--%>
        <%@include file="/pages/common/baseinfo.jsp"%>
    </head>
    <body>
        <div style="padding: 10px">

            <form class="layui-form layui-form-pane" action="#" method="post">

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">姓名</label>
                    <div class="layui-input-inline" style="width:400px">
                        <input type="text" name="username" placeholder="请输入" lay-verify="required" id="username"
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">性别</label>
                    <div class="layui-input-block" style="width:400px">
                        <input type="radio" name="sex" value="男" title="男" checked="">
                        <input type="radio" name="sex" value="女" title="女">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">出生日期</label>
                    <div class="layui-input-inline" style="width:400px" >
                        <input type="text" class="layui-input" id="birthDate" lay-verify="required">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">民族</label>
                    <div class="layui-input-inline" style="width:400px" >
                        <input type="text" class="layui-input" id="nation" lay-verify="required">
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label" style="width: 150px">本人籍贯</label>
                        <div class="layui-input-inline" style="width: 120px">
                            <select name="province" data-area="西藏自治区" lay-filter="province">
                                <option value="">选择省</option>
                            </select>
                        </div>
                        <div class="layui-input-inline" style="width: 120px">
                            <select name="city" data-area="日喀则市" lay-filter="city">
                                <option value="">选择市</option>
                            </select>
                        </div>
                        <div class="layui-input-inline" style="width: 120px">
                            <select name="district" data-area="仲巴县" lay-filter="district">
                                <option value="">选择区</option>
                            </select>
                        </div>
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">工作单位</label>
                    <div class="layui-input-inline" style="width:400px">
                        <select id="office" name="office" lay-verify="required">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">所在类区</label>
                    <div class="layui-input-inline" style="width:400px">
                        <select name="area_class" lay-verify="required">
                            <option value="">请选择</option>
                            <option value="二类区">二类区</option>
                            <option value="三类区">三类区</option>
                            <option value="四类区">四类区</option>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">现任职务</label>
                    <div class="layui-input-inline" style="width:400px">
                        <input type="text" name="zhiwu" placeholder="请输入" lay-verify="required"
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">职级</label>
                    <div class="layui-input-inline" style="width:400px">
                        <select id="level" name="quiz1" lay-verify="required">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">联系电话</label>
                    <div class="layui-input-inline" style="width:400px">
                        <input type="tel" name="phone" lay-verify="required|phone"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">允许休假天数</label>
                    <div class="layui-input-inline" style="width:400px">
                        <input type="tel" name="phone" lay-verify="required|number"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>

                <button type="submit" class="layui-btn" style="display:none" id="submitbtn" lay-submit lay-filter="demo1">立即提交</button>
               <%-- <div class="layui-form-item" >
                    <div class="layui-input-block" style="padding-left: 50%">
                        <button type="submit" class="layui-btn" lay-submit lay-filter="demo1">立即提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>--%>

            </form>
        </div>

        <script type="text/javascript">

            layui.config({
                base: 'static/js/' //存放拓展模块的根目录
            }).extend({ //设定模块别名
                common: 'common' //如果 common.js 是在根目录，也可以不用设定别名
            });


            layui.use(['laydate','form','common'], function() {
                var laydate = layui.laydate;
                var form = layui.form;
                var $ = layui.jquery;
                var index = parent.layer.getFrameIndex(window.name);
                var common = layui.common;

                //三级地址联动
                common.showCity('province', 'city', 'district');
                laydate.render({
                    elem: '#birthDate'//指定元素
                    ,type: 'date'
                });

                //为工作单位下拉框select绑定后台数据
                $.ajax({
                    url: 'systemDataServlet?action=bindOfficeSelectData',
                    dataType: 'json',
                    type: 'post',
                    success: function(data) {
                        if (data!== null) {
                            $("#office").empty();
                            $("#office").append(new Option("请选择", "0"));
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
                            $("#level").append(new Option("请选择", "0"));
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

                form.render();
                form.on('submit(demo1)', function(data){
                    $.ajax({
                        type : 'POST',
                        url : 'createResource.do',
                        data : {
                            name : $('#name').val(),
                            url : $('#url').val(),
                            type : $('input[name]:checked').val(),
                            parentId : $('#parentId').val(),
                            permission : $('#permission').val(),
                            available : $('#available').is(':checked') === true ? 1 : 0
                        },
                        dataType : 'json',
                        success : function(data) {
                            // 成功提示框
                            parent.layer.msg('添加成功', {
                                icon : 6,
                            });
                            //关闭此页面
                            parent.layer.close(index);
                            //关闭弹框
                            //parent.layer.closeAll('iframe');
                        },
                        error : function(data) {
                            // 异常提示
                            parent.layer.msg('出现网络故障', {
                                icon : 5
                            });
                            //关闭此页面
                            parent.layer.close(index);
                            //关闭弹框
                            //parent.layer.closeAll('iframe');
                        }
                    });
                    return false;
                });
            })

        </script>
    </body>
</html>
