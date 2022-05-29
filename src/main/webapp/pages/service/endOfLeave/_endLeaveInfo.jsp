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
    <form class="layui-form layui-form-pane">

        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 150px;">请选择销假日期</label>
            <div class="layui-input-inline" style="width: 300px">
                <input type="text" class="layui-input"
                       autocomplete="off" lay-verify="required"
                       name="endLeaveDate"
                       id="endLeaveDate">
            </div>
        </div>


        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px">请填写销假备注</label>
            <div class="layui-input-inline" style="width:300px">
                <input type="text" name="endLeaveRemark"  lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <%--隐藏域--%>
        <div class="layui-form-item">
            <div class="layui-input-inline" style="width:300px">
                <input name="serialnumber" id="serialnumber" type="hidden"
                       style="display:none">
            </div>
        </div>
        <%--隐藏域--%>
        <div class="layui-form-item">
            <div class="layui-input-inline" style="width:300px">
                <input name="currentPage" id="currentPage"  type="hidden"
                       style="display:none">
            </div>
        </div>
        <%--隐藏域--%>
        <div class="layui-form-item">
            <div class="layui-input-inline" style="width:300px">
                <input name="minDate" id="minDateId"  type="hidden"
                       style="display:none">
            </div>
        </div>

        <%--隐藏按钮--%>
        <button type="submit" class="layui-btn" style="display:none"
                id="resumeWorkSubmit" lay-submit lay-filter="resumeWorkSubmit"></button>
        <button type="reset" class="layui-btn" style="display:none"
                id="resumeWorkReset" lay-submit lay-filter="resumeWorkReset"></button>

    </form>
</div>

<script type="text/javascript">
    layui.use(['laydate','element','form', ],function () {
        var form = layui.form;
        var laydate = layui.laydate;
        var index = parent.layer.getFrameIndex(window.name);

        //获取父页面的值
        var minDate = parent.$("#minDate").val();
        var table_elem_name = parent.$("#table_elem_name").val();
        var query_action = parent.$("#query_action").val();
        //解析出年月日的数字
        var minDate_year = getYearFromYMDChinese(minDate);
        var minDate_month =getMonthFromYMDChinese(minDate);
        var minDate_day = getDayFromYMDChinese(minDate);

        //选中后的回调
        //设置最小选择日期
        laydate.render({
            elem: '#endLeaveDate'
            ,format:"yyyy年MM月dd日"
            ,min : minDate_year+'-'+minDate_month+'-'+minDate_day
        });

        form.on('submit(resumeWorkSubmit)', function(data){
            const sourceData = data.field;
            //解析数据
            const serialnumber = sourceData.serialnumber;
            const end_leave_remark = sourceData.endLeaveRemark;
            const end_date = sourceData.endLeaveDate;

            $.ajax({
                type : 'POST',
                url : 'askForLeaveServlet?action=resumeWork',
                data : {
                    serialnumber : serialnumber,
                    end_leave_remark : end_leave_remark,
                    end_date : end_date,
                    end_leave_operator:"${sessionScope.user.operator}"
                },
                dataType : 'json',
                success : function(result) {
                    let code = result.data;
                    if(code == 1){
                        //备份与删除成功，调用obj.del()方法删除此行数据，调用table.render（）刷新表格数据
                        parent.layer.msg('销假成功', {icon: 1,time: 1000});
                        //重载表格
                        parent.layui.table.reload(table_elem_name, {
                            url: 'askForLeaveServlet?action='+query_action
                            ,page: {
                                curr: sourceData.currentPage
                            }
                            ,request: {
                                pageName: 'curr' //页码的参数名称，默认：page
                                ,limitName: 'nums' //每页数据量的参数名，默认：limit
                            }
                        });
                        //关闭此页面
                        parent.layer.close(index);
                    }else {
                        // 异常提示
                        let msgIndex = parent.layer.msg('业务提交失败', {
                            icon : 5
                        });
                        parent.layer.close(msgIndex);
                    }
                    //关闭此页面
                    parent.layer.close(index);
                },
                error : function(data) {
                    // 异常提示
                    parent.layer.msg('出现网络故障', {
                        icon : 5
                    });
                    //关闭此页面
                    parent.layer.close(index);
                }
            });
            return false;
        });

    });

</script>
</body>
</html>
