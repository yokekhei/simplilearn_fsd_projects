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
        <table class="table table-hover mb-0 small">
          <thead>
            <tr>
              <th>
                <span>
                    <i class="far fa-window-maximize"></i>
                  </span>
              </th>
              <th>
                <span>Booking ID</span>
                <a href="${pageContext.request.contextPath}/booking?sortBy=bookingId"><i class="fa fa-fw fa-sort"></i></a>
              </th>
              <th>
                <span>Guest</span>
                <a href="${pageContext.request.contextPath}/booking?sortBy=guest"><i class="fa fa-fw fa-sort"></i></a>
              </th>
              <th>
                <span>Flight Code</span>
                <a href="${pageContext.request.contextPath}/booking?sortBy=flightCode"><i class="fa fa-fw fa-sort"></i></a>
              </th>
              <th>
                <span>From City</span>
                <a href="${pageContext.request.contextPath}/booking?sortBy=fromCity"><i class="fa fa-fw fa-sort"></i></a>
              </th>
              <th>
                <span>To City</span>
                <a href="${pageContext.request.contextPath}/booking?sortBy=toCity"><i class="fa fa-fw fa-sort"></i></a>
              </th>
              <th>
                <span>Depart Date/Time</span>
                <a href="${pageContext.request.contextPath}/booking?sortBy=departDt"><i class="fa fa-fw fa-sort"></i></a>
              </th>
              <th>
                <span>Arrive Date/Time</span>
                <a href="${pageContext.request.contextPath}/booking?sortBy=arriveDt"><i class="fa fa-fw fa-sort"></i></a>
              </th>
              <th>
                <span>Total ($)</span>
                <a href="${pageContext.request.contextPath}/booking?sortBy=totalPrice"><i class="fa fa-fw fa-sort"></i></a>
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