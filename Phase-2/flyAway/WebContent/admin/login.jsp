<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/common/header.jsp" %>
  <link href="${pageContext.request.contextPath}/css/signin.css" rel="stylesheet" type="text/css">
</head>
<body class="text-center">
  <form class="form-signin" action="${pageContext.request.contextPath}/login" method="post">
    <img class="mb-4" src="${pageContext.request.contextPath}/images/logo.jpg" alt="" width="99" height="86">
    <h1 class="h3 mb-3 font-weight-normal">Administrator Sign In</h1>
    <div class="alert alert-danger alert-dismissible">
      <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        Invalid Credentials!!
    </div>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="email" id="inputEmail" class="form-control" placeholder="Email address" name="email" required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <p class="mt-5 mb-3 text-muted">&copy; FlyAway 2020</p>
  </form>
 </body>
 </html>