<%@ page import="javaCode.yourcart.controller.admin.AdminCategoryServlet" %>
<%@include file="header.jsp" %>

<%-- include slidebar file --%> 
<%@include file="slidebar.jsp" %>

<div class="col-sm-9 padding-right">
    <h2>Category
       <a class="btn btn-default" style="float: right;" href="addcategory.jsp">New Category</a>
    </h2>
    <div class="table-responsive cart_info">
        <table class="table table-condensed">
            <thead>
                <tr class="cart_menu">
                    <td class="id">Id</td>
                    <td class="name">Name</td>
                    <td></td>
                </tr>
            </thead>
            <tbody>
            <c:if test="${!empty requestScope.allCategorysAdmin}">
                    <c:forEach items="${requestScope.allCategorysAdmin}" var="category">
                        <tr>
                            <td class="cart_id">
                                <p>${category.id}</p>
                            </td>
                    
                            <td class="cart_id">
                                <p>${category.name}</p>
                            </td>
                            <td class="cart_delete">
                                <a class="cart_quantity_delete" href="DeleteCategory?id=${category.id}"><i class="fa fa-times"></i></a>
                                <a class="cart_quantity_delete" href="AdminCategory?id=${category.id}"><i class="fa fa-pencil"></i></a>
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
</section> <!--/#cart_items-->

<%-- include footer file --%> 
<%@include file="footer.jsp" %>
<%-- include notify file --%> 
<%@include file="notify.jsp" %>