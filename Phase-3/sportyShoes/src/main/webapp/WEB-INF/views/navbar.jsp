<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body class="d-flex flex-column h-100">
  <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-secondary">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/images/logo.png" alt="" width="54" height="47"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Categories</a>
          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdown01">
            <c:forEach var="category" items="${sessionScope.categoryList}">
              <a class="dropdown-item" href="${pageContext.request.contextPath}/catalog?categoryId=${category.getId()}"><c:out value="${category.getName()}"/></a>
            </c:forEach>
          </div>
        </li>
      </ul>
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/bag">
            <c:if test="${not empty sessionScope.bag}">
              <i class="fas fa-shopping-bag"></i> Bag Items: <c:out value="${sessionScope.bag.getTotalQuantity()}" />
            </c:if>
            <c:if test="${empty sessionScope.bag}">
              <i class="fas fa-shopping-bag"></i> Bag Items: 0
            </c:if>
          </a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fas fa-user"></i> <c:out value="${sessionScope.loginUser.getFirstName()}"/>
          </a>
          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdown01">
            <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Sign out</a>
          </div>
        </li>
      </ul>
  </div>
</nav>
