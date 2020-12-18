package org.yokekhei.fsd.p3.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserSearchForm {
	
	private String firstName;
	
	@NotNull
	@NotEmpty(message = "From date must not empty")
	private String fromDate;
	
	@NotNull
	@NotEmpty(message = "To date must not empty")
	private String toDate;
	
	public UserSearchForm() {
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
		return "UserSearchForm [firstName=" + firstName + ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}
	
}
