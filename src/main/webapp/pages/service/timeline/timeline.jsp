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
                            <h3 class="layui-timeline-title">2022年3月6日</h3>
                            <p>优化与修复</p>
                            <ul>
                                <li>优化了一些显示信息</li>
                                <li>修复了一些销假信息抓取错误问题</li>
                                <li>新增了人员信息、待审批信息、今日到期未销假人员信息、以往到期未销假人员信息、历史请销假信息数据导出功能</li>
                                <li>全局新增了籍贯模糊查询</li>
                                <li>对“待审批信息查询”、“今日到期未销假人员信息”、“以往到期未销假人员信息”、
                                    “所有待销假人员”、“历史记录查询”页面布局进行了优化</li>
                            </ul>
                        </div>
                    </li>
                    <li class="layui-timeline-item">
                        <i class="layui-icon layui-timeline-axis"></i>
                        <div class="layui-timeline-content layui-text">
                            <h3 class="layui-timeline-title">2022年3月4日</h3>
                            <p>优化与修复</p>
                            <ul>
                                <li>修复请假操作中无法对较大数据进行登记的问题</li>
                                <li>优化了绑定领导的操作逻辑</li>
                                <li>对“待审批信息查询”、“待销假信息查询”、“历史请销假信息查询”页面布局进行了优化</li>
                            </ul>
                        </div>
                    </li>
                    <li class="layui-timeline-item">
                        <i class="layui-icon layui-timeline-axis"></i>
                        <div class="layui-timeline-content layui-text">
                            <h3 class="layui-timeline-title">2022年3月1日</h3>
                            <p>优化与修复</p>
                            <ul>
                                <li>对操作逻辑进行了优化</li>
                                <li>修复了发送提醒短信后刷新失效的问题</li>
                            </ul>
                        </div>
                    </li>
                    <li class="layui-timeline-item">
                        <i class="layui-icon layui-timeline-axis"></i>
                        <div class="layui-timeline-content layui-text">
                            <h3 class="layui-timeline-title">2022年2月25日</h3>
                            <p>修复以下问题</p>
                            <ul>
                                <li>在所有面向领导发送的短信内容中，
                                    对单位数据的抓取由原来抓取领导所在单位
                                    变成现在抓取请假者所在单位
                                </li>
                            </ul>
                        </div>
                    </li>
                    <li class="layui-timeline-item">
                        <i class="layui-icon layui-timeline-axis"></i>
                        <div class="layui-timeline-content layui-text">
                            <h3 class="layui-timeline-title">2022年2月23日</h3>
                            <p>新增以下功能</p>
                            <ul>
                                <li>显示所有到期未销假人员</li>
                                <li>可为今日到期未销假人员、所有到期未销假人员发送提醒短信</li>
                                <li>其中所有到期未销假人员发送提醒短信频率为一日一次</li>
                                <li>若发送失败，则可再次发送；发送成功后，按钮自动置灰</li>
                            </ul>
                        </div>
                    </li>
                    <li class="layui-timeline-item">
                        <i class="layui-icon layui-timeline-axis"></i>
                        <div class="layui-timeline-content layui-text">
                            <h3 class="layui-timeline-title">2021年12月13日</h3>
                            <p>
                                <br>按照设计方案完成系统部署上线
                            </p>
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
