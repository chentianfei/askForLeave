<%--
  Created by IntelliJ IDEA.
  User: tianfeichen
  Date: 2021/8/21
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--更新个人--%>
<head>
    <%--基础引入--%>
    <%@include file="/pages/common/baseinfo.jsp"%>
</head>
<body>
<div style="padding: 10px">

    <form class="layui-form layui-form-pane">

        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px">原始密码</label>
            <div class="layui-input-inline" style="width:400px">
                <input type="password" name="oldPassword" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px">新密码</label>
            <div class="layui-input-inline" style="width:400px">
                <input type="password" name="newPassword" id="newPassword" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>


        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px">确认输入</label>
            <div class="layui-input-inline" style="width:400px">
                <input type="password" name="newPassword2" id="newPassword2" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <!-- 提交按钮 -->
        <button type="submit" class="layui-btn" style="display:none"
                id="updatePWDSubmit" lay-submit
                lay-filter="updatePWDSubmit"></button>

    </form>
</div>

<script type="text/javascript">

    layui.use(['laydate','form','common','table'], function() {
        var form = layui.form;
        var $ = layui.jquery;
        var index = parent.layer.getFrameIndex(window.name);

        $("#newPassword2").blur(function (e) {
            var newPassword2value =  $("#newPassword2").val();
            var newPasswordvalue =  $("#newPassword").val();
            if(newPassword2value!==newPasswordvalue){
                parent.layer.msg('两次输入的密码不一致，请重新输入', {
                    icon : 5
                });
                $("#newPassword2").val("");
            }
        });

        form.render();
        form.on('submit(updatePWDSubmit)', function(data){
            const sourceData = data.field;

            const oldPassword = sourceData.oldPassword;
            const newPassword = sourceData.newPassword;
            const id = parent.$("#user_id").val();

            $.ajax({
                type : 'POST',
                url : 'systemDataServlet?action=updatePasswordByUserID',
                data : {
                    id:id,
                    newPassword : newPassword,
                    oldPassword : oldPassword
                },
                dataType : 'json',
                success : function(data) {
                    if(data==1){
                        parent.layer.msg('修改成功', {
                            icon : 6,
                        });
                    }else if(data==-2){
                        // 异常提示
                        parent.layer.msg('原密码错误', {
                            icon : 5
                        });
                    }else{
                        // 异常提示
                        parent.layer.msg('修改失败', {
                            icon : 5
                        });
                    }
                    //关闭此页面
                    parent.layer.close(index);
                },
                error : function(data) {
                    // 异常提示
                    parent.layer.msg('出现网络故障', {
                        icon : 5
                    });
                    //关闭此页面
                    parent.layer.close(index);
                }
            });
            return false;
        });
    });
</script>
</body>
</html>
