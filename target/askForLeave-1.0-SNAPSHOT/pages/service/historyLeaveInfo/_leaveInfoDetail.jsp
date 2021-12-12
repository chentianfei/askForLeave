<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--查看请假信息详情--%>
<head>
    <%--基础引入--%>
    <%@include file="/pages/common/baseinfo.jsp"%>
</head>
<body>
<div style="padding: 10px">

    <div class="layui-collapse" lay-filter="historyInfoDetail">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">人员基本信息</h2>
            <div class="layui-colla-content">
                <form class="layui-form layui-form-pane" id="personInfo">

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
        </div>

        <div class="layui-colla-item">
            <h2 class="layui-colla-title" id="leaveInfo">请假数据详情</h2>
            <div class="layui-colla-content">
                <form class="layui-form layui-form-pane">
                    <div class="layui-form-item">
                        <label class="layui-form-label" style="font-size: 11px">流水号</label>
                        <div class="layui-input-block">
                            <input type="text" id="serialnumber" class="layui-input"
                                   disabled autocomplete="off" >
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" style="font-size: 11px">请假类型</label>
                        <div class="layui-input-block">
                            <input type="text" id="leave_type" class="layui-input"
                                   disabled autocomplete="off" >
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" style="font-size: 11px">开始日期</label>
                        <div class="layui-input-block">
                            <input type="text" id="start_date" class="layui-input"
                                   disabled autocomplete="off" >
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" style="font-size: 11px">预计到岗日期</label>
                        <div class="layui-input-block">
                            <input type="text" id="leave_days_projected" class="layui-input"
                                   disabled autocomplete="off" >
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" style="font-size: 11px">不在岗期间主持工作领导</label>
                        <div class="layui-input-block">
                            <input type="text" id="work_leader" class="layui-input"
                                   disabled autocomplete="off" >
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" style="font-size: 11px">请假事由</label>
                        <div class="layui-input-block">
                            <input type="text" id="leave_reason" class="layui-input"
                                   disabled autocomplete="off" >
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" style="font-size: 11px">批准人</label>
                        <div class="layui-input-block">
                            <input type="text" id="approver" class="layui-input"
                                   disabled autocomplete="off" >
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" style="font-size: 11px">出发地</label>
                        <div class="layui-input-block">
                            <input type="text" id="depart_location" class="layui-input"
                                   disabled autocomplete="off" >
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" style="font-size: 11px">到达地</label>
                        <div class="layui-input-block">
                            <input type="text" id="arrive_location" class="layui-input"
                                   disabled autocomplete="off" >
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" style="font-size: 11px">预计到岗日期</label>
                        <div class="layui-input-block">
                            <input type="text" id="end_date_maybe" class="layui-input"
                                   disabled autocomplete="off" >
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" style="font-size: 11px">请假备注</label>
                        <div class="layui-input-block">
                            <input type="text" id="start_leave_remark" class="layui-input"
                                   disabled autocomplete="off" >
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" style="font-size: 11px">请假操作者</label>
                        <div class="layui-input-block">
                            <input type="text" id="start_leave_operator" class="layui-input"
                                   disabled autocomplete="off" >
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" style="font-size: 11px">实际到岗日期</label>
                        <div class="layui-input-block">
                            <input type="text" id="end_date" class="layui-input"
                                   disabled autocomplete="off" >
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" style="font-size: 11px">实际请假天数</label>
                        <div class="layui-input-block">
                            <input type="text" id="leave_days_actual" class="layui-input"
                                   disabled autocomplete="off" >
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" style="font-size: 11px">销假备注</label>
                        <div class="layui-input-block">
                            <input type="text" id="end_leave_remark" class="layui-input"
                                   disabled autocomplete="off" >
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" style="font-size: 11px">销假操作者</label>
                        <div class="layui-input-block">
                            <input type="text" id="end_leave_operator" class="layui-input"
                                   disabled autocomplete="off" >
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>


</div>

<script type="text/javascript">
    layui.use(["jquery","layer",'laydate',"element","form"],function (){
        var layer = layui.layer;
        var $ = layui.jquery;
        var form = layui.form;
        var laydate = layui.laydate;
        var element = layui.element;

        /*//初始化预计到岗时间
        laydate.render({
            elem: '#birthDate'
            ,format: 'yyyy年MM月dd日'
        });*/
    })
</script>

</body>
</html>
