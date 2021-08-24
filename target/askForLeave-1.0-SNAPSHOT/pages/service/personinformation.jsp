<%--
  Created by IntelliJ IDEA.
  User: tianfeichen
  Date: 2021/8/21
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%@ include file="/pages/common/top.jsp" %>

    </head>

    <body>
        <div style="width:auto">
            <div style="float: left;background: antiquewhite">
                <%@ include file="/pages/common/menu.jsp" %>
            </div>
            <div style="float: left;background:silver">
                <div class="" id="">
                    <form class="layui-form" action="/personServlet" method="post" id="">
                        <div class="">
                            <div class="">
                                <div class="">
                                    <label class=" ">姓名</label>
                                    <input name="person_name" id="person_name" class="" style="width: 74%">
                                </div>

                                <div class="">
                                    <label class="">性别</label>
                                    <select name="person_sex" id="person_sex" class="" style="width: 74%">
                                        <option value=""></option>
                                        <option value="男">男</option>
                                        <option value="女">女</option>
                                    </select>
                                </div>

                                <div class="layui-form-item">
                                    <label class="layui-form-label width_auto text-r" style="margin-top:2px">省市县：</label>
                                    <div class="layui-input-inline" style="width:400px">
                                        <input type="text" autocomplete="on" class="layui-input" id="city-picker" name="city-picker" readonly="readonly"
                                               data-toggle="city-picker" placeholder="请选择">
                                    </div>
                                </div>

                                <div class="">
                                    <label class="">联系电话</label>
                                    <input id="person_phone" name="person_phone" class=""
                                           style="width: 74%">
                                </div>
                            </div>

                            <div class="">

                                <div id="">
                                    <div class="">
                                        <label class="">工作单位</label>
                                        <select name="person_work_address" id="person_work_address"
                                                class="" style="width: 74%">
                                            <option value=""></option>
                                            <%--el表达式读取数据库数据--%>
                                            <option>"测试1"</option>
                                            <option>"测试2"</option>
                                            <option>"测试3"</option>
                                        </select>
                                    </div>

                                    <div class="3">
                                        <label class=" ">现任职务</label>
                                        <%--<input id="person_position" name="person_position" class="l"
                                               style="width:74%">--%>
                                        <select name="person_work_address" id="person_work_address"
                                                class="" style="width: 74%">
                                            <option value=""></option>
                                            <%--el表达式读取数据库数据--%>
                                            <option>"测试1"</option>
                                            <option>"测试2"</option>
                                            <option>"测试3"</option>
                                        </select>
                                    </div>

                                    <div class="3">
                                        <label class=" ">职级</label>
                                        <select name="person_position_rank" id="person_position_rank"
                                                class="" style="width: 74%">
                                            <option value=""></option>
                                            <%--el表达式读取数据库数据--%>
                                            <option>"测试1"</option>
                                            <option>"测试2"</option>
                                            <option>"测试3"</option>
                                        </select>
                                    </div>
                                </div>

                            </div>
                            <div class="">

                            </div>

                            <div id="changePageNum">
                                <input type="hidden" name="pageNum" id="pageNum" value="1">
                            </div>

                            <div class="12">
                                <button type="button" class="btn blue" style="float: right; margin-right: 25px;"
                                        onclick="person_reset()">查询
                                </button>
                                <button type="reset" class="btn blue" style="float: right; margin-right: 25px;"
                                        onclick="person_reset()">重置查询
                                </button>
                                <%--<button type="reset" class="btn blue" style="float: right; margin-right: 25px;"
                                        onclick="person_reset()">重置查询
                                </button>
                                <a class="btn blue" role="button" onclick="find_person('1')"
                                   style="float: right; margin-right: 10px">查询</a>--%>
                            </div>

                        </div>
                    </form>

                </div>

                <div style="height: 80px;background: aqua"></div>

                <div class="">
                    <div class="">
                        <div class="">
                            <i></i> 人员信息管理
                        </div>
                    </div>
                    <div class="">
                        <div class="">
                            <div>
                                <a class="" href="pages/service/addPerson.jsp"
                                   style="float: left; margin-right: 10px; margin-left: 15px;">新增人员</a>

                                <%--    <form action="ExcelToDB" style="float: left;" id="fileUpload" method="post" enctype="multipart/form-data">
                                        <input class="btn blue" type="button" value="批量导入" onclick="submitExcel()" style="float: left;">
                                        <input id="excelFile" type="file" name="file" style="float: left; margin-top: 5px; margin-left: 5px;">
                                    </form>
                                    --%>
                            </div>

                            <div class="" id="" style="margin-top: 10px;">
                                <div id="">
                                    <table class="" id="">
                                        <thead>
                                            <tr>
                                                <th><input class="checkBox" type="checkbox" onclick="swapCheck()"></th>
                                                <th> 姓名</th>
                                                <th> 性别</th>
                                                <th> 出生年月</th>
                                                <th> 本人籍贯</th>
                                                <th> 工作单位</th>
                                                <th> 现任职务</th>
                                                <th> 职级</th>
                                                <th> 联系电话</th>
                                                <th> 允许休假天数</th>
                                                <th> 相关领导</th>
                                                <th> 修改</th>
                                                <th> 删除</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td><input name="" value="" class="checkBox" type="checkbox">
                                                </td>
                                                <td>次仁帕珠</td>
                                                <td>男</td>
                                                <td> 出生年月</td>
                                                <td>西藏日喀则市桑珠孜区</td>
                                                <td>孔玛乡</td>
                                                <td>孔玛乡人民政府副书记</td>
                                                <td></td>
                                                <td>18208021001</td>
                                                <td>70</td>
                                                <td><a href="/personServlet?action=bindRelatedLeader&id=">相关领导</a></td>
                                                <td><a href="/personServlet?action=updatePersonInfo&id=">修改</a></td>
                                                <td><a href="/personServlet?action=deleteThePerson&id=">删除</a></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="">
                                    <div class="">
                                        <ul class="" style="visibility: visible;">
                                            <li class="prev ">
                                                <a href="#"> 批量删除 </a>
                                            </li>
                                            <li class="prev ">
                                                <a href="#">批量增加领导 </a>
                                            </li>
                                        </ul>


                                        <%@include file="../common/page_nav.jsp" %>

                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

      
    </body>
</html>
