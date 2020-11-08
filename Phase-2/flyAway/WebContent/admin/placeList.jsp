<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/header.jsp" %>
  <link href="${pageContext.request.contextPath}/css/navbar-top-fixed.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/css/sticky-footer-navbar.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="/admin/navbar.jsp" %>
<main role="main" class="flex-shrink-0">
  <div class="container">
    <div class="mt-5 card card-cascade narrower">
      <div class="py-2 mx-4 mb-3 d-flex justify-content-between align-items-center">
        <h4 class="text-primary">Places</h4>
        <div>
          <button type="button" class="btn btn-lg px-2" data-toggle="modal" data-target="#modalPlaceAdd">
            <i class="fas fa-plus-circle mt-0 text-success"></i>
          </button>
          <%@ include file="/admin/placeAdd.jsp" %>
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
                <span>Location Code</span>
              </th>
              <th>
                <span>Location Name</span>
              </th>
              <th>
                <span>City Name</span>
              </th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="place" items="${sessionScope.placeList}">
              <tr>
                <td>
                  <button type="button" class="btn btn-sm px-2" data-toggle="modal" data-target="#modalPlaceUpdate">
                    <i class="fas fa-pencil-alt mt-0 text-primary"></i>
                  </button>
                  <button type="button" class="btn btn-sm px-2" data-toggle="modal" data-target="#modalPlaceDelete">
                    <i class="far fa-trash-alt mt-0 text-danger"></i>
                  </button>
                </td>
                <td class="locationCode"><c:out value="${place.getLocationCode()}"/></td>
                <td class="locationName"><c:out value="${place.getLocationName()}"/></td>
                <td class="cityName"><c:out value="${place.getCityName()}"/></td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
        <%@ include file="/admin/placeUpdate.jsp" %>
        <%@ include file="/admin/placeDelete.jsp" %>
      </div>
    </div>
  </div>
</main>
<%@ include file="/admin/alert.jsp" %>
<%@ include file="/footer.html" %>