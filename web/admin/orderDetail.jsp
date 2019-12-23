

<%@include file="header.jsp" %>

<%-- include slidebar file --%>
<%@include file="slidebar.jsp" %>

<div class="col-sm-9 padding-right">
    <c:if test="${!empty requestScope.order}">
        <h2>Order ${requestScope.order.order_id}</h2>
        <input id="order_id" style="display: none" value="${requestScope.order.order_id}"/>
        <h3>Customer: ${requestScope.order.user_name}</h3>
        <p>Address: ${requestScope.order.address}</p>
        <p>Status Order :</p>
        <select name='role' id="status">
            <option value="0" ${order.status_id == 0 ? 'selected="selected"' : ''}>Pending</option>
            <option value="1" ${order.status_id == 1 ? 'selected="selected"' : ''}>Cancel</option>
            <option value="2" ${order.status_id == 2 ? 'selected="selected"' : ''}>Done</option>
        </select>
        <%--        <p>Status: ${requestScope.order.status_id == 0 ? "Pending" : requestScope.order.status == 1 ? "Cancel" : "Done"}</p>--%>
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
                            <a href=""><img src="../${product.photo}" alt=""></a>
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
    <div class="payment-options">
        <a class="btn btn-primary" id="update">Update</a>
    </div>

</div>
</div>
</div>
</section>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#update").click(function(){
            $.ajax({
                url:"UpdateOrder",
                type: "GET",
                data:{status_id: $("#status").val(), order_id: $("#order_id").val()},
                success: data => {
                    if (data.redirect) {
                        window.location.href = data.redirect;
                    }
                    else{
                        alert("Order Update Success", "success");
                    }
                }
            })
        });
    });

</script>
<%@include file="../footer.jsp" %>
