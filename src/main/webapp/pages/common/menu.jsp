<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<ul class="layui-nav layui-nav-tree layui-nav-side" lay-filter="test" >--%>
<ul class="layui-nav layui-nav-tree" lay-filter="test" >
    <li class="layui-nav-item">
        <a href="pages/service/personinformation.jsp">人员信息管理</a>
    </li>

    <li class="layui-nav-item layui-nav-itemed">
        <a href="javascript:;">请销假操作</a>
        <dl class="layui-nav-child">
            <dd><a href="pages/service/askForLeave.jsp">请假登记</a></dd>
            <dd><a href="pages/service/approvalLeave.jsp">请假审批</a></dd>
            <dd><a href="pages/service/endOfLeave.jsp">销假管理</a></dd>
        </dl>
    </li>

    <li class="layui-nav-item">
        <a href="pages/service/historyLeaveInfo.jsp">历史信息查询</a>
    </li>

    <li class="layui-nav-item layui-nav-itemed">
        <a href="javascript:;">系统设置</a>
        <dl class="layui-nav-child">
            <dd><a href="pages/service/updatePWD.jsp">修改登录密码</a></dd>
            <dd><a href="pages/service/setWords.jsp">自定义字段</a></dd>
            <dd><a href="pages/service/setAlertDay.jsp">修改短信提醒天数</a></dd>
            <dd><a href="pages/service/adminInfo.jsp">账号管理</a></dd>
        </dl>
    </li>
</ul>
