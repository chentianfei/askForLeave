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
                        <input type="text" id="person_id" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="font-size: 11px">姓名</label>
                    <div class="layui-input-block">
                        <input type="text" id="person_name" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="font-size: 11px">性别</label>
                    <div class="layui-input-block">
                        <input type="text" id="sex" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="font-size: 11px">出生年月</label>
                    <div class="layui-input-block">
                        <input type="text" id="birthDate" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="font-size: 11px">民族</label>
                    <div class="layui-input-block">
                        <input type="text" id="nationality" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="font-size: 11px">本人籍贯</label>
                    <div class="layui-input-block">
                        <input type="text" id="nativePlace" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="font-size: 11px">联系电话</label>
                    <div class="layui-input-block">
                        <input type="text" id="phoneNum" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="font-size: 11px">工作单位</label>
                    <div class="layui-input-block">
                        <input type="text" id="office" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="font-size: 11px">现任职务</label>
                    <div class="layui-input-block">
                        <input type="text" id="job" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="font-size: 11px">职级</label>
                    <div class="layui-input-block">
                        <input type="text" id="level" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="font-size: 11px">所在类区</label>
                    <div class="layui-input-block">
                        <input type="text" id="area" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="font-size: 11px">允许休假天数</label>
                    <div class="layui-input-block">
                        <input type="text" id="leaveDays" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="font-size: 11px">相关领导id</label>
                    <div class="layui-input-block">
                        <input type="text" id="leaderID" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="font-size: 11px">请假类型</label>
                    <div class="layui-input-block">
                        <input type="text" id="leaveType" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="font-size: 11px">请假事由</label>
                    <div class="layui-input-block">
                        <input type="text" id="reason" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="font-size: 11px">批准人</label>
                    <div class="layui-input-block">
                        <input type="text" id="permitPerson" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="font-size: 11px">开始日期</label>
                    <div class="layui-input-block">
                        <input type="text" id="startDate" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="font-size: 11px">预计到岗日期</label>
                    <div class="layui-input-block">
                        <input type="text" id="endDate" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="font-size: 11px">预计请假天数</label>
                    <div class="layui-input-block">
                        <input type="text" id="days" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="font-size: 11px">起始地</label>
                    <div class="layui-input-block">
                        <input type="text" id="startLocation" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="font-size: 11px">到达地</label>
                    <div class="layui-input-block">
                        <input type="text" id="endLocation" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="font-size: 11px">请假备注</label>
                    <div class="layui-input-block">
                        <input type="text" id="leaveRemark" class="layui-input" name="title" disabled autocomplete="off" >
                    </div>
                </div>


                <button class="layui-btn" style="display:none" id="submitbtn" lay-submit >立即提交</button>

            </form>
        </div>

        <script type="text/javascript">
            layui.use(["table","jquery","layer","form"],function (){
                var layer = layui.layer;
                var table = layui.table;
                var $ = layui.jquery;
                var form = layui.form;

                //获取父页面某元素的值
                parent.$('#username').val()

            })
        </script>

    </body>
</html>
