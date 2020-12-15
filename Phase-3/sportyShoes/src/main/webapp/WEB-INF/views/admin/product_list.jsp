<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
  <link href="${pageContext.request.contextPath}/css/navbar-top.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="/WEB-INF/views/admin/navbar.jsp" %>
<main role="main" class="flex-shrink-0">
  <div class="container">
    <div class="mt-5 card card-cascade narrower">
      <div class="py-2 mx-4 mb-3 d-flex justify-content-between align-items-center">
        <h4 class="text-primary">Products</h4>
        <div>
          <button type="button" class="btn btn-lg px-2" data-toggle="modal" data-target="#modalProductAdd">
            <i class="fas fa-plus-circle mt-0 text-success"></i>
          </button>
          <%@ include file="/WEB-INF/views/admin/product_add.jsp" %>
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
                <span>Category</span>
              </th>
              <th>
                <span>Price</span>
              </th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="product" items="${sessionScope.productList}">
              <tr>
                <td>
                  <button type="button" class="btn btn-sm px-2" data-toggle="modal" data-target="#modalProductUpdate">
                    <i class="fas fa-pencil-alt mt-0 text-primary"></i>
                  </button>
                  <button type="button" class="btn btn-sm px-2" data-toggle="modal" data-target="#modalProductDelete">
                    <i class="far fa-trash-alt mt-0 text-danger"></i>
                  </button>
                </td>
                <td class="productName"><c:out value="${product.getName()}"/></td>
                <td class="productCategory"><c:out value="${product.getCategory().getName()}"/></td>
                <td class="productPrice"><fmt:formatNumber type = "number" pattern="########0.00" value="${product.getPrice()}" /></td>
                <td class="productLongDesc" style="display:none;"><c:out value="${product.getLongDesc()}"/></td>
                <td class="productShortDesc" style="display:none;"><c:out value="${product.getShortDesc()}"/></td>
                <td class="productId" style="display:none;"><c:out value="${product.getId()}"/></td>
                <td class="productCategoryId" style="display:none;"><c:out value="${product.getCategory().getId()}"/></td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
        <%@ include file="/WEB-INF/views/admin/product_update.jsp" %>
        <%@ include file="/WEB-INF/views/admin/product_delete.jsp" %>
      </div>
    </div>
  </div>
</main>
<%@ include file="/WEB-INF/views/common/alert.jsp" %>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>