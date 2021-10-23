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
                    <label class="layui-form-label" style="width:150px">出生年月</label>
                    <div class="layui-input-inline" style="width:400px" >
                        <input type="text" class="layui-input" id="birthDate" lay-verify="required">
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label" style="width:150px">本人籍贯</label>
                        <div class="layui-input-inline" style="width: 100px">
                            <select name="quiz1" lay-verify="required">
                                <option value="">请选择省</option>
                                <option value="浙江">浙江省</option>
                                <option value="你的工号">江西省</option>
                                <option value="你最喜欢的老师">福建省</option>
                            </select>
                        </div>
                        <div class="layui-input-inline" style="width: 100px">
                            <select name="quiz2" lay-verify="required">
                                <option value="">请选择市</option>
                                <option value="杭州">杭州</option>
                                <option value="宁波" disabled="">宁波</option>
                                <option value="温州">温州</option>
                                <option value="温州">台州</option>
                                <option value="温州">绍兴</option>
                            </select>
                        </div>
                        <div class="layui-input-inline" style="width: 130px">
                            <select name="quiz3" lay-verify="required">
                                <option value="">请选择县/区</option>
                                <option value="西湖区">西湖区</option>
                                <option value="余杭区">余杭区</option>
                                <option value="拱墅区">临安市</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">工作单位</label>
                    <div class="layui-input-inline" style="width:400px">
                        <select name="quiz1" lay-verify="required">
                            <option value="">请选择</option>
                            <option value="浙江">浙江省</option>
                            <option value="你的工号">江西省</option>
                            <option value="你最喜欢的老师">福建省</option>
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
                        <select name="quiz1" lay-verify="required">
                            <option value="">请选择</option>
                            <option value="浙江">浙江省</option>
                            <option value="你的工号">江西省</option>
                            <option value="你最喜欢的老师">福建省</option>
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
            layui.use(['laydate','form'], function() {
                var laydate = layui.laydate;
                var form = layui.form;

                laydate.render({
                    elem: '#birthDate'//指定元素
                    ,type: 'month'
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
                            parent.layer.closeAll('iframe'); //关闭弹框
                        },
                        error : function(data) {
                            // 异常提示
                            parent.layer.msg('出现网络故障', {
                                icon : 5
                            });
                            parent.layer.closeAll('iframe'); //关闭弹框
                        }
                    });
                    return false;
                });
            })

        </script>
    </body>
</html>
