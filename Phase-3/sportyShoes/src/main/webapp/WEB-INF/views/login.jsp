<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form class="form-signin" action="${pageContext.request.contextPath}/login" method="post" modelAttribute="user">
  <form:label path="email" for="inputEmail" class="sr-only">Email address</form:label>
  <form:input path="email" type="email" id="inputEmail" class="form-control" placeholder="Email address" required="required" autofocus="autofocus" />
  <form:label path="password" for="inputPassword" class="sr-only">Password</form:label>
  <form:input path="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required="required" />
  <form:button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</form:button>
  <p class="mt-5 mb-3 text-muted">&copy; SportyShoes 2020</p>
</form:form>