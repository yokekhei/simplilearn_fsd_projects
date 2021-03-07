<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
  <link href="${pageContext.request.contextPath}/css/navbar-top.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="/WEB-INF/views/navbar.jsp" %>
<main role="main" class="flex-shrink-0">
  <div class="container">
    <div class="py-5">
      <h3>Courses</h3>
    </div>
    <div>
      <c:forEach var="courseList" items="${requestScope.courseList}">
        <div class="row mb-4">
          <c:forEach var="course" items="${courseList}">
            <div class="col-md-4">
              <a href="${pageContext.request.contextPath}/course?id=${course.getId()}"
                class="custom-card">
                <div class="card h-100">
                  <img class="card-img-top"
                    src="${pageContext.request.contextPath}/course/image?id=${course.getId()}"
                    alt="${course.getName()}">
                  <div class="card-body">
                    <h6 class="card-title">${course.getName()}</h6>
                  </div>
                </div>
              </a>
            </div>
          </c:forEach>
        </div>
      </c:forEach>    
    </div>
  </div>
</main>
<%@ include file="/WEB-INF/views/common/alert.jsp" %>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>