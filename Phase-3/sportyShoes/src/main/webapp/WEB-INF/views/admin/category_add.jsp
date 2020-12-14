<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="modal fade" id="modalCategoryAdd">
  <div class="modal-dialog modal-lg modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Category</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
        <form:form action="${pageContext.request.contextPath}/admin/category?action=add" method="post" class="needs-validation" novalidate="novalidate" modelAttribute="category">
          <div class="row">
            <div class="col">
              <form:label path="name" for="categoryName">Name</form:label>
              <form:input path="name" id="categoryName" type="text" class="form-control" placeholder="Category name" value="" autocomplete="off" required="required" />
              <div class="invalid-feedback">
                Category name is required.
              </div>
            </div>
          </div>
          <div class="row">
             <div class="col">
              <form:label path="desc" for="categoryDesc">Description</form:label>
              <form:input path="desc" id="categoryDesc" type="text" class="form-control" placeholder="Category description" value="" required="required" />
              <div class="invalid-feedback">
                Category description is required.
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