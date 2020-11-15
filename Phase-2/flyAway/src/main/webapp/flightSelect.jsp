<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="st" uri="/WEB-INF/customTags.tld" %>
<%@ include file="/common/header.jsp" %>
</head>
<body>
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
      <table class="table table-hover mb-0 small">
        <thead>
          <tr>
            <th>
              <span>
                <i class="far fa-window-maximize"></i>
              </span>
            </th>
            <th>
              <span>Flight Code</span>
              <a href="${pageContext.request.contextPath}/guest?action=search&sortBy=flightCode&srcLocation=${requestScope.srcLocation}&dstLocation=${requestScope.dstLocation}&departDate=${requestScope.departDate}"><i class="fa fa-fw fa-sort"></i></a>
            </th>
            <th>
              <span>From City</span>
            </th>
            <th>
              <span>To City</span>
            </th>
            <th>
              <span>Depart Date/Time</span>
              <a href="${pageContext.request.contextPath}/guest?action=search&sortBy=departDt&srcLocation=${requestScope.srcLocation}&dstLocation=${requestScope.dstLocation}&departDate=${requestScope.departDate}"><i class="fa fa-fw fa-sort"></i></a>
            </th>
            <th>
              <span>Arrive Date/Time</span>
              <a href="${pageContext.request.contextPath}/guest?action=search&sortBy=arriveDt&srcLocation=${requestScope.srcLocation}&dstLocation=${requestScope.dstLocation}&departDate=${requestScope.departDate}"><i class="fa fa-fw fa-sort"></i></a>
            </th>
            <th>
              <span>Adult ($)</span>
              <a href="${pageContext.request.contextPath}/guest?action=search&sortBy=adultPrice&srcLocation=${requestScope.srcLocation}&dstLocation=${requestScope.dstLocation}&departDate=${requestScope.departDate}"><i class="fa fa-fw fa-sort"></i></a>
            </th>
            <th>
              <span>Child ($)</span>
              <a href="${pageContext.request.contextPath}/guest?action=search&sortBy=childPrice&srcLocation=${requestScope.srcLocation}&dstLocation=${requestScope.dstLocation}&departDate=${requestScope.departDate}"><i class="fa fa-fw fa-sort"></i></a>
            </th>
            <th>
              <span>Infant ($)</span>
              <a href="${pageContext.request.contextPath}/guest?action=search&sortBy=infantPrice&srcLocation=${requestScope.srcLocation}&dstLocation=${requestScope.dstLocation}&departDate=${requestScope.departDate}"><i class="fa fa-fw fa-sort"></i></a>
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
              <td><c:out value="${flight.getAirline().getFlightCode()} ${flight.getFlightNo()}"/></td>
              <td><c:out value="${flight.getSource().getLocationCode()}"/></td>
              <td><c:out value="${flight.getDestination().getLocationCode()}"/></td>
              <td><c:out value="${flight.getDepartDateTime()}"/></td>
              <td><c:out value="${flight.getArriveDateTime()}"/></td>
              <td><st:CustomDecimalTag scale="2">${flight.getAdultPrice()}</st:CustomDecimalTag></td>
              <td><st:CustomDecimalTag scale="2">${flight.getChildPrice()}</st:CustomDecimalTag></td>
              <td><st:CustomDecimalTag scale="2">${flight.getInfantPrice()}</st:CustomDecimalTag></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</main>
<%@ include file="/common/footer.html" %>
    