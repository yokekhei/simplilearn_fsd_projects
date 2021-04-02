package org.yokekhei.fsd.capstone.api.entity;

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
@Table(name = "orderitems")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "oitem_id")
	private Long id;

	@OneToOne(targetEntity = Food.class)
	@JoinColumn(name = "oitem_food")
	private Food food;

	@Column(name = "oitem_quantity", nullable = false)
	private Integer quantity;

	@Column(name = "oitem_price", nullable = false)
	private BigDecimal price;

	@Column(name = "oitem_discount")
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
