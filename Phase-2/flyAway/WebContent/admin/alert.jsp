<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sweetalert.min.js"></script>
<c:if test="${not empty sessionScope.alert}">
  <script type="text/javascript">
    swal('${sessionScope.alert}', '', 'error')
  </script>
  <c:remove var="alert" scope="session" />
</c:if>