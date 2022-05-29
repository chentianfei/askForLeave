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
                    <div class="layui-inline">
                        <label class="layui-form-label" style="width: 120px">籍贯信息</label>
                        <div class="layui-input-inline" style="width: 100px">
                            <select name="province" data-area=""
                                    lay-verify="required"
                                    lay-filter="province" lay-search>
                                <option value="">选择省</option>
                            </select>
                        </div>
                        <div class="layui-input-inline" style="width: 100px">
                            <select name="city" data-area=""
                                    lay-verify="required"
                                    lay-filter="city" lay-search>
                                <option value="">选择市</option>
                            </select>
                        </div>
                        <div class="layui-input-inline" style="width: 100px">
                            <select name="district" lay-filter="district"
                                    lay-verify="required"
                                    lay-search>
                                <option value="">选择区</option>
                            </select>
                        </div>
                    </div>


                </div>

                <button type="submit" class="layui-btn" style="display:none"
                        id="setNativeSubmit" lay-submit lay-filter="setNativeSubmit"></button>
                <button type="reset" class="layui-btn" style="display:none"
                        id="setNativeReset" lay-submit lay-filter="setNativeReset"></button>

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

                form.on('submit(setNativeSubmit)', function(data){
                    const sourceData = data.field;

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
                    const nativeplace = provinceName + ' ' + cityName + ' ' + districtName;
                    const nativeType = parent.$("#nativeType:hidden").val();
                    //给父页面的nativeplace赋值
                    parent.$("#"+nativeType).val(nativeplace);
                    //关闭此页面
                    parent.layer.close(index);
                    return false;
                });
            });
        </script>
    </body>
</html>
