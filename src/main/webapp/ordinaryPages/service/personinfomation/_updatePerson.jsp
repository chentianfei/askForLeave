<%--
  Created by IntelliJ IDEA.
  User: tianfeichen
  Date: 2021/8/21
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--更新个人--%>
<head>
    <%--基础引入--%>
    <%@include file="/pages/common/baseinfo.jsp"%>
</head>
<body>
<div style="padding: 10px">

    <form class="layui-form layui-form-pane">

        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px">姓名</label>
            <div class="layui-input-inline" style="width:400px">
                <input type="text" name="name" id="name" placeholder="请输入" lay-verify="required" id="username"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px">性别</label>
            <div class="layui-input-block" style="width:400px">
                <input type="radio" name="sex" value="男" title="男" checked="">
                <input type="radio" name="sex" value="女" title="女">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px">出生日期</label>
            <div class="layui-input-inline" style="width:400px" >
                <input type="text" class="layui-input" placeholder="请点击"
                       id="birthDate" name="birthDate"  lay-verify="required">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px">民族</label>
            <div class="layui-input-inline" style="width:400px">
                <select id="nation" name="nation" lay-verify="required" lay-search>
                    <option value=""></option>
                </select>
            </div>
        </div>


        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 150px">本人籍贯</label>
                <div class="layui-input-inline" style="width: 120px">
                    <select id="province" name="province"  lay-filter="province"
                            lay-verify="required"
                            lay-search>
                        <option value="">选择省</option>
                    </select>
                </div>
                <div class="layui-input-inline" style="width: 120px">
                    <select id="city" name="city"  lay-filter="city"
                            lay-verify="required"
                            lay-search>
                        <option value="">选择市</option>
                    </select>
                </div>
                <div class="layui-input-inline" style="width: 120px">
                    <select id="district" name="district"  lay-filter="district"
                            lay-verify="required"
                            lay-search>
                        <option value="">选择区</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px">所在类区</label>
            <div class="layui-input-inline" style="width:400px">
                <select id="area_class" name="area_class" lay-verify="required" lay-search>
                    <option value="">请选择</option>
                    <option value="二类区">二类区</option>
                    <option value="三类区">三类区</option>
                    <option value="四类区">四类区</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px">现任职务</label>
            <div class="layui-input-inline" style="width:400px">
                <input type="text" name="post" placeholder="请输入"
                       id="post"
                       lay-verify="required"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px">职级</label>
            <div class="layui-input-inline" style="width:400px">
                <select id="level" name="level" lay-verify="required" lay-search>
                    <option value="">请选择</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px">联系电话</label>
            <div class="layui-input-inline" style="width:400px">
                <input type="tel" id="phone" name="phone" placeholder="请输入"
                       lay-verify="required|phone"
                       onblur="isPhoneExists()"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width:150px">允许休假天数</label>
            <div class="layui-input-inline" style="width:400px">
                <input type="text" name="allow_Leave_Days"
                       id="allow_Leave_Days"
                       placeholder="请输入" lay-verify="required|number|integer"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <!-- 数据域 -->
        <div class="layui-form-item" style="display:none">
            <label class="layui-form-label" style="width:150px">人员编号</label>
            <div class="layui-input-inline" style="width:400px">
                <input name="person_id"
                       id="person_id"
                       placeholder="请输入" lay-verify="required|number"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <!-- 数据域 -->
        <button type="submit" class="layui-btn" style="display:none"
                id="updatePersonSubmit" lay-submit lay-filter="updatePersonSubmit"></button>
        <button type="reset" class="layui-btn" style="display:none"
                id="addPersonReset" lay-submit lay-filter="updatePersonReset"></button>

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

        //日期框
        laydate.render({
            elem: '#birthDate'//指定元素
            ,type: 'date'
        });

        form.render();

        form.on('submit(updatePersonSubmit)', function(data){
            const sourceData = data.field;
            const person_id = sourceData.person_id;
            const area_class = sourceData.area_class;
            const birthDate = replaceYMDChinese(sourceData.birthDate);
            const level = sourceData.level;
            const name = sourceData.name;
            const nation = sourceData.nation;
            const office = ${sessionScope.user.office};
            const phone = sourceData.phone;
            const sex = sourceData.sex;
            const post = sourceData.post;
            const allow_Leave_Days = sourceData.allow_Leave_Days;

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

            //获取当前页码
            var currentPage = parent.$(".layui-laypage-skip .layui-input").val();

            $.ajax({
                type : 'POST',
                url : 'personServlet?action=updatePersonInfo',
                data : {
                    person_id : person_id,
                    name : name,
                    sex : sex,
                    nation : nation,
                    birthDate : birthDate,
                    nativePlace:nativePlace,
                    office : office,
                    post : post,
                    area_class : area_class,
                    level : level,
                    phone : phone,
                    allow_Leave_Days:allow_Leave_Days
                },
                dataType : 'json',
                success : function(data) {
                    // 成功提示框
                    parent.layer.msg('更新成功', {
                        icon : 6,
                    });

                    //重载表格
                    parent.layui.table.reload('personinformation', {
                        url: 'personServlet?action=queryAllPerson'
                        ,page: {
                            curr: currentPage //重新从第 1 页开始
                        }
                        ,request: {
                            pageName: 'curr' //页码的参数名称，默认：page
                            ,limitName: 'nums' //每页数据量的参数名，默认：limit
                        }
                    });

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
                    //关闭弹框
                    //parent.layer.closeAll('iframe');
                }
            });
            return false;
        });
    });
</script>
</body>
</html>
