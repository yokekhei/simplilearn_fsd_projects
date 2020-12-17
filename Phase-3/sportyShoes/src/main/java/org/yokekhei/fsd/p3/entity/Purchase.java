package org.yokekhei.fsd.p3.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "purchases")
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_id")
	private Long id;
	
	@OneToOne(targetEntity = User.class)
	@JoinColumn(name = "purchase_user")
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, targetEntity = PurchaseItem.class, fetch = FetchType.EAGER)
	@JoinTable(name = "purchasedetails",
		joinColumns = @JoinColumn( name="purchase_id"),
		inverseJoinColumns = @JoinColumn( name="pitem_id"))
	private List<PurchaseItem> items = new ArrayList<>();
	
	@Column(name = "purchase_price", nullable = false)
	private BigDecimal totalPrice;
	
	@CreationTimestamp
	@Column(name = "created_at")
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

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", user=" + user + ", items=" + items + ", totalPrice=" + totalPrice
				+ ", createdDateTime=" + createdDateTime + "]";
	}
	
}
