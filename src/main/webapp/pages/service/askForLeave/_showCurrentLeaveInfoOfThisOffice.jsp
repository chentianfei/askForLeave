<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--销假审批页面--%>
<head>
    <%--基础引入--%>
    <%@include file="/pages/common/baseinfo.jsp" %>
    <style>
        .layui-input-date{
            height: 25px;
            margin-top: 3px;
        }
        #endOfLeave {
            margin-top:3px;
        }
        #endOfLeave_quickly{
            margin-top:3px;
        }
    </style>
</head>
<body>
    <%--主体信息--%>
        <div class="layui-col-md12">
            <%--数据展示--%>
            <table class="layui-hide" id="currentLeaveInfoOfThisOffice" lay-filter="currentLeaveInfoOfThisOffice"></table>
        </div>
    <script>
        layui.use(['table', 'upload', 'laydate',  'form', 'layer','common'], function () {
            var layer = layui.layer
            var $ = layui.jquery;
            var table = layui.table;
            var form = layui.form;
            var laydate = layui.laydate;
            var common = layui.common;

            layer.config({
                skin: 'layui-layer-molv'
            })

            //表格数据初始化
            table.render({
                elem: '#currentLeaveInfoOfThisOffice'
                ,url:'askForLeaveServlet?action=queryOfficeCurrentLeaveInfoByPersonId'
                ,defaultToolbar: []
                ,request: {
                    pageName: 'curr' //页码的参数名称，默认：page
                    ,limitName: 'nums' //每页数据量的参数名，默认：limit
                }
                ,where:{
                    person_id : parent.$("#person_id:hidden").val()
                }
                ,limit:10
                ,limits:[5,10,15]
                , cols: [[
                    {field: 'serialnumber', title: '流水号', unresize:true,align:'center',width: 110}
                    ,{field:'name', title:'姓名', align:'center',width:110}
                    ,{field:'office', title:'工作单位', align:'center',width:210}
                    ,{field:'post', title:'现任职务', align:'center',width:210}
                    ,{field:'phone', title:'联系电话', align:'center',width:130}
                    ,{field:'leave_type', title:'请假类型', align:'center',width:110}
                    ,{field:'start_date',title:'开始日期',align:"center",width:140}
                    ,{field:'leave_days_projected', title:'请假天数', align:'center',width:90}
                    ,{field:'work_leader', title:'不在岗期间主持工作领导', align:'center',width:200}
                    ,{field:'leave_reason', title:'请假事由', align:'center',width:160}
                    ,{field:'approver', title:'批准人', align:'center',width:100}
                    ,{field:'depart_location', title:'出发地', align:'center',width:130}
                    ,{field:'arrive_location', title:'到达地', align:'center',width:130}
                    ,{field:'end_date_maybe',title:'预计到岗日期',align:'center',width:140}
                    ,{field:'start_leave_remark', title:'请假备注', align:'center',width:190}
                    ,{field:'start_leave_operator', title:'请假操作者', align:'center',width:170}
                ]]
                ,page : true
                ,parseData: function(res) { //res 即为原始返回的数据
                    //将本次查询的数据赋值给导出数据指定的变量
                    exportData = res.count;
                    return {
                        "code": res.code, //解析接口状态
                        "msg": res.msg, //解析提示文本
                        "count": res.count.length, //解析数据长度
                        "data": res.data //解析数据列表
                    };
                }
            });
        });

    </script>

</body>


</html>
