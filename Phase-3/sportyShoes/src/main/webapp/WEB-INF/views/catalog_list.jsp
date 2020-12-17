<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
  <link href="${pageContext.request.contextPath}/css/navbar-top.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="/WEB-INF/views/navbar.jsp" %>
<main role="main" class="flex-shrink-0">
  <div class="container">
    <div class="py-4">
      <h4 class="text-primary">${requestScope.catalogTitle}</h4>
    </div>
    <div>
      <c:forEach var="productList" items="${requestScope.productList}">
        <div class="row mb-4">
          <c:forEach var="product" items="${productList}">
            <div class="col-md-4">
              <a href="${pageContext.request.contextPath}/catalog/product?id=${product.getId()}" class="custom-card">
                <div class="card h-100">
                  <img class="card-img-top" src="${pageContext.request.contextPath}/catalog/product/image?productId=${product.getId()}" alt="${product.getName()}">
                  <div class="card-body">
                    <h6 class="card-title">${product.getName()}</h6>
                    <p class="card-text grey">${product.getShortDesc()}</p>
                    <p class="card-text">$ <fmt:formatNumber type = "number" pattern="########0.00" value="${product.getPrice()}" /></p>
                  </div>
                </div>
              </a>
            </div>
          </c:forEach>
        </div>
      </c:forEach>    
    </div>
  </div>
</main>
<%@ include file="/WEB-INF/views/common/alert.jsp" %>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>