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
      <h2>Select Flights</h2>
      <hr class="mb-4">
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
              <span>Adult ($)</span>
            </th>
            <th>
              <span>Child ($)</span>
            </th>
            <th>
              <span>Infant ($)</span>
            </th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="flight" items="${requestScope.flightSearchResult}">
            <tr>
              <td>
                <button type="button" class="btn btn-sm px-2" 
                  onclick="location.href='${pageContext.request.contextPath}/guest?action=book&id=${flight.getFlightId()}&adult=${requestScope.adultNo}&child=${requestScope.childNo}&infant=${requestScope.infantNo}'">
                  <i class="fas fa-book mt-0 text-primary"></i>
                </button>
              </td>
              <td><c:out value="${flight.getAirline().getFlightCode()}${flight.getFlightNo()}"/></td>
              <td><c:out value="${flight.getSource().getLocationCode()}"/></td>
              <td><c:out value="${flight.getDestination().getLocationCode()}"/></td>
              <td><c:out value="${flight.getDepartDateTime()}"/></td>
              <td><c:out value="${flight.getArriveDateTime()}"/></td>
              <td><c:out value="${flight.getAdultPriceString()}"/></td>
              <td><c:out value="${flight.getChildPriceString()}"/></td>
              <td><c:out value="${flight.getInfantPriceString()}"/></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</main>
<%@ include file="/common/footer.html" %>
    