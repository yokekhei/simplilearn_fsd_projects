package org.yokekhei.fsd.p3.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Payment {
	
	private Long id;
	private Purchase purchaseOrder;
	
	@NotNull
	@NotEmpty(message = "Name on credit card must not empty")
	private String payorName;
	
	@NotNull
	@DecimalMin(value = "0.01", message = "Total paid price must be greater than 0.00")
	private BigDecimal totalPaid;
	
	private LocalDateTime createdDateTime;
	
	public Payment() {
	}

	public Payment(Purchase purchaseOrder, String payorName, BigDecimal totalPaid) {
		this.purchaseOrder = purchaseOrder;
		this.payorName = payorName;
		this.totalPaid = totalPaid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Purchase getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(Purchase purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public String getPayorName() {
		return payorName;
	}

	public void setPayorName(String payorName) {
		this.payorName = payorName;
	}

	public BigDecimal getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(BigDecimal totalPaid) {
		this.totalPaid = totalPaid;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", purchaseOrder=" + purchaseOrder + ", payorName=" + payorName + ", totalPaid=" + totalPaid
				+ ", createdDateTime=" + createdDateTime + "]";
	}
	
}
