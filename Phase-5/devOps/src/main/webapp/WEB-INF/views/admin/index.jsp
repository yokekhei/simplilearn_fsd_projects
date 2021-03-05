<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
  <link href="${pageContext.request.contextPath}/css/signin.css" rel="stylesheet" type="text/css">
</head>
<body class="text-center">
  <form:form class="form-signin" action="${pageContext.request.contextPath}/admin/login" method="post" modelAttribute="user">
    <a href="${pageContext.request.contextPath}/">
      <img class="mb-4" src="${pageContext.request.contextPath}/images/logo.png" alt="" width="163" height="162">
    </a>
    <h1 class="h3 mb-3 font-weight-normal">Administrator Sign In</h1>
    <c:if test="${not empty requestScope.authMsg}">
      <div class="alert alert-danger alert-dismissible" id="failAlert">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        ${requestScope.authMsg}
      </div>
      <c:remove var="authMsg" scope="request" />
    </c:if>
    <form:label path="email" for="adminEmail" class="sr-only">Email address</form:label>
    <form:input path="email" type="email" id="adminEmail" class="form-control" placeholder="Email address" required="required" autofocus="autofocus" />
    <form:label path="password" for="adminPassword" class="sr-only">Password</form:label>
    <form:input path="password" type="password" id="adminPassword" class="form-control" placeholder="Password" required="required" />
    <form:button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</form:button>
    <p class="mt-5 mb-3 text-muted">&copy; Learner's Academy 2021</p>
  </form:form>
 </body>
 </html>