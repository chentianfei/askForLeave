<%--
  Created by IntelliJ IDEA.
  User: tianfeichen
  Date: 2021/8/21
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%--添加个人--%>
    <head>
        <%--基础引入--%>
        <%@include file="/pages/common/baseinfo.jsp"%>
    </head>
    <body>
        <div style="padding: 10px">

            <form class="layui-form layui-form-pane" action="#" method="post">

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label" style="width:150px">领导姓名</label>
                        <div class="layui-input-inline" style="width:405px">
                            <input type="text" autocomplete=“off”
                                   id="leaderName"
                                   name="leaderName" placeholder="请输入" lay-verify="required" id="username"
                                   class="layui-input">
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <div class="layui-input-inline" style="width:555px">
                            <select name="leader_id" id="leader_id" lay-verify="required">
                                <option value="">请先输入领导姓名</option>
                            </select>
                        </div>
                    </div>
                </div>

                <!-- 数据域 -->
                <div class="layui-form-item" style="display:none">
                    <label class="layui-form-label" style="width:150px">下属编号</label>
                    <div class="layui-input-inline" style="width:400px">
                        <input type="hidden" name="subordinate_id"
                               id="subordinate_id"
                               placeholder="请输入"
                               class="layui-input">
                    </div>
                </div>

                <button type="submit" class="layui-btn" style="display:none"
                        id="bindLeaderSubmit" lay-submit lay-filter="bindLeaderSubmit">立即提交</button>


            </form>
        </div>

        <script type="text/javascript">
            layui.use(['form','common'], function() {
                var form = layui.form;
                var $ = layui.jquery;

                form.render();
                form.on('submit(bindLeaderSubmit)', function(data){
                    const sourceData = data.field;

                    const leader_id = sourceData.leader_id;
                    const subordinate_id = sourceData.subordinate_id;
                    const leaderName = sourceData.leaderName;

                    $.ajax({
                        type : 'POST',
                        url : 'personServlet?action=queryRelatedLeader',
                        data : {
                            subordinate_id : subordinate_id
                        },
                        dataType : 'json',
                        success : function(data) {
                            //可执行代码，若executeCode=0，则代表需要绑定的领导还未绑定过，>0代表已经被绑定过
                            var executeCode = 0;

                            $.each(data.data,function (index,item) {
                                var leaderIdHaved  = item.person_id;
                                if(leaderIdHaved == leader_id){
                                    executeCode++;
                                    //领导已被绑定，不能重复绑定
                                    $("#leader_id").empty();
                                }
                            })

                            if(executeCode == 0){
                                //代表所提交的领导信息还未被提交过
                                $.ajax({
                                    type: 'POST',
                                    url: 'personServlet?action=bindRelatedLeader',
                                    data: {
                                        leader_id: leader_id,
                                        subordinate_id: subordinate_id,
                                        leaderName: leaderName,
                                    },
                                    dataType: 'json',
                                    success: function (data) {
                                        parent.layer.msg('绑定成功', {
                                            icon: 6
                                        });

                                        /*//重载表格
                                        parent.parent.layui.table.reload('leader_info', {
                                            where: {
                                                subordinate_id: subordinate_id
                                            }
                                        });
*/
                                        //重载表格
                                        parent.layui.table.reload('personinformation', {
                                            url: 'personServlet?action=queryAllPerson'
                                            ,page: {
                                                curr: 1 //重新从第 1 页开始
                                            }
                                            ,request: {
                                                pageName: 'curr' //页码的参数名称，默认：page
                                                ,limitName: 'nums' //每页数据量的参数名，默认：limit
                                            }
                                        });
                                    },
                                    error : function(data) {
                                        parent.layer.msg('出现网络故障', {
                                            icon : 5
                                        });
                                    }
                                })
                            }else {
                                parent.layer.msg('该领导已被绑定！', {
                                    icon: 5,
                                    time: 3000
                                });
                            }

                        },
                        error : function(data) {
                            parent.layer.msg('出现网络故障', {
                                icon : 5
                            });
                        }
                    })

                });

                //名字输入框onblur后发起数据查询——开始
                $("#leaderName").blur(function (e) {
                    $.ajax({
                        url: 'personServlet?action=queryPersonInfoByName',
                        dataType: 'json',
                        data:{
                            person_name : $("#leaderName").val()
                        },
                        type: 'post',
                        success: function (result) {
                            if (result.data.length > 0) {
                                $("#leader_id").empty();
                                $("#leader_id").append("<option value=''>已查询出领导信息，请选择</option>");
                                $.each(result, function (key, value) {
                                    if(key == "data"){
                                        $.each(result.data,function (infoIndex,personInfo) {
                                            var leaderInfo = personInfo.name+":"
                                                +personInfo.office+"-"
                                                +personInfo.post+"-"
                                                +personInfo.phone;
                                            var lerder_id_result = personInfo.person_id;
                                            //加入和本人id不同的领导信息
                                            if(lerder_id_result != $("#subordinate_id").val()) {
                                                $('#leader_id').append(new Option(leaderInfo,lerder_id_result));
                                            }else{
                                                $("#leader_id").empty();
                                                parent.layer.msg("不能选择您自己作为领导",{
                                                    icon : 5
                                                });

                                            }
                                        })
                                    }
                                });
                            } else {
                                $("#leader_id").empty();
                                $("#leader_id").append("<option value=''>无该领导信息，请核实输入是否正确</option>");
                            }
                            //重新渲染
                            form.render("select");
                        }
                    });
                });
                //名字输入框onblur后发起数据查询——结束

            });



        </script>
    </body>
</html>
