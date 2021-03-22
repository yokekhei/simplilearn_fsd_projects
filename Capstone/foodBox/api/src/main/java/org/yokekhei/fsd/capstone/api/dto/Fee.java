package org.yokekhei.fsd.capstone.api.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Fee {

	@NotNull
	@NotEmpty(message = "Fee type must not empty")
	@Size(max = 50, message = "Fee type length must be less than or equal to 50")
	private String type;

	@NotNull
	@DecimalMin(value = "0.00", message = "Discount must be greater than or equal to 0.00")
	private BigDecimal value;

	public Fee() {
	}

	public Fee(String type, BigDecimal value) {
		this.type = type;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Fee [type=" + type + ", value=" + value + "]";
	}

}
