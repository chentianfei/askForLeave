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

            <form class="layui-form layui-form-pane">

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">领导姓名</label>
                    <div class="layui-input-inline" style="width:400px">
                        <input type="text" name="office_leader_name" id="office_leader_name"  class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">领导类型</label>
                    <div class="layui-input-block" style="width:400px">
                        <input type="radio" name="office_leader_type" value="正职" title="正职" checked="">
                        <input type="radio" name="office_leader_type" value="副职" title="副职">
                        <input type="radio" name="office_leader_type" value="主持工作的副职" title="主持工作的副职">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">联系电话</label>
                    <div class="layui-input-inline" style="width:400px">
                        <input type="tel" id="office_leader_phone" name="office_leader_phone" placeholder="请输入"
                               autocomplete=“off”
                               lay-verify="required|phone"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>

                <!-- 提交按钮 -->
                <button type="submit" class="layui-btn" style="display:none"
                        id="addLeaderInfoSubmit" lay-submit
                        lay-filter="addLeaderInfoSubmit"></button>

            </form>
        </div>

        <script type="text/javascript">

            layui.use(['laydate','form','common','table'], function() {
                var form = layui.form;
                var $ = layui.jquery;
                var index = parent.layer.getFrameIndex(window.name);

                form.render();

                form.on('submit(addLeaderInfoSubmit)', function(data){
                    const sourceData = data.field;

                    const office_leader_name = sourceData.office_leader_name;
                    const office_leader_type = sourceData.office_leader_type;
                    const office_leader_phone = sourceData.office_leader_phone;
                    const office_id = parent.$("#office_id").val();

                    $.ajax({
                        type : 'POST',
                        url : 'systemDataServlet?action=bindLeaderForOfficeByOfficeId',
                        data : {
                            office_leader_name : office_leader_name,
                            office_leader_type : office_leader_type,
                            office_leader_phone : office_leader_phone,
                            office_id : office_id
                        },
                        dataType : 'json',
                        success : function(data) {
                            if(data==1){
                                parent.layer.msg('添加成功', {
                                    icon : 6,
                                });
                                //重载表格
                                parent.layui.table.reload('leader_info', {
                                    url:'systemDataServlet?action=queryOfficeLeaderByOfficeId'
                                    ,where: {
                                        office_id: office_id
                                    }
                                });
                            }
                            else if(data == -2){
                                parent.layer.msg('该领导已被添加，请不要重复添加', {
                                    icon : 5
                                });
                            }
                            else {
                                // 异常提示
                                parent.layer.msg('添加失败', {
                                    icon : 5
                                });
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
