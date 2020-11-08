<script type="text/javascript">
	$(document).ready(function() {
		$('#modalAirlineDelete').on('show.bs.modal', function (e) {
			var _button = $(e.relatedTarget);
			var _row = _button.parents("tr");
			var _airlineCode = _row.find(".airlineCode").text();
			var _flightCode = _row.find(".flightCode").text();
			var _companyName = _row.find(".companyName").text();
			var _country = _row.find(".country").text();
			
			$(this).find(".airlineCode").val(_airlineCode);
			$(this).find(".flightCode").val(_flightCode);
			$(this).find(".companyName").val(_companyName);
			$(this).find(".country").val(_country);
		});
	});
</script>
<div class="modal fade" id="modalAirlineDelete">
  <div class="modal-dialog modal-lg modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Airline</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
        <form action="${pageContext.request.contextPath}/airline?action=delete" method="post">
          <div class="row">
            <div class="col">
              <label for="airlineCode">Airline Code</label>
              <input type="text" class="form-control airlineCode" name="airlineCode" placeholder="" required readonly />
              <div class="invalid-feedback">
                Valid airline code is required.
              </div>
            </div>
          </div>
          <div class="row">
             <div class="col">
              <label for="flightCode">Flight Code</label>
              <input type="text" class="form-control flightCode" name="flightCode" placeholder="" required readonly />
              <div class="invalid-feedback">
                Valid flight code is required.
              </div>
            </div>
          </div>
          <div class="row">
             <div class="col">
              <label for="companyName">Company Name</label>
              <input type="text" class="form-control companyName" name="companyName" placeholder="" required readonly />
              <div class="invalid-feedback">
                Valid company name is required.
              </div>
            </div>
          </div>
          <div class="row">
             <div class="col">
              <label for="country">Country</label>
              <input type="text" class="form-control country" name="country" placeholder="" required readonly />
              <div class="invalid-feedback">
                Valid country is required.
              </div>
            </div>
          </div>
          <hr class="mb-4">
          <button class="btn btn-danger btn-lg btn-block" type="submit">Delete</button>
        </form>
      </div>
    </div>
  </div>
</div>