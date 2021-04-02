package org.yokekhei.fsd.capstone.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class Order {

	private Long id;

	@NotNull
	private String chargeId;

	@NotNull
	private String userId;

	private List<OrderItem> items = new ArrayList<>();

	@NotNull
	@DecimalMin(value = "0.01", message = "Order price must be greater than 0.00")
	private BigDecimal price;

	private BigDecimal discount;
	private BigDecimal deliveryFee;
	private LocalDateTime createdDateTime;

	public Order() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChargeId() {
		return chargeId;
	}

	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	
	public void add(OrderItem item) {
		this.items.add(item);
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

	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", chargeId=" + chargeId + ", userId=" + userId + ", items=" + items + ", price="
				+ price + ", discount=" + discount + ", deliveryFee=" + deliveryFee + ", createdDateTime="
				+ createdDateTime + "]";
	}

}
