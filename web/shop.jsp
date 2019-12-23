<%-- 
    Document   : shop
    Created on : Feb 28, 2017, 10:51:25 PM
    Author     : MotYim
--%>
<%@ page import="javaCode.yourcart.controller.user.Shop" %>
<%-- include header file --%> 
<%@include file="header.jsp" %>


<%-- include slidebar file --%> 
<%@include file="slidebar.jsp" %>

<div class="col-sm-9 padding-right">
    <div class="features_items"><!--features_items-->
        <h2 class="title text-center">Features Items</h2>

        <c:choose>
            <c:when test="${!empty requestScope.allProducts}">
                <c:forEach items="${requestScope.allProducts}" var="product">
                    <c:set var="product" value="${product}" scope="request"/>
                    <c:set var="status" value="${2}" scope="request"/>
                    <c:import url="item.jsp"/>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h1>Nothing to show ... -_-</h1>
            </c:otherwise>
        </c:choose>

    </div><!--features_items-->

    <c:if test="${!empty requestScope.allProducts}">
        <ul class="pagination">
                <%--handel Category--%>
            <c:if test="${not empty query}">
                <c:set var="stringQuery" value="&cate=${query}"/>
            </c:if>

                <%--to display Previous arrow except for the 1st page --%>
            <c:if test="${currentPage != 1}">
                <li><a href="Shop?page=${currentPage - 1}${stringQuery}">&laquo;</a></li>
            </c:if>

                <%--to displaying Page numbers--%>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage == i}">
                        <li class="active"><a href="">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="Shop?page=${i}${stringQuery}">${i}</a></li>

                    </c:otherwise>
                </c:choose>
            </c:forEach>

                <%--to display Next arrow --%>
            <c:if test="${currentPage lt noOfPages}">
                <li><a href="Shop?page=${currentPage + 1}${stringQuery}">&raquo;</a></li>
            </c:if>
        </ul>
    </c:if>
</div>
</div>
</div>
</section>

<%-- include footer file --%> 
<%@include file="footer.jsp" %>