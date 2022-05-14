//全局配置
layui.config({
    base: 'static/js/' //存放拓展模块的根目录
}).extend({ //设定模块别名
    common: 'common' //如果 common.js 是在根目录，也可以不用设定别名
});

//负数验证
layui.use(['form','common'], function () {
    var $ = layui.$
        , layer = layui.layer
        , form = layui.form
    var common = layui.common;

    //三级地址联动
    common.showCity('province', 'city', 'district');
    //数字验证
    form.verify({
        integer: [
            /^[1-9]\d*$/
            , '只能输入正整数'
        ]
    });

});

/**
 * 遍历表格内容返回数组
 * @param  Int   id 表格id
 * @return Array
 */
function getTableContent(id){
    var mytable = document.getElementById(id);
    var data = [];
    for(var i=0,rows=mytable.rows.length; i<rows; i++){
        for(var j=0,cells=mytable.rows[i].cells.length; j<cells; j++){
            if(!data[i]){
                data[i] = new Array();
            }
            data[i][j] = mytable.rows[i].cells[j].innerHTML;
        }
    }
    return data;
}

function getLeaderInfoFromLeaderTable(){
    var mytable = document.getElementById("leader_table");
    var data = [];
    for(var i=0,rows=mytable.rows.length; i<rows; i++){
        /*mytable.rows[i].cells.length-1:不需要最后一个操作的innerhtml*/
        for(var j=0,cells=mytable.rows[i].cells.length-1; j<cells; j++){
            if(!data[i]){
                data[i] = new Array();
            }
            data[i][j] = mytable.rows[i].cells[j].innerHTML;
        }
    }
    return data;
}

layer.config({
    skin:'layui-layer-molv'
})

//日期字符串中更换年月日为-
function replaceYMDChinese(YMDChineseStr){
    return YMDChineseStr.replace("年","-").replace("月","-").replace("日","");
}

//字符串时间比较大小,返回大的日期
function compareDateStrRTNBig(OneDateStr,TwoDateStr){
    let OneDateStrReplaceChinese = replaceYMDChinese(OneDateStr);
    let TwoDateStrReplaceChinese = replaceYMDChinese(TwoDateStr);
    let OneDate =  new Date(OneDateStrReplaceChinese.replace(/-/,"/"));
    let TwoDate =  new Date(TwoDateStrReplaceChinese.replace(/-/,"/"));
    return compareDateRTNBig(OneDate,TwoDate);
}

//字符串时间比较大小,返回比较结果，dateTime1>dateTime2返回true，反之亦反
function compareDateStrRTNBoolean(dateTime1,dateTime2){
    let OneDateStrReplaceChinese = replaceYMDChinese(dateTime1);
    let TwoDateStrReplaceChinese = replaceYMDChinese(dateTime2);
    let OneDate =  new Date(OneDateStrReplaceChinese.replace(/-/,"/"));
    let TwoDate =  new Date(TwoDateStrReplaceChinese.replace(/-/,"/"));

    return compareDateRTNBoolean(OneDate,TwoDate);
}

//js date类型数据比较大小，返回大的日期
function compareDateRTNBig(dateTime1,dateTime2) {
    let formatDate1 = new Date(dateTime1);
    let formatDate2 = new Date(dateTime2);
    if(formatDate1 > formatDate2){
        return formatDate1;
    }else{
        return formatDate2;
    }
}

//js date类型数据比较大小，返回比较结果，dateTime1>dateTime2返回true，反之亦反
function compareDateRTNBoolean(dateTime1,dateTime2) {
    let formatDate1 = new Date(dateTime1);
    let formatDate2 = new Date(dateTime2);
    if(formatDate1 >= formatDate2){
        return true;
    }else{
        return false;
    }
}

//从带中文年月日的字符串中解析出年的数字:~~是为了去除字符串内容前面的0
function getYearFromYMDChinese(YMDChineseStr){
    let yearCharIndex = YMDChineseStr.indexOf("年");
    let monthCharIndex = YMDChineseStr.indexOf("月");
    return ~~YMDChineseStr.slice(0,yearCharIndex)
}

//从带中文年月日的字符串中解析出年的数字:~~是为了去除字符串内容前面的0
function getMonthFromYMDChinese(YMDChineseStr){
    let yearCharIndex = YMDChineseStr.indexOf("年");
    let monthCharIndex = YMDChineseStr.indexOf("月");
    return ~~YMDChineseStr.slice(yearCharIndex+1,monthCharIndex)
}

//从带中文年月日的字符串中解析出年的数字:~~是为了去除字符串内容前面的0
function getDayFromYMDChinese(YMDChineseStr){
    let monthCharIndex = YMDChineseStr.indexOf("月");
    let dayCharIndex = YMDChineseStr.indexOf("日");
    return ~~YMDChineseStr.slice(monthCharIndex+1,dayCharIndex)
}

//时间戳的处理
layui.laytpl.toDateString = function(d, format){
    let date = new Date(d || new Date())
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
            url: 'systemDataServlet?action=queryOffice',
            dataType: 'json',
            type: 'post',
            success: function(data) {
                var sourceData = data.data;
                if (sourceData!== null) {
                    $("#office").empty();
                    $("#office").append("<option value=''>请选择</option>");
                    $.each(sourceData, function(index, item) {
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
            url: 'systemDataServlet?action=queryLevelInfo',
            dataType: 'json',
            type: 'post',
            success: function (data) {
                var sourceData = data.data;
                if (sourceData !== null) {
                    $("#level").empty();
                    $("#level").append("<option value=''>请选择</option>");
                    $.each(sourceData, function (index, item) {
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

//为角色信息下拉框select绑定后台数据
function bindRoleInfoSelectData() {
    layui.use(['laydate','form','common'], function() {
        var form = layui.form;
        var $ = layui.jquery;
        $.ajax({
            url: 'systemDataServlet?action=queryRoleInfo',
            dataType: 'json',
            type: 'post',
            success: function (Data) {
                var sourceData = Data.data;
                if (sourceData !== null) {
                    $("#role").empty();
                    $("#role").append("<option value=''>请选择</option>");
                    $.each(sourceData, function (index, item) {
                        $('#role').append(new Option(item.role_name+"-"+item.role_description,
                            item.id));
                    });
                } else {
                    $("#role").append(new Option("暂无数据", ""));
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
            url: 'systemDataServlet?action=queryLeaveType',
            dataType: 'json',
            type: 'post',
            success: function (data) {
                var sourceData = data.data;
                if (sourceData !== null) {
                    $("#leave_type").empty();
                    $("#leave_type").append("<option value=''>请选择</option>");
                    $.each(sourceData, function (index, item) {
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

//为请假种类复选框checkbox绑定后台数据
function bindLeaveTypeCheckboxData() {
    layui.use(['laydate','form','common'], function() {
        var form = layui.form;
        var $ = layui.jquery;
        $.ajax({
            url: 'systemDataServlet?action=queryLeaveType',
            dataType: 'json',
            type: 'post',
            success: function (data) {
                var sourceData = data.data;
                if (sourceData !== null) {
                    $.each(sourceData, function (index, item) {
                        $("#leave_type").append("<input type='checkbox' lay-filter='leave_type' name='leave_type' value='"+item.leave_type+"' title='"+item.leave_type+"'>");
                    });
                } else {
                    $("#leave_type").append("<span style='color: red'>网络错误，无数据</span>");
                }
                //重新渲染
                form.render('checkbox');
            }
        });
    });
}

//为发送对象状态开关绑定状态
function bindSendObjSwitchStatus() {
    layui.use(['laydate','form','common'], function() {
        var form = layui.form;
        var $ = layui.jquery;
        //获取相关标签
        var $doesSendSelf = $("#doesSendSelf");
        var $doesSendLeader = $("#doesSendLeader");

        //query switch status
        $.ajax({
            url: 'systemDataServlet?action=querySendObjStatusCode',
            dataType: 'json',
            type: 'post',
            success: function (data) {
                var sourceData = data.data;
                var doesSendSelfCode = sourceData.doesSendSelfUpdateCode;
                var doesSendLeaderCode = sourceData.doesSendLeaderUpdateCode;

                if (doesSendSelfCode== 1) {
                    $doesSendSelf.attr('checked', 'checked');
                    form.render();
                } else {
                    $doesSendSelf.removeAttr('checked');
                    form.render();
                }

                if (doesSendLeaderCode== 1) {
                    $doesSendLeader.attr('checked', 'checked');
                    form.render();
                } else {
                    $doesSendLeader.removeAttr('checked');
                    form.render();
                }
            }
        })
    });
}

//获取当前短信提醒天数
function bindCurrentSmsAlertDays() {
        //query currentSmsAlertDays
        $.ajax({
            url: 'systemDataServlet?action=querySmsAlertDays',
            dataType: 'json',
            type: 'post',
            success: function (data) {
                $("#currentSmsAlertDays").html(data.data);
            }
        })
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


