<%--
  Created by IntelliJ IDEA.
  User: tianfeichen
  Date: 2021/8/21
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%@ include file="/pages/common/top.jsp"%>
        <script src="static/js/leave_ctf.js"></script>
    </head>
    <body>
        <div style="width:auto">
            <div style="float: left;background: antiquewhite">
                <%@ include file="/pages/common/menu.jsp"%>
            </div>
            <div style="float: left;background:silver">
                <div>
                    <h1>新增人员信息</h1>
                </div>
                <div style="width: 100%">
                    <form action="personServlet" method="post">
                        <input type="hidden" name="action" value="addOnePerson">
                        <div >
                            <label><h5>姓名</h5></label>
                            <div>
                                <input type="text" name="person_name" id="" autofocus>
                            </div>
                        </div>
                        <div>
                            <label><h5>性别</h5></label>
                            <div>
                                <select name="person_sex" class="form-control" alt="string">
                                    <option value="男">男</option>
                                    <option value="女">女</option>
                                </select>
                            </div>
                        </div>
                        <div>
                            <label><h5>出生年月</h5></label>
                            <div class="col-md-6">
                                <input name="person_born_time" type="month" class="form-control" alt="string">
                            </div>
                        </div>
                        <div>
                            <label ><h5>本人籍贯</h5></label>
                            <div>
                                <div class="col-md-6">
                                    <%@include file="../common/address.jsp"%>
                                </div>
                            </div>
                        </div>
                        <div>
                            <label><h5>工作单位</h5></label>
                            <div>
                                <%--查询数据库--%>
                                    <select id="gongzuodanwei" name="">
                                        <option>"测试1"</option>
                                        <option>"测试2"</option>
                                        <option>"测试3"</option>
                                    </select>
                            </div>
                        </div>
                        <div>
                            <label><h5>现任职务</h5></label>
                            <div>
                                <input type="text" name="" id="">
                            </div>
                        </div>
                        <div>
                            <label><h5>职级</h5></label>
                            <div>

                            </div>
                        </div>
                        <div>
                            <label><h5>联系电话</h5></label>
                            <div>
                                <input type="tel" name="" id="">
                            </div>
                        </div>
                        <div>
                            <label><h5>允许休假天数</h5></label>
                            <div>
                                <input type="number" min="0" max="100" name="" id="">
                            </div>
                        </div>
                        <div>
                            <div>
                                <%--正确：onclick="cancelToLastPage('${pageContext.getAttribute("basepath")}')"--%>
                                <%--错误：onclick="cancelToLastPage(${pageContext.getAttribute("basepath")})"--%>
                                    <%--错误原因：JavaScript 参数是string类型的时候前后要加“”,有些情况下一定要加转义符--%>
                                <button type="button"
                                        onclick="cancelToLastPage('${pageContext.getAttribute("basepath")}')">
                                    取消
                                </button>
                            </div>
                            <div>
                                <button type="submit">提交</button>
                            </div>
                            <div>
                                <button type="reset">重置</button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>

    </body>
</html>
