
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<%@ page import="javaCode.yourcart.controller.user.History" %>
<%@include file="header.jsp" %>

<%-- include slidebar file --%>
<%@include file="slidebar.jsp" %>
<div class="col-sm-9 padding-right">
    <h2>Orders</h2>
    <div class="table-responsive cart_info">
        <table class="table table-condensed">
            <thead>
            <tr class="cart_menu">
                <td class="image"></td>
                <td class="description">NAME</td>
                <td class="quantity">UID</td>
                <td class="quantity">ORDER_ID</td>
                <td class="description">DATE</td>
                <td class="quantity">STATUS</td>
                <td class="description">ADDRESS</td>
                <td class="cart_delete"></td>
            </tr>
            </thead>
            <tbody>
            <c:if test="${!empty requestScope.orders}">
                <c:forEach items="${requestScope.orders}" var="order">
                    <tr>
                        <td class="cart_product">
                            <img src="../${order.user_photo}">
                        </td>
                        <td class="cart_description">
                            <p>${order.user_name == null ? "Not update" : order.user_name}</p>
                        </td>
                        <td class="cart_quantity">
                            <p>${order.user_id}</p>
                        </td>
                        <td class="cart_quantity">
                            <p>${order.order_id}</p>
                        </td>
                        <td class="cart_description">
                            <p>${order.date}</p>
                        </td>
                        <td class="cart_description">
                            <p>${order.status_id == 0 ? "Pending" : order.status_id == 1 ? "Cancel" : "Done"}</p>
                        </td>
                        <td class="cart_description">
                            <p>${order.address}</p>
                        </td>
                        <td class="cart_delete">
                            <a class="cart_quantity_delete" href="DetailAdminOrder?id=${order.order_id}"><i class="fa fa-pencil"></i></a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>

        <ul class="pagination">
            <c:if test="${currentPageOrder != 1}">
                <li><a href="AdminAllOrder?page=${currentPageOrder - 1}">&laquo;</a></li>
            </c:if>

            <c:forEach begin="1" end="${noOfPagesOrder}" var="i">
                <c:choose>
                    <c:when test="${currentPageOrder == i}">
                        <li class="active"><a href="">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="AdminAllOrder?page=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <%--to display Next arrow --%>
            <c:if test="${currentPageOrder lt noOfPagesOrder}">
                <li><a href="AdminAllOrder?page=${currentPageOrder + 1}">&raquo;</a></li>
            </c:if>
        </ul>

    </div>
</div>
</div>
</div>
</section>
<%@include file="footer.jsp" %>
<%-- include notify file --%>
<%@include file="notify.jsp" %>