<%@page import="org.yokekhei.fsd.p2.Common"%>
<%@page import="org.yokekhei.fsd.p2.bean.Place"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.yokekhei.fsd.p2.service.AdminService"%>
<%@page import="org.yokekhei.fsd.p2.service.AdminServiceImpl"%>
<%@page import="org.yokekhei.fsd.p2.service.FlyAwayServiceException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/header.jsp" %>
</head>
<%!
List<Place> places;
%>
<%
try {
	AdminService service = new AdminServiceImpl(
			(SessionFactory) (getServletContext().getAttribute("hbmSessionFactory")));
	places = service.getAllPlaces();
} catch (FlyAwayServiceException e) {
	Common.viewError(e.getMessage(), request, response);
}
%>
<main role="main" class="flex-shrink-0">
  <div class="container">
    <div class="py-4">
      <a href="${pageContext.request.contextPath}/index.jsp">
        <img class="mb-4 text-center float-left" src="${pageContext.request.contextPath}/images/logo.jpg" alt="" width="99" height="86">
      </a>
      <h2>Search Flights</h2>
      <hr class="mb-4">
    </div>
    <div class="py-4">
      <c:if test="${requestScope.searchFlightStatus == 'fail'}">
        <div class="alert alert-danger alert-dismissible">
          <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
          Search flights failed. Please try again.
        </div>
        <c:remove var="searchFlightStatus" scope="request" />
      </c:if>
      <c:if test="${requestScope.searchFlightStatus == 'sameLocation'}">
        <div class="alert alert-warning alert-dismissible">
          <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
          From city cannot same as To city.
        </div>
        <c:remove var="searchFlightStatus" scope="request" />
      </c:if>
      <c:if test="${requestScope.searchFlightStatus == 'empty'}">
        <div class="alert alert-info alert-dismissible">
          <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
          Sorry, no flight available.
        </div>
        <c:remove var="searchFlightStatus" scope="request" />
      </c:if>
      <form action="${pageContext.request.contextPath}/guest?action=search" method="post" class="needs-validation" novalidate>
        <div class="row">
          <div class="col-md-4">
            <label for="srcLocation">From</label>
            <select name="srcLocation" class="custom-select" required>
              <option value="">Choose...</option>
              <%
                for (Place place : places) {
              %>
              <option value="<%= place.getLocationCode() %>"><% out.println(place.getCityName() + " (" + place.getLocationCode() + ")"); %></option>
              <%
              }
              %>
            </select>
            <div class="invalid-feedback">
              Valid city is required.
            </div>
          </div>
          <div class="col-md-4">
            <label for="dstLocation">To</label>
            <select name="dstLocation" class="custom-select" required>
              <option value="">Choose...</option>
              <%
                for (Place place : places) {
              %>
              <option value="<%= place.getLocationCode() %>"><% out.println(place.getCityName() + " (" + place.getLocationCode() + ")"); %></option>
              <%
              }
              %>
            </select>
            <div class="invalid-feedback">
              Valid city is required.
            </div>
          </div>
          <div class="col-md-4">
            <label for="departDate">Depart Date</label>
            <input id="departDate" name="departDate" autocomplete="off" required />
            <div class="invalid-feedback">
              Valid depart date is required.
            </div>
            <script>
            	$('#departDate').datepicker({
            		format: 'dd-mm-yyyy',
            		uiLibrary: 'bootstrap4',
            		disableDates: function (date) {
            			const currentDate = new Date().setHours(0,0,0,0);
            			return date.setHours(0,0,0,0) >= currentDate ? true : false;
            		}
            	});
            </script>
          </div>
        </div>
        <div class="row">
          <div class="col-md-4">
            <label for="adultNo">Adult</label>
            <input type="number" class="form-control" name="adultNo" placeholder="1" value="1" min="1" />
            <small class="w-100 text-secondary">12 years and above</small>
            <div class="invalid-feedback">
              Valid adult number is required.
            </div>
          </div>
          <div class="col-md-4">
            <label for="childNo">Child</label>
            <input type="number" class="form-control" name="childNo" placeholder="0" value="0" min="0" />
            <small class="w-100 text-secondary">2 to 11 years</small>
            <div class="invalid-feedback">
              Valid child number is required.
            </div>
          </div>
          <div class="col-md-4">
            <label for="infantNo">Infant</label>
            <input type="number" class="form-control" name="infantNo" placeholder="0" value="0" min="0" />
            <small class="w-100 text-secondary">Under 2 years</small>
            <div class="invalid-feedback">
              Valid infant number is required.
            </div>
          </div>
        </div>
        <hr class="mb-4">
        <button class="btn btn-primary btn-lg btn-block" type="submit">Search</button>
      </form>
    </div>
  </div>
</main>
<%@ include file="/common/footer.html" %>