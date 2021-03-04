<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form class="form-register" action="${pageContext.request.contextPath}/register"
  method="post" modelAttribute="guest">
    <div class="form-row">
      <div class="col">
        <form:input path="userName" id="registerUserName" type="text" class="form-control"
          placeholder="Username" required="required" />
      </div>
    </div>
    <div class="form-row">
      <div class="col">
        <form:input path="email" id="registerEmail" type="email" class="form-control"
          placeholder="Email address" required="required" autofocus="autofocus" />
      </div>
    </div>
    <div class="form-row">
      <div class="col">
        <form:input path="password" id="registerPassword" type="password" class="form-control"
          placeholder="Password" required="required" />
      </div>
    </div>
    <form:button class="btn btn-lg btn-primary btn-block" name="registerBtn" type="submit">
      Sign up
    </form:button>
    <p class="mt-5 mb-3 text-muted">&copy; Learner's Academy 2021</p>
</form:form>