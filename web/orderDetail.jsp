<%--
    Document   : products
    Created on : Feb 28, 2017, 11:39:20 PM
    Author     : MotYim
--%>

<%-- include header file --%>

<%@ page import="javaCode.yourcart.controller.admin.AdminProductServlet" %>
<%@include file="admin/header.jsp" %>

<%-- include slidebar file --%>
<%@include file="userSlideBar.jsp" %>

<div class="col-sm-9 padding-right">
    <c:if test="${!empty requestScope.order}">
        <p>Customer: ${requestScope.order.user_name}</p>
        <img src="../${order.user_photo}">
        <h2>Order ${requestScope.order.order_id}</h2>
        <p>Address: ${requestScope.order.address}</p>
        <p>Status: ${order.status_id == 0 ? "Pending" : order.status == 1 ? "Cancel" : "Done"}</p>
    </c:if>

    <div class="table-responsive cart_info">
        <table class="table table-condensed">
            <thead>
            <tr class="cart_menu">
                <td class="image">Item</td>
                <td class="description">Name</td>
                <td class="price">Price</td>
                <td class="quantity">Quantity</td>

                <td></td>
            </tr>
            </thead>
            <tbody>
            <c:if test="${!empty requestScope.products}">

                <c:forEach items="${requestScope.products}" var="product">
                    <tr>
                        <td class="cart_product">
                            <a href=""><img src="${product.photo}" alt=""></a>
                        </td>
                        <td class="cart_description">
                            <p>${product.name}</p>

                        </td>
                        <td class="cart_price">
                            <h4>$${product.price}</h4>
                        </td>
                        <td class="cart_price">
                            <p>${product.quantity}</p>
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


