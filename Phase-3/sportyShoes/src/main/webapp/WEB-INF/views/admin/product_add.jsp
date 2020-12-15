<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="modal fade" id="modalProductAdd">
  <div class="modal-dialog modal-lg modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Product</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
        <form:form action="${pageContext.request.contextPath}/admin/product?action=add" method="post" class="needs-validation" novalidate="novalidate" modelAttribute="product" enctype="multipart/form-data">
          <div class="row">
            <div class="col">
              <form:label path="name" for="productName">Name</form:label>
              <form:input path="name" id="productName" type="text" class="form-control" placeholder="Product name" value="" autocomplete="off" required="required" />
              <div class="invalid-feedback">
                Product name is required.
              </div>
            </div>
          </div>
          <div class="row">
             <div class="col">
              <form:label path="categoryId" for="category">Category</form:label>
              <form:select path="categoryId" class="custom-select" required="required">
                <form:option value="">Choose...</form:option>
                <c:forEach var="category" items="${sessionScope.categoryList}">
                  <form:option value="${category.getId()}"><c:out value="${category.getName()}"/></form:option>
                </c:forEach>
              </form:select>
              <div class="invalid-feedback">
                Valid category is required.
              </div>
            </div>
          </div>
          <div class="row">
             <div class="col">
              <form:label path="shortDesc" for="productShortDesc">Short Description</form:label>
              <form:input path="shortDesc" id="productShortDesc" type="text" class="form-control" placeholder="Product short description" value="" required="required" />
              <div class="invalid-feedback">
                Product short description is required.
              </div>
            </div>
          </div>
          <div class="row">
             <div class="col">
              <form:label path="longDesc" for="productLongDesc">Long Description</form:label>
              <form:textarea path="longDesc" id="productLongDesc" class="form-control" rows="3" placeholder="Product long description" required="required"></form:textarea>
              <div class="invalid-feedback">
                Product long description is required.
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <form:label path="price" for="productPrice">Price</form:label>
              <form:input path="price" id="productPrice" type="text" class="form-control" placeholder="0.00" value="" autocomplete="off" required="required" />
              <div class="invalid-feedback">
                Valid price is required.
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <form:label path="imageFile" for="imageFile">Image File</form:label>
              <form:input path="imageFile" id="imageFile" type="file" class="form-control-file" required="required" />
              <div class="invalid-feedback">
                Shoe image is required.
              </div>
            </div>
          </div>
          <hr class="mb-4">
          <form:button class="btn btn-success btn-lg btn-block" type="submit">Add</form:button>
        </form:form>
      </div>
    </div>
  </div>
</div>