/**
 * 请假系统js 系统内置短信发送模块
 */
// 导入js模块
// document.write("<script src='../js/jquery.min.js'></script>");
//导出用户信息excel
function find_user_dbtoexcel() {
    $.post("UserDBToExcel", function (filePath, status) {
        if (status == 'success') {
            window.open(filePath);
        }
    });
}

// 上传excel
function submitExcel() {
    var excelFile = $("#excelFile").val();
    if (excelFile == '') {
        alert("请选择需上传的文件!");
        return false;
    }
    if (excelFile.indexOf('.xls') == -1) {
        alert("文件格式不正确，请选择正确的Excel文件(后缀名.xls)！");
        return false;
    }
    $("#fileUpload").submit();
}

// 多选
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

var num = 1;// 初始化分页num
var findnum = 1;// 初始化分页num,查询时第一次保存的总记录数
// var nid=0;//编号
// var today;//今日日期
var uid = -1;// 全局id
// 删除相关领导
function deleteRelated(ids, user_related_id) {
    ids += '';
    if (ids.indexOf("$") > 0) {// 删除共同领导
        $.post("batchOperate?opt=deleteCommonLeader", {
            "IDS": ids,
            "user_related_id": user_related_id
        }, function (data, status) {
            if (status == 'success') {
                window.location.href = 'user_related_person.html?id=-1&ids='
                    + ids;
            }
        })
    } else {// 个人相关领导删除
        $.post("relatedPerson?opt=delete", {
            "user_id": ids,
            "user_related_id": user_related_id
        }, function (data, status) {
            if (status == 'success') {
                window.location.href = 'user_related_person.html?id=' + ids;
            }
        })
    }
}

// 相关领导页面加载
function relatedPersonLoad(pageNum) {
    lefter();
    var id = getUrlParam('id');
    if (id != -1) {
        $("#related_person").load("relatedPerson?opt=list", {
            "pageNum": pageNum,
            "id": id
        });
    } else if (id == -1) {
        var ids = getUrlParam("ids");
        $("#related_person").load("batchOperate?opt=listRelated", {
            "IDS": ids
        });
    }

}

// 登录
function login() {
    var data = $("#login").serialize();
    $.post("Login", data, function (data, status) {
        if (status == 'success') {
            window.location.href = 'user_information.html';
        }
    });
}

// 批量审批/删除/增加
function leavePasswdALL(opt) {
    var ids = document.getElementsByName('IDS');
    // var value = new Array();
    var uri = '';
    var strIDs = '';
    for (var i = 0; i < ids.length; i++) {
        if (ids[i].checked) {
            // 拼接选中该的id字符串
            strIDs += ids[i].value + '$';
        }
    }

    strIDs = strIDs.substr(0, strIDs.length - 1);
    if (opt == 'isLeave') {// 审批通过
        // leavePasswd(id, 'pass');
        $.post("batchOperate?opt=pass", {
            "IDS": strIDs
        }, function (data, status) {
            if (status == 'success') {
                window.location.href = 'isleave.html';
            }
        });
    } else if (opt == 'delete') {// 删除
        $.post("batchOperate?opt=delete", {
            "IDS": strIDs
        }, function (data, status) {
            if (status == 'success') {
                window.location.href = 'user_information.html';
            }
        });
    } else if (opt == 'add') {// 批量增加相关领导
        // window.location.href = 'add_related_person.html?id=-1&ids=' + strIDs;
        window.location.href = 'user_related_person.html?id=-1&ids=' + strIDs;
    }
}

// 审批
function leavePasswd(id, opt) {
    $.post("leavePasswd", {
        "id": id,
        "opt": opt
    }, function (data, status) {
        if (status == 'success') {
            window.location.href = 'isleave.html';
        }
    });
}

// 重名者的id选择,相关领导
function optionUser3() {
    var id = document.getElementById("selectUserId").value;
    // alert(id);
    $.post("temporaryLeave?opt=idSearch", {
        "id": id
    }, function (data) {
        $("#getoneUser").html(data);
    });
}

