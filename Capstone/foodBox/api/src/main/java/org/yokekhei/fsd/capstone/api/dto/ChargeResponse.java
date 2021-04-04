package org.yokekhei.fsd.capstone.api.dto;

public class ChargeResponse {

	private String id;
	private String status;
	private String balanceTransaction;

	public ChargeResponse() {
	}

	public ChargeResponse(String id, String status, String balanceTransaction) {
		this.id = id;
		this.status = status;
		this.balanceTransaction = balanceTransaction;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBalanceTransaction() {
		return balanceTransaction;
	}

	public void setBalanceTransaction(String balanceTransaction) {
		this.balanceTransaction = balanceTransaction;
	}

	@Override
	public String toString() {
		return "ChargeResponse [id=" + id + ", status=" + status + ", balanceTransaction=" + balanceTransaction + "]";
	}

}
