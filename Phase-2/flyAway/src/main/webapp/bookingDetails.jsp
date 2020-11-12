<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/header.jsp" %>
  <link href="${pageContext.request.contextPath}/css/multi-page-form.css" rel="stylesheet" type="text/css">
</head>
<main role="main" class="flex-shrink-0">
  <div class="container">
    <div class="py-4">
      <a href="${pageContext.request.contextPath}/index.jsp">
        <img class="mb-4 text-center float-left" src="${pageContext.request.contextPath}/images/logo.jpg" alt="" width="99" height="86">
      </a>
      <h2>Booking Details</h2>
      <hr class="mb-5">
    </div>
    <div class="px-4">
      <c:out value="${requestScope.bookingDetails}"/>
      <input type="hidden" name="myhiddenvalue" value="${requestScope.bookingDetails}" />
    </div>
  </div>
</main>
<%@ include file="/common/footer.html" %>