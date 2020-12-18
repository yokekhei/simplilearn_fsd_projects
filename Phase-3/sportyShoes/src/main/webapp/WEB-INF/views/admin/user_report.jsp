<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
  <link href="${pageContext.request.contextPath}/css/navbar-top.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="/WEB-INF/views/admin/navbar.jsp" %>
<main role="main" class="flex-shrink-0">
  <div class="container">
    <div class="mt-5 card card-cascade narrower">
      <div class="py-2 mx-4 mb-3 d-flex justify-content-between align-items-center">
        <h4 class="text-primary">Signed-Up Users</h4>
      </div>
      <div class="px-4 mb-3">
        <form:form action="${pageContext.request.contextPath}/admin/report/user?action=search" method="post" modelAttribute="userSearchForm">
          <div class="form-row">
            <div class="col">
              <form:input path="firstName" type="text" class="form-control" placeholder="First name" value="" autocomplete="off" />
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
              <form:button class="btn btn-primary btn-block" type="submit">Search</form:button>
            </div>
          </div>
        </form:form>
      </div>
      <div class="px-4">
        <table class="table table-hover mb-0 small">
          <thead>
            <tr>
              <th>
                <span>Email</span>
              </th>
              <th>
                <span>Name</span>
              </th>
              <th>
                <span>Date of Birth</span>
              </th>
              <th>
                <span>Gender</span>
              </th>
              <th>
                <span>Status</span>
              </th>
              <th>
                <span>Signed-Up Date</span>
              </th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="user" items="${requestScope.userList}">
              <tr>
                <td><c:out value="${user.getEmail()}"/></td>
                <td><c:out value="${user.getFirstName()} ${user.getLastName()}"/></td>
                <td><c:out value="${user.getDobString()}"/></td>
                <td>
                  <c:choose>
                    <c:when test="${user.getGender() == 'M'}">
                      Male
                    </c:when>
                    <c:otherwise>
                      Female
                    </c:otherwise>
                  </c:choose>
                </td>
                <td>
                  <c:choose>
                    <c:when test = "${user.getEnabled() == true}">
                      Active
                    </c:when>
                    <c:otherwise>
                      Inactive
                    </c:otherwise> 
                  </c:choose>
                </td>
                <td><c:out value="${user.getCreatedDateString()}"/></td>
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