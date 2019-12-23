

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javaCode.yourcart.controller.user.History" %>
<%@include file="admin/header.jsp" %>

<%-- include slidebar file --%>
<%@include file="userSlideBar.jsp" %>
<div class="col-sm-9 padding-right">

    <h2>History</h2>

    <div class="table-responsive cart_info">
        <table class="table table-condensed">
            <thead>

            <tr class="cart_menu">
                <td class="quantity">historyId</td>
                <td class="description">date</td>
                <td class="quantity">status</td>
                <td class="quantity">address</td>
                <td class="quantity"></td>
                <td></td>
            </tr>
            </thead>
            <tbody>

            <c:if test="${!empty requestScope.orders}">

                <c:forEach items="${requestScope.orders}" var="order">
                    <tr>
                        <td class="cart_quantity">
                            <p>${order.order_id}</p>
                        </td>
                        <td class="cart_description">
                            <p>${order.date == null ? "Chưa cập nhật" : order.date}</p>

                        </td>
                        <td class="cart_quantity">
                            <p>${order.status_id == 0 ? "Đang chờ" : order.status == 1 ? "Huỷ" : "Hoàn thành"}</p>
                        </td>
                        <td class="">
                            <p>${order.address}</p>
                        </td>
                        <td class="cart_delete">
                            <a class="cart_quantity_delete" href="DetailUserOrder?id=${order.order_id}"><i class="fa fa-pencil"></i></a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>


            </tbody>
        </table>
    </div>

</div>
</div>
</div>
</section>