// 根据姓名获得数据，判断重名等,临时记录
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
}function name_event3() {
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

// 重名者的id选择,临时记录
function optionUser2() {
    var leave_user_id = getUrlParam('id');
    var related_leader_id = document.getElementById("selectUserId").value;
    // alert(id);
    $.post("relatedPerson?opt=idSearch", {
        "id": related_leader_id,
        "leave_user_id": leave_user_id
    }, function (data) {
        $("#getoneUser").html(data);
    });
}

// 根据姓名获得数据，判断重名等，相关领导
function name_event2() {
    var leave_user_id = getUrlParam('id');
    // 回车键
    var event = arguments.callee.caller.arguments[0] || window.event;
    if (event.keyCode == 13) {// 判断是否按了回车，enter的keycode代码是13
        var name = document.getElementById("user_name").value;// 调半天竟然是少了.value!
        // alert(name);
        // $("#get_userInfo").trigger("click");//回车提交表单
        $.post("relatedPerson?opt=nameSearch", {
            "user_name": name,
            "leave_user_id": leave_user_id
        }, function (data) {
            $("#getoneUser").html(data);
        });
    }
}

// 重名者的id选择，相关领导
function optionUser() {
    var id = document.getElementById("selectUserId").value;
    // alert(id);
    $.post("getUserInfo?opt=idSearch", {
        "id": id
    }, function (data) {
        $("#getoneUser").html(data);
    });
    //个人请假记录查看
    $("#leavedInfo").load("getUserInfo?opt=idLeavedInfo", {
        "id": id
    });
    leavedInfoHistory(id);
}

//展示个人历史请假数据
function leavedInfoHistory(id) {
    //个人请假历史记录展示
    $("#leavedInfoHistory").load("getUserInfo?opt=idleavedInfoHistory", {
        "id": id
    });
}

// 根据姓名获得数据，判断重名等
function name_event() {
    // 回车键
    var event = arguments.callee.caller.arguments[0] || window.event;
    if (event.keyCode == 13) {// 判断是否按了回车，enter的keycode代码是13
        var name = document.getElementById("user_name").value;// 调半天竟然是少了.value!
        // alert(name);
        // $("#get_userInfo").trigger("click");//回车提交表单
        $.post("getUserInfo?opt=nameSearch", {
            "user_name": name
        }, function (data) {
            $("#getoneUser").html(data);
        });
        //姓名唯一
        $.post("getUserInfo?opt=nameLeavedInfo", {
            "user_name": name
        }, function (data) {
            $("#leavedInfo").html(data);

            //获得id
            var id = $("#selectUserId").find("option").eq(1).val();
            //个人历史请假记录
            leavedInfoHistory(id);
        });
        // 这样竟然不行...为啥啊，上面optionUser()中直接load就行...
        // $("#leavedInfo").load("getUserInfo?opt=nameLeavedInfo",{"user_name":user_name});
    }
}

function jump_event(opt) {
    // 回车键
    var event = arguments.callee.caller.arguments[0] || window.event;
    if (event.keyCode == 13) {// 判断是否按了回车，enter的keycode代码是13
        var pagenum = document.getElementById("jumpPage").value;// 调半天竟然是少了.value!
        //跳转页面改变页码
        findnum = pagenum;
        switch (opt) {
            case "user_info":
                pageChange('', '')
                break;
            case "isleave":
                pageChange2('', '')
                break;
            case "cutleave":
                pageChange5('', '')
                break;
            case "history_leave":
                pageChange3('', '')
                break;
            case "temporary":
                pageChange4('', '')
                break;
        }

        //find_user('2')
        //window.location.href = 'user_information.html?opt=history';
    }
}

// 打印页面数据加载2
function print2() {
    var id = getUrlParam('id');// 请假人员信息的id
    $("#printInfo").load("printInfo?kind=print2", {
        "id": id
    });
    $("#printInfo2").load("printInfo?kind=print21", {
        "id": id
    });
    $("#printInfo3").load("printInfo?kind=print23", {
        "id": id
    });
    $("#responsive").load("printInfo?kind=print22", {
        "id": id
    }, function (data, status) {
        if (status == 'success') {
            $.post("../SetupField?opt=getBum", {
                "id": id
            }, function (bum) {
                $(".bum").html('编号:&nbsp;&nbsp;&nbsp;&nbsp;' + bum);
            });
        }
    });
}

// 打印页面数据加载1
function print1() {
    var id = getUrlParam('id');// 请假人员'信息'的id
    $("#printInfo").load("printInfo?kind=print1", {
        "id": id
    });
    $("#printInfo2").load("printInfo?kind=print11", {
        "id": id
    });
    $("#printInfo3").load("printInfo?kind=print13", {
        "id": id
    });
    $("#responsive").load("printInfo?kind=print12", {
        "id": id
    }, function (data, status) {
        if (status == 'success') {
            $.post("../SetupField?opt=getBum", {
                "id": id
            }, function (bum) {// 上一级目录的servlet请求
                $(".bum").html('编号:&nbsp;&nbsp;&nbsp;&nbsp;' + bum);
            });
        }
    });
}

// 打印一次
function print_once() {
    jQuery.print('#printArea')
    var id = getUrlParam('id');// 请假人员'信息'的id
    $.post("../leavePasswd", {
        "id": id,
        "opt": 'printed'
    }, function (data, status) {
        // if (status == 'success') {
        //     window.location.href = 'isleave.html';
        // }
    });
}

// 根据id获得请假人员种类,进行跳转
function get_personKind(id) {
    // alert(id);
    $.post("getUserInfo?opt=ID", {
        "id": id
    }, function (kind, status) {
        if (status == 'success') {
            // alert(person_kind);
            // 跳转到相应的打印页面
            window.location.href = 'print/' + kind + '.html?id=' + id;
        }
    });
}

// 人员相关领导信息录入
function get_userInfo2(opt) {
    // 领导id
    var related_leader_id = document.getElementsByName("related_leader_id")[0].value;

    var id = getUrlParam('id');
    if (id != -1) {
        $.post("getUserInfo?opt=addRelated", {
            "leave_user_id": id,
            "related_leader_id": related_leader_id
        }, function (data, status) {
            if (status == 'success') {
                window.location.href = 'user_related_person.html?id=' + id;
            }
        });
    } else if (id == -1) {
        // 多选选中的id
        var ids = getUrlParam("ids");
        $.post("batchOperate?opt=" + opt, {
            "IDS": ids,
            "related_leader_id": related_leader_id
        }, function (data, status) {
            if (status == 'success') {
                window.location.href = 'user_related_person.html?id=-1&ids='
                    + ids;
            }
        });
    }
}

// 请假操作
function get_userInfo(opt) {

    // document.getElementById("input_submit").click()


    var data = $("#askForLeave").serialize();
    const obj = {}
    $("#askForLeave").serializeArray().forEach((item) => {
        obj[item.name] = item.value
    })

    if (!obj.leave_leader || !obj.leave_reason || !obj.leave_p_name || !obj.leave_start_addr || !obj.leave_end_addr || !obj.leave_remark) {
        alert('字段不能为空');
        return;
    }

    // 请假规则提交前进行一遍规则的判断提示是否提交
    $.post("getUserInfo?opt=" + opt + "2", data, function (id, status) {
        if (status === 'success') {
            if (id.indexOf(",") !== -1) {
                nums = id.split(",")
                num1 = nums[0]
                num2 = nums[1]
                if (num1 === '1') {
                    alert('您今年剩余可请假天数：' + num2 + '天')
                    var r = confirm("条件不符合,仍然提交?");
                    if (r) {
                        var myDate = new Date();
                        year = myDate.getFullYear();
                        console.log("当前年份" + year);

                        if (obj.leave_start_day.indexOf(year) !== -1 || obj.leave_kind !== '事假') {
                            alert('强制提交年份请选择明年,且只允许事假');
                            return;
                        }
                        $.post("getUserInfo?opt=" + opt, data,
                            function (id, status) {
                                alert("操作成功");
                                // 根据返回的id判断查询数据判断跳转
                                get_personKind(id);
                                // window.location.href = 'print.html';
                            });
                    }
                    return;
                }
            }
            if (id === '0') {// 条件不符合返回0
                var r = confirm("条件不符合,仍然提交？");
                if (r) {
                    $.post("getUserInfo?opt=" + opt, data,
                        function (id, status) {
                            alert("操作成功");
                            // 根据返回的id判断查询数据判断跳转
                            get_personKind(id);
                            // window.location.href = 'print.html';
                        });
                } else {
                    return;
                }
            } else {
                alert("操作成功");
                // 根据返回的id判断查询数据判断跳转
                get_personKind(id);
            }
        }
    });
    // get_personKind(personId);
}

// 查找临时请假历史信息
function findTemporaryHistoryLeave(again) {
    if (again == 1) {
        findnum = 1;
        num = 1;
        findPageNum(1);
    }
    var data = $("#findTemporaryHistoryLeave").serialize();
    $
        .post(
            "findUser?opt=find_TemporaryHistoryLeave",
            data,
            function (data) {
                // alert(data);
                // $("#leave_passed").html(data);
                $("#temporary_Leave").html(data);

                // 第一次或点击首页查询时触发
                if (findnum == 1) {
                    $("#countAll").load(
                        "pageList?list=countAllTemporaryHistory2",
                        {
                            "pageNum": 1
                        }, function (data, status) {
                            getCurrentPageNum();	//当前页
                        });
                }
            });
}

// 查找销假信息
function find_cutLeave(again) {
    if (again == 1) {
        findnum = 1;
        num = 1;
        findPageNum(1);
    }
    var data = $("#find_cutLeave").serialize();
    // alert("$"+data);
    $.post("findUser?opt=find_cutLeave",
        data,
        function (data) {
            // alert(data);
            // $("#leave_passed").html(data);
            $("#cutLeave").html(data);
            // 第一次或点击首页查询时触发
            if (findnum == 1) {
                $("#countAll").load(
                    "pageList?list=countAllcutHistory2", {
                        "pageNum": 1
                    }, function (data, status) {
                        getCurrentPageNum();	//当前页
                    });
            }
        });
}

// 查找审批信息
function find_isleave(again) {
    if (again == 1) {
        findnum = 1;
        num = 1;
        findPageNum(1);
    }
    var data = $("#find_isleave").serialize();
    // alert("$"+data);
    $.post("findUser?opt=find_isleave",
        data,
        function (data) {
            // alert(data);
            // $("#leave_passed").html(data);
            $("#leave_passed").html(data);
            // 第一次或点击首页查询时触发
            if (findnum == 1) {
                $("#countAll").load("pageList?list=countAllLeave2",
                    {
                        "pageNum": 1
                    }, function (data, status) {
                        getCurrentPageNum();	//当前页
                    });
            }
        });
}

// 查找用户历史请假信息
function find_history(again) {
    if (again == 1) {
        findnum = 1;
        num = 1;
        findPageNum(1);
    }
    var data = $("#find_history").serialize();
    // alert("$"+data);
    $.post("findUser?opt=userLeave",
        data,
        function (data) {
            // alert(data);
            // $("#leave_history").html(data);
            $("#leave_history").html(data);// body --id
            // 第一次或点击首页查询时触发
            if (findnum == 1) {
                $("#countAll").load("pageList?list=countAllHistory2", {
                    "pageNum": 1
                }, function (data, status) {
                    getCurrentPageNum();	//当前页
                });
            }
        });

}

// 查找用户信息
function find_user(again) {
    if (again == 1) {
        findnum = 1;
        num = 1;
        findPageNum(1);
    }
    var data = $("#findForm").serialize();
    // alert("$"+data);
    $.post("findUser?opt=userInfo", data, function (data) {
        $("#user_info").html(data);

        $("#countAll").load("pageList?list=countAll2", {
            "pageNum": 1
        }, function (data, status) {
            getCurrentPageNum();	//当前页
        });
    });
}

// 导出查询出来的信息成excel表
function find_history_dbtoexcel() {
    var data = $("#find_history").serialize();
    $.post("Find_History_DBToExcel", data, function (filePath, status) {
        if (status == 'success') {
            window.open(filePath);
        }
    });
}

// 下载季度表
function find_quarterly(quarterly) {
    var select_year = document.getElementById("select_year").value;
    $.post("DBToExcel", {
        "quarterly": quarterly,
        "select_year": select_year
    }, function (filePath, status) {
        if (status == 'success') {
            window.open(filePath);
        }
    });
    // var url = "DBToExcel?quarterly="+quarterly+"&select_year="+select_year;
    // 模拟表单提交数据，ajax提交后无法下载文件(excel)
    // $('<form method="post" action="' + url +
    // '"></form>').appendTo('body').submit();
}

//修改人员信息取消
function cancle_user() {
    window.location.href = 'user_information.html?opt=history';
}

// 修改人员信息
function update_user() {
    var phone = document.getElementById("user_phone").value;
    if (!(/^1[2345789]\d{9}$/).test(phone)) {
        alert("手机号码有误，请重填");
        return false;
    }
    var data = $("#uploadForm").serialize();
    // alert("$"+data);
    $.post("updateUser?opt=update", data, function (data, status) {
        if (status == 'success') {
            alert("修改成功");
            window.location.href = 'user_information.html?opt=history';
        }
    });

    // 刷新
    // location.reload();
    return true;
}

// 新增用户
function add_user() {
    var phone = document.getElementById("user_phone").value;
    if (!(/^1[2345789]\d{9}$/).test(phone)) {
        alert("手机号码有误，请重填");
        return false;
    }
    var data = $("#uploadForm").serialize();
    // alert("$"+data);
    $.post("updateUser?opt=add", data, function (data, status) {
        if (status == 'success') {
            alert("添加成功");
            window.location.href = 'user_information.html';
        }
    });
    return true;
}

// 临时请假记录添加，删除等
function temporaryLeaveOpt(opt) {
    var data = $("#temporaryLeavedd").serialize();// serialize()函数不是属性要加()...
    // alert(data);
    $.post("temporaryLeave?opt=" + opt, data, function (data, status) {
        if (status == 'success') {
            alert("操作成功");
            window.location.href = 'temporary_leave_history.html';
        }
    });
}

// 临时请假历史记录
function temporaryHistoryLeave(pageNum) {
    lefter2();
    $("#temporary_Leave").load("pageList?list=temporaryHistoryLeave", {
        "pageNum": pageNum
    });
    $("#countAll").load("pageList?list=countAllTemporaryHistory", {
        "pageNum": 1
    }, function (data, status) {
        getCurrentPageNum();	//当前页
    });
}

// 临时请假记录
function temporaryLeave() {
    lefter2();
    $("#temporary_Leave").load("pageList?list=temporaryLeave", {
        "pageNum": 1
    });
}

// 历史请假记录
function historyLeave(pageNum) {
    lefter();
    $("#leave_history").load("pageList?list=historyLeave", {
        "pageNum": pageNum
    });
    // 统计数据数目
    $("#countAll").load("pageList?list=countAllHistory", {
        "pageNum": 1
    }, function (data, status) {
        getCurrentPageNum();	//当前页
    });
    //页面下拉框数据加载
    $("#setupField").load("SetupField?opt=listField");
    const myDate = new Date();
    $("#select_year").val(myDate.getFullYear());
}

// 销假记录查询(部分历史请假记录)
function cuthistoryLeave(pageNum) {
    lefter();
    $("#cutLeave").load("pageList?list=cuthistoryLeave", {
        "pageNum": pageNum
    });
    //总页数统计
    $("#countAll").load("pageList?list=countAllcutHistory", {
        "pageNum": 1
    }, function (data, status) {
        getCurrentPageNum();	//当前页
    });
    // $("#setupField").load("SetupField?opt=listField");//统计数据数目
}

// 销假(修改历史记录)
function cutLeave(ask_for_leave_id) {
    // 选择到岗日期
    var leave_end_day = document.getElementById("leave_end_day").value;
    if (leave_end_day == "" || leave_end_day == null) {
        alert("请选择到岗日期");
        return;
    }

    var value = prompt("到岗备注", "");
    var id = ask_for_leave_id;
    $.post("pageList?list=updatehistoryLeave", {
        "leave_end_day": leave_end_day,
        "leave_cut_remark": value,
        "id": id,
        "pageNum": 1
    }, function (data, status) {
        // alert("测试");
        //location.reload();
        // 再次查询，首页查询刷新ajax
        // cuthistoryLeave(1);
        find_cutLeave('2')
    });
}

// 销假页面分页
function pageChange5(opt, find) {
    if (opt == 'next') {
        findnum++;

    } else if (opt == 'prev') {
        findnum--;
        if (findnum <= 1)
            findnum = 1;
    } else if (opt == 'first') {
        findnum = 1;
    }
    findPageNum(findnum);
    find_cutLeave('2');
    getCurrentPageNum(); 	//当前页
}

//临时请假历史信息页面分页
function pageChange4(opt, find) {
    if (opt == 'next') {
        findnum++;

    } else if (opt == 'prev') {
        findnum--;
        if (findnum <= 1)
            findnum = 1;
    } else if (opt == 'first') {
        findnum = 1;
    }
    findPageNum(findnum);
    findTemporaryHistoryLeave('2');
    getCurrentPageNum();	//当前页
}

// 历史请假页面分页
function pageChange3(opt, find) {
    if (opt == 'next') {
        findnum++;
    } else if (opt == 'prev') {
        findnum--;
        if (findnum <= 1)
            findnum = 1;
    } else if (opt == 'first') {
        findnum = 1;
    }
    findPageNum(findnum);
    find_history('2');
    getCurrentPageNum(); 	//当前页
}

// 请假审批页面分页
function pageChange2(opt, find) {
    if (opt == 'next') {
        findnum++;
    } else if (opt == 'prev') {
        findnum--;
        if (findnum <= 1)
            findnum = 1;
    } else if (opt == 'first') {
        findnum = 1;
    }
    findPageNum(findnum);
    find_isleave('2');
    getCurrentPageNum();//当前页
}

//人员信息页面分页
function pageChange(opt, find) {
    //getCurrentPageNum();	//当前页码
    if (opt == 'next') {
        findnum++;
    } else if (opt == 'prev') {
        findnum--;
        if (findnum <= 1)
            findnum = 1;
    } else if (opt == 'first') {
        findnum = 1;
    }
    findPageNum(findnum);
    find_user('2');
    getCurrentPageNum();
}

//请假审批页面数据加载
function isleaveLoad(pageNum) {
    lefter();
    $("#leave_passed").load("pageList?list=leaveInfo", {
        "pageNum": pageNum
    });
    $("#countAll").load("pageList?list=countAllLeave", {
        "pageNum": 1
    }, function (data, status) {
        getCurrentPageNum();	//当前页
    });

}

// 修改人员页面数据加载
function updatePageLoad() {
    lefter();
    var id = getUrlParam('id');
    // alert(id);
    $("#updatePage").load("updatePageList", {
        "id": id
    }, function (data, status) {
        if (status == 'success') {
            // $("#setupField").load("SetupField?opt=listField2");//修改页面设置的数据加载
        }
    });
}

// 获取url参数方法
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

// 获取url参数对象数组
function GetRequest() {
    var url = location.search; // 获取url中"?"符后的字串
    var theRequest = new Array();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[i] = unescape(strs[i].split("=")[1]);
            // alert(unescape(strs[i].split("=")[1]));
        }
    }
    return theRequest;
}

