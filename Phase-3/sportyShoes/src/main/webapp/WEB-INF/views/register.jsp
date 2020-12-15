<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form class="form-register" action="${pageContext.request.contextPath}/register" method="post" modelAttribute="guest">
  <div class="form-row">
    <div class="col">
      <form:input path="email" type="email" class="form-control" placeholder="Email address" required="required" autofocus="autofocus" />
    </div>
    <div class="col">
      <form:input path="password" type="password" class="form-control" placeholder="Password" required="required" />
    </div>
  </div>
  <div class="form-row">
    <div class="col">
      <form:input path="firstName" type="text" class="form-control" placeholder="First name" required="required" />
    </div>
    <div class="col">
      <form:input path="lastName" type="text" class="form-control" placeholder="Last name" required="required" />
    </div>
  </div>
  <div class="form-row">
    <div class="col">
      <form:input path="dobString" id="dob" autocomplete="off" placeholder="DOB (DD-MM-YYYY)" required="required" />
      <script>
      	$('#dob${i}').datepicker({
      		format: 'dd-mm-yyyy',
      		uiLibrary: 'bootstrap4'
      	});
      </script>
    </div>
    <div class="col" style="margin-bottom: 10px;">
      <form:select path="gender" class="form-control" required="required">
        <option value="">Choose...</option>
        <option value="M">Male</option>
        <option value="F">Female</option>
      </form:select>
    </div>
  </div>
  <form:button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</form:button>
  <p class="mt-5 mb-3 text-muted">&copy; SportyShoes 2020</p>
</form:form>