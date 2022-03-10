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
        <%@include file="/pages/common/baseinfo.jsp"%>
        <style>
            #relatedLeader{
                margin-top: 5px;
            }
        </style>

    </head>
    <body>
        <div class="layui-layout layui-layout-admin">
            <%--头部信息--%>
            <%@include file="/pages/common/header.jsp"%>

            <%--左侧导航--%>
            <%@include file="/pages/common/menu.jsp"%>

            <%--主体信息--%>
            <div class="layui-body layui-bg-gray" style="padding: 20px">

                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
                    <legend>本系统研发历程</legend>
                </fieldset>

                <ul class="layui-timeline">

                    <li class="layui-timeline-item">
                        <i class="layui-icon layui-timeline-axis"></i>
                        <div class="layui-timeline-content layui-text">
                            <h3 class="layui-timeline-title">2022年3月10日</h3>
                            <p>修复</p>
                            <ul>
                                <li>修复了领导绑定中的问题</li>
                            </ul>
                        </div>
                    </li>

                    <li class="layui-timeline-item">
                        <i class="layui-icon layui-timeline-axis"></i>
                        <div class="layui-timeline-content layui-text">
                            <h3 class="layui-timeline-title">2022年3月6日</h3>
                            <p><br>修复与优化</p>
                            <ul>
                                <li>对人员信息管理页面的绑定领导功能出现的异常进行了修复</li>
                                <li>取消人员信息管理页面查询后条件自动清空的设定</li>
                                <li>对请假审批页面的布局进行了优化</li>
                                <li>全局新增籍贯模糊查询</li>
                                <li>将到期未销假人员页面分为今日到期未销假人员、以往到期未销假人员页面</li>
                                <li>在今日到期未销假人员、以往到期未销假人员新增查询功能</li>
                                <li>将今日到期未销假人员、以往到期未销假人员、
                                    待销假人员的信息排序规则修改为按预计到岗时间升序排列</li>                            </ul>
                        </div>
                    </li>

                    <li class="layui-timeline-item">
                        <i class="layui-icon layui-timeline-axis"></i>
                        <div class="layui-timeline-content layui-text">
                            <h3 class="layui-timeline-title">2022年2月27日</h3>
                            <p>
                                <br>基于仁布县委组织部请销假管理系统进行优化开发
                            </p>
                            <p>优化功能点</p>
                            <ul>
                                <li>去除人员信息中的“配偶籍贯”字段</li>
                                <li>全局禁用短信发送功能</li>
                                <li>优化各查询模块功能与性能</li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>

            <%--底部信息--%>
            <div class="layui-footer">
                <%@include file="/pages/common/footer.jsp" %>
            </div>



        </div>

    </body>

</html>
