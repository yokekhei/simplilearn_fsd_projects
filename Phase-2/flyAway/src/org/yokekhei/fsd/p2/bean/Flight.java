package org.yokekhei.fsd.p2.bean;

import javax.persistence.Basic;
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

@Table(name="Flight")
public class Flight {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, name="flight_id")
	private int flightId;
	
	@Column(nullable=false, name="flight_no")
	private int flightNo;
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity = Airline.class)
	@JoinColumn(name="airline_code")
	private Airline airline;
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity = Place.class)
	@JoinColumn(name="location_code", insertable=false, updatable=false)
	private Place source;
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity = Place.class)
	@JoinColumn(name="location_code", insertable=false, updatable=false)
	private Place destination;
	
	@Basic
	@Column(nullable=false, name="depart_date")
	private java.time.LocalDate departDate;
	 
	@Basic
	@Column(nullable=false, name="depart_time")
	private java.time.LocalTime departTime;
	
	@Column(nullable=false, name="adult_price")
	private double adultPrice;
	
	@Column(nullable=false, name="child_price")
	private double childPrice;
	
	@Column(nullable=false, name="infant_price")
	private double infantPrice;
	
	public Flight() {
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public int getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Place getSource() {
		return source;
	}

	public void setSource(Place source) {
		this.source = source;
	}

	public Place getDestination() {
		return destination;
	}

	public void setDestination(Place destination) {
		this.destination = destination;
	}

	public java.time.LocalDate getDepartDate() {
		return departDate;
	}

	public void setDepartDate(java.time.LocalDate departDate) {
		this.departDate = departDate;
	}

	public java.time.LocalTime getDepartTime() {
		return departTime;
	}

	public void setDepartTime(java.time.LocalTime departTime) {
		this.departTime = departTime;
	}

	public double getAdultPrice() {
		return adultPrice;
	}

	public void setAdultPrice(double adultPrice) {
		this.adultPrice = adultPrice;
	}

	public double getChildPrice() {
		return childPrice;
	}

	public void setChildPrice(double childPrice) {
		this.childPrice = childPrice;
	}

	public double getInfantPrice() {
		return infantPrice;
	}

	public void setInfantPrice(double infantPrice) {
		this.infantPrice = infantPrice;
	}

	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", airline=" + airline + ", source=" + source + ", destination="
				+ destination + ", departDate=" + departDate + ", departTime=" + departTime + ", adultPrice="
				+ adultPrice + ", childPrice=" + childPrice + ", infantPrice=" + infantPrice + "]";
	}
	
}
