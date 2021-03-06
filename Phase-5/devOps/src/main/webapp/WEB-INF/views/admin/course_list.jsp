<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
  <link href="${pageContext.request.contextPath}/css/navbar-top.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="/WEB-INF/views/admin/navbar.jsp" %>
<main role="main" class="flex-shrink-0">
  <div class="container">
    <div class="mt-5 card card-cascade narrower">
      <div class="py-2 mx-4 mb-3 d-flex justify-content-between align-items-center">
        <h3 class="text-primary">Courses</h3>
        <div>
          <button type="button" class="btn btn-lg px-2" type="button"
            onclick="location.href='${pageContext.request.contextPath}/admin/course/add'">
            <i class="fas fa-plus-circle mt-0 text-success"></i>
          </button>
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
                <span>Name</span>
              </th>
              <th>
                <span>Date Created</span>
              </th>
              <th>
                <span>Enabled/Disabled</span>
              </th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="course" items="${sessionScope.courseList}">
              <tr>
                <td>
                  <button type="button" class="btn btn-sm px-2"
                    onclick="location.href='${pageContext.request.contextPath}/admin/course/update/${course.getId()}'">
                    <i class="fas fa-pencil-alt mt-0 text-primary"></i>
                  </button>
                  <button type="button" class="btn btn-sm px-2">
                    <i class="far fa-trash-alt mt-0 text-danger"></i>
                  </button>
                </td>
                <td><c:out value="${course.getName()}"/></td>
                <td><c:out value="${course.getCreatedDateString()}"/></td>
                <td>
                  <div class="custom-control custom-switch">
                    <input class="custom-control-input" type="checkbox" id="courseEnabled${course.getId()}"
                      ${course.getEnabled() ? "checked" : ""}
                      onchange="location.href='${pageContext.request.contextPath}/admin/course/enabled" />
                    <label class="custom-control-label" for="courseEnabled${course.getId()}">
                      ${course.getEnabled() ? 'Enabled' : 'Disabled'}
                    </label>
                  </div>
                </td>
                <td style="display:none;"><c:out value="${course.getId()}"/></td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</main>
<%@ include file="/WEB-INF/views/common/alert.jsp" %>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>