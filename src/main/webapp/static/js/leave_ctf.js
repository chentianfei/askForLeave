function cancelToLastPage(basepath){
    location.href = basepath+"pages/service/personinformation.jsp";
}


//全选功能
var isCheckAll = false;
function swapCheck() { // checkbox

    if (isCheckAll) {
        $("input[type='checkbox']").each(function () {
            this.checked = false;
        });
        isCheckAll = false;
    } else {
        $("input[type='checkbox']").each(function () {
            this.checked = true;
        });
        isCheckAll = true;
    }
}

//判断重名
function isMultiName(){
    var event = arguments.callee.caller.arguments[0];
    if(event.code === "Enter"){
        /*window.open ('pages/service/tanchuangTest.jsp', 'newwindow', 'height=100, width=400, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no')*/
        showModalDialog('pages/service/tanchuangTest.jsp','example04','dialogWidth:400px;dialogHeight:300px;dialogLeft:200px;dialogTop:150px;center:yes;help:yes;resizable:yes;status:yes')
    }
}

function name_event3() {
    // 回车键
    var event = arguments.callee.caller.arguments[0] || window.event;
    if (event.keyCode == 13) {// 判断是否按了回车，enter的keycode代码是13
        var name = document.getElementById("user_name").value;// 调半天竟然是少了.value!
        // alert(name);
        // $("#get_userInfo").trigger("click");//回车提交表单
        $.post("temporaryLeave?opt=nameSearch", {
            "user_name": name
        }, function (data) {
            $("#getoneUser").html(data);
        });
    }
}