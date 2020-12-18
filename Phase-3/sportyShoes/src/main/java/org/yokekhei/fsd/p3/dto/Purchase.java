package org.yokekhei.fsd.p3.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.yokekhei.fsd.p3.Common;

public class Purchase {

	private Long id;
	private User user;
	private List<PurchaseItem> items = new ArrayList<>();
	private BigDecimal totalPrice;
	private LocalDateTime createdDateTime;
	
	public Purchase() {
	}
	
	public Purchase(User user, List<PurchaseItem> items, BigDecimal totalPrice) {
		this.user = user;
		this.items = items;
		this.totalPrice = totalPrice;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
	public String getCreatedDateString() {
		if (createdDateTime != null) {
			return Common.toLocalDateString(createdDateTime);
		}
		
		return "";
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", user=" + user + ", items=" + items + ", totalPrice=" + totalPrice
				+ ", createdDateTime=" + createdDateTime + "]";
	}
	
}
