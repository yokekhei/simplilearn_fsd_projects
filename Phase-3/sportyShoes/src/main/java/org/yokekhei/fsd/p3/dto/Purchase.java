package org.yokekhei.fsd.p3.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Purchase {

	private Long id;
	private List<PurchaseItem> items = new ArrayList<>();
	private BigDecimal totalPrice;
	private LocalDateTime createdDateTime;
	
	public Purchase() {
	}

	public Purchase(List<PurchaseItem> items, BigDecimal totalPrice, LocalDateTime createdDateTime) {
		this.items = items;
		this.totalPrice = totalPrice;
		this.createdDateTime = createdDateTime;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<PurchaseItem> getItems() {
		return items;
	}

	public void setItems(List<PurchaseItem> items) {
		this.items = items;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", items=" + items + ", totalPrice=" + totalPrice + ", createdDateTime="
				+ createdDateTime + "]";
	}
	
}
