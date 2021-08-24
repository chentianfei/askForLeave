
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%--请假操作页面--%>
    <head>
        <%@ include file="/pages/common/top.jsp"%>
        <script type="text/javascript" src="static/js/leave_ctf.js"></script>
    </head>

    <body>
        <div>

            <div style="float: left;width: 30%">
              <%@ include file="/pages/common/menu.jsp"%>
            </div>

            <div style="float: left;width: 70%">

                <div class="layui-form" style="float: left;width: 50%">

                    <form action="personServlet" class="layui-form layui-form-pane"> <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
                        <input type="hidden" name="action" value="updatePersonInfo">

                        <div class="layui-form-item">
                            <label class="layui-form-label">姓名</label>
                            <div class="layui-input-block">
                                <input type="text" name="" placeholder="" autocomplete="off"
                                       id="personname_ctf" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">性别</label>
                            <div class="layui-input-block">
                                <input type="text" name="" readonly="readonly" placeholder="" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">联系电话</label>
                            <div class="layui-input-block">
                                <input type="text" name="" readonly="readonly" placeholder="" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">单位</label>
                            <div class="layui-input-block">
                                <input type="text" name="" readonly="readonly" placeholder="" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">职务</label>
                            <div class="layui-input-block">
                                <input type="text" name="" readonly="readonly" placeholder="" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">本人籍贯</label>
                            <div class="layui-input-block">
                                <input type="text" name="" readonly="readonly" placeholder="" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">不在岗期间主持工作领导</label>
                            <div class="layui-input-block">
                                <input type="text" name="" placeholder="" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">请假种类</label>
                            <div class="layui-input-block">
                                <select name="interest" lay-filter="aihao">
                                    <option value="0">事假</option>
                                    <option value="1">休假</option>
                                    <option value="1">产假</option>
                                    <option value="1">陪产假</option>
                                    <option value="1">病假</option>
                                    <option value="1">丧假</option>
                                </select>
                            </div>
                        </div>


                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">请假事由</label>
                            <div class="layui-input-block">
                                <textarea placeholder="请输入内容" class="layui-textarea"></textarea>
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label">日期选择</label>
                            <div class="layui-input-block">
                                <input type="text" name="date" id="date1" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </form>

                    <script>
                        layui.use(['form', 'layedit', 'laydate'], function(){
                            var form = layui.form
                                ,layer = layui.layer
                                ,layedit = layui.layedit
                                ,laydate = layui.laydate;

                            //日期
                            laydate.render({
                                elem: '#date'
                            });
                            laydate.render({
                                elem: '#date1'
                            });

                            /*$("#personname_ctf").on("blur",function () {
                                $.ajax({
                                    url:"personServlet",
                                    success:function () {
                                    },
                                    type:"post",
                                    dataType:"json",
                                    data:{
                                        action:"bindRelatedLeader"
                                    }
                                });


                            });*/

                        });
                    </script>
                </div>

                <div style="float:left;width: 50%">
                    <div>
                        <div> 本年度请假信息统计</div>
                        <div>
                            <table class="layui-hide" id="thisYearLeaveInfoCount"></table>
                            <script>
                                layui.use('table', function(){
                                    var table = layui.table;

                                    //展示已知数据
                                    table.render({
                                        elem: '#thisYearLeaveInfoCount'
                                        ,cols: [[ //标题栏
                                            {field: 'leaveKind', title: '假期类型',fixed:"left"}
                                            ,{field: 'totalCount', title: '累计次数',minWidth:160}
                                            ,{field: 'totalDays', title: '累计天数',minWidth:160}
                                        ]]
                                        ,data: [{
                                            "leaveKind": "${requestScope.leaveKind}"
                                            ,"totalCount": "${requestScope.totalCount}"
                                            ,"totalDays": "${requestScope.totalDays}"
                                        }]
                                        ,skin: 'line'//表格风格
                                        // ,even: true
                                        //,page: true //是否显示分页
                                        //,limits: [5, 7, 10]
                                        //,limit: 5 //每页默认显示的数量
                                    });

                                    $("#personname_ctf").on("blur",function () {
                                        table.render({
                                            elem: '#thisYearLeaveInfoCount'
                                            ,url: 'personServlet'
                                            //where: {token: 'sasasas', id: 123} //如果无需传递额外参数，可不加该参数
                                            ,method: 'post' //如果无需自定义HTTP类型，可不加该参数
                                            , request: {
                                                //如果无需自定义请求参数，可不加该参数
                                                action: "bindRelatedLeader"
                                            }
                                            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                                            ,parseData: function(res){ //res 即为原始返回的数据
                                                return {
                                                    "code": res.status, //解析接口状态
                                                    "msg": res.message, //解析提示文本
                                                    "count": res.total, //解析数据长度
                                                    "data": res.data.item //解析数据列表
                                                };
                                            }
                                            //response: {} //如果无需自定义数据响应名称，可不加该参数
                                        })
                                    });
                                });
                            </script>
                        </div>
                    </div>

                    <div>
                        <div> 本年度请假信息记录</div>
                        <div>
                            <table class="layui-hide" id="thisYearLeaveInfo"></table>
                            <script>
                                layui.use('table', function(){
                                    var table = layui.table;

                                    //展示已知数据
                                    table.render({
                                        elem: '#thisYearLeaveInfo'
                                        ,cols: [[ //标题栏
                                            {field: 'leaveKind', title: '假期类型'}
                                            ,{field: 'leaveDays', title: '请假天数'}
                                            ,{field: 'startDay', title: '开始日期',minWidth:160}
                                            ,{field: 'endDay', title: '结束日期',minWidth:160}
                                        ]]
                                        ,data: [{
                                            "leaveKind": "杜甫"
                                            ,"leaveDays": "杜甫"
                                            ,"startDay": "test@email.com"
                                            ,"endDay": "人生恰似一场修行"
                                        }]
                                        ,skin: 'line'//表格风格
                                        // ,even: true
                                        //,page: true //是否显示分页
                                        //,limits: [5, 7, 10]
                                        //,limit: 5 //每页默认显示的数量
                                    });
                                });
                            </script>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>
