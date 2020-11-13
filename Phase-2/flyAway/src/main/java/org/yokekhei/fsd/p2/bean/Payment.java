package org.yokekhei.fsd.p2.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity

@Table(name="Payment")
public class Payment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, name="payment_id")
	private int paymentId;
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity = Booking.class)
	@JoinColumn(name="booking_id")
	private Booking booking;
	
	@Column(nullable=false, name="payor_name")
	private String payorName;
	
	@Column(nullable=false, name="total_paid")
	private double totalPaid;
	
	public Payment() {
	}

	public Payment(Booking booking, String payorName, double totalPaid) {
		super();
		this.booking = booking;
		this.payorName = payorName;
		this.totalPaid = totalPaid;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
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

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", booking=" + booking + ", payorName=" + payorName + ", totalPaid="
				+ totalPaid + "]";
	}
	
}
