<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <ul class="layui-nav layui-nav-tree" lay-filter="test">
            <li class="layui-nav-item">
                <a href="ordinaryPages/service/personinfomation/personinformation.jsp">人员信息管理</a>
            </li>

            <%--<li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">请假操作</a>
                <dl class="layui-nav-child">
                    <dd><a href="ordinaryPages/service/askForLeave/askForLeave.jsp">常规请假登记</a></dd>
                    <dd><a href="ordinaryPages/service/rest/rest.jsp">调休登记</a></dd>
                    <dd><a href="ordinaryPages/service/approvalLeave/approvalLeave.jsp">请假审批</a></dd>
                    <dd></dd>
                </dl>
            </li>--%>

           <%-- <li class="layui-nav-item">
            <a href="ordinaryPages/service/endOfLeave/endOfLeave.jsp">销假操作</a>
        </li>--%>

            <li class="layui-nav-item">
                <a href="ordinaryPages/service/historyLeaveInfo/historyLeaveInfo.jsp">历史记录查询</a>
            </li>

           <%-- <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">系统设置</a>
                <dl class="layui-nav-child">
                    <dd><a href="ordinaryPages/service/systemInfo/updatePWD.jsp">修改登录密码</a></dd>
                    <dd><a href="ordinaryPages/service/systemInfo/sendlog.jsp">自定义字段</a></dd>
                    <dd><a href="ordinaryPages/service/systemInfo/setAlertDay.jsp">修改短信提醒天数</a></dd>
                    <dd><a href="ordinaryPages/service/systemInfo/upData.jsp">账号管理</a></dd>
                </dl>
            </li>--%>
        </ul>
    </div>
</div>

<script>

</script>