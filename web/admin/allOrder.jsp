
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javaCode.yourcart.controller.user.History" %>
<%@include file="header.jsp" %>

<%-- include slidebar file --%>
<%@include file="slidebar.jsp" %>
<div class="col-sm-9 padding-right">

    <h2>History
        <a class="btn btn-default" style="float: right;" href="../">Create Order</a>
    </h2>


    <div class="table-responsive cart_info">
        <table class="table table-condensed">
            <thead>

            <tr class="cart_menu">
                <td class="image">User</td>
                <td class="description">Username</td>
                <td class="quantity">UserId</td>
                <td class="quantity">orderId</td>
                <td class="description">date</td>
                <td class="quantity">status</td>
                <td class="description">address</td>
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
    </div>

</div>
</div>
</div>
</section>
