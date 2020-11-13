<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/header.jsp" %>
</head>
<body>
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
      <div class="card">
        <div class="card-body">
          <div class="row">
            <div class="col-md-5">
              <h4 class="text-primary">Depart date</h4>
              <p>${sessionScope.bookingDetails.getFlight().getDepartDateString()}</p>
            </div>
            <div class="col-md-7">
              <h5>${sessionScope.bookingDetails.getFlight().getSource().getCityName()} <strong class="text-danger">-</strong> ${sessionScope.bookingDetails.getFlight().getDestination().getCityName()}</h5>
              <small>${sessionScope.bookingDetails.getFlight().getAirline().getFlightCode()} ${sessionScope.bookingDetails.getFlight().getFlightNo()} <strong class="text-danger">|</strong> 
                ${sessionScope.bookingDetails.getFlight().getDepartDateTime()} ~ ${sessionScope.bookingDetails.getFlight().getArriveDateTime()} <strong class="text-danger">|</strong>
                ${requestScope.flightDuration}
              </small>
              <hr />
            </div>
          </div>
          <div class="row">
            <div class="col-md-5">
              <h4 class="text-primary">Depart total</h4>
              <p><strong>$ ${requestScope.totalCharge}</strong></p>
            </div>
            <div class="col-md-7">
              <h5 class="text-success">Fares, taxes and fees</h5>
              <div class="row">
                <div class="col-md-9">
                  <p><small>${requestScope.adultNo}x Adult</small></p>
                </div>
                <div class="col-md-3">
                  <p class="text-right"><small>${sessionScope.bookingDetails.getTotalAdultFareString()} $</small></p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-9">
                  <p><small>${requestScope.childNo}x Child</small></p>
                </div>
                <div class="col-md-3">
                  <p class="text-right"><small>${sessionScope.bookingDetails.getTotalChildFareString()} $</small></p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-9">
                  <p><small>${requestScope.infantNo}x Infant</small></p>
                </div>
                <div class="col-md-3">
                  <p class="text-right"><small>${sessionScope.bookingDetails.getTotalInfantFareString()} $</small></p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-9">
                  <p><small>${requestScope.adultNo + requestScope.childNo}x Passenger Service Charge</small></p>
                </div>
                <div class="col-md-3">
                  <p class="text-right"><small>${sessionScope.bookingDetails.getTotalPassengerServiceChargeString()} $</small></p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-9">
                  <p><small>${requestScope.adultNo + requestScope.childNo}x Service Tax</small></p>
                </div>
                <div class="col-md-3">
                  <p class="text-right"><small>${sessionScope.bookingDetails.getTotalServiceTaxString()} $</small></p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-9">
                  <p><small>${requestScope.adultNo + requestScope.childNo}x Regulatory Service Charge</small></p>
                </div>
                <div class="col-md-3">
                  <p class="text-right"><small>${sessionScope.bookingDetails.getTotalRegulatoryServiceChargeString()} $</small></p>
                </div>
              </div>
              <hr />
              <h5 class="text-success">Passengers</h5>
              <c:forEach var="p" items="${sessionScope.bookingDetails.getPassengers()}">
                <p>
                  <small>
                    <c:if test="${p.getType() == 'A'}">
                      <i class="fas fa-male"></i>
                    </c:if>
                    <c:if test="${p.getType() == 'C'}">
                      <i class="fas fa-child"></i>
                    </c:if>
                    <c:if test="${p.getType() == 'I'}">
                      <i class="fas fa-baby"></i>
                    </c:if>
                    &nbsp;${p.getFirstName()} ${p.getLastName()}
                  </small>
                </p>
              </c:forEach>
            </div>
          </div>
        </div>
        <div class="card-footer">
          <form action="${pageContext.request.contextPath}/guest?action=pay" method="post">
            <button class="btn btn-danger btn-lg btn-block" type="submit">Continue</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</main>
<%@ include file="/common/footer.html" %>