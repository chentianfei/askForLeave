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

            <form class="layui-form layui-form-pane">

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:120px">假期类型</label>
                    <div class="layui-input-inline" style="width:220px">
                        <input type="text" name="leave_type" id="leave_type"
                               lay-verify="required"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>

                <!-- 提交按钮 -->
                <button type="submit" class="layui-btn" style="display:none"
                        id="addLeaveTypeSubmit" lay-submit
                        lay-filter="addLeaveTypeSubmit"></button>

            </form>
        </div>

        <script type="text/javascript">
            layui.use(['laydate','form','common','table'], function() {
                var form = layui.form;
                var $ = layui.jquery;
                var index = parent.layer.getFrameIndex(window.name);

                form.render();

                form.on('submit(addLeaveTypeSubmit)', function(data){
                    const sourceData = data.field;

                    const leave_type = sourceData.leave_type;

                    $.ajax({
                        type : 'POST',
                        url : 'systemDataServlet?action=addALeaveType',
                        data : {
                            leave_type : leave_type
                        },
                        dataType : 'json',
                        success : function(data) {
                            if(data==1){
                                parent.layer.msg('添加成功', {
                                    icon : 6,
                                });
                                //重载表格
                                parent.layui.table.reload('leaveType', {
                                    url:'systemDataServlet?action=queryLeaveType'
                                    ,page: {
                                        curr: 1
                                    }
                                    ,request: {
                                        pageName: 'curr'
                                        ,limitName: 'nums'
                                    }
                                });
                            }else if(data == -2){
                                parent.layer.msg('该假期已被添加，请不要重复添加', {
                                    icon : 5
                                });
                            } else {
                                // 异常提示
                                parent.layer.msg('添加失败', {
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