<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--分页开始--%>
<div id="page_nav">

    <%--如果是第 一页，不显示下一页和末页--%>
    <c:if test="${requestScope.page.currentPageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.currentPageNo -1}">上一页</a>
    </c:if>

    <%--分页页码开始--%>
    <c:choose>

        <c:when test="${requestScope.page.totalNumOfPage <= requestScope.page.pageLabelCount}">
            <%--页码标签位置数量 比 总的数据需要展示的页面的数量 多:
            只需循环遍历实际的页面数量即可
            --%>
            <c:forEach begin="1" end="${requestScope.page.totalNumOfPage}" var="pageNo">
                <c:if test="${requestScope.page.currentPageNo != pageNo}">
                    <a href="${requestScope.page.url}&pageNo=${pageNo}">
                            ${pageNo}
                    </a>
                </c:if>
                <c:if test="${requestScope.page.currentPageNo == pageNo}">
                    【${pageNo}】
                </c:if>
            </c:forEach>
        </c:when>

        <c:when test="${requestScope.page.totalNumOfPage > requestScope.page.pageLabelCount}">
            <c:choose>

                <c:when test="${requestScope.page.currentPageNo>=1 && requestScope.page.currentPageNo<=requestScope.page.pageLabelCount_midIndex}">
                    <c:forEach begin="1" end="${requestScope.page.pageLabelCount}" var="pageNo">
                        <%--不是当前页的话，才加超链接--%>
                        <c:if test="${requestScope.page.currentPageNo != pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${pageNo}">
                                    ${pageNo}
                            </a>
                        </c:if>
                        <%--是当前页的话，不加超链接--%>
                        <c:if test="${requestScope.page.currentPageNo == pageNo}">
                            【${pageNo}】
                        </c:if>
                    </c:forEach>
                </c:when>

                <c:when test="${requestScope.page.currentPageNo>=requestScope.page.pageLabelCount_midIndex+1
                            && requestScope.page.currentPageNo<=requestScope.page.totalNumOfPage-requestScope.page.pageLabelCount_rightCount-1
                            }">
                    <%--该情况页码分为三部分：
                    1.当前页前面的leftcount个标签(遍历输出,需要加上超链接)
                    2.当前页
                    3.当前页前面的rightcount个标签(遍历输出,需要加上超链接) --%>
                    <c:forEach begin="${requestScope.page.currentPageNo-requestScope.page.pageLabelCount_leftCount}"
                               end="${requestScope.page.currentPageNo - 1}"
                               var="pageNo">
                        <a href="${requestScope.page.url}&pageNo=${pageNo}">
                                ${pageNo}
                        </a>
                    </c:forEach>
                    【${requestScope.page.currentPageNo}】
                    <c:forEach begin="${requestScope.page.currentPageNo + 1}"
                               end="${requestScope.page.currentPageNo + requestScope.page.pageLabelCount_rightCount}"
                               var="pageNo">
                        <a href="${requestScope.page.url}&pageNo=${pageNo}">
                                ${pageNo}
                        </a>
                    </c:forEach>
                </c:when>

                <c:when test="${requestScope.page.currentPageNo>=requestScope.page.totalNumOfPage-requestScope.page.pageLabelCount_rightCount
                            && requestScope.page.currentPageNo<=requestScope.page.totalNumOfPage
                            }">

                    <c:forEach begin="${requestScope.page.totalNumOfPage - requestScope.page.pageLabelCount +1}"
                               end="${requestScope.page.totalNumOfPage}"
                               var="pageNo">

                        <c:if test="${requestScope.page.currentPageNo != pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${pageNo}">
                                    ${pageNo}
                            </a>
                        </c:if>
                        <c:if test="${requestScope.page.currentPageNo == pageNo}">
                            【${pageNo}】
                        </c:if>

                    </c:forEach>

                </c:when>

            </c:choose>
        </c:when>
    </c:choose>
    <%--分页页码结束--%>

    <%--如果是最后一页，不显示下一页和末页--%>
    <c:if test="${requestScope.page.currentPageNo != requestScope.page.totalNumOfPage}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.currentPageNo +1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.totalNumOfPage}">末页</a>
    </c:if>


    共${requestScope.page.totalNumOfPage}页，${requestScope.page.totalNumOfData}条记录 到第<input value="${requestScope.page.currentPageNo}" name="pn" id="pn_input"/>页
    <input type="button" value="确定">

    <script type="text/javascript">
        $("input:button").click(function(){
            var pageNo = $("#pn_input").val();
            location.href="${pageContext.getAttribute("basepath")}"+"${requestScope.page.url}&pageNo="+pageNo;
        });
    </script>
</div>
<%--分页结束--%>