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

        <%--隐藏按钮--%>
        <button type="submit" class="layui-btn" style="display:none"
                id="batchResumeWorkSubmit" lay-submit lay-filter="batchResumeWorkSubmit"></button>

    </form>
</div>

<script type="text/javascript">
    layui.use(['laydate','element','form', 'table'],function () {
        var form = layui.form;
        var laydate = layui.laydate;
        var table = layui.table;
        var $ = layui.jquery;
        var index = parent.layer.getFrameIndex(window.name);

        //选中后的回调
        //设置最小选择日期
        laydate.render({
            elem: '#endLeaveDate'
            ,format:"yyyy年MM月dd日"
        });

        form.on('submit(batchResumeWorkSubmit)', function(data){
            const sourceData = data.field;
            //解析数据
            var table_elem_name = parent.$("#table_elem_name:hidden").val();
            var query_action = parent.$("#query_action:hidden").val();
            //为序列号集合赋值
            let serialnumber_set = parent.$("#serialnumber_set:hidden").val();
            //设置销假日期
            let end_date = sourceData.endLeaveDate;
            //设置销假备注
            let end_leave_remark = sourceData.endLeaveRemark;
            //获取当前页码
            var currentPage = parent.$(".layui-laypage-skip .layui-input").val();
            /*ajax开始*/
            $.ajax({
                type: "POST",
                url: "askForLeaveServlet?action=batchResumeWork",
                data:{
                    serialnumber_set: JSON.stringify(serialnumber_set),
                    end_leave_remark: end_leave_remark,
                    end_date: end_date,
                    end_leave_operator:"${sessionScope.user.operator}"
                },
                dataType: 'json',
                success: function (result) {
                    parent.layer.msg(result.message, {
                        icon : 6
                    });
                    //重载表格
                    parent.layui.table.reload(table_elem_name, {
                        url: 'askForLeaveServlet?action='+query_action
                        ,page: {
                            curr: 1
                        }
                        ,request: {
                            pageName: 'curr' //页码的参数名称，默认：page
                            ,limitName: 'nums' //每页数据量的参数名，默认：limit
                        }
                    });

                    //关闭此页面
                    parent.layer.close(index);
                },
                error: function (e) {
                    // 异常提示
                    parent.layer.msg('网络故障'+e.status, {
                        icon : 5
                    });
                }
            });
            /*ajax结束*/
            //如果没有return false，提交会失败，所以开启这个非常重要
            //众所周知，表单一点击提交按钮(submit)必然跳转页面，如果表单的action为空也会跳转到自己的页面，即效果为刷新当前页。
            //如下，可以看到一点击提交按钮，浏览器的刷新按钮闪了一下：（该机制就是导致此问题的根本原因）
            //方式三：阻止表单默认提交机制
            //如果既要求效率，并且form action与ajax分离不开，那么就只能采用阻止表单默认提交机制。
            return false;
        });

    });

</script>
</body>
</html>
