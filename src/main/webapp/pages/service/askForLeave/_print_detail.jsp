<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%--查看请假信息详情--%>
    <head>
      <%--  &lt;%&ndash;基础引入&ndash;%&gt;
        <%@include file="/pages/common/baseinfo.jsp"%>

        <%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
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
        <script type="text/javascript" src="static/js/qrcode.js"></script>

        <title>谢通门县委组织部请假信息核验</title>

        <link rel="shortcut icon" type="image/x-icon" href="favicon.ico"/>


    </head>
    <body>

        <div style="padding: 10px;font-size: 20px;">
                <form class="layui-form layui-form-pane">

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">人员编号</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="person_id" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.person_id}">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">姓名</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="name" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.name}">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">性别</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="sex" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.sex}">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">出生年月</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="birthDate" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.birthDate}">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">民族</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="nation" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.nation}">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">本人籍贯</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="nativePlace" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.nativePlace}">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">联系电话</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="phone" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.phone}">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">工作单位</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="office" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.office}">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">现任职务</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="post" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.post}">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">职级</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="level" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.level}">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">所在类区</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="area_class" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.area_class}">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">允许休假天数</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="allow_Leave_Days" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.allow_Leave_Days}">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">婚姻状态</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="marriage_status" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.marriage_status}">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">配偶姓名</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="name_spouse" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.name_spouse}">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">配偶籍贯</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="nativeplace_spouse" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.nativeplace_spouse}">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">请假类型</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="leave_type" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.leave_type}">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">开始日期</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="start_date" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.start_date}">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">请假天数</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="leave_days_projected" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.leave_days_projected}">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">不在岗期间主持工作领导</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="work_leader" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.work_leader}">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">请假事由</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="leave_reason" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.leave_reason}">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">批准人</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="approver" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.approver}">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">出发地</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="depart_location" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.depart_location}">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">到达地</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="arrive_location" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.arrive_location}">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">预计到岗日期</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="end_date_maybe" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.end_date_maybe}">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">请假备注</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="start_leave_remark" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.start_leave_remark}">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:350px">请假操作者</label>
                    <div class="layui-input-inline" style="width:600px">
                        <input type="text" id="start_leave_operator" class="layui-input"
                               disabled autocomplete="off" style="background: #eee" value="${LeaveInfo.start_leave_operator}">
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
            })
        </script>

    </body>
</html>
