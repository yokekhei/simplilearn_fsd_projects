package org.yokekhei.fsd.p3.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bag {

	private List<BagItem> items = new ArrayList<>();
	private BigDecimal totalPrice;
	
	public Bag() {
		totalPrice = new BigDecimal(0.0);
	}

	public List<BagItem> getItems() {
		return items;
	}

	public void setItems(List<BagItem> items) {
		this.items = items;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public void resetTotalPrice() {
		totalPrice = new BigDecimal(0.0);
	}
	
	public Integer getTotalQuantity() {
		Integer total = 0;
		
		for (BagItem item : items) {
			total += item.getQuantity();
		}
		
		return total;
	}

	@Override
	public String toString() {
		return "Bag [items=" + items + ", totalPrice=" + totalPrice + "]";
	}
	
}
