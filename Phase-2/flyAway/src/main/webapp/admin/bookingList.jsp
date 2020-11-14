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
    <div class="mt-5 card card-cascade narrower">
      <div class="py-2 mx-4 mb-3 d-flex justify-content-between align-items-center">
        <h4 class="text-primary">Bookings</h4>
      </div>
      <div class="px-4">
        <table class="table table-hover mb-0">
          <thead>
            <tr>
              <th>
                <span>
                    <i class="far fa-window-maximize"></i>
                  </span>
              </th>
              <th>
                <span>Booking ID</span>
              </th>
              <th>
                <span>Guest</span>
              </th>
              <th>
                <span>Flight Code</span>
              </th>
              <th>
                <span>From City</span>
              </th>
              <th>
                <span>To City</span>
              </th>
              <th>
                <span>Depart Date/Time</span>
              </th>
              <th>
                <span>Arrive Date/Time</span>
              </th>
              <th>
                <span>Total ($)</span>
              </th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="booking" items="${sessionScope.bookingList}">
              <tr>
                <td>
                  <button type="button" class="btn btn-sm px-2" 
                    onclick="location.href='${pageContext.request.contextPath}/booking?action=view&id=${booking.getBookingId()}'">
                    <i class="fas fa-eye mt-0 text-primary"></i>
                  </button>
                </td>
                <td><c:out value="${booking.getBookingId()}"/></td>
                <td><c:out value="${booking.getGuest().getFirstName()} ${booking.getGuest().getLastName()}"/></td>
                <td><c:out value="${booking.getFlight().getAirline().getFlightCode()} ${booking.getFlight().getFlightNo()}"/></td>
                <td><c:out value="${booking.getFlight().getSource().getLocationCode()}"/></td>
                <td><c:out value="${booking.getFlight().getDestination().getLocationCode()}"/></td>
                <td><c:out value="${booking.getFlight().getDepartDateTime()}"/></td>
                <td><c:out value="${booking.getFlight().getArriveDateTime()}"/></td>
                <td><st:CustomDecimalTag scale="2">${booking.getTotalCharge()}</st:CustomDecimalTag></td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</main>
<%@ include file="/admin/alert.jsp" %>
<%@ include file="/common/footer.html" %>