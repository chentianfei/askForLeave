//全局配置
layui.config({
    base: 'static/js/' //存放拓展模块的根目录
}).extend({ //设定模块别名
    common: 'common' //如果 common.js 是在根目录，也可以不用设定别名
});

layui.use(['form','common'], function() {
    var common = layui.common;
    //三级地址联动
    common.showCity('province', 'city', 'district');
});

layer.config({
    skin:'layui-layer-molv'
})

//时间戳的处理
layui.laytpl.toDateString = function(d, format){
    var date = new Date(d || new Date())
        ,ymd = [
        this.digit(date.getFullYear(), 4)
        ,this.digit(date.getMonth() + 1)
        ,this.digit(date.getDate())
    ]
        ,hms = [
        this.digit(date.getHours())
        ,this.digit(date.getMinutes())
        ,this.digit(date.getSeconds())
    ];
}

//全局取消回车默认事件
document.onkeydown = function(e){
    if(e.keyCode==13){e.preventDefault();//禁用回车的默认事件
    }
}

/*
 * 表格分页参照
 * parseData:function(res){ //res 即为原始返回的数据
    console.log(res);
    var current_pages;
    //第一次显示的时候this.page=true，把这种情况单独列出
    if(this.page===true)current_pages=1;
    else current_pages=this.page.curr;
    //根据分页要求选出需要显示的数据
    var data= res.data.slice(this.limit*(current_pages-1),this.limit*current_pages);
    return {
        "code": res.code,
        "msg":res.msg,
        "count": res.data.length,
        "data": data
    }*/

//根据人员编号person_id查询请假具体信息与统计数据，并重载表格
function queryLeaveInfoAndReloadTable(){



    //处理本年度请假信息统计显示功能
    table_LeaveInfoCount.reload({
        where: {
            person_name: person_name
            , phoneNum: phoneNum
        }
    });

    //处理本年度请假详细信息显示功能
    table_LeaveInfo.reload({
        where: {
            person_name: person_name
            ,phoneNum: phoneNum
        }
    });

}

//点击查看详情按钮
function openPersonDetailPage(person_id){
    console.log(person_id);
    layer.msg("person_id:"+person_id);
}

/*手机号码：唯一，手机框失去焦点后ajax到后台判断是否存在*/
function isPhoneExists() {
    layui.use(['layer'], function () {
        var layer = layui.layer;

        $.ajax({
            type: 'POST',
            url: 'personServlet?action=isPhoneExists',
            data: {
                phone: $('#phone').val()
            },
            dataType: 'json',
            success: function (data) {
                if (data.existCode == 0) {
                    //改手机号已经存在，不能重复添加，清空值
                    $("#phone").css({"border": "1px solid red"});
                    //parent.layer.msg('出现网络故障', {
                    parent.layer.msg('该手机号已存在，请修改！',
                        {
                            icon: 5,
                            time: 2000
                        }
                    );
                    $("#phone").val('');
                }else {
                    //改手机号不存在，可以添加
                    $("#phone").css({"border": "solid 1px green",
                        "border-radius": "2px"});
                }
            },
            error: function (data) {
                // 异常提示
                parent.layer.msg('出现网络故障', {
                    icon: 5
                });
            }

        });
    });
}

//下拉框数据源绑定
//为民族下拉框select绑定数据
function bindNationSelectData(){
    layui.use(['laydate','form','common'], function() {
        var form = layui.form;
        var $ = layui.jquery;
        $.ajax({
            url: 'systemDataServlet?action=bindNationSelectData',
            dataType: 'json',
            type: 'post',
            success: function (data) {
                if (data !== null) {
                    $("#nation").empty();
                    $("#nation").append("<option value=''>请选择</option>");
                    $.each(data, function (index, item) {
                        $('#nation').append(new Option(item.nation_name,item.nation_name));
                    });
                } else {
                    $("#nation").append(new Option("暂无数据", ""));
                }
                //重新渲染
                form.render("select");
            }
        });
    });
}


//为工作单位下拉框select绑定后台数据
function bindOfficeSelectData(){
    layui.use(['laydate','form','common'], function() {
        var form = layui.form;
        var $ = layui.jquery;
        $.ajax({
            url: 'systemDataServlet?action=bindOfficeSelectData',
            dataType: 'json',
            type: 'post',
            success: function(data) {
                if (data!== null) {
                    $("#office").empty();
                    $("#office").append("<option value=''>请选择</option>");
                    $.each(data, function(index, item) {
                        $('#office').append(new Option(item.office_name,item.office_name));
                    });
                } else {
                    $("#office").append(new Option("暂无数据", ""));
                }
                //重新渲染
                form.render("select");
            }
        });
    });
}

//为职级下拉框select绑定后台数据
function bindLevelSelectData() {
    layui.use(['laydate','form','common'], function() {
        var form = layui.form;
        var $ = layui.jquery;
        $.ajax({
            url: 'systemDataServlet?action=bindLevelSelectData',
            dataType: 'json',
            type: 'post',
            success: function (data) {
                if (data !== null) {
                    $("#level").empty();
                    $("#level").append("<option value=''>请选择</option>");
                    $.each(data, function (index, item) {
                        $('#level').append(new Option(item.level_name,item.level_name));
                    });
                } else {
                    $("#level").append(new Option("暂无数据", ""));
                }
                //重新渲染
                form.render("select");
            }
        });
    });
}

//为请假种类下拉框select绑定后台数据
function bindLeaveTypeSelectData() {
    layui.use(['laydate','form','common'], function() {
        var form = layui.form;
        var $ = layui.jquery;
        $.ajax({
            url: 'systemDataServlet?action=bindLeaveTypeSelectData',
            dataType: 'json',
            type: 'post',
            success: function (data) {
                if (data !== null) {
                    $("#leave_type").empty();
                    $("#leave_type").append("<option value=''>请选择</option>");
                    $.each(data, function (index, item) {
                        $('#leave_type').append(new Option(item.leave_type,item.leave_type));
                    });
                } else {
                    $("#leave_type").append(new Option("暂无数据", ""));
                }
                //重新渲染
                form.render("select");
            }
        });
    });
}

//遍历选中行数据数组对象，根据str的值返回对应参数
function queryAndBindInfo_array(data_array,str){
    var key,key_in;
    //循环data,发现匹配str的就返回对应值
    for(key in data_array){
        for(key_in in data_array[key]){
            if(key_in === str){
                return data_array[key][key_in];
            }
        }
    }

}

//遍历选中行数据对象，根据str的值返回对应参数
function queryAndBindInfo_obj(data_obj,str){
    var key;
    //循环data,发现匹配str的就返回对应值
    for(key in data_obj){
        if(key === str){
            return data_obj[key];
        }
    }

}


