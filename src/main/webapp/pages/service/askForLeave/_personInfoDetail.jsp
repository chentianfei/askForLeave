
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%--判断重名--%>
    <head>
        <%--基础引入--%>
        <%@include file="/pages/common/baseinfo.jsp"%>
        <style>
            .laytable-cell-radio {
                padding-top:15px;
            }
        </style>
    </head>
    <body>
        <div style="padding: 10px">
            <table class="layui-hide" id="multipleName" lay-filter="multipleName"></table>
        </div>

        <script type="text/javascript">
            layui.use(["table","jquery"],function (){
                var table = layui.table;
                var $ = layui.jquery;

                table.render({
                    elem: '#multipleName'
                    ,url:'personServlet?action=queryMultipleName'
                    ,method:"post"
                    //*****此处若用get会导致前台乱码，persername作为参数传到后台用get再传回来时就会乱码***
                    ,where:{
                        person_name: parent.$('#person_name').val()//获取父页面#username的input里面的值，传给后台处理
                    }
                    ,cols: [[
                        {type:'radio'}
                        ,{field:'person_name', title: '姓名',width:90}
                        ,{field:'sex',  title: '性别',width:90, sort: true}
                        ,{field:'nationality', title: '民族',width:70}
                        ,{field:'birthDate', title: '出生年月',width:150}
                        ,{field:'nativePlace',title: '本人籍贯'}
                        ,{field:'office', title: '工作单位'}
                        ,{field:'job',  title: '现任职务',width:90}
                        ,{field:'phoneNum',  title: '联系电话',width:120}

                    ]]
                    ,page: false
                });

                //头工具栏事件
                table.on("toolbar(multipleName)", function(obj){
                    var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
                    var index = parent.layer.getFrameIndex(window.name);
                    switch (obj.event) {
                        case "returnDataAndClose":
                            var data = checkStatus.data;  //获取选中行数据
                            for (var key in data) {
                                //data本身就是个json对象，是行数据对象，相当于java里的list
                                //一行表示data里有一条数据，多行多个，故如果要取得每一个里面的数据
                                //需要进行两次遍历，先取得行数据，在取得该行里的人员信息的对应信息
                                for (var key_in in data[key]) {
                                    // console.log("key_in=" + key_in);
                                    // console.log("data[key][key_in]=" + data[key][key_in]);
                                    if (key_in === "sex") {
                                        $("#td_sex",window.parent.document).html(data[key][key_in]);
                                    } else if (key_in === "nationality"){
                                        $("#td_nationality",window.parent.document).html(data[key][key_in]);
                                    } else if (key_in === "birthDate"){
                                        $("#td_birthDate",window.parent.document).html(data[key][key_in]);
                                    } else if (key_in === "nativePlace"){
                                        $("#td_nativePlace",window.parent.document).html(data[key][key_in]);
                                    } else if (key_in === "office"){
                                        $("#td_office",window.parent.document).html(data[key][key_in]);
                                    } else if (key_in === "job"){
                                        $("#td_job",window.parent.document).html(data[key][key_in]);
                                    } else if (key_in === "phoneNum"){
                                        $("#td_phoneNum",window.parent.document).html(data[key][key_in]);
                                    }
                                }
                            }
                            parent.layer.close(index);
                            break;
                        case "close":
                            parent.layer.close(index);
                            break;
                    };
                });
            })


        </script>
    </body>
</html>
