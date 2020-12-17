package org.yokekhei.fsd.p3.dto;

import java.math.BigDecimal;

public class PurchaseItem {

	private Long Id;
	private Product product;
	private Integer quantity;
	private BigDecimal totalPrice;
	
	public PurchaseItem() {
	}

	public PurchaseItem(Product product, Integer quantity, BigDecimal totalPrice) {
		this.product = product;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "PurchaseItem [Id=" + Id + ", product=" + product + ", quantity=" + quantity + ", totalPrice="
				+ totalPrice + "]";
	}
	
}
