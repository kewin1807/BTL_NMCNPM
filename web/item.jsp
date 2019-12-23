<%--
  Created by IntelliJ IDEA.
  User: macbook
  Date: 12/22/19
  Time: 6:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%--0 -> new--%>
<%--1 -> best--%>
<%--2 -> nothing--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<c:out value="${requestScope.product}"/>--%>

<div class="col-sm-4">

    <div class="product-image-wrapper">
        <div class="single-products">
            <div class="productinfo text-center">
                <img src=${requestScope.product.photo} alt="" />
                <h2>$${requestScope.product.price}</h2>
                <p>${requestScope.product.name}</p>
                <p>Quantity: ${requestScope.product.quantity}</p>
                <c:choose>
                    <c:when test="${requestScope.product.quantity==0}">
                        <a class="btn btn-default add-to-cart"><i class="glyphicon glyphicon-minus-sign"></i>Out of stock</a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="product-overlay">
                <div class="overlay-content">
                    <h2>$${requestScope.product.price}</h2>
                    <p>${requestScope.product.name}</p>
                    <p>Quantity: ${requestScope.product.quantity}</p>
                    <c:choose>
                        <c:when test="${requestScope.product.quantity==0}">
                            <button class="add-to-cart" disabled><i class="glyphicon glyphicon-minus-sign"></i>Out of stock</button>
                        </c:when>
                        <c:otherwise>
                            <a  id="${requestScope.product.productId}" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <c:choose>
                <c:when test="${requestScope.status==0}">
                    <img src="images/home/new.png" class="new" alt="" />
                </c:when>
                <c:when test="${requestScope.status==1}">
                    <img src="images/home/best.png" class="new" alt="" style="width: 42px; height: 42px"/>
                </c:when>
            </c:choose>

        </div>
        <div class="choose">
            <ul class="nav nav-pills nav-justified">
                <li><a href="Product?id=${requestScope.product.productId}"><i class="fa fa-plus-square"></i>View Details</a></li>
            </ul>
        </div>
    </div>
</div>
