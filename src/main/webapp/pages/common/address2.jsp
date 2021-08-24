
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <script src="http://localhost:8080/askForLeave/static/js/jquery-3.6.0.js"></script>
        <script src="http://localhost:8080/askForLeave/static/js/province.js"> </script>
        <script src="http://localhost:8080/askForLeave/static/js/method.js"> </script>
        <script src="http://localhost:8080/askForLeave/static/layui/layui.js"></script>
        <link rel="stylesheet" href="http://localhost:8080/askForLeave/static/layui/css/layui.css">
    </head>
    <body>
        <%--您选择的是： <input type="text" value="" id="addr-show">--%>
        <form class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">籍贯</label>
                <div class="layui-input-block">
                    <!-- 省份选择 -->
                    <select lay-verify="required" name="city_prov" id="prov" lay-filter="prov">
                        <option>请选择省份</option>
                    </select>
                    <!-- 城市选择 -->
                    <select lay-verify="required" name="city_city" id="city" lay-filter="city" >
                        <option>请选择城市</option>
                    </select>
                    <!-- 区县选择 -->
                    <select lay-verify="required" name="city_country" id="country" lay-filter="country" >
                        <option>请选择县区</option>
                    </select>
                </div>
            </div>
        </form>
        <script type="text/javascript">
            layui.use('form', function(){
                var form = layui.form;

                form.on('select(prov)', function(data){
                    showCity(data.elem);
                    form.render(); //更新全部
                });
                form.on('select(city)', function(data){
                    showCountry(data.elem);
                    form.render(); //更新全部
                });

                form.on('select(country)', function(data){
                    selectCountry(data.elem);
                    form.render(); //更新全部
                });

            });


        </script>


        <%-- <button type="button" class="btn" onClick="showAddr()">确定</button>--%>
    </body>
</html>