// 获取cookie
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name) == 0)
            return c.substring(name.length, c.length);
    }
    return "";
}

// 提示框
function diag() {
    $("#dialog-form").dialog({
        autoOpen: false,
        height: 300,
        width: 450,
        modal: true,
        buttons: {
            "关闭": function () {
                $(this).dialog("close");
            }
        },
    });
}

//设置cookie,exdays:过期天数
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

// 获取cookie
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name) == 0)
            return c.substring(name.length, c.length);
    }
    return "";
}

//分页/查询条件保存成cookie,页面onunload()事件触发
function savePageCookie() {
    setCookie("pageNum", findnum, 1)	//上一次离开页面时的分页
    var data = $("#findForm").serialize();	//以及查询条件
    setCookie("data", data, 1)
}

//反序列化成数组,只取值
function getArray(data) {
    var array = new Array();
    var a = data.split("&");
    for (var i = 0; i < a.length; i++) {
        var b = a[i].split("=");
        for (var j = 1; j < b.length; j = j + 2) {
            var c = b[j];
            array.push(c);
        }
    }
    return array;
}

//获得当前页码/总页码显示
function getCurrentPageNum() {
    $("#currentPageNum").html(findnum);	//当前页码
    //总页数
    var str = $("#countAll").text();
    var allnum = parseInt(str.substring(2));
    allnum = Math.ceil(allnum / 7);	//这里的7有问题，应该由后台传回来当前页的记录数
    $("#allPageNum").html(allnum);
}

