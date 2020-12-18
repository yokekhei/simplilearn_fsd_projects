<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
  <link href="${pageContext.request.contextPath}/css/navbar-top.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="/WEB-INF/views/admin/navbar.jsp" %>
<main role="main" class="flex-shrink-0">
  <div class="container">
    <div class="py-4">
      <div class="card">
        <div class="card-body">
          <div class="row">
            <div class="col-md-8">
              <h4 class="text-primary">Purchase Order #${requestScope.order.getId()}</h4>
              <c:forEach var="item" items="${requestScope.order.getItems()}">
                <div class="row py-4">
                  <div class="col-md-3">
                    <img class="card-img-top" src="${pageContext.request.contextPath}/catalog/product/image?productId=${item.getProduct().getId()}" alt="${item.getProduct().getName()}">
                  </div>
                  <div class="col-md-6">
                    <h5>${item.getProduct().getName()}</h5>
                    <p>${item.getProduct().getCategory().getName()}</p>
                    <form>
                      <div class="form-group row">
                        <label for="quantity" class="col-sm-6 col-form-label">Quantity</label>
                        <div class="col-sm-6">
                          <input name="quantity" type="number" class="form-control" name="quantity" placeholder="1" min="1" value="${item.getQuantity()}" disabled />
                        </div>
                      </div>
                    </form>
                  </div>
                  <div class="col-md-3">
                    <h5 class="text-right">$ <fmt:formatNumber type = "number" pattern="########0.00" value="${item.getTotalPrice()}" /></h5>
                  </div>
                </div>
                <hr />
              </c:forEach>
            </div>
            <div class="col-md-4">
              <h4>Summary</h4>
              <div class="row py-3">
                <div class="col-md-7">
                  <p>Sub-Total</p>
                </div>
                <div class="col-md-5">
                  <p class="text-right"><fmt:formatNumber type = "number" pattern="########0.00" value="${requestScope.order.getTotalPrice()}" /> $</p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-7">
                  <p>Shipping &amp; Handling</p>
                </div>
                <div class="col-md-5">
                  <p class="text-right">0.00 $</p>
                </div>
              </div>
              <hr />
              <div class="row">
                <div class="col-md-7">
                  <h5>Total</h5>
                </div>
                <div class="col-md-5">
                  <h5 class="text-right"><fmt:formatNumber type = "number" pattern="########0.00" value="${requestScope.order.getTotalPrice()}" /> $</h5>
                </div>
              </div>
              <hr />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</main>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>