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
                    <label class="layui-form-label" style="width:120px">单位名称</label>
                    <div class="layui-input-inline" style="width:220px">
                        <input type="text" name="office_name" id="office_name"
                               lay-verify="required"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>

            <%--    <div class="layui-upload">
                    <button type="button" class="layui-btn layui-btn-normal"
                             onclick="bind_leader()">
                        添加领导
                    </button>
                    <div style="max-width: 800px">
                        <table class="layui-table" id="leader_table">
                            <colgroup>
                                <col width="260">
                                <col width="260">
                                <col width="260">
                                <col width="260">
                            </colgroup>
                            <thead>
                                <tr>
                                    <th style="text-align:center">领导姓名</th>
                                    <th style="text-align:center">领导类型</th>
                                    <th style="text-align:center">联系电话</th>
                                    <th style="text-align:center">操作</th>
                                </tr>
                            </thead>
                            <tbody id="leader_info_table_tbody">

                            </tbody>
                        </table>
                    </div>
                </div>


                &lt;%&ndash;接受子页面传来的office_leader_name的值，以便后续发送&ndash;%&gt;
                <input  type="hidden" class="layui-input"
                        name="office_leader_name"
                        style="display:none"
                        id="office_leader_name"  />
                &lt;%&ndash;接受office_leader_type的值，以便后续发送&ndash;%&gt;
                <input  type="hidden" class="layui-input"
                        name="office_leader_type"
                        style="display:none"
                        id="office_leader_type"  />
                &lt;%&ndash;接受office_leader_phone的值，以便后续发送&ndash;%&gt;
                <input  type="hidden" class="layui-input"
                        name="office_leader_phone"
                        style="display:none"
                        id="office_leader_phone"  />--%>

                <!-- 提交按钮 -->
                <button type="submit" class="layui-btn" style="display:none"
                        id="addOfficeInfoSubmit" lay-submit
                        lay-filter="addOfficeInfoSubmit"></button>

            </form>
        </div>

        <script type="text/javascript">



            layui.use(['laydate','form','common','table'], function() {
                var form = layui.form;
                var $ = layui.jquery;
                var index = parent.layer.getFrameIndex(window.name);

                form.render();

                form.on('submit(addOfficeInfoSubmit)', function(data){
                    const sourceData = data.field;

                    const office_name = sourceData.office_name;

                    $.ajax({
                        type : 'POST',
                        url : 'systemDataServlet?action=addAOffice',
                        data : {
                            office_name : office_name
                        },
                        dataType : 'json',
                        success : function(data) {
                            if(data==1){
                                parent.layer.msg('添加成功', {
                                    icon : 6,
                                });
                                //重载表格
                                parent.layui.table.reload('officeInfo', {
                                    url:'systemDataServlet?action=queryOffice'
                                    ,page: {
                                        curr: 1
                                    }
                                    ,request: {
                                        pageName: 'curr'
                                        ,limitName: 'nums'
                                    }
                                });
                            }
                            else if(data == -2){
                                parent.layer.msg('该单位已被添加，请不要重复添加', {
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
