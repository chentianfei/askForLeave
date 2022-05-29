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
                    <label class="layui-form-label" style="width:150px">姓名</label>
                    <div class="layui-input-inline" style="width:400px">
                        <input type="text" name="name" placeholder="请输入"
                               autocomplete=“off”
                               lay-verify="required" id="name" name="name"
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
                               autocomplete=“off”
                               id="birthDate" name="birthDate"  lay-verify="required">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">参公年月</label>
                    <div class="layui-input-inline" style="width:400px" >
                        <input type="text" class="layui-input" placeholder="请点击"
                               autocomplete=“off”
                               id="start_work_date" name="start_work_date"
                               lay-verify="required">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">婚姻状态</label>
                    <div class="layui-input-block">
                        <input type="radio" name="marriage_status" value="未婚" id="spinsterhood" lay-verify="required"
                               lay-filter="spinsterhood" title="未婚" checked="">
                        <input type="radio" name="marriage_status" value="已婚" id="married" lay-verify="required"
                               lay-filter="married" title="已婚">
                        <input type="radio" name="marriage_status" value="丧偶" id="widowed" lay-verify="required"
                               lay-filter="widowed" title="丧偶">
                        <input type="radio" name="marriage_status" value="离异" id="divorced" lay-verify="required"
                               lay-filter="divorced" title="离异">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">配偶姓名</label>
                    <div class="layui-input-inline" style="width:400px">
                        <input type="tel" id="name_spouse" name="name_spouse" disabled
                               autocomplete="off" class="layui-input" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">配偶籍贯原信息</label>
                    <div class="layui-input-inline" style="width:400px">
                        <input type="text" name="nativeplaceSpouseForAlert" id="nativeplaceSpouseForAlert"
                               disabled
                               class="layui-input" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">配偶籍贯</label>
                    <div class="layui-input-inline" style="width:400px">
                        <input type="tel" id="nativeplace_spouse" name="nativeplace_spouse" disabled
                               autocomplete="off" class="layui-input" style="background: #eee">
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
                    <label class="layui-form-label" style="width:150px">本人籍贯原信息</label>
                    <div class="layui-input-inline" style="width:400px">
                        <input type="text" name="nativaPlaceForAlert" id="nativaPlaceForAlert"
                               disabled
                               class="layui-input" style="background: #eee">
                    </div>
                </div>

                <div class="layui-form-item" >
                    <div class="layui-inline">
                        <label class="layui-form-label" style="width: 150px">本人籍贯</label>
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

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">工作单位</label>
                    <div class="layui-input-inline" style="width:400px">
                        <select id="office" name="office" lay-verify="required" lay-search>
                            <option value=""></option>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">所在类区</label>
                    <div class="layui-input-inline" style="width:400px">
                        <select name="area_class" id="area_class" lay-verify="required" lay-search>
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
                        <input type="text" name="post" placeholder="请输入" id="post"
                               autocomplete=“off”
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
                               autocomplete=“off”
                               lay-verify="required|phone"
                               onblur="isPhoneExists()"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:150px">允许休假天数</label>
                    <div class="layui-input-inline" style="width:400px">
                        <input type="text" name="allow_Leave_Days" id="allow_Leave_Days"
                               placeholder="请输入" lay-verify="required|number|integer"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>

                <%--接受person_id的值，以便子页面获取--%>
                <input  type="hidden" class="layui-input"
                        name="person_id"
                        style="display:none"
                        id="person_id"  />

                <button type="submit" class="layui-btn" style="display:none"
                        id="updatePersonSubmit" lay-submit lay-filter="updatePersonSubmit"></button>
                <button type="reset" class="layui-btn" style="display:none"
                        id="updatePersonReset" lay-submit lay-filter="updatePersonReset"></button>

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
                    , type: 'date'
                });
                laydate.render({
                    elem: '#start_work_date'//指定元素
                    , type: 'date'
                });

                //数字验证
                form.verify({
                    integer: [
                        /^[1-9]\d*$/
                        , '只能输入正整数'
                    ]
                });

                form.render();


                /*点击已婚，弹窗填信息*/
                form.on('radio(married)', function(data){
                    $("#name_spouse").val("未知");
                    $("#nativeplace_spouse").val("未知");
                    layer.open({
                        type: 2,
                        title: '填写配偶信息',
                        maxmin: true, //开启最大化最小化按钮
                        area: ['500px', '300px'],
                        content: "pages/service/personinfomation/_addPerson_addSpouse.jsp",
                        anim:2,
                        resize:false,
                        id:'LAY_layuipro',
                        btn:['提交'],
                        yes:function (index, layero) {
                            //提交按钮的回调
                            var body = layer.getChildFrame('body', index);
                            // 找到隐藏的提交按钮模拟点击提交
                            body.find('#addSpouseSubmit').click();
                        },
                        cancel: function () {
                            //右上角关闭回调
                            //return false 开启该代码可禁止点击该按钮关闭
                        }
                    });
                });
                /*点击未婚，清空配偶信息*/
                form.on('radio(spinsterhood)', function(data){
                    //清空name_spouse与nativeplace_spouse的值
                    $("#name_spouse").val("未婚");
                    $("#nativeplace_spouse").val("未婚");
                });
                /*点击丧偶，清空配偶信息*/
                form.on('radio(widowed)', function(data){
                    //清空name_spouse与nativeplace_spouse的值
                    $("#name_spouse").val("丧偶");
                    $("#nativeplace_spouse").val("丧偶");
                });
                /*点击离异，清空配偶信息*/
                form.on('radio(divorced)', function(data){
                    //清空name_spouse与nativeplace_spouse的值
                    $("#name_spouse").val("离异");
                    $("#nativeplace_spouse").val("离异");
                });

                form.on('submit(updatePersonSubmit)', function(data){
                    const sourceData = data.field;

                    const person_id = sourceData.person_id;
                    const name = sourceData.name;
                    const sex = sourceData.sex;
                    const nation = sourceData.nation;
                    const birthDate = sourceData.birthDate;
                    const start_work_date = sourceData.start_work_date;
                    const marriage_status = sourceData.marriage_status;
                    const name_spouse = sourceData.name_spouse;
                    const nativeplace_spouse = sourceData.nativeplace_spouse;
                    const office = sourceData.office;
                    const post = sourceData.post;
                    const area_class = sourceData.area_class;
                    const level = sourceData.level;
                    const phone = sourceData.phone;
                    const allow_Leave_Days = sourceData.allow_Leave_Days;
                    /*--------解析解析框中的地址内容-----*/
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
                    //封装地址内容
                    const nativePlace = provinceName + ' ' + cityName + ' ' + districtName;
                    /*--------解析解析框中的地址内容-----*/
                    //获取当前页码
                    var currentPage = parent.$(".layui-laypage-skip .layui-input").val();
                    $.ajax({
                        type : 'POST',
                        url : 'personServlet?action=updatePersonInfo',
                        data : {
                            person_id:person_id,
                            name:name,
                            sex:sex,
                            nation:nation,
                            birthDate:birthDate,
                            start_work_date:start_work_date,
                            nativePlace:nativePlace,
                            marriage_status:marriage_status,
                            name_spouse:name_spouse,
                            nativeplace_spouse:nativeplace_spouse,
                            office:office,
                            post:post,
                            area_class:area_class,
                            level:level,
                            phone:phone,
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
                                    curr: currentPage
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
