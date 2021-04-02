package org.yokekhei.fsd.capstone.api.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderItem {

	private Long id;

	@NotNull
	private Food food;

	@NotNull
	@Min(value = 1, message = "Order item quantity must be at least 1")
	private Integer quantity;

	@NotNull
	@DecimalMin(value = "0.01", message = "Order item price must be greater than 0.00")
	private BigDecimal price;

	private BigDecimal discount;

	public OrderItem() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", food=" + food + ", quantity=" + quantity + ", price=" + price + ", discount="
				+ discount + "]";
	}

}
