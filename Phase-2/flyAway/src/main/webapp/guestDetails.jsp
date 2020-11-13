<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/header.jsp" %>
  <link href="${pageContext.request.contextPath}/css/multi-page-form.css" rel="stylesheet" type="text/css">
</head>
<body>
<main role="main" class="flex-shrink-0">
  <div class="container">
    <div class="py-4">
      <a href="${pageContext.request.contextPath}/index.jsp">
        <img class="mb-4 text-center float-left" src="${pageContext.request.contextPath}/images/logo.jpg" alt="" width="99" height="86">
      </a>
      <h2>Guest Details</h2>
      <hr class="mb-5">
    </div>
    <div class="px-4">
      <form id="guestDetailsForm" action="${pageContext.request.contextPath}/guest?action=register&id=${requestScope.flightId}&adult=${requestScope.adultNo}&child=${requestScope.childNo}&infant=${requestScope.infantNo}" method="post">
        <div class="tab">
          <h4>Contact details</h4>
          <div class="row">
            <div class="col-md-6">
              <input type="text" class="form-control" name="firstName" placeholder="Given name" value="" autocomplete="off" required />
            </div>
            <div class="col-md-6">
              <input type="text" class="form-control" name="lastName" placeholder="Family name/Surname" value="" autocomplete="off" required />
            </div>
          </div>
          <div class="row py-3">
            <div class="col-md-6">
              <input type="email" class="form-control" name="email" placeholder="Email address" value="" autocomplete="off" required />
            </div>
            <div class="col-md-6">
              <input type="tel" class="form-control" name="phone" placeholder="+00-123456789" value=""
                pattern="[\+]\d{2,}[\-]\d{1,}" title='Phone Number (Format: +00-123456789' autocomplete="off" required />
              <small class="w-100 text-secondary">Format: +00-123456789</small>
            </div>
          </div>
        </div>
        <c:forEach var = "i" begin = "1" end = "${requestScope.adultNo}">
          <div class="tab">
            <h4>Adult <c:out value = "${i}"/></h4>
            <div class="row">
              <div class="col-md-6">
                <input type="text" class="form-control" name="adultFirstName${i}" placeholder="Given name" value="" autocomplete="off" required />
              </div>
              <div class="col-md-6">
                <input type="text" class="form-control" name="adultLastName${i}" placeholder="Family name/Surname" value="" autocomplete="off" required />
              </div>
            </div>
            <div class="row py-3">
              <div class="col-md-6">
                <label for="adultDob${i}">Date of Birth</label>
                <input id="adultDob${i}" name="adultDob${i}" autocomplete="off" placeholder="DD-MM-YYYY" required />
                <script>
              	  $('#adultDob${i}').datepicker({
              		  format: 'dd-mm-yyyy',
              		  uiLibrary: 'bootstrap4',
              		  disableDates: function (date) {
              			  const currentDate = new Date();
              			  return currentDate.getFullYear() - date.getFullYear() >= 12;
              		  }
              	  });
                </script>
              </div>
              <div class="col-md-6">
                <div class="d-block my-3">
                  <div class="custom-control custom-radio">
                    <input type="radio" class="custom-control-input" id="adultMale${i}" name="adultGender${i}" value="M" checked required>
                    <label class="custom-control-label" for="adultMale${i}">Male</label>
                  </div>
                  <div class="custom-control custom-radio">
                    <input type="radio" class="custom-control-input" id="adultFemale${i}" name="adultGender${i}" value="F" required>
                    <label class="custom-control-label" for="adultFemale${i}">Female</label>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </c:forEach>
        <c:forEach var = "i" begin = "1" end = "${requestScope.childNo}">
          <div class="tab">
            <h4>Child <c:out value = "${i}"/></h4>
            <div class="row">
              <div class="col-md-6">
                <input type="text" class="form-control" name="childFirstName${i}" placeholder="Given name" value="" autocomplete="off" required />
              </div>
              <div class="col-md-6">
                <input type="text" class="form-control" name="childLastName${i}" placeholder="Family name/Surname" value="" autocomplete="off" required />
              </div>
            </div>
            <div class="row py-3">
              <div class="col-md-6">
                <label for="childDob${i}">Date of Birth</label>
                <input id="childDob${i}" name="childDob${i}" autocomplete="off" placeholder="DD-MM-YYYY" required />
                <script>
              	  $('#childDob${i}').datepicker({
              		  format: 'dd-mm-yyyy',
              		  uiLibrary: 'bootstrap4',
              		  disableDates: function (date) {
              			  const currentDate = new Date();
              			  return currentDate.getFullYear() - date.getFullYear() >= 2 &&
              			  	currentDate.getFullYear() - date.getFullYear() < 12;
              		  }
              	  });
                </script>
              </div>
              <div class="col-md-6">
                <div class="d-block my-3">
                  <div class="custom-control custom-radio">
                    <input type="radio" class="custom-control-input" id="childMale${i}" name="childGender${i}" value="M" checked required>
                    <label class="custom-control-label" for="childMale${i}">Male</label>
                  </div>
                  <div class="custom-control custom-radio">
                    <input type="radio" class="custom-control-input" id="childFemale${i}" name="childGender${i}" value="F" required>
                    <label class="custom-control-label" for="childFemale${i}">Female</label>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </c:forEach>
        <c:forEach var = "i" begin = "1" end = "${requestScope.infantNo}">
          <div class="tab">
            <h4>Infant <c:out value = "${i}"/></h4>
            <div class="row">
              <div class="col-md-6">
                <input type="text" class="form-control" name="infantFirstName${i}" placeholder="Given name" value="" autocomplete="off" required />
              </div>
              <div class="col-md-6">
                <input type="text" class="form-control" name="infantLastName${i}" placeholder="Family name/Surname" value="" autocomplete="off" required />
              </div>
            </div>
            <div class="row py-3">
              <div class="col-md-6">
                <label for="infantDob${i}">Date of Birth</label>
                <input id="infantDob${i}" name="infantDob${i}" autocomplete="off" placeholder="DD-MM-YYYY" required />
                <script>
              	  $('#infantDob${i}').datepicker({
              		  format: 'dd-mm-yyyy',
              		  uiLibrary: 'bootstrap4',
              		  disableDates: function (date) {
              			  const currentDate = new Date();
              			  return currentDate.getFullYear() - date.getFullYear() < 2;
              		  }
              	  });
                </script>
              </div>
              <div class="col-md-6">
                <div class="d-block my-3">
                  <div class="custom-control custom-radio">
                    <input type="radio" class="custom-control-input" id="infantMale${i}" name="infantGender${i}" value="M" checked required>
                    <label class="custom-control-label" for="infantMale${i}">Male</label>
                  </div>
                  <div class="custom-control custom-radio">
                    <input type="radio" class="custom-control-input" id="infantFemale${i}" name="infantGender${i}" value="F" required>
                    <label class="custom-control-label" for="infantFemale${i}">Female</label>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </c:forEach>
        <hr class="mb-4">
        <div class="row">
          <div class="col-md-6">
            <button type="button" class="btn btn-secondary btn-lg btn-block" id="prevBtn" onclick="nextPrev(-1)">Previous</button>
          </div>
          <div class="col-md-6">
            <button type="button" class="btn btn-success btn-lg btn-block" id="nextBtn" onclick="nextPrev(1)">Next</button>
          </div>
        </div>
        <div class="text-center py-5">
          <c:forEach var = "i" begin = "1" end = "${requestScope.totalPerson + 1}">
            <span class="step"></span>
          </c:forEach>
        </div>
      </form>
    </div>
  </div>
</main>
<script>
var currentTab = 0;
showTab(currentTab);

function showTab(n) {
	var x = document.getElementsByClassName("tab");
	x[n].style.display = "block";
	
	if (n == 0) {
		document.getElementById("prevBtn").style.display = "none";
	} else {
		document.getElementById("prevBtn").style.display = "inline";
	}
	
	if (n == (x.length - 1)) {
		document.getElementById("nextBtn").innerHTML = "Submit";
	} else {
		document.getElementById("nextBtn").innerHTML = "Next";
	}
	
	fixStepIndicator(n)
}

function nextPrev(n) {
	var x = document.getElementsByClassName("tab");
	
	if (n == 1 && !validateForm()) return false;
	
	x[currentTab].style.display = "none";
	currentTab = currentTab + n;
	
	if (currentTab >= x.length) {
		document.getElementById("guestDetailsForm").submit();
		return false;
	}
	
	showTab(currentTab);
}

function validateForm() {
	var x, y, i, valid = true;
	x = document.getElementsByClassName("tab");
	y = x[currentTab].getElementsByTagName("input");
	
	for (i = 0; i < y.length; i++) {
		if (y[i].value == "") {
			y[i].className += " invalid";
			valid = false;
		}
		
		if (y[i].checkValidity() === false) {
			y[i].className += " invalid";
			valid = false;
		}
	}
	
	if (valid) {
		document.getElementsByClassName("step")[currentTab].className += " finish";
	}
	
	return valid;
}

function fixStepIndicator(n) {
	var i, x = document.getElementsByClassName("step");
	
	for (i = 0; i < x.length; i++) {
		x[i].className = x[i].className.replace(" active", "");
	}
	
	x[n].className += " active";
}
</script>
<%@ include file="/common/footer.html" %>