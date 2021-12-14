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
        <div class="layui-layout layui-layout-admin">
            <%--头部信息--%>
            <%@include file="/pages/common/header.jsp" %>

            <%--左侧导航--%>
            <%@include file="/pages/common/menu.jsp" %>

            <%--主体信息--%>
            <div class="layui-body layui-bg-gray" style="padding: 10px">
                <%--信息查询--%>
                <div class="layui-row">
                        <div class="layui-bg-gray" style="padding: 10px;">
                            <div class="layui-row">
                                <div class="layui-col-md12">
                                    <div class="layui-card">
                                        <div class="layui-card-header layui-bg-blue">待销假信息查询</div>
                                        <div class="layui-card-body">
                                            <%--查询表单开始--%>
                                            <form class="layui-form layui-form-pane">

                                                <div class="layui-form-item">
                                                    <%--姓名--%>
                                                    <div class="layui-inline">
                                                        <label class="layui-form-label" style="width: 100px">姓名</label>
                                                        <div class="layui-input-inline"  style="width: 150px">
                                                            <input type="text" name="name"
                                                                   id="name"
                                                                   placeholder="请输入"
                                                                   class="layui-input" autocomplete=“off”>
                                                        </div>
                                                    </div>
                                                    <%--工作单位--%>
                                                    <div class="layui-inline">
                                                        <label class="layui-form-label" style="width: 100px">工作单位</label>
                                                        <div class="layui-input-inline"  style="width: 150px" >
                                                            <select id="office" name="office" lay-search>
                                                                <option value=""></option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <%--职级--%>
                                                    <div class="layui-inline">
                                                        <label class="layui-form-label" style="width: 100px">职级</label>
                                                        <div class="layui-input-inline"  style="width: 150px">
                                                            <select id="level" name="level" lay-search >
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <%--所在类区--%>
                                                    <div class="layui-inline">
                                                        <label class="layui-form-label" style="width:100px">所在类区</label>
                                                        <div class="layui-input-inline" style="width:150px">
                                                            <select name="area_class" id="area_class" lay-search >
                                                                <option value="">请选择</option>
                                                                <option value="二类区">二类区</option>
                                                                <option value="三类区">三类区</option>
                                                                <option value="四类区">四类区</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <%--联系方式--%>
                                                    <div class="layui-inline">
                                                        <label class="layui-form-label" style="width: 100px">联系电话</label>
                                                        <div class="layui-input-inline"  style="width: 150px">
                                                            <input type="tel" name="phone" id="phone"
                                                                   placeholder="请输入" autocomplete="off" class="layui-input">
                                                        </div>
                                                    </div>

                                                </div>

                                                <div class="layui-form-item">
                                                    <%--本人籍贯--%>
                                                    <div class="layui-inline">
                                                        <label class="layui-form-label" style="width: 100px">本人籍贯</label>
                                                        <div class="layui-input-inline" style="width: 135px">
                                                            <select name="province" <%--data-area="西藏自治区"--%> lay-filter="province">
                                                                <option value="">选择省</option>
                                                            </select>
                                                        </div>
                                                        <div class="layui-input-inline" style="width: 135px">
                                                            <select name="city" <%--data-area="日喀则市"--%> lay-filter="city">
                                                                <option value="">选择市</option>
                                                            </select>
                                                        </div>
                                                        <div class="layui-input-inline" style="width: 135px">
                                                            <select name="district" <%--data-area="仲巴县" --%>lay-filter="district">
                                                                <option value="">选择区</option>
                                                            </select>
                                                        </div>
                                                    </div>

                                                    <%--请假种类--%>
                                                    <div class="layui-inline">
                                                        <label class="layui-form-label">请假种类</label>
                                                        <div class="layui-input-block" id="leave_type" ></div>
                                                    </div>

                                                </div>

                                                <div class="layui-form-item">
                                                    <%--出发地--%>
                                                    <div class="layui-inline">
                                                        <label class="layui-form-label" style="width: 100px">出发地</label>
                                                        <div class="layui-input-inline" style="width: 150px">
                                                            <input type="text" name="depart_location"
                                                                   id="depart_location"
                                                                   placeholder=""
                                                                   autocomplete="off"
                                                                   class="layui-input">
                                                        </div>
                                                    </div>

                                                    <%--到达地--%>
                                                    <div class="layui-inline">
                                                        <label class="layui-form-label" style="width: 100px">到达地</label>
                                                        <div class="layui-input-inline" style="width: 150px">
                                                            <input type="text" name="arrive_location"
                                                                   id="arrive_location"
                                                                   placeholder=""
                                                                   autocomplete="off"
                                                                   class="layui-input">
                                                        </div>
                                                    </div>

                                                    <%--假期类型--%>
                                                    <%-- <div class="layui-inline">
                                                         <label class="layui-form-label"
                                                                style="width: 100px">请假种类</label>
                                                         <div class="layui-input-inline" style="width: 150px">
                                                             <select id="leave_type" name="leave_type"  lay-search="">
                                                                 <option value=""></option>
                                                             </select>
                                                         </div>
                                                     </div>--%>


                                                    <%--批准人--%>
                                                    <div class="layui-inline">
                                                        <label class="layui-form-label"
                                                               style="width: 100px">批准人</label>
                                                        <div class="layui-input-inline" style="width: 150px">
                                                            <input type="text" name="approver"
                                                                   autocomplete="off"
                                                                   id="approver" placeholder=""
                                                                   class="layui-input">
                                                        </div>
                                                    </div>

                                                </div>

                                                <%--带范围的选择区域--%>
                                                <div class="layui-form-item">

                                                    <%--开始时间 start_date--%>
                                                    <div class="layui-inline">
                                                        <label class="layui-form-label"
                                                               style="width: 145px">请假开始时间</label>
                                                        <div class="layui-inline"  id="start_date">
                                                            <div class="layui-input-inline" style="width: 170px">
                                                                <input type="text" autocomplete="off" id="start_date_min"
                                                                       name="start_date_min"
                                                                       class="layui-input" placeholder="开始日期">
                                                            </div>
                                                            <div class="layui-form-mid">至</div>
                                                            <div class="layui-input-inline" style="width: 170px">
                                                                <input type="text" autocomplete="off" id="start_date_max"
                                                                       name="start_date_max"
                                                                       class="layui-input" placeholder="结束日期">
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <%--预计到岗时间 end_date_maybe--%>
                                                    <div class="layui-inline">
                                                        <label class="layui-form-label"
                                                               style="width: 145px">预计到岗时间</label>
                                                        <div class="layui-inline"  id="end_date_maybe">
                                                            <div class="layui-input-inline" style="width: 170px">
                                                                <input type="text" autocomplete="off" id="end_date_maybe_min"
                                                                       name="end_date_maybe_min"
                                                                       class="layui-input" placeholder="开始日期">
                                                            </div>
                                                            <div class="layui-form-mid">至</div>
                                                            <div class="layui-input-inline" style="width: 170px">
                                                                <input type="text" autocomplete="off" id="end_date_maybe_max"
                                                                       name="end_date_maybe_max"
                                                                       class="layui-input" placeholder="结束日期">
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>

                                                <div class="layui-form-item" style="padding-left: 70%">
                                                    <div class="layui-input-block">
                                                        <button type="submit" class="layui-btn" lay-submit lay-filter="resumeWorkInfoQuery">查询</button>
                                                        <button type="reset" class="layui-btn layui-btn-normal" >重置</button>
                                                    </div>
                                                </div>

                                            </form>
                                            <%--查询表单结束--%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                <%--信息管理--%>
                <div class="layui-row">
                    <div class="layui-col-md12">
                        <div class="layui-bg-gray" style="padding: 10px;">
                            <div class="layui-row">
                                <div class="layui-col-md12">
                                    <div class="layui-card">
                                        <div class="layui-card-header layui-bg-blue">待销假信息</div>
                                        <div class="layui-card-body">

                                            <%--数据展示--%>
                                            <table class="layui-hide" id="resumeWorkInfo" lay-filter="resumeWorkInfo"></table>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <%--底部信息--%>
            <div class="layui-footer">
                <%@include file="/pages/common/footer.jsp" %>
            </div>

            <%--接受person_id的值，以便子页面获取--%>
            <input  type="hidden" class="layui-input"
                    name="start_date"
                    style="display:none"
                    id="start_date"  />

        </div>

        <%--表格内部工具栏--%>
        <script type="text/html" id="audit">
            <button type="button" class="layui-btn layui-btn-xs" lay-event="endOfLeave" id="endOfLeave">销假</button>
            <button type="button" class="layui-btn layui-btn-xs" lay-event="endOfLeave_quickly" id="endOfLeave_quickly">快速销假</button>
        </script>

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

                //初始化请假开始时间
                laydate.render({
                    elem: '#start_date'
                    ,range: ['#start_date_min', '#start_date_max']
                });

                //初始化预计到岗时间
                laydate.render({
                    elem: '#end_date_maybe'
                    ,range: ['#end_date_maybe_min', '#end_date_maybe_max']
                });

                bindLevelSelectData();
                bindNationSelectData();
                bindOfficeSelectData();
                //初始化请假种类下拉框
                //bindLeaveTypeSelectData();
                // 初始化请假种类复选框数据
                bindLeaveTypeCheckboxData();

                table.render({
                    elem: '#resumeWorkInfo'
                    ,url:'askForLeaveServlet?action=queryALLResumeWorkInfo'
                    //,toolbar: '#toolbar' //开启头部工具栏，并为其绑定左侧模板
                    ,title: '待销假信息表'
                    ,request: {
                        pageName: 'curr' //页码的参数名称，默认：page
                        ,limitName: 'nums' //每页数据量的参数名，默认：limit
                    }
                    ,limit:5
                    ,limits:[5,10,15]
                    , cols: [[
                        {field: 'serialnumber', title: '流水号', unresize:true,align:'center',width: 80}
                        ,{field:'name', title:'姓名', align:'center',width:110}
                        ,{field:'office', title:'工作单位', align:'center',width:210}
                        ,{field:'post', title:'现任职务', align:'center',width:210}
                        ,{field:'phone', title:'联系电话', align:'center',width:130}
                        ,{field:'leave_type', title:'请假类型', align:'center',width:110}
                        ,{field:'start_date',title:'开始日期',align:'center',width:140}
                        ,{field:'leave_days_projected', title:'请假天数', align:'center',width:90}
                        ,{field:'work_leader', title:'不在岗期间主持工作领导', align:'center',width:200}
                        ,{field:'leave_reason', title:'请假事由', align:'center',width:160}
                        ,{field:'approver', title:'批准人', align:'center',width:100}
                        ,{field:'depart_location', title:'出发地', align:'center',width:130}
                        ,{field:'arrive_location', title:'到达地', align:'center',width:130}
                        ,{field:'end_date_maybe',title:'预计到岗日期',align:'center',width:140}
                        ,{field:'start_leave_remark', title:'请假备注', align:'center',width:190}
                        ,{field:'start_leave_operator', title:'请假操作者', align:'center',width:170}
                        ,{fixed: 'right', title:'操作', align:'center',id:"toolbar", toolbar: '#audit',width: 165}
                    ]]
                    ,page : true
                });



                table.on('tool(resumeWorkInfo)', function (obj) {
                    var data = obj.data;

                    //给隐藏域赋值，方便子页面取值
                    var start_date = data.start_date;
                    $("#start_date").val(start_date);

                    if (obj.event === "endOfLeave") {
                        //获取当前页码
                        let currentPage = $(".layui-laypage-skip .layui-input").val();
                        //获取当前日期
                        let currentDate = jutils.formatDate(new Date(),"yyyy年MM月dd日");
                        //2.弹出提示框，提示填写销假备注，并核实销假日期是否正确
                        var msgIndex = layer.msg('请确认到岗日期【'+currentDate+"】是否正确？", {
                            time: 0 //不自动关闭
                            ,btn: ['正确', '错误']
                            ,yes: function(index){
                                //关闭上一层弹框
                                layer.close(index);
                                layer.prompt(
                                    {title:"请填写销假备注"
                                        ,formType: 2
                                        ,value: '正常到岗'
                                        ,btn:["提交","返回"]
                                    },
                                    function (val,index2) {
                                        // val 输入值；index2 该窗口索引
                                        /*向后台发起ajax请求*/
                                        $.ajax({
                                            type: "post",
                                            url: "askForLeaveServlet?action=resumeWork",
                                            dataType:"JSON",
                                            data: {
                                                serialnumber: data.serialnumber,
                                                end_leave_remark: val,
                                                end_date: currentDate,
                                                end_leave_operator:"${sessionScope.user.operator}"
                                            },
                                            success: function (result) {
                                                let code = result.data;
                                                if(code == 1){
                                                    //备份与删除成功，调用obj.del()方法删除此行数据，调用table.render（）刷新表格数据
                                                    obj.del();
                                                    layer.msg('销假成功', {icon: 1,time: 1000});
                                                    //重载表格
                                                    table.reload('resumeWorkInfo', {
                                                        url: 'askForLeaveServlet?action=queryALLResumeWorkInfo'
                                                        ,page: {
                                                            curr: currentPage//重新从第 1 页开始
                                                        }
                                                        ,request: {
                                                            pageName: 'curr' //页码的参数名称，默认：page
                                                            ,limitName: 'nums' //每页数据量的参数名，默认：limit
                                                        }
                                                    });

                                                }

                                            },
                                            //请求失败，包含具体的错误信息
                                            error: function (e) {
                                                layer.msg('业务提交失败'+e.status, {
                                                    icon : 5
                                                });
                                            }
                                        });
                                        layer.close(index2);
                                })
                            }
                            ,btn2:function (index) {
                                layer.close(index);
                                //弹窗选择正确数据
                                layer.open({
                                    type: 2,
                                    title: '请输入正确参数',
                                    maxmin: true, //开启最大化最小化按钮
                                    area:['500px', '500px'],
                                    content: "pages/service/endOfLeave/_endLeaveDate.jsp",
                                    anim:2,
                                    resize:false,
                                    id:'LAY_layuipro',
                                    btn:['提交','取消'],
                                    success: function (layero, index) {
                                        //提交按钮的回调
                                        var body = layer.getChildFrame('body', index);
                                        body.find("#serialnumber").val(data.serialnumber);
                                        body.find("#currentPage").val(currentPage);
                                    },
                                    yes:function (index, layero) {
                                        //提交按钮的回调
                                        var body = layer.getChildFrame('body', index);
                                        // 找到隐藏的提交按钮模拟点击提交
                                        body.find('#resumeWorkSubmit').click();
                                    },
                                    btn2: function (index, layero) {
                                        //return false;// 开启该代码可禁止点击该按钮关闭
                                    },
                                    cancel: function () {
                                        //右上角关闭回调
                                        //return false 开启该代码可禁止点击该按钮关闭
                                    }
                                });

                            }
                        });

                    }
                    else if(obj.event === "endOfLeave_quickly") {
                        //获取当前页码
                        let currentPage = $(".layui-laypage-skip .layui-input").val();
                        //获取当前日期
                        let currentDate = jutils.formatDate(new Date(),"yyyy年MM月dd日");
                        if(compareDateStrRTNBoolean(currentDate,start_date)){
                            $.ajax({
                                type: "post",
                                url: "askForLeaveServlet?action=resumeWork",
                                dataType:"JSON",
                                data: {
                                    serialnumber: data.serialnumber,
                                    end_leave_remark: "正常到岗",
                                    end_date: currentDate,
                                    end_leave_operator:"${sessionScope.user.operator}"
                                },
                                success: function (result) {
                                    let code = result.data;
                                    if(code == 1){
                                        //备份与删除成功，调用obj.del()方法删除此行数据，调用table.render（）刷新表格数据
                                        obj.del();
                                        layer.msg('销假成功', {icon: 1,time: 1000});
                                        //重载表格
                                        table.reload('resumeWorkInfo', {
                                            url: 'askForLeaveServlet?action=queryALLResumeWorkInfo'
                                            ,page: {
                                                curr: currentPage//重新从第 1 页开始
                                            }
                                            ,request: {
                                                pageName: 'curr' //页码的参数名称，默认：page
                                                ,limitName: 'nums' //每页数据量的参数名，默认：limit
                                            }
                                        });
                                    }
                                },
                                //请求失败，包含具体的错误信息
                                error: function (e) {
                                    layer.msg('业务提交失败'+e.status, {
                                        icon : 5
                                    });
                                }
                            });
                        }else {
                            layer.alert("当前日期小于起始日期，不能销假");
                        }
                    }
                });

                //监听查询区域的提交按钮事件
                form.on('submit(resumeWorkInfoQuery)',function (data) {
                    //获取当前页码
                    var currentPage = $(".layui-laypage-skip .layui-input").val();
                    //获取数据
                    const sourceData = data.field;
                    //人员基本信息
                    const name = sourceData.name;
                    const level = sourceData.level;
                    const office = sourceData.office;
                    const phone = sourceData.phone;
                    const area_class = sourceData.area_class;
                    //解析解析框中的地址内容
                    const city = sourceData.city;
                    const district = sourceData.district;
                    const province = sourceData.province;
                    // 通过地址code码获取地址名称
                    var address = common.getCity({
                        province,
                        city,
                        district
                    });
                    let provinceName = address.provinceName;
                    let cityName = address.cityName;
                    let districtName = address.districtName;
                    //解析解析框中的地址内容
                    const nativePlace = provinceName + ' ' + cityName + ' ' + districtName;

                    //请假信息
                    const depart_location = sourceData.depart_location;
                    const arrive_location = sourceData.arrive_location;
                    const approver = sourceData.approver;
                    //const leave_type = sourceData.leave_type;

                    //处理请假类型复选框数据并封装
                    var leave_typeSource = [];
                    $("#leave_type>div.layui-form-checked").each(function(index,ele){
                        leave_typeSource.push($(ele).find("span").html());
                    })

                    const end_date_maybe_max = sourceData.end_date_maybe_max;
                    const end_date_maybe_min = sourceData.end_date_maybe_min;
                    const start_date_max = sourceData.start_date_max;
                    const start_date_min = sourceData.start_date_min;

                    table.reload('resumeWorkInfo', {
                        url: 'askForLeaveServlet?action=querySomeResumeWorkInfo'
                        ,where: {
                            //设定异步数据接口的额外参数
                            //人员信息
                            name : name,
                            nativePlace:nativePlace,
                            office : office,
                            area_class : area_class,
                            level : level,
                            phone : phone,
                            //请假信息
                            depart_location : depart_location,
                            arrive_location : arrive_location,
                            approver : approver,
                            leave_type : leave_typeSource.toLocaleString(),
                            end_date_maybe_max : end_date_maybe_max,
                            end_date_maybe_min : end_date_maybe_min,
                            start_date_max : start_date_max,
                            start_date_min : start_date_min
                        }
                        ,page:true
                        ,request: {
                            pageName: 'curr' //页码的参数名称，默认：page
                            ,limitName: 'nums' //每页数据量的参数名，默认：limit
                        }
                        ,page: {
                            curr: currentPage //重新从第 1 页开始
                        }
                    });

                    return false;
                });
            });

        </script>

    </body>


</html>
