<%--
  Created by IntelliJ IDEA.
  User: tianfeichen
  Date: 2021/8/21
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%--选择销假日期--%>
    <head>
        <%--基础引入--%>
        <%@include file="/pages/common/baseinfo.jsp"%>
    </head>
    <body>
        <div style="padding: 10px">
            <div class="layui-form">
                <div class="layui-inline">
                    <label class="layui-form-label">请选择销假日期</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" id="endLeaveDate" placeholder="yyyy-MM-dd">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">请填写销假备注</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" id="endLeaveRemark">
                    </div>
                </div>

                <div class="layui-input-block">
                    <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">提交 </button>
                    <button type="reset" class="layui-btn layui-btn-normal">重置</button>
                </div>

            </div>
        </div>

        <script type="text/javascript">
            layui.use(['laydate','element','form', ],function () {
                var laydate = layui.laydate;

                //选中后的回调
                laydate.render({
                    elem: '#endLeaveDate'
                    ,done: function(value, date){
                        layer.alert('你选择的日期是：' + value + '<br>获得的对象是' + JSON.stringify(date));
                    }
                });

            });

        </script>
    </body>
</html>
