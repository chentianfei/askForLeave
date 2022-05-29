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
                    <label class="layui-form-label" style="width:200px">业务编号</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" name="serialnumber" placeholder="" disabled
                               class="layui-input" id="serialnumber" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">姓名</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" name="nameInput" placeholder="" disabled
                               class="layui-input" id="nameInput" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">工作单位</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" name="office" placeholder="" disabled
                               class="layui-input" id="office" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">现任职务</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" name="post" placeholder="" disabled
                               class="layui-input" id="post" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">联系方式</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" name="phone" placeholder="" disabled
                               class="layui-input" id="phone" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">请假种类</label>
                    <div class="layui-input-inline" style="width:350px">
                        <select id="leave_type" name="leave_type" lay-verify="required" lay-search="">
                            <option value=""></option>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">开始日期</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" class="layui-input"
                               autocomplete="off" lay-verify="required"
                               name="start_date"
                               id="start_date">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">请假天数</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" name="leave_days_projected"
                               id="leave_days_projected"
                               lay-verify="number|required|integer"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">不在岗期间主持工作领导</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" name="work_leader"
                               autocomplete="off" lay-verify="required"
                               id="work_leader" placeholder=""
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">请假事由</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" name="leave_reason"
                               autocomplete="off" lay-verify="required"
                               id="leave_reason" placeholder=""
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">批准人</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" name="approver"
                               autocomplete="off" lay-verify="required"
                               id="approver" placeholder=""
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">出发地</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" name="depart_location" value="未知"
                               autocomplete="off" lay-verify="required"
                               id="depart_location" placeholder=""
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">到达地</label>
                    <div class="layui-input-inline" style="width:350px">
                        <input type="text" name="arrive_location" value="未知"
                               autocomplete="off" lay-verify="required"
                               id="arrive_location" placeholder=""
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:200px">请假备注</label>
                    <div class="layui-input-inline" style="width:350px">
                        <textarea placeholder="" name="start_leave_remark"
                                  id="start_leave_remark"
                                  autocomplete="off"
                                  class="layui-textarea"></textarea>
                    </div>
                </div>

                <button type="submit" class="layui-btn" style="display:none"
                        id="updateLeaveInfoSubmit" lay-submit lay-filter="updateLeaveInfoSubmit"></button>
                <button type="reset" class="layui-btn" style="display:none"
                        id="updateLeaveInfoReset" lay-submit lay-filter="updateLeaveInfoReset"></button>

            </form>
        </div>

        <script type="text/javascript">
            layui.use(['laydate','form','common','table'], function() {
                var laydate = layui.laydate;
                var form = layui.form;
                var $ = layui.jquery;
                var index = parent.layer.getFrameIndex(window.name);
                var common = layui.common;
                var table = layui.table;
                //日期框
                laydate.render({
                    elem: '#start_date'//指定元素
                    , type: 'date'
                });

                //数字验证
                form.verify({
                    integer: [
                        /^[1-9]\d*$/
                        , '只能输入正整数'
                    ]
                });

                form.render();

                form.on('submit(updateLeaveInfoSubmit)', function(data){
                    const sourceData = data.field;

                    const serialnumber = sourceData.serialnumber;
                    const leave_type = sourceData.leave_type;
                    const start_date = sourceData.start_date;
                    const leave_days_projected = sourceData.leave_days_projected;
                    const work_leader = sourceData.work_leader;
                    const leave_reason = sourceData.leave_reason;
                    const approver = sourceData.approver;
                    const depart_location = sourceData.depart_location;
                    const arrive_location = sourceData.arrive_location;
                    const start_leave_remark = sourceData.start_leave_remark;
                    //获取当前页码
                    var currentPage = parent.$(".layui-laypage-skip .layui-input").val();
                    parent.layer.confirm('您的操作行为将写入日志，确定修改？', {
                        btn: ['确定','取消'] //按钮
                    }, function(){
                        $.ajax({
                            type : 'POST',
                            url : 'askForLeaveServlet?action=updateLeaveInfo',
                            data : {
                                serialnumber:serialnumber,
                                leave_type:leave_type,
                                start_date:start_date,
                                leave_days_projected:leave_days_projected,
                                work_leader:work_leader,
                                leave_reason:leave_reason,
                                approver:approver,
                                depart_location:depart_location,
                                arrive_location:arrive_location,
                                start_leave_operator:"${sessionScope.user.operator}",
                                start_leave_remark:start_leave_remark
                            },
                            dataType : 'json',
                            success : function(data) {
                                if(data.code==1){
                                    // 成功提示框
                                    parent.layer.msg('更新成功', {
                                        icon : 6,
                                    });

                                    //重载表格
                                    parent.layui.table.reload('approval', {
                                        url: 'askForLeaveServlet?action=queryAllLeaveInfo'
                                        ,page: {
                                            curr: currentPage
                                        }
                                        ,request: {
                                            pageName: 'curr' //页码的参数名称，默认：page
                                            ,limitName: 'nums' //每页数据量的参数名，默认：limit
                                        }
                                    });
                                    //关闭此页面
                                    parent.layer.close(index);
                                }
                                else{
                                    // 失败提示框
                                    parent.layer.msg('修改失败，原因：'+data.message, {
                                        icon : 5,
                                    });
                                }
                            },
                            error : function(data) {
                                // 异常提示
                                parent.layer.msg('出现网络故障', {
                                    icon : 5
                                });
                                //关闭此页面
                                parent.layer.close(index);
                                //关闭弹框
                                //parent.layer.closeAll('iframe');
                            }
                        });
                    }, function(){
                        //关闭此页面
                        parent.layer.close(index);
                    });

                    return false;
                });
            });
        </script>

    </body>
</html>
