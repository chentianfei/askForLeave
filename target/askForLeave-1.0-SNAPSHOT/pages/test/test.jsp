
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>test</title>
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

        <link rel="stylesheet" href="static/layui/css/layui.css">
        <script type="text/javascript" src="static/js/jquery-3.6.0.js"></script>
        <script type="text/javascript" src="static/layui/layui.js"></script>

        <title>【演示】仲巴县委组织部请销假管理系统</title>

        <link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />

        <style>
            body .demo-class .layui-layer-title{background:#c00; color:#fff; border: none;}
            body .demo-class .layui-layer-btn{border-top:1px solid #E9E7E7}
            body .demo-class .layui-layer-btn a{background:#333;}
        </style>
    </head>
    <body>
        <script type="text/javascript" >
            var userAgent = window.navigator.userAgent; //取得浏览器的userAgent字符串
            if(userAgent.indexOf("Chrome") > -1){
                window.location.href = "pages/service/personinfomation/personinformation.jsp";  // 当前页面跳转
            }
            else {
                layer.open({
                    type: 1,
                    skin: 'demo-class',
                    offset: 'auto',
                    area:["600px","200px"],
                    title:"<i class='layui-icon layui-icon-face-cry' style='font-size: 15px; color: white;'></i> " +
                        "<span style='font-size: 16px'>浏览器不兼容提示</span>",
                    closeBtn: 0, //不显示关闭按钮
                    anim: 2,
                    shadeClose: true, //开启遮罩关闭
                    content:
                        "<div class='layui-panel'>"+
                        "<div style='padding: 30px;'>"+
                        "<span id='info' style='font-size:15px'></span>"+
                        "</div></div>"
                });

                $("#info").html("您当前的浏览器为："+userAgent+"</br>为保证运行效果，" +
                    "请下载并安装Chrome浏览器，否则将无法登录哦!</br>" +
                    "<a href='https://www.google.cn/chrome/' style='color: #01AAED'>点此下载<a/>");
            }
        </script>
    </body>

</html>