//人员信息页面数据加载
function pageLoad(pageNum) {
    lefter();
    $("#setupField").load("SetupField?opt=listField", {}, function (data, status) {
        var opt = getUrlParam("opt")
        if (opt == "history") {//返回记录历史状态
            var fnum = getCookie("pageNum");
            findnum = fnum;	//赋值给当前分页
            findPageNum(findnum);
            //反序列化到表单
            var data = getCookie("data");
            data = decodeURIComponent(data, true);
            var array = getArray(data);
            $("#user_name").val(array[0]);	//姓名
            $("#user_sex").val(array[1]);	//性别
            $("#user_work_time").val(array[2]);
            $("#user_origin").val(array[3]);
            $("#user_spouse_origin").val(array[4]);
            $("#user_work_address").val(array[5]);
            $("#user_position").val(array[6]);
            $("#user_position_rank").val(array[7]);
            $("#user_class_area").val(array[8]);
            $("#user_separated").val(array[9]);
            $("#user_phone").val(array[10]);
            $("#pageNum").val(array[11]);
            find_user('2');
        } else {
            diag();//弹出框初始化
            var first = getCookie("first");
            // alert(first);
            if (first == "1")
                $.post("remindMessage", function (data, status) {
                    if (data != '' && data != null) {
                        // alert(data)
                        $("#dialog-form").append(data);
                        $("#dialog-form").dialog("open");
                    }
                });

            lefter();
            // alert();
            //用户信息
            $("#user_info").load("pageList?list=userInfo", {
                "pageNum": pageNum
            });
            //统计总数目
            $("#countAll").load("pageList?list=countAll", {
                "pageNum": 1
            }, function (data, status) {
                //当前页码显示以及总页数
                getCurrentPageNum();
            });
        }
    });// 设置的字段的列表展示
}

