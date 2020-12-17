package org.yokekhei.fsd.p3.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purchaseitems")
public class PurchaseItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pitem_id")
	private Long id;
	
	@OneToOne(targetEntity = Product.class)
	@JoinColumn(name = "pitem_product")
	private Product product;
	
	@Column(name = "pitem_quantity", nullable = false)
	private Integer quantity;
	
	@Column(name = "pitem_price", nullable = false)
	private BigDecimal totalPrice;
	
	public PurchaseItem() {
	}

	public PurchaseItem(Product product, Integer quantity, BigDecimal totalPrice) {
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
	public String toString() {
		return "PurchaseItem [id=" + id + ", product=" + product + ", quantity=" + quantity + ", totalPrice="
				+ totalPrice + "]";
	}
	
}
