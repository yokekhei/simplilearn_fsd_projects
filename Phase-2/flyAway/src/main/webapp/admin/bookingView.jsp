<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="st" uri="/WEB-INF/customTags.tld" %>
<%@ include file="/common/header.jsp" %>
  <link href="${pageContext.request.contextPath}/css/navbar-top.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="/admin/navbar.jsp" %>
<main role="main" class="flex-shrink-0">
  <div class="container">
    <div class="py-3">
      <h4>Booking ${requestScope.booking.getBookingId()}</h4>
    </div>
    <div>
      <div class="card">
        <div class="card-body">
          <div class="row">
            <div class="col-md-5">
              <h4 class="text-primary">Depart date</h4>
              <p><st:CustomDateTag format="dd MMM yyyy">${requestScope.booking.getFlight().getDepartDate()}</st:CustomDateTag></p>
            </div>
            <div class="col-md-7">
              <h5>${requestScope.booking.getFlight().getSource().getCityName()} <strong class="text-danger">-</strong> ${requestScope.booking.getFlight().getDestination().getCityName()}</h5>
              <small>${requestScope.booking.getFlight().getAirline().getFlightCode()} ${requestScope.booking.getFlight().getFlightNo()} <strong class="text-danger">|</strong> 
                ${requestScope.booking.getFlight().getDepartDateTime()} ~ ${requestScope.booking.getFlight().getArriveDateTime()} <strong class="text-danger">|</strong>
                ${requestScope.booking.getFlight().getDuration()}
              </small>
              <hr />
            </div>
          </div>
          <div class="row">
            <div class="col-md-5">
              <h4 class="text-primary">Depart total</h4>
              <p><strong>$ <st:CustomDecimalTag scale="2">${booking.getTotalCharge()}</st:CustomDecimalTag></strong></p>
            </div>
            <div class="col-md-7">
              <h5 class="text-success">Fares, taxes and fees</h5>
              <div class="row">
                <div class="col-md-9">
                  <p><small>${requestScope.adultNo}x Adult</small></p>
                </div>
                <div class="col-md-3">
                  <p class="text-right"><small><st:CustomDecimalTag scale="2">${requestScope.booking.getTotalAdultFare()}</st:CustomDecimalTag> $</small></p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-9">
                  <p><small>${requestScope.childNo}x Child</small></p>
                </div>
                <div class="col-md-3">
                  <p class="text-right"><small><st:CustomDecimalTag scale="2">${requestScope.booking.getTotalChildFare()}</st:CustomDecimalTag> $</small></p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-9">
                  <p><small>${requestScope.infantNo}x Infant</small></p>
                </div>
                <div class="col-md-3">
                  <p class="text-right"><small><st:CustomDecimalTag scale="2">${requestScope.booking.getTotalInfantFare()}</st:CustomDecimalTag> $</small></p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-9">
                  <p><small>${requestScope.adultNo + requestScope.childNo}x Passenger Service Charge</small></p>
                </div>
                <div class="col-md-3">
                  <p class="text-right"><small><st:CustomDecimalTag scale="2">${requestScope.booking.getTotalPassengerServiceCharge()}</st:CustomDecimalTag> $</small></p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-9">
                  <p><small>${requestScope.adultNo + requestScope.childNo}x Service Tax</small></p>
                </div>
                <div class="col-md-3">
                  <p class="text-right"><small><st:CustomDecimalTag scale="2">${requestScope.booking.getTotalServiceTax()}</st:CustomDecimalTag> $</small></p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-9">
                  <p><small>${requestScope.adultNo + requestScope.childNo}x Regulatory Service Charge</small></p>
                </div>
                <div class="col-md-3">
                  <p class="text-right"><small><st:CustomDecimalTag scale="2">${requestScope.booking.getTotalRegulatoryServiceCharge()}</st:CustomDecimalTag> $</small></p>
                </div>
              </div>
              <hr />
              <h5 class="text-success">Passengers</h5>
              <c:forEach var="p" items="${requestScope.booking.getPassengers()}">
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
               <hr />
              <h5 class="text-success">Contact</h5>
              <p>
                <small>
                  ${requestScope.booking.getGuest().getFirstName()} ${requestScope.booking.getGuest().getLastName()}&nbsp;
              	  <i class="fas fa-envelope-open"></i> ${requestScope.booking.getGuest().getEmail()}&nbsp;
              	  <i class="fas fa-mobile"></i> ${requestScope.booking.getGuest().getPhone()}
              	</small>
             </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</main>
<%@ include file="/common/footer.html" %>