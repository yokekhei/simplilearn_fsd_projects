package org.yokekhei.fsd.capstone.api.dto;

import java.math.BigDecimal;

public class ChargeRequest {

	private String description;
	private BigDecimal amount;
	private String currency;
	private String stripeEmail;
	private String stripeToken;

	public ChargeRequest() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getStripeEmail() {
		return stripeEmail;
	}

	public void setStripeEmail(String stripeEmail) {
		this.stripeEmail = stripeEmail;
	}

	public String getStripeToken() {
		return stripeToken;
	}

	public void setStripeToken(String stripeToken) {
		this.stripeToken = stripeToken;
	}

	@Override
	public String toString() {
		return "ChargeRequest [description=" + description + ", amount=" + amount + ", currency=" + currency
				+ ", stripeEmail=" + stripeEmail + ", stripeToken=" + stripeToken + "]";
	}

}
