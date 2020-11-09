<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/header.jsp" %>
  <link href="${pageContext.request.contextPath}/css/navbar-top-fixed.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/css/sticky-footer-navbar.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="/admin/navbar.jsp" %>
<main role="main" class="flex-shrink-0">
  <div class="container">
    <div class="mt-5 card card-cascade narrower">
      <div class="py-2 mx-4 mb-3 d-flex justify-content-between align-items-center">
        <h4 class="text-primary">Airlines</h4>
        <div>
          <button type="button" class="btn btn-lg px-2" data-toggle="modal" data-target="#modalAirlineAdd">
            <i class="fas fa-plus-circle mt-0 text-success"></i>
          </button>
          <%@ include file="/admin/airlineAdd.jsp" %>
        </div>
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
                <span>Airline Code</span>
              </th>
              <th>
                <span>Flight Code</span>
              </th>
              <th>
                <span>Company Name</span>
              </th>
              <th>
                <span>Country</span>
              </th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="airline" items="${sessionScope.airlineList}">
              <tr>
                <td>
                  <button type="button" class="btn btn-sm px-2" data-toggle="modal" data-target="#modalAirlineUpdate">
                    <i class="fas fa-pencil-alt mt-0 text-primary"></i>
                  </button>
                  <button type="button" class="btn btn-sm px-2" data-toggle="modal" data-target="#modalAirlineDelete">
                    <i class="far fa-trash-alt mt-0 text-danger"></i>
                  </button>
                </td>
                <td class="airlineCode"><c:out value="${airline.getAirlineCode()}"/></td>
                <td class="flightCode"><c:out value="${airline.getFlightCode()}"/></td>
                <td class="companyName"><c:out value="${airline.getCompanyName()}"/></td>
                <td class="country"><c:out value="${airline.getCountry()}"/></td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
        <%@ include file="/admin/airlineUpdate.jsp" %>
        <%@ include file="/admin/airlineDelete.jsp" %>
      </div>
    </div>
  </div>
</main>
<%@ include file="/admin/alert.jsp" %>
<%@ include file="/common/footer.html" %>