<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <ul class="layui-nav layui-nav-tree" lay-filter="test">
            <li class="layui-nav-item">
                <a href="pages/service/personinfomation/personinformation.jsp">人员信息管理</a>
            </li>

            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">请销假操作</a>
                <dl class="layui-nav-child">
                    <dd><a href="pages/service/askForLeave/askForLeave.jsp">请假登记</a></dd>
                    <dd><a href="pages/service/approvalLeave/approvalLeave.jsp">请假审批</a></dd>
                    <dd><a href="pages/service/endOfLeave/endOfLeave.jsp">销假管理</a></dd>
                </dl>
            </li>

            <li class="layui-nav-item">
                <a href="pages/service/historyLeaveInfo/historyLeaveInfo.jsp">历史记录查询</a>
            </li>

            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">系统设置</a>
                <dl class="layui-nav-child">
                    <dd><a href="pages/service/systemInfo/updatePWD.jsp">修改登录密码</a></dd>
                    <dd><a href="pages/service/systemInfo/setWords.jsp">自定义字段</a></dd>
                    <dd><a href="pages/service/systemInfo/setAlertDay.jsp">修改短信提醒天数</a></dd>
                    <dd><a href="pages/service/systemInfo/adminInfo.jsp">账号管理</a></dd>
                </dl>
            </li>
        </ul>
    </div>
</div>

<script>

</script>