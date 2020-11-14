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
      <h2>Boarding Pass</h2>
      <hr class="mb-5">
    </div>
    <div class="px-4">
      <div class="alert alert-success alert-dismissible">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
          Thank you. Your payment was successful.
      </div>
      <%! int seatNo = Common.getRandomSeatNo(); %>
      <c:forEach var="passenger" items="${requestScope.bookingDetails.getPassengers()}">
        <div class="row mb-4">
          <div class="col">
            <div class="card border-secondary">
              <div class="card-header text-white bg-secondary">
                <div class="row">
                  <div class="col-md-7">
                    <h5><strong>${passenger.getFirstName()} ${passenger.getLastName()}</strong></h5>
                  </div>
                  <div class="col-md-5">
                    <p class="text-right">
                      <a href="#"><i class="fas fa-save text-white"></i></a>
                      <a href="#"><i class="fas fa-print text-white"></i></a>
                    </p>
                  </div>
                </div>
              </div>
              <div class="card-body">
                <div class="row">
                  <div class="col-md-7">
                    <small class="text-secondary">Depart</small><br/>
                    <p><strong>${requestScope.bookingDetails.getFlight().getSource().getCityName()} (${requestScope.bookingDetails.getFlight().getSource().getLocationCode()})</strong></p>
                    <small class="text-secondary">Arrive</small><br/>
                    <p><strong>${requestScope.bookingDetails.getFlight().getDestination().getCityName()} (${requestScope.bookingDetails.getFlight().getDestination().getLocationCode()})</strong></p>
                    <small class="text-secondary">Booking no.</small><br/>
                    <p><strong>${requestScope.bookingDetails.getBookingId()}</strong></p>
                  </div>
                  <div class="col-md-5">
                    <small class="text-secondary">Flight no.</small><br/>
                    <p><strong>${requestScope.bookingDetails.getFlight().getAirline().getFlightCode()} ${requestScope.bookingDetails.getFlight().getFlightNo()}</strong></p>
                    <small class="text-secondary">Boarding date</small><br/>
                    <p><strong>${requestScope.boardingDate}</strong></p>
                    <small class="text-secondary">Boarding time</small><br/>
                    <p><strong>${requestScope.boardingTime}</strong></p>
                    <small class="text-secondary">Seat no.</small><br/>
                    <c:choose>
                      <c:when test="${passenger.getType() == 'I'}">
                        <p><strong>-</strong></p>
                      </c:when>
                      <c:otherwise>
                        <p><strong><%= seatNo-- %>E</strong></p>
                      </c:otherwise>
                    </c:choose>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
  </div>
</main>
<%@ include file="/common/footer.html" %>