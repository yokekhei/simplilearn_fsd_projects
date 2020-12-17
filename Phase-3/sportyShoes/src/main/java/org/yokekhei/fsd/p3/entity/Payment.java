package org.yokekhei.fsd.p3.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "payment")
public class Payment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, name="payment_id")
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity = Purchase.class)
	@JoinColumn(name = "purchase_id")
	private Purchase purchaseOrder;
	
	@Column(name = "payor_name", nullable = false)
	private String payorName;
	
	@Column(name = "total_paid", nullable = false)
	private double totalPaid;
	
	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdDateTime;
	
	public Payment() {
	}

	public Payment(Purchase purchaseOrder, String payorName, double totalPaid) {
		this.purchaseOrder = purchaseOrder;
		this.payorName = payorName;
		this.totalPaid = totalPaid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Purchase getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(Purchase purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public String getPayorName() {
		return payorName;
	}

	public void setPayorName(String payorName) {
		this.payorName = payorName;
	}

	public double getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(double totalPaid) {
		this.totalPaid = totalPaid;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", purchaseOrder=" + purchaseOrder + ", payorName=" + payorName + ", totalPaid="
				+ totalPaid + ", createdDateTime=" + createdDateTime + "]";
	}
	
}
