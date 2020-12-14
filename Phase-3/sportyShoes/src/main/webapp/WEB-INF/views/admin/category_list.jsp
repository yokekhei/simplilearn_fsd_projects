<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
  <link href="${pageContext.request.contextPath}/css/navbar-top.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="/WEB-INF/views/admin/navbar.jsp" %>
<main role="main" class="flex-shrink-0">
  <div class="container">
    <div class="mt-5 card card-cascade narrower">
      <div class="py-2 mx-4 mb-3 d-flex justify-content-between align-items-center">
        <h4 class="text-primary">Categories</h4>
        <div>
          <button type="button" class="btn btn-lg px-2" data-toggle="modal" data-target="#modalCategoryAdd">
            <i class="fas fa-plus-circle mt-0 text-success"></i>
          </button>
          <%@ include file="/WEB-INF/views/admin/category_add.jsp" %>
        </div>
      </div>
      <div class="px-4">
        <table class="table table-hover mb-0 small">
          <thead>
            <tr>
              <th>
                <span>
                    <i class="far fa-window-maximize"></i>
                  </span>
              </th>
              <th>
                <span>Name</span>
              </th>
              <th>
                <span>Description</span>
              </th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="category" items="${sessionScope.categoryList}">
              <tr>
                <td>
                  <button type="button" class="btn btn-sm px-2" data-toggle="modal" data-target="#modalCategoryUpdate">
                    <i class="fas fa-pencil-alt mt-0 text-primary"></i>
                  </button>
                  <button type="button" class="btn btn-sm px-2" data-toggle="modal" data-target="#modalCategoryDelete">
                    <i class="far fa-trash-alt mt-0 text-danger"></i>
                  </button>
                </td>
                <td class="categoryName"><c:out value="${category.getName()}"/></td>
                <td class="categoryDesc"><c:out value="${category.getDesc()}"/></td>
                <td class="categoryId" style="display:none;"><c:out value="${category.getId()}"/></td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
        <%@ include file="/WEB-INF/views/admin/category_update.jsp" %>
        <%@ include file="/WEB-INF/views/admin/category_delete.jsp" %>
      </div>
    </div>
  </div>
</main>
<%@ include file="/WEB-INF/views/common/alert.jsp" %>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>