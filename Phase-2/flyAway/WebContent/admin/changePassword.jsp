<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/header.jsp" %>
  <link href="${pageContext.request.contextPath}/css/navbar-top-fixed.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/css/sticky-footer-navbar.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="/admin/navbar.jsp" %>
<main role="main" class="flex-shrink-0">
  <div class="container">
    <div class="py-5">
      <h4>Change Password</h4>
      <hr class="mb-4">
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
      <c:if test="${sessionScope.changePasswordStatus == 'fail'}">
        <div class="alert alert-danger alert-dismissible">
          <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
          Change password failed. Please try again.
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
      <form action="${pageContext.request.contextPath}/password?action=change" method="post" class="needs-validation" novalidate>
        <div class="row">
          <div class="col">
            <input type="password" class="form-control" name="password" placeholder="Enter Password" value="" required />
            <div class="invalid-feedback">
              New password is required.
            </div>
          </div>
        </div>
        <p/>
        <div class="row">
          <div class="col">
            <input type="password" class="form-control" name="confirmPassword" placeholder="Confirm Password" value="" required />
            <div class="invalid-feedback">
              Confirm password is required.
            </div>
          </div>
        </div>
        <hr class="mb-4">
        <button class="btn btn-primary btn-lg btn-block" type="submit">Submit</button>
      </form>
    </div>
  </div>
</main>
<%@ include file="/footer.html" %>