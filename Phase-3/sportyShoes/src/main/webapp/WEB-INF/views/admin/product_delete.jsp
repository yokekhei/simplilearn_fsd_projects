<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
	$(document).ready(function() {
		$('#modalProductDelete').on('show.bs.modal', function (e) {
			var _button = $(e.relatedTarget);
			var _row = _button.parents("tr");
			var _productId = _row.find(".productId").text();
			var _productCategoryId = _row.find(".productCategoryId").text();
			var _productName = _row.find(".productName").text();
			var _productShortDesc = _row.find(".productShortDesc").text();
			var _productLongDesc = _row.find(".productLongDesc").text();
			var _productPrice = _row.find(".productPrice").text();
			
			$(this).find(".productId").val(_productId);
			$(this).find(".productCategoryId").val(_productCategoryId);
			$(this).find(".productName").val(_productName);
			$(this).find(".productShortDesc").val(_productShortDesc);
			$(this).find(".productLongDesc").val(_productLongDesc);
			$(this).find(".productPrice").val(_productPrice);
		});
	});
</script>
<div class="modal fade" id="modalProductDelete">
  <div class="modal-dialog modal-lg modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Product</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
        <form:form action="${pageContext.request.contextPath}/admin/product?action=delete" method="post" modelAttribute="product" enctype="multipart/form-data">
          <div class="row" style="display:none;">
            <div class="col">
              <form:label path="id" for="productId">Product ID</form:label>
              <form:input path="id" id="productId" type="text" class="form-control productId" />
            </div>
          </div>
          <div class="row">
            <div class="col">
              <form:label path="name" for="productName">Name</form:label>
              <form:input path="name" id="productName" type="text" class="form-control productName" placeholder="" value="" autocomplete="off" required="required" readOnly="readOnly" />
            </div>
          </div>
          <div class="row">
             <div class="col">
              <form:label path="categoryId" for="category">Category</form:label>
              <form:select path="categoryId" class="custom-select productCategoryId" required="required" disabled="disabled">
                <form:option value="">Choose...</form:option>
                <c:forEach var="category" items="${sessionScope.categoryList}">
                  <form:option value="${category.getId()}"><c:out value="${category.getName()}"/></form:option>
                </c:forEach>
              </form:select>
            </div>
          </div>
          <div class="row">
             <div class="col">
              <form:label path="shortDesc" for="productShortDesc">Short Description</form:label>
              <form:input path="shortDesc" id="productShortDesc" type="text" class="form-control productShortDesc" placeholder="" value="" required="required" readOnly="readOnly" />
            </div>
          </div>
          <div class="row">
             <div class="col">
              <form:label path="longDesc" for="productLongDesc">Long Description</form:label>
              <form:textarea path="longDesc" id="productLongDesc" class="form-control productLongDesc" rows="3" placeholder="" required="required" readOnly="readOnly"></form:textarea>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <form:label path="price" for="productPrice">Price</form:label>
              <form:input path="price" id="productPrice" type="text" class="form-control productPrice" placeholder="" value="" autocomplete="off" required="required" readOnly="readOnly" />
            </div>
          </div>
          <hr class="mb-4">
          <form:button class="btn btn-danger btn-lg btn-block" type="submit">Delete</form:button>
        </form:form>
      </div>
    </div>
  </div>
</div>