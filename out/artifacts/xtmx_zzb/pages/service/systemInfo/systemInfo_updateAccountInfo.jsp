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
            <label class="layui-form-label" style="width:150px">用户名</label>
            <div class="layui-input-inline" style="width:400px">
                <input type="text" name="user_name" id="user_name"
                       autocomplete="off"  lay-verify="required"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px">操作人姓名</label>
            <div class="layui-input-inline" style="width:400px">
                <input type="text" name="operator" id="operator"
                       lay-verify="required"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px">操作人联系方式</label>
            <div class="layui-input-inline" style="width:400px">
                <input type="text" name="operator_phone" lay-verify="required|phone"
                       id="operator_phone" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px">工作单位</label>
            <div class="layui-input-inline" style="width:400px">
                <select id="office" name="office" lay-verify="required" lay-search>
                    <option value=""></option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px">角色选择角色</label>
            <div class="layui-input-inline" style="width:400px">
                <select id="role" name="role" lay-verify="required" lay-search>
                    <option value=""></option>
                </select>
            </div>
        </div>

        <!-- 提交按钮 -->
        <button type="submit" class="layui-btn" style="display:none"
                id="updateAccountInfoSubmit" lay-submit
                lay-filter="updateAccountInfoSubmit"></button>

    </form>
</div>

<script type="text/javascript">

    layui.use(['laydate','form','common','table'], function() {
        var form = layui.form;
        var $ = layui.jquery;
        var index = parent.layer.getFrameIndex(window.name);

        form.render();
        form.on('submit(updateAccountInfoSubmit)', function(data){
            const sourceData = data.field;

            const id = parent.$("#user_id").val();
            const operator = sourceData.operator;
            const user_name = sourceData.user_name;
            const operator_phone = sourceData.operator_phone;
            const office = sourceData.office;
            const role_id = sourceData.role;

            //获取当前页码
            var currentPage = parent.$(".layui-laypage-skip .layui-input").val();

            $.ajax({
                type : 'POST',
                url : 'systemDataServlet?action=updateUserInfo',
                data : {
                    id : id,
                    user_name:user_name,
                    operator : operator,
                    operator_phone : operator_phone,
                    office : office,
                    role_id : role_id
                },
                dataType : 'json',
                success : function(data) {
                    if(data==1){
                        parent.layer.msg('更新成功', {
                            icon : 6,
                        });
                        parent.layui.table.reload('accountInfo', {
                            url:'systemDataServlet?action=queryAccountInfo'
                            ,page: {
                                curr: currentPage
                            }
                            ,request: {
                                pageName: 'curr'
                                ,limitName: 'nums'
                            }
                        });
                    }else {
                        // 异常提示
                        parent.layer.msg('更新失败', {
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
