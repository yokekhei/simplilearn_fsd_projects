<div class="modal fade" id="modalAirlineAdd">
  <div class="modal-dialog modal-lg modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Airline</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
        <form action="${pageContext.request.contextPath}/airline?action=add" method="post" class="needs-validation" novalidate>
          <div class="row">
            <div class="col">
              <label for="airlineCode">Airline Code</label>
              <input type="text" class="form-control" name="airlineCode" placeholder="" value="" required>
              <div class="invalid-feedback">
                Valid airline code is required.
              </div>
            </div>
          </div>
          <div class="row">
             <div class="col">
              <label for="flightCode">Flight Code</label>
              <input type="text" class="form-control" name="flightCode" placeholder="" value="" required>
              <div class="invalid-feedback">
                Valid flight code is required.
              </div>
            </div>
          </div>
          <div class="row">
             <div class="col">
              <label for="companyName">Company Name</label>
              <input type="text" class="form-control" name="companyName" placeholder="" value="" required>
              <div class="invalid-feedback">
                Valid company name is required.
              </div>
            </div>
          </div>
          <div class="row">
             <div class="col">
              <label for="country">Country</label>
              <input type="text" class="form-control" name="country" placeholder="" value="" required>
              <div class="invalid-feedback">
                Valid country is required.
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