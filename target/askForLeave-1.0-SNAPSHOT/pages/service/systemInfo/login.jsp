<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%--请假审批页面--%>
    <head>
        <%--基础引入--%>
        <%@include file="/pages/common/baseinfo.jsp"%>
    </head>
    <body>
        <div class="layui-layout layui-layout-admin">
            <%--头部信息--%>
            <%@include file="/pages/common/header.jsp"%>

            <%--左侧导航--%>
            <%@include file="/pages/common/menu.jsp"%>

            <%--主体信息--%>
            <div class="layui-body layui-bg-gray" style="padding: 10px">

            </div>

        </div>


        <script>
            //JS
            layui.use(['element', 'layer', 'util'], function(){
                var element = layui.element
                    ,layer = layui.layer
                    ,util = layui.util
                    ,$ = layui.$;

                //头部事件
                util.event('lay-header-event', {
                    //左侧菜单事件
                    menuLeft: function(othis){
                        layer.msg('展开左侧菜单的操作', {icon: 0});
                    }
                    ,menuRight: function(){
                        layer.open({
                            type: 1
                            ,content: '<div style="padding: 15px;">处理右侧面板的操作</div>'
                            ,area: ['260px', '100%']
                            ,offset: 'rt' //右上角
                            ,anim: 5
                            ,shadeClose: true
                        });
                    }
                });

            });
        </script>

    </body>
</html>
