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
                    <label class="layui-form-label" style="width:200px">人员编号</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="person_id" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">姓名</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="name" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">性别</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="sex" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">出生年月</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="birthDate" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">民族</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="nation" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">本人籍贯</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="nativePlace" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">联系电话</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="phone" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">工作单位</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="office" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">现任职务</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="post" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">职级</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="level" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">所在类区</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="area_class" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">允许休假天数</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="allow_Leave_Days" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">婚姻状态</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="marriage_status" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">配偶姓名</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="name_spouse" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">配偶籍贯</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="nativeplace_spouse" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">请假类型</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="leave_type" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">开始日期</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="start_date" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">请假天数</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="leave_days_projected" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">不在岗期间主持工作领导</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="work_leader" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">请假事由</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="leave_reason" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">批准人</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="approver" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">出发地</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="depart_location" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">到达地</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="arrive_location" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">预计到岗日期</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="end_date_maybe" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">请假备注</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="start_leave_remark" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">请假操作者</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" id="start_leave_operator" class="layui-input"
                               disabled autocomplete="off" style="background: #eee">
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
