package org.yokekhei.fsd.capstone.api.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Offer {

	private Long id;

	@NotNull
	@NotEmpty(message = "Offer name must not empty")
	@Size(max = 30, message = "Offer name length must be less than or equal to 30")
	private String name;

	@NotNull
	@NotEmpty(message = "Discount type must not empty")
	@Size(max = 3, message = "Discount type length must be less than or equal to 3")
	private String discountType;

	@DecimalMin(value = "0.01", message = "Discount must be greater than 0.00")
	private BigDecimal discount;

	public Offer() {
	}

	public Offer(Long id) {
		this.id = id;
	}

	public Offer(Long id, String name, String discountType, BigDecimal discount) {
		this.id = id;
		this.name = name;
		this.discountType = discountType;
		this.discount = discount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", name=" + name + ", discountType=" + discountType + ", discount=" + discount + "]";
	}

}
