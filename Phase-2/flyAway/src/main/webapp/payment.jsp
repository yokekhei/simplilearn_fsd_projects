<%@page import="org.yokekhei.fsd.p2.Common"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/header.jsp" %>
</head>
<main role="main" class="flex-shrink-0">
  <div class="container">
    <div class="py-4">
      <a href="${pageContext.request.contextPath}/index.jsp">
        <img class="mb-4 text-center float-left" src="${pageContext.request.contextPath}/images/logo.jpg" alt="" width="99" height="86">
      </a>
      <h2>Payment</h2>
      <hr class="mb-5">
    </div>
    <div class="px-4">
      <div class="row">
        <div class="col-md-7">
          <div class="card">
            <div class="card-header text-white bg-secondary">Credit/debit card</div>
            <div class="card-body">
              <form action="${pageContext.request.contextPath}/guest?action=paid" method="post" class="needs-validation" novalidate>
                <div class="row">
                  <div class="col">
                    <label for="nameOnCard">Name on Card</label>
                    <input type="text" class="form-control" name="nameOnCard" placeholder="" value="" autocomplete="off" required />
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
                <button class="btn btn-danger btn-lg btn-block" type="submit">Purchase</button>
              </form>
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
                <div class="col-md-5">
                  <span class="text-right"><strong>${requestScope.totalCharge} $</strong></span>
                </div>
              </div>
            </div>
            <div class="card-body">
              <div class="row">
                <div class="col-md-7">
                  <p><small>Sub-total</small></p>
                </div>
                <div class="col-md-5">
                  <p class="text-right"><small>${requestScope.totalCharge} $</small></p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-7">
                  <p><small>Processing fee</small></p>
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
<%@ include file="/common/footer.html" %>