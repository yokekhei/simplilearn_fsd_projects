<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
  <link href="${pageContext.request.contextPath}/css/user-home.css" rel="stylesheet" type="text/css">
</head>
<body class="text-center">
  <img class="mb-4" src="${pageContext.request.contextPath}/images/logo.png" alt="" width="161" height="99">
  <c:if test="${not empty requestScope.userFailMsg}">
    <div class="alert alert-danger alert-dismissible mb-3 user-alert">
      <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        ${requestScope.userFailMsg}
    </div>
    <c:remove var="userFailMsg" scope="request" />
  </c:if>
  <c:if test="${not empty requestScope.success}">
    <div class="alert alert-success alert-dismissible mb-3 user-alert">
      <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        ${requestScope.success}
    </div>
    <c:remove var="success" scope="request" />
  </c:if>
  <ul class="nav nav-pills mb-3 justify-content-center" id="mainTab" role="tablist">
    <li class="nav-item">
      <a class="nav-link active" id="signin-tab" data-toggle="tab" href="#signin" role="tab" aria-controls="signin" aria-selected="true">Sign In</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" id="register-tab" data-toggle="tab" href="#register" role="tab" aria-controls="register" aria-selected="false">Sign Up</a>
    </li>
  </ul>
  <div class="tab-content">
    <div class="tab-pane active" id="signin" role="tabpanel" aria-labelledby="signin-tab">
      <%@ include file="/WEB-INF/views/login.jsp" %>
    </div>
    <div class="tab-pane" id="register" role="tabpanel" aria-labelledby="register-tab">
      <%@ include file="/WEB-INF/views/register.jsp" %>
    </div>
  </div>
</body>
</html>