<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
  <link href="${pageContext.request.contextPath}/css/navbar-top.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="/WEB-INF/views/admin/navbar.jsp" %>
<main role="main" class="flex-shrink-0">
  <div class="container">
    <div class="mt-5 card card-cascade narrower">
      <div class="py-2 mx-4 mb-3 d-flex justify-content-between align-items-center">
        <h4 class="text-primary">Purchase Orders</h4>
      </div>
      <div class="px-4 mb-3">
        <form:form action="${pageContext.request.contextPath}/admin/report/purchase?action=filter" method="post" modelAttribute="purchaseFilterForm">
          <div class="form-row">
            <div class="col">
              <form:select path="categoryId" class="custom-select">
                <form:option value="">Choose Category...</form:option>
                <c:forEach var="category" items="${requestScope.categoryList}">
                  <form:option value="${category.getId()}"><c:out value="${category.getName()}"/></form:option>
                </c:forEach>
              </form:select>
            </div>
            <div class="col">
              <form:input path="fromDate" id="fromDate" autocomplete="off" placeholder="From" value="${requestScope.startDate}" required="required" />
              <script>
                $('#fromDate${i}').datepicker({
                	format: 'dd-mm-yyyy',
                	uiLibrary: 'bootstrap4'
                });
              </script>
            </div>
            <div class="col">
              <form:input path="toDate" id="toDate" autocomplete="off" placeholder="To" value="${requestScope.endDate}" required="required" />
              <script>
                $('#toDate${i}').datepicker({
                	format: 'dd-mm-yyyy',
                	uiLibrary: 'bootstrap4'
                });
              </script>
            </div>
            <div class="col">
              <form:button class="btn btn-primary btn-block" type="submit">Filter</form:button>
            </div>
          </div>
        </form:form>
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
                <span>Purchase Order #</span>
              </th>
              <th>
                <span>Purchase User</span>
              </th>
              <th>
                <span>Total Paid</span>
              </th>
              <th>
                <span>Purchase Date</span>
              </th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="order" items="${requestScope.orderList}">
              <tr>
                <td>
                  <button type="button" class="btn btn-sm px-2" 
                    onclick="location.href='${pageContext.request.contextPath}/admin/report/purchase/view?id=${order.getId()}'">
                    <i class="fas fa-eye mt-0 text-primary"></i>
                  </button>
                </td>
                <td><c:out value="${order.getId()}"/></td>
                <td><c:out value="${order.getUser().getFirstName()} ${order.getUser().getLastName()}"/></td>
                <td><fmt:formatNumber type = "number" pattern="########0.00" value="${order.getTotalPrice()}" /></td> 
                <td><c:out value="${order.getCreatedDateString()}"/></td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</main>
<%@ include file="/WEB-INF/views/common/alert.jsp" %>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>