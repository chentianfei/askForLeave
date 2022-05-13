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
                    <label class="layui-form-label" style="width:150px">配偶姓名</label>
                    <div class="layui-input-inline" style="width:400px">
                        <input type="text" name="name_spouse" placeholder="请输入"
                               autocomplete=“off”
                               lay-verify="required" id="name_spouse"
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label" style="width: 150px">配偶籍贯</label>
                        <div class="layui-input-inline" style="width: 120px">
                            <select name="province" data-area="西藏自治区"
                                    lay-verify="required"
                                    lay-filter="province" lay-search>
                                <option value="">选择省</option>
                            </select>
                        </div>
                        <div class="layui-input-inline" style="width: 120px">
                            <select name="city" data-area="日喀则市"
                                    lay-verify="required"
                                    lay-filter="city" lay-search>
                                <option value="">选择市</option>
                            </select>
                        </div>
                        <div class="layui-input-inline" style="width: 120px">
                            <select name="district" lay-filter="district"
                                    lay-verify="required"
                                    lay-search>
                                <option value="">选择区</option>
                            </select>
                        </div>
                    </div>
                </div>

                <button type="submit" class="layui-btn" style="display:none"
                        id="addSpouseSubmit" lay-submit lay-filter="addSpouseSubmit"></button>
                <button type="reset" class="layui-btn" style="display:none"
                        id="addSpouseReset" lay-submit lay-filter="addSpouseReset"></button>

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

                form.render();

                form.on('submit(addSpouseSubmit)', function(data){
                    const sourceData = data.field;

                    const name_spouse = sourceData.name_spouse;
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
                    const nativeplace_spouse = provinceName + ' ' + cityName + ' ' + districtName;
                    //给父页面的name_spouse赋值
                    parent.$("#name_spouse").val(name_spouse);
                    //给父页面的nativeplace_spouse赋值
                    parent.$("#nativeplace_spouse").val(nativeplace_spouse);
                    return false;
                });
            });
        </script>
    </body>
</html>
