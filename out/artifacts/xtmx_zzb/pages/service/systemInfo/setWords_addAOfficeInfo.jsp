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

            function bind_leader() {
                layer.open({
                    type: 2,
                    title: '填写领导信息',
                    maxmin: true, //开启最大化最小化按钮
                    area: ['500px', '300px'],
                    content: "pages/service/systemInfo/setWords_addAOfficeInfo_bindLeader.jsp",
                    anim:2,
                    resize:false,
                    id:'LAY_layuipro',
                    btn:['提交','重置'],
                    yes:function (index, layero) {
                        //提交按钮的回调
                        var body = layer.getChildFrame('body', index);
                        // 找到隐藏的提交按钮模拟点击提交
                        body.find('#addOfficeLeaderInfoSubmit').click();
/*
                        //获取元素值
                        var office_leader_name = $("#office_leader_name").val();
                        var office_leader_phone = $("#office_leader_phone").val()
                        var office_leader_type;
                        switch ($("#office_leader_type").val()) {
                            case "chief":
                                office_leader_type = "正职";
                                break;
                            case "deputy":
                                office_leader_type = "副职";
                                break;
                            case "preside":
                                office_leader_type = "主持工作的副职";
                                break;
                        }
                        //添加一行
                        var tr = $([
                            '<tr id="leader'+ index +'">'

                                ,'<td style="text-align:center"> ' + office_leader_name +
                                '</td>'

                                ,'<td style="text-align:center">'+ office_leader_type +
                                '</td>'

                                ,'<td style="text-align:center">' + office_leader_phone +
                                '</td>'

                                ,'<td style="text-align:center">'
                                    ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                                ,'</td>'

                            ,'</tr>'
                        ].join(''));

                        //删除
                        tr.find('.demo-delete').on('click', function(){
                            tr.remove();
                        });

                        $("#leader_table").append(tr);*/
                    },
                    btn2: function (index, layero) {
                        //重置按钮的回调
                        var body = layer.getChildFrame('body', index);
                        // 找到隐藏的提交按钮模拟点击提交
                        body.find('#addSpouseReset').click();
                        return false;// 开启该代码可禁止点击该按钮关闭
                    },
                    cancel: function () {
                        //右上角关闭回调
                        //return false 开启该代码可禁止点击该按钮关闭
                    }
                });
            }

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
