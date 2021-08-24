<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<script src="http://localhost:8080/askForLeave/static/js/jquery-3.6.0.js"></script>
<script src="http://localhost:8080/askForLeave/static/js/province.js"></script>
<script src="http://localhost:8080/askForLeave/static/js/method.js"></script>--%>

<%--<label class="layui-form-label">籍贯</label>
<div class="layui-input-block">--%>
    <!-- 省份选择 -->
    <select lay-verify="required" name="city_prov" id="prov" lay-filter="prov">
        <option>请选择省份</option>
    </select>
    <!-- 城市选择 -->
    <select lay-verify="required" name="city_city" id="city" lay-filter="city">
        <option>请选择城市</option>
    </select>
    <!-- 区县选择 -->
    <select lay-verify="required" name="city_country" id="country" lay-filter="country">
        <option>请选择县区</option>
    </select>
<%--</div>--%>


<script type="text/javascript">
    layui.use('form', function () {
        var form = layui.form;

        form.on('select(prov)', function (data) {
            showCity(data.elem);
            form.render(); //更新全部
        });
        form.on('select(city)', function (data) {
            showCountry(data.elem);
            form.render(); //更新全部
        });

        form.on('select(country)', function (data) {
            selectCountry(data.elem);
            form.render(); //更新全部
        });
    });
</script>

