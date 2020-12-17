<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
  <link href="${pageContext.request.contextPath}/css/navbar-top.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="/WEB-INF/views/navbar.jsp" %>
<main role="main" class="flex-shrink-0">
  <div class="container">
    <div class="py-4">
      <h4 class="text-primary">Payment</h4>
    </div>
    <div>
      <div class="row">
        <div class="col-md-7">
          <div class="card">
            <div class="card-header text-white bg-secondary">Credit/Debit card</div>
            <div class="card-body">
              <form:form action="${pageContext.request.contextPath}/payment" method="post" class="needs-validation" novalidate="novalidate" modelAttribute="payment">
                <div class="row">
                  <div class="col">
                    <form:label path="payorName" for="nameOnCard">Name on Card</form:label>
                    <form:input path="payorName" type="text" class="form-control" id="nameOnCard" placeholder="" value="" autocomplete="off" required="required" />
                    <div class="invalid-feedback">
                      Name on card is required.
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col">
                    <label for="cardNo">Card Number</label>
                    <input type="text" class="form-control" name="cardNo" placeholder="" value="" pattern="[0-9]+" autocomplete="off" required />
                    <div class="invalid-feedback">
                      Valid card number is required.
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col">
                    <label>Expiration</label>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-6">
                    <select name="cardExpMonth" class="custom-select" required>
                      <option value="">MM</option>
                      <c:forEach var="i" begin="1" end="12">
                        <c:if test="${i < 10}">
                          <option value="${i}">0<c:out value="${i}"/></option>
                        </c:if>
                        <c:if test="${i >= 10}">
                          <option value="${i}"><c:out value="${i}"/></option>
                        </c:if>
                      </c:forEach>
                    </select>
                    <div class="invalid-feedback">
                      Valid month is required.
                    </div>
                  </div>
                  <div class="col-md-6">
                    <select name="cardExpYear" class="custom-select" required>
                      <option value="">YY</option>
                      <c:forEach var="j" begin="${requestScope.currentYear}" end="${requestScope.currentYear + 19}">
                        <option value="${j}"><c:out value="${j}"/></option>
                      </c:forEach>
                    </select>
                    <div class="invalid-feedback">
                      Valid year is required.
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col">
                    <label>CVV</label>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-4">
                    <input type="text" class="form-control" name="cvv" placeholder="CVV" value="" pattern="[0-9]{3}" autocomplete="off" required />
                    <div class="invalid-feedback">
                      Valid CVV is required.
                    </div>
                  </div>
                  <div class="col-md-8">
                    <img src="${pageContext.request.contextPath}/images/cvv.png" alt="">
                  </div>
                </div>
                <hr class="mb-4">
                <form:input path="totalPaid" type="hidden" class="form-control" value="${sessionScope.bag.getTotalPrice()}" />
                <form:button class="btn btn-danger btn-lg btn-block" type="submit">Place Order</form:button>
              </form:form>
            </div>
          </div>
        </div>
        <div class="col-md-5">
          <div class="card">
            <div class="card-header text-white bg-secondary">
              <div class="row">
                <div class="col-md-7">
                  <span>Total</span>
                </div>
                <div class="col-md-5 text-right">
                  <strong><fmt:formatNumber type = "number" pattern="########0.00" value="${sessionScope.bag.getTotalPrice()}" /> $</strong>
                </div>
              </div>
            </div>
            <div class="card-body">
              <div class="row">
                <div class="col-md-7">
                  <p><small>Sub-Total</small></p>
                </div>
                <div class="col-md-5">
                  <p class="text-right"><small><fmt:formatNumber type = "number" pattern="########0.00" value="${sessionScope.bag.getTotalPrice()}" /> $</small></p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-7">
                  <p><small>Shipping &amp; Handling</small></p>
                </div>
                <div class="col-md-5">
                  <p class="text-right"><small>0.00 $</small></p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</main>
<%@ include file="/WEB-INF/views/common/alert.jsp" %>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>