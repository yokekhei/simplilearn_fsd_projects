<script type="text/javascript">
	$(document).ready(function() {
		$('#modalFlightUpdate').on('show.bs.modal', function (e) {
			var _button = $(e.relatedTarget);
			var _row = _button.parents("tr");
			var _flightId = _row.find(".flightId").text();
			var _flightNo = _row.find(".flightNo").text();
			var _airline = _row.find(".airlineCode").text();
			var _srcLocation = _row.find(".srcLocation").text();
			var _dstLocation = _row.find(".dstLocation").text();
			var _departDateTime = _row.find(".departDateTime").text();
			var _arriveDateTime = _row.find(".arriveDateTime").text();
			var _adultPrice = _row.find(".adultPrice").text();
			var _childPrice = _row.find(".childPrice").text();
			var _infantPrice = _row.find(".infantPrice").text();
			
			$(this).find(".flightId").val(_flightId);
			$(this).find(".flightNo").val(_flightNo);
			$(this).find(".airline").val(_airline);
			$(this).find(".srcLocation").val(_srcLocation);
			$(this).find(".dstLocation").val(_dstLocation);
			$(this).find(".departDate").val(_departDateTime.substring(0, 10));
			$(this).find(".departTime").val(_departDateTime.substring(11));
			$(this).find(".arriveDate").val(_arriveDateTime.substring(0, 10));
			$(this).find(".arriveTime").val(_arriveDateTime.substring(11));
			$(this).find(".adultPrice").val(_adultPrice);
			$(this).find(".childPrice").val(_childPrice);
			$(this).find(".infantPrice").val(_infantPrice);
		});
	});
</script>
<div class="modal fade" id="modalFlightUpdate">
  <div class="modal-dialog modal-lg modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Flight</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
        <form action="${pageContext.request.contextPath}/flight?action=update" method="post" class="needs-validation" novalidate>
          <div class="row" style="display:none;">
            <div class="col">
              <label for="flightId">Flight ID</label>
              <input type="text" class="form-control flightId" name="flightId" />
            </div>
          </div>
          <div class="row">
            <div class="col">
              <label for="flightNo">Flight No.</label>
              <input type="text" class="form-control flightNo" name="flightNo" placeholder="1234" required />
              <div class="invalid-feedback">
                Valid flight no. is required.
              </div>
            </div>
          </div>
          <div class="row">
             <div class="col">
              <label for="airline">Airline</label>
              <select name="airline" class="custom-select airline" required>
                <option value="">Choose...</option>
                <c:forEach var="airline" items="${sessionScope.airlineList}">
                  <option value="${airline.getAirlineCode()}"><c:out value="${airline.getFlightCode()} - ${airline.getCompanyName()}"/></option>
                </c:forEach>
              </select>
              <div class="invalid-feedback">
                Valid airline is required.
              </div>
            </div>
          </div>
          <div class="row">
             <div class="col">
              <label for="srcLocation">Source</label>
              <select name="srcLocation" class="custom-select srcLocation" required>
                <option value="">Choose...</option>
                <c:forEach var="place" items="${sessionScope.placeList}">
                  <option value="${place.getLocationCode()}"><c:out value="${place.getLocationCode()} - ${place.getCityName()}"/></option>
                </c:forEach>
              </select>
              <div class="invalid-feedback">
                Valid source is required.
              </div>
            </div>
          </div>
          <div class="row">
             <div class="col">
              <label for="dstLocation">Destination</label>
              <select name="dstLocation" class="custom-select dstLocation" required>
                <option value="">Choose...</option>
                <c:forEach var="place" items="${sessionScope.placeList}">
                  <option value="${place.getLocationCode()}"><c:out value="${place.getLocationCode()} - ${place.getCityName()}"/></option>
                </c:forEach>
              </select>
              <div class="invalid-feedback">
                Valid destination is required.
              </div>
            </div>
          </div>
          <div class="row">
             <div class="col-md-6">
              <label for="departDate">Depart Date</label>
              <input id="departDateUpdate" name="departDate" class="departDate" required />
              <div class="invalid-feedback">
                Valid depart date is required.
              </div>
              <script>
              	$('#departDateUpdate').datepicker({
              		format: 'dd-mm-yyyy',
              		uiLibrary: 'bootstrap4'
              	});
              </script>
            </div>
            <div class="col-md-6">
              <label for="departTime">Depart Time</label>
              <input id="departTimeUpdate" name="departTime" class="departTime" required />
              <div class="invalid-feedback">
                Valid depart time is required.
              </div>
              <script>
              	$('#departTimeUpdate').timepicker({
              		format: 'HH:MM',
              		uiLibrary: 'bootstrap4'
                });
              </script>
            </div>
          </div>
          <div class="row">
             <div class="col-md-6">
              <label for="arriveDate">Arrive Date</label>
              <input id="arriveDateUpdate" name="arriveDate" class="arriveDate" required />
              <div class="invalid-feedback">
                Valid arrive date is required.
              </div>
              <script>
              	$('#arriveDateUpdate').datepicker({
              		format: 'dd-mm-yyyy',
              		uiLibrary: 'bootstrap4'
              	});
              </script>
            </div>
            <div class="col-md-6">
              <label for="arriveTime">Arrive Time</label>
              <input id="arriveTimeUpdate" name="arriveTime" class="arriveTime" required />
              <div class="invalid-feedback">
                Valid arrive time is required.
              </div>
              <script>
              	$('#arriveTimeUpdate').timepicker({
              		format: 'HH:MM',
              		uiLibrary: 'bootstrap4'
                });
              </script>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <label for="adultPrice">Adult Price</label>
              <input type="text" class="form-control adultPrice" name="adultPrice" placeholder="0.00" required />
              <div class="invalid-feedback">
                Valid adult price is required.
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <label for="childPrice">Child Price</label>
              <input type="text" class="form-control childPrice" name="childPrice" placeholder="0.00" required />
              <div class="invalid-feedback">
                Valid child price is required.
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <label for="infantPrice">Infant Price</label>
              <input type="text" class="form-control infantPrice" name="infantPrice" placeholder="0.00" required />
              <div class="invalid-feedback">
                Valid infant price is required.
              </div>
            </div>
          </div>
          <hr class="mb-4">
          <button class="btn btn-primary btn-lg btn-block" type="submit">Update</button>
        </form>
      </div>
    </div>
  </div>
</div>