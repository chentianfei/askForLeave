<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--查看请假信息详情--%>
<head>
    <%--基础引入--%>
    <%@include file="/pages/common/baseinfo.jsp"%>
</head>
<body>
<div style="padding: 10px">
    <form class="layui-form layui-form-pane" action="">

        <div class="layui-form-item">
            <label class="layui-form-label" style="font-size: 11px">人员编号</label>
            <div class="layui-input-block">
                <input type="text" id="person_id" class="layui-input"
                       disabled autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="font-size: 11px">姓名</label>
            <div class="layui-input-block">
                <input type="text" id="name" class="layui-input"
                       disabled autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="font-size: 11px">性别</label>
            <div class="layui-input-block">
                <input type="text" id="sex" class="layui-input"
                       disabled autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="font-size: 11px">出生年月</label>
            <div class="layui-input-block">
                <input type="text" id="birthDate" class="layui-input"
                       disabled autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="font-size: 11px">民族</label>
            <div class="layui-input-block">
                <input type="text" id="nation" class="layui-input"
                       disabled autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="font-size: 11px">本人籍贯</label>
            <div class="layui-input-block">
                <input type="text" id="nativePlace" class="layui-input"
                       disabled autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="font-size: 11px">联系电话</label>
            <div class="layui-input-block">
                <input type="text" id="phone" class="layui-input"
                       disabled autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="font-size: 11px">工作单位</label>
            <div class="layui-input-block">
                <input type="text" id="office" class="layui-input"
                       disabled autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="font-size: 11px">现任职务</label>
            <div class="layui-input-block">
                <input type="text" id="post" class="layui-input"
                       disabled autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="font-size: 11px">职级</label>
            <div class="layui-input-block">
                <input type="text" id="level" class="layui-input"
                       disabled autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="font-size: 11px">所在类区</label>
            <div class="layui-input-block">
                <input type="text" id="area_class" class="layui-input"
                       disabled autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="font-size: 11px">允许休假天数</label>
            <div class="layui-input-block">
                <input type="text" id="allow_Leave_Days" class="layui-input"
                       disabled autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="font-size: 11px">相关领导</label>
            <div class="layui-input-block">
                <input type="text" id="leader" class="layui-input"
                       disabled autocomplete="off" >
            </div>
        </div>

    </form>
</div>

<script type="text/javascript">
    layui.use(["jquery","layer",'laydate',"form"],function (){
        var layer = layui.layer;
        var $ = layui.jquery;
        var form = layui.form;
        var laydate = layui.laydate;

        /*//初始化预计到岗时间
        laydate.render({
            elem: '#birthDate'
            ,format: 'yyyy年MM月dd日'
        });*/
    })
</script>

</body>
</html>
