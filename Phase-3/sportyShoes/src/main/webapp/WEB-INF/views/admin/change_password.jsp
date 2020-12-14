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
    <div class="py-5">
      <h4>Change Password</h4>
      <hr class="mb-4">
      <c:if test="${not empty sessionScope.passwordFailure}">
        <div class="alert alert-danger alert-dismissible">
          <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
          ${sessionScope.passwordFailure}
        </div>
        <c:remove var="passwordFailure" scope="session" />
      </c:if>
      <c:if test="${sessionScope.changePasswordStatus == 'success'}">
        <div class="alert alert-success alert-dismissible">
          <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
          Change password successfully.
        </div>
        <c:remove var="changePasswordStatus" scope="session" />
      </c:if>
      <c:if test="${sessionScope.changePasswordStatus == 'notMatch'}">
        <div class="alert alert-danger alert-dismissible">
          <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
          Confirm password not match.
        </div>
        <c:remove var="changePasswordStatus" scope="session" />
      </c:if>
      <c:if test="${sessionScope.changePasswordStatus == 'same'}">
        <div class="alert alert-warning alert-dismissible">
          <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
          Password is same as previous password.
        </div>
        <c:remove var="changePasswordStatus" scope="session" />
      </c:if>
      <form:form action="${pageContext.request.contextPath}/admin/password?action=change" method="post" class="needs-validation" novalidate="novalidate" modelAttribute="user">
        <div class="row">
          <div class="col">
            <form:input path="password" type="password" class="form-control" placeholder="Enter Password" value="" required="required" />
            <div class="invalid-feedback">
              New password is required.
            </div>
          </div>
        </div>
        <p/>
        <div class="row">
          <div class="col">
            <form:input path="confirmPassword" type="password" class="form-control" placeholder="Confirm Password" value="" required="required" />
            <div class="invalid-feedback">
              Confirm password is required.
            </div>
          </div>
        </div>
        <form:input path="email" type="hidden" class="form-control" value="${user.getEmail()}" />
        <hr class="mb-4">
        <form:button class="btn btn-primary btn-lg btn-block" type="submit">Submit</form:button>
      </form:form>
    </div>
  </div>
</main>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>