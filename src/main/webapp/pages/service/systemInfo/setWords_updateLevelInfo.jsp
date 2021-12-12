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
            <label class="layui-form-label" style="width:150px">职级id</label>
            <div class="layui-input-inline" style="width:400px">
                <input type="text" name="id" id="id" disabled
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px">职级名称</label>
            <div class="layui-input-inline" style="width:400px">
                <input type="text" name="level_name" id="level_name"
                       lay-verify="required"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <!-- 提交按钮 -->
        <button type="submit" class="layui-btn" style="display:none"
                id="updateLevelInfoSubmit" lay-submit
                lay-filter="updateLevelInfoSubmit"></button>

    </form>
</div>

<script type="text/javascript">

    layui.use(['laydate','form','common','table'], function() {
        var form = layui.form;
        var $ = layui.jquery;
        var index = parent.layer.getFrameIndex(window.name);

        form.render();

        form.on('submit(updateLevelInfoSubmit)', function(data){
            const sourceData = data.field;

            const id = sourceData.id;
            const level_name = sourceData.level_name;

            //获取当前页码
            var currentPage = parent.$(".layui-laypage-skip .layui-input:eq(1)").val();

            $.ajax({
                type : 'POST',
                url : 'systemDataServlet?action=updateLevelInfo',
                data : {
                    id : id,
                    level_name : level_name
                },
                dataType : 'json',
                success : function(data) {
                    if(data==1){
                        parent.layer.msg('更新成功', {
                            icon : 6,
                        });
                        //重载表格
                        parent.layui.table.reload('levelInfo', {
                            url:'systemDataServlet?action=queryLevelInfo'
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