// 添加人员信息也页面数据加载
function addUser() {
    lefter();
    $("#setupField").load("SetupField?opt=listField2");// 统计数据数目
}

// 加载左侧页面
function lefter() {
    $("#lefter").load("lefter.html");// 加载左侧页面
}

// 加载左侧页面
function lefter2() {
    $("#lefter").load("lefter2.html");// 加载左侧页面
}

// 查询页面pageNum
function findPageNum(findnum) {
    $("#changePageNum").html(
        "<input type=\"hidden\" name=\"pageNum\" value=\"" + findnum
        + "\">");
}

function user_reset() {
    findnum = 1;
    num = 1;
}

/**
 * 设置
 */
// 短信提前发送天数展示
function setupDayLoad() {
    lefter();
    $("#setupDay").load("SetupField?opt=listDay");
}

// 短信提前发送天数修改
function setupDay() {
    var field = document.getElementById("field").value;
    $.post("SetupField?opt=updateDay", {
        "field": field
    });
}

// 添加账号
function addAdmin() {
    var new_password = document.getElementById("new_password").value;
    var new_password2 = document.getElementById("new_password2").value;
    if (new_password != new_password2) {
        alert("两次密码不一致");
        return false;
    }
    var name = document.getElementById("name").value;
    var user_name = document.getElementById("user_name").value;
    // var old_password=document.getElementById("old_password").value;
    // alert('$'+old_password+'$'+new_password);
    $.post("Login?opt=addAdmin", {
            "new_password": new_password,
            "name": name,
            "user_name": user_name,
        },
        function (data, status) {
            if (data === "1") {
                alert("添加成功")
                window.location.href = 'add_admin.html';
            } else if (data === "2") {
                alert("账号已存在")
            }
        });
    return true;
}

