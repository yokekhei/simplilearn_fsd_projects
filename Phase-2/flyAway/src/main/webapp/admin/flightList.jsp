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
        <h4 class="text-primary">Flights</h4>
        <div>
          <button type="button" class="btn btn-lg px-2" data-toggle="modal" data-target="#modalFlightAdd">
            <i class="fas fa-plus-circle mt-0 text-success"></i>
          </button>
          <%@ include file="/admin/flightAdd.jsp" %>
        </div>
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
                <span>Flight No.</span>
                <a href="${pageContext.request.contextPath}/flight?sortBy=flightNo"><i class="fa fa-fw fa-sort"></i></a>
              </th>
              <th>
                <span>Airline</span>
                <a href="${pageContext.request.contextPath}/flight?sortBy=flightCode"><i class="fa fa-fw fa-sort"></i></a>
              </th>
              <th>
                <span>From City</span>
                <a href="${pageContext.request.contextPath}/flight?sortBy=fromCity"><i class="fa fa-fw fa-sort"></i></a>
              </th>
              <th>
                <span>To City</span>
                <a href="${pageContext.request.contextPath}/flight?sortBy=toCity"><i class="fa fa-fw fa-sort"></i></a>
              </th>
              <th>
                <span>Depart Date/Time</span>
                <a href="${pageContext.request.contextPath}/flight?sortBy=departDt"><i class="fa fa-fw fa-sort"></i></a>
              </th>
              <th>
                <span>Arrive Date/Time</span>
                <a href="${pageContext.request.contextPath}/flight?sortBy=arriveDt"><i class="fa fa-fw fa-sort"></i></a>
              </th>
              <th>
                <span>Adult ($)</span>
                <a href="${pageContext.request.contextPath}/flight?sortBy=adultPrice"><i class="fa fa-fw fa-sort"></i></a>
              </th>
              <th>
                <span>Child ($)</span>
                <a href="${pageContext.request.contextPath}/flight?sortBy=childPrice"><i class="fa fa-fw fa-sort"></i></a>
              </th>
              <th>
                <span>Infant ($)</span>
                <a href="${pageContext.request.contextPath}/flight?sortBy=infantPrice"><i class="fa fa-fw fa-sort"></i></a>
              </th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="flight" items="${sessionScope.flightList}">
              <tr>
                <td>
                  <button type="button" class="btn btn-sm px-2" data-toggle="modal" data-target="#modalFlightUpdate">
                    <i class="fas fa-pencil-alt mt-0 text-primary"></i>
                  </button>
                  <button type="button" class="btn btn-sm px-2" data-toggle="modal" data-target="#modalFlightDelete">
                    <i class="far fa-trash-alt mt-0 text-danger"></i>
                  </button>
                </td>
                <td class="flightNo"><c:out value="${flight.getFlightNo()}"/></td>
                <td class="flightCode"><c:out value="${flight.getAirline().getFlightCode()}"/></td>
                <td class="srcLocation"><c:out value="${flight.getSource().getLocationCode()}"/></td>
                <td class="dstLocation"><c:out value="${flight.getDestination().getLocationCode()}"/></td>
                <td class="departDateTime"><c:out value="${flight.getDepartDateTime()}"/></td>
                <td class="arriveDateTime"><c:out value="${flight.getArriveDateTime()}"/></td>
                <td class="adultPrice"><st:CustomDecimalTag scale="2">${flight.getAdultPrice()}</st:CustomDecimalTag></td>
                <td class="childPrice"><st:CustomDecimalTag scale="2">${flight.getChildPrice()}</st:CustomDecimalTag></td>
                <td class="infantPrice"><st:CustomDecimalTag scale="2">${flight.getInfantPrice()}</st:CustomDecimalTag></td>
                <td class="flightId" style="display:none;"><c:out value="${flight.getFlightId()}"/></td>
                <td class="airlineCode" style="display:none;"><c:out value="${flight.getAirline().getAirlineCode()}"/></td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
        <%@ include file="/admin/flightUpdate.jsp" %>
        <%@ include file="/admin/flightDelete.jsp" %>
      </div>
    </div>
  </div>
</main>
<%@ include file="/admin/alert.jsp" %>
<%@ include file="/common/footer.html" %>