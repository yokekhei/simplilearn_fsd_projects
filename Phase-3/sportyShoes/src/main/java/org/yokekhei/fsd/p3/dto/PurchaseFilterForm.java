package org.yokekhei.fsd.p3.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PurchaseFilterForm {
	
	private Long categoryId;
	
	@NotNull
	@NotEmpty(message = "From purchase date must not empty")
	private String fromDate;
	
	@NotNull
	@NotEmpty(message = "To purchase date must not empty")
	private String toDate;
	
	public PurchaseFilterForm() {
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "PurchaseFilterForm [categoryId=" + categoryId + ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}
	
}
