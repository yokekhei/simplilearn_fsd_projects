<div class="modal fade" id="modalPlaceAdd">
  <div class="modal-dialog modal-lg modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Place</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
        <form action="${pageContext.request.contextPath}/place?action=add" method="post" class="needs-validation" novalidate>
          <div class="row">
            <div class="col">
              <label for="locationCode">Location Code</label>
              <input type="text" class="form-control" name="locationCode" placeholder="ABC" value="" required />
              <div class="invalid-feedback">
                Valid location code is required.
              </div>
            </div>
          </div>
          <div class="row">
             <div class="col">
              <label for="locationName">Location Name</label>
              <input type="text" class="form-control" name="locationName" placeholder="" value="" required />
              <div class="invalid-feedback">
                Valid location name is required.
              </div>
            </div>
          </div>
          <div class="row">
             <div class="col">
              <label for="cityName">City Name</label>
              <input type="text" class="form-control" name="cityName" placeholder="" value="" required />
              <div class="invalid-feedback">
                Valid city name is required.
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