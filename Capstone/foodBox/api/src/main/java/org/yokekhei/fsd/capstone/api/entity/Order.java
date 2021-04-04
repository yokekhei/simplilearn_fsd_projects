package org.yokekhei.fsd.capstone.api.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;

	@Column(name = "order_charge_id", nullable = false)
	private String chargeId;

	@OneToOne(targetEntity = User.class)
	@JoinColumn(name = "order_user")
	private User user;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = OrderItem.class)
	@JoinTable(name = "orderdetails", joinColumns = @JoinColumn(name = "order_id"),
	           inverseJoinColumns = @JoinColumn(name = "oitem_id"))
	private List<OrderItem> items = new ArrayList<>();

	@Column(name = "order_price", nullable = false)
	private BigDecimal price;

	@Column(name = "order_discount")
	private BigDecimal discount;

	@Column(name = "order_delivery_fee")
	private BigDecimal deliveryFee;

	@CreationTimestamp
	@Column(name = "created_at")
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		return "Order [id=" + id + ", chargeId=" + chargeId + ", user=" + user + ", items=" + items + ", price=" + price
				+ ", discount=" + discount + ", deliveryFee=" + deliveryFee + ", createdDateTime=" + createdDateTime
				+ "]";
	}

}
