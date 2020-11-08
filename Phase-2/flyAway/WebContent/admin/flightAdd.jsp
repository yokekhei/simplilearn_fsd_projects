<div class="modal fade" id="modalFlightAdd">
  <div class="modal-dialog modal-lg modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Flight</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
        <form action="${pageContext.request.contextPath}/flight?action=add" method="post" class="needs-validation" novalidate>
          <div class="row">
            <div class="col">
              <label for="flightNo">Flight No.</label>
              <input type="text" class="form-control" name="flightNo" placeholder="1234" value="" required />
              <div class="invalid-feedback">
                Valid flight no. is required.
              </div>
            </div>
          </div>
          <div class="row">
             <div class="col">
              <label for="airline">Airline</label>
              <select name="airline" class="custom-select" required>
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
              <select name="srcLocation" class="custom-select" required>
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
              <select name="dstLocation" class="custom-select" required>
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
             <div class="col">
              <label for="departDate">Depart Date</label>
              <input id="departDateAdd" name="departDate" required />
              <div class="invalid-feedback">
                Valid depart date is required.
              </div>
              <script>
              	$('#departDateAdd').datepicker({
              		format: 'dd-mm-yyyy',
              		uiLibrary: 'bootstrap4'
              	});
              </script>
            </div>
          </div>
          <div class="row">
             <div class="col">
              <label for="departTime">Depart Time</label>
              <input id="departTimeAdd" name="departTime" required />
              <div class="invalid-feedback">
                Valid depart time is required.
              </div>
              <script>
              	$('#departTimeAdd').timepicker({
              		format: 'HH:MM',
              		uiLibrary: 'bootstrap4'
                });
              </script>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <label for="adultPrice">Adult Price</label>
              <input type="text" class="form-control" name="adultPrice" placeholder="0.00" value="" required />
              <div class="invalid-feedback">
                Valid adult price is required.
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <label for="childPrice">Child Price</label>
              <input type="text" class="form-control" name="childPrice" placeholder="0.00" value="" required />
              <div class="invalid-feedback">
                Valid child price is required.
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <label for="infantPrice">Infant Price</label>
              <input type="text" class="form-control" name="infantPrice" placeholder="0.00" value="" required />
              <div class="invalid-feedback">
                Valid infant price is required.
              </div>
            </div>
          </div>
          <hr class="mb-4">
          <button class="btn btn-success btn-lg btn-block" type="submit">Add</button>
        </form>
      </div>
    </div>
  </div>
</div>