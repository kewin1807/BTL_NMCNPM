<%--
    Document   : index
    Created on : Feb 28, 2017, 10:25:09 PM
    Author     : bruno
--%>
<%--<jsp:include page="/IndexProductServlet"/> --%>
<%-- include header file --%> 
<%@include file="header.jsp" %>


<%-- include slider file --%> 
<%--<%@include file="slider.jsp" %>--%>


<%-- include slidebar file --%> 
<%@include file="slidebar.jsp" %>
<jsp:include page="/IndexProductServlet"/>
<div class="col-sm-9 padding-right">
    <div class="features_items"><!--features_items-->
        <h2 class="title text-center">New Items</h2>

        <c:if test="${!empty requestScope.limitedProducts}">

            <c:forEach items="${requestScope.limitedProducts}" var="product">
                <c:set var="product" value="${product}" scope="request"/>
                <c:set var="status" value="${0}" scope="request"/>
                <c:import url="item.jsp"/>

            </c:forEach>
        </c:if>

    </div><!--features_items-->
    <div class="features_items"><!--features_items-->
        <h2 class="title text-center">Best Sold Items</h2>

        <c:if test="${!empty requestScope.bestSellProducts}">

            <c:forEach items="${requestScope.bestSellProducts}" var="product">
                <c:set var="product" value="${product}" scope="request"/>
                <c:set var="status" value="${1}" scope="request"/>
                <c:import url="item.jsp"/>
            </c:forEach>
        </c:if>

    </div><!--features_items-->
</div>
</div>
</div>
</section>


<%-- include footer file --%> 
<%@include file="footer.jsp" %>
