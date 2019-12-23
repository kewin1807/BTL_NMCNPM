
<%-- include header file --%> 
<%@include file="header.jsp" %>

<%-- include slidebar file --%> 
<%@include file="slidebar.jsp" %>

        <div class="col-sm-8">
            <div class="product-information"><!--/product-information-->
                <form action="AdminCategory" method="post" >
                    <h2>${type} Category</h2>
                    <label>Category Name</label>
                    <input type="text" placeholder="Category Name" name="CategoryName" value="${category.name}" class="input-field" id="CategoryName" required/>
                    <input type="hidden" name="id" value="${category.id}"/>
                    <button type="submit" class="btn btn-default">${type} Category </button>
                </form>
            </div>
        </div>
    </div>





</div>
</div>
</div>
</section>


<%@include file="footer.jsp" %>