// 更新管理员密码
function updatePassword() {
    var new_password = document.getElementById("new_password").value;
    var new_password2 = document.getElementById("new_password2").value;
    if (new_password != new_password2) {
        alert("两次密码不一致");
        return false;
    }
    return true;
}

// 字段修改页面相关操作
function setupLoad() {
    lefter();
    $("#work_address").load("SetupField?opt=listAddress");
    $("#work_position").load("SetupField?opt=listPosition");
    $("#work_position_rank").load("SetupField?opt=listPositionRank");
}

// 账号管理
function adminLoad() {
    lefter();
    $("#admin").load("SetupField?opt=listAdmin");
}

function setupAddress(opt, flag) {
    if (opt == "add") {
        var field = document.getElementById("user_work_address").value;
        $.post("SetupField?opt=add", {
            "field": field,
            "flag": flag
        });
    } else if (opt == 'delete') {
        var id = flag;
        $.post("SetupField?opt=delete", {
            "id": id
        });
    }
    window.location.href = 'update_words.html';
}

function setupPosition(opt, flag) {
    if (opt == "add") {
        var field = document.getElementById("user_work_position").value;
        $.post("SetupField?opt=add", {
            "field": field,
            "flag": flag
        });
    } else if (opt == 'delete') {
        var id = flag;
        $.post("SetupField?opt=delete", {
            "id": id
        });
    }
    window.location.href = 'update_words.html';
}

function setupAdmin(opt, flag) {
    if (opt == 'delete') {
        var id = flag;
        $.post("SetupField?opt=deleteAdmin", {
            "id": id
        });
    }
    window.location.href = 'add_admin.html';
}

function setupPositionRank(opt, flag) {
    if (opt == "add") {
        var field = document.getElementById("user_work_position_rank").value;
        $.post("SetupField?opt=add", {
            "field": field,
            "flag": flag
        });
    } else if (opt == 'delete') {
        var id = flag;
        $.post("SetupField?opt=delete", {
            "id": id
        });
    }
    window.location.href = 'update_words.html';
}

/**
 * 规则
 */
// 规则删除
function deleteRule(id) {
    $.post("updateRule?opt=delete", {
        "id": id
    }, function (data, status) {
        if (status == 'success') {
            window.location.href = 'rule_manage.html';
        }
    });
}

// 规则展示
function ruleLoad() {
    lefter();
    $("#listRule").load("updateRule?opt=listRule");
}

// 修改规则中页面数据展示
function updateRule() {
    lefter();
    var id = getUrlParam('id');
    $("#updateRuleList").load("updateRule?opt=updateRuleList", {
        "id": id
    });

    $("#position_rank").load("SetupField?opt=listRule", {
        "flag": 2
    });// 职级

}

// 添加规则中职级的展示
function addRule() {
    lefter();
    $("#position_rank").load("SetupField?opt=listRule", {
        "flag": 2
    });// 职级
}

// 添加规则
function update_rule(opt) {
    var data = $("#updateRule").serialize();
    // alert("$"+data);
    $.post("updateRule?opt=" + opt, data, function (data, status) {
        window.location.href = 'rule_manage.html';
    });
    // return true;
}