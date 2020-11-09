<script type="text/javascript">
	$(document).ready(function() {
		$('#modalPlaceUpdate').on('show.bs.modal', function (e) {
			var _button = $(e.relatedTarget);
			var _row = _button.parents("tr");
			var _locationCode = _row.find(".locationCode").text();
			var _locationName = _row.find(".locationName").text();
			var _cityName = _row.find(".cityName").text();
			
			$(this).find(".locationCode").val(_locationCode);
			$(this).find(".locationName").val(_locationName);
			$(this).find(".cityName").val(_cityName);
		});
	});
</script>
<div class="modal fade" id="modalPlaceUpdate">
  <div class="modal-dialog modal-lg modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Place</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
        <form action="${pageContext.request.contextPath}/place?action=update" method="post" class="needs-validation" novalidate>
          <div class="row">
            <div class="col">
              <label for="locationCode">Location Code</label>
              <input type="text" class="form-control locationCode" name="locationCode" placeholder="" required readonly />
              <div class="invalid-feedback">
                Valid location code is required.
              </div>
            </div>
          </div>
          <div class="row">
             <div class="col">
              <label for="locationName">Location Name</label>
              <input type="text" class="form-control locationName" name="locationName" placeholder="" required />
              <div class="invalid-feedback">
                Valid location name is required.
              </div>
            </div>
          </div>
          <div class="row">
             <div class="col">
              <label for="cityName">City Name</label>
              <input type="text" class="form-control cityName" name="cityName" placeholder="" required />
              <div class="invalid-feedback">
                Valid city name is required.
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