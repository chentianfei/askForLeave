<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basepath = request.getScheme() +
            "://" +
            request.getServerName() +
            ":" +
            request.getServerPort() +
            request.getContextPath() +
            "/";
    pageContext.setAttribute("basepath", basepath);
%>

<!-- base:定义每个页面跳转起始的相对位置-->
<base href="<%=basepath%>">

<script type="text/javascript" src="static/js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" href="static/layui/css/layui.css">
<script type="text/javascript" src="static/layui/layui.js"></script>
<script type="text/javascript" src="static/js/city-picker.js"></script>
<script type="text/javascript" src="static/js/leave_ctf.js"></script>
<script type="text/javascript" src="static/js/jutils.min.js"></script>

<title>仲巴县人社局请销假管理系统</title>

<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
