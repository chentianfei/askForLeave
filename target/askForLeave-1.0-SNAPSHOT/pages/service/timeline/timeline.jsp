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
                            <h3 class="layui-timeline-title">2022年6月12日</h3>
                            <p>修复了一些已知问题、优化部分查询逻辑、上线部分新功能</p>
                            <ul>
                                <li>修复了搜索产生的人员信息无法进行数据更新的问题</li>
                                <li>修复了人员信息搜索无法通过性别搜索的问题</li>
                                <li>修复了人员信息搜索无法通过性别搜索的问题</li>
                                <li>优化了历史记录查询界面的数据查询逻辑</li>
                                <li>新增了请假信息校验，通过为回执单新增信息校验二维码，实现数据校验</li>
                            </ul>
                        </div>
                    </li>

                    <li class="layui-timeline-item">
                       <i class="layui-icon layui-timeline-axis"></i>
                        <div class="layui-timeline-content layui-text">
                            <h3 class="layui-timeline-title">2022年5月26日</h3>
                            <p>3.0系统上线</p>
                            <ul>
                                <li>新版系统上线</li>
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
