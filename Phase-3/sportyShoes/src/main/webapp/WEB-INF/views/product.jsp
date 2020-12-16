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
      <div class="card">
        <div class="card-body">
          <div class="row">
            <div class="col-md-5">
            <img class="card-img-top" src="${pageContext.request.contextPath}/catalog/product/image?productId=${product.getId()}" alt="${product.getName()}">
            </div>
            <div class="col-md-7">
              <div class="row">
                <div class="col-md-9">
                  <h5>${product.getName()}</h5>
                </div>
                <div class="col-md-3">
                  <h5 class="text-right">$ <fmt:formatNumber type = "number" pattern="########0.00" value="${product.getPrice()}" /></h5>
                </div>
              </div>
              <div class="row">
                <div class="col-md-12">
                  <p class="text-secondary">${product.getShortDesc()}</p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-12">
                  <p>${product.getLongDesc()}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="card-footer">
          <form action="${pageContext.request.contextPath}/bag?action=add" method="post">
            <button class="btn btn-danger btn-lg btn-block" type="submit">Add to Bag</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</main>
<%@ include file="/WEB-INF/views/common/alert.jsp" %>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>