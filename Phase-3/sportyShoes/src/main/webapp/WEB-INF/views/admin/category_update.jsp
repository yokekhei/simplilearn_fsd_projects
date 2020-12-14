<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
	$(document).ready(function() {
		$('#modalCategoryUpdate').on('show.bs.modal', function (e) {
			var _button = $(e.relatedTarget);
			var _row = _button.parents("tr");
			var _categoryId = _row.find(".categoryId").text();
			var _categoryName = _row.find(".categoryName").text();
			var _categoryDesc = _row.find(".categoryDesc").text();
			
			$(this).find(".categoryId").val(_categoryId);
			$(this).find(".categoryName").val(_categoryName);
			$(this).find(".categoryDesc").val(_categoryDesc);
		});
	});
</script>
<div class="modal fade" id="modalCategoryUpdate">
  <div class="modal-dialog modal-lg modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Category</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
        <form:form action="${pageContext.request.contextPath}/admin/category?action=update" method="post" class="needs-validation" novalidate="novalidate" modelAttribute="category">
          <div class="row" style="display:none;">
            <div class="col">
              <form:label path="id" for="categoryId">Category ID</form:label>
              <form:input path="id" id="categoryId" type="text" class="form-control categoryId" />
            </div>
          </div>
          <div class="row">
            <div class="col">
              <form:label path="name" for="categoryName">Name</form:label>
              <form:input path="name" id="categoryName" type="text" class="form-control categoryName" placeholder="" value="" autocomplete="off" required="required" readOnly="readOnly" />
              <div class="invalid-feedback">
                Category name is required.
              </div>
            </div>
          </div>
          <div class="row">
             <div class="col">
              <form:label path="desc" for="categoryDesc">Description</form:label>
              <form:input path="desc" id="categoryDesc" type="text" class="form-control categoryDesc" placeholder="" value="" required="required" />
              <div class="invalid-feedback">
                Category description is required.
              </div>
            </div>
          </div>
          <hr class="mb-4">
          <form:button class="btn btn-primary btn-lg btn-block" type="submit">Update</form:button>
        </form:form>
      </div>
    </div>
  </div>
</div>