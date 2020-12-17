package org.yokekhei.fsd.p3.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BagItem {
	
	@NotNull
	private Long id;
	
	private Product product;
	
	@NotNull
	@Min(value = 1, message = "Bag item quantity must be greater than 0")
	private Integer quantity;
	
	@NotNull
	@DecimalMin(value = "0.01", message = "Total price must be greater than 0.00")
	private BigDecimal totalPrice;
	
	public BagItem() {
	}

	public BagItem(Long id, Product product,
			Integer quantity, BigDecimal totalPrice) {
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
    public boolean equals(Object o) { 
		if (o == this) {
			return true;
		}
		
		if (!(o instanceof BagItem)) { 
            return false; 
        }
		
		BagItem bi = (BagItem) o;
		
		return bi.getId().equals(id);
	}

	@Override
	public String toString() {
		return "BagItem [id=" + id + ", product=" + product + ", quantity=" + quantity + ", totalPrice=" + totalPrice
				+ "]";
	}
	
}
