package org.yokekhei.fsd.p2.bean;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;

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

import org.yokekhei.fsd.p2.Common;

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
	@JoinColumn(name="src_location")
	private Place source;
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity = Place.class)
	@JoinColumn(name="dest_location")
	private Place destination;
	
	@Basic
	@Column(nullable=false, name="depart_date")
	private LocalDate departDate;
	 
	@Basic
	@Column(nullable=false, name="depart_time")
	private LocalTime departTime;
	
	@Basic
	@Column(nullable=false, name="arrive_date")
	private LocalDate arriveDate;
	
	@Basic
	@Column(nullable=false, name="arrive_time")
	private LocalTime arriveTime;
	
	@Column(nullable=false, name="adult_price")
	private double adultPrice;
	
	@Column(nullable=false, name="child_price")
	private double childPrice;
	
	@Column(nullable=false, name="infant_price")
	private double infantPrice;
	
	public Flight() {
	}

	public Flight(int flightNo, Airline airline, Place source, Place destination, LocalDate departDate,
			LocalTime departTime, LocalDate arriveDate, LocalTime arriveTime, double adultPrice, double childPrice,
			double infantPrice) {
		super();
		this.flightNo = flightNo;
		this.airline = airline;
		this.source = source;
		this.destination = destination;
		this.departDate = departDate;
		this.departTime = departTime;
		this.arriveDate = arriveDate;
		this.arriveTime = arriveTime;
		this.adultPrice = adultPrice;
		this.childPrice = childPrice;
		this.infantPrice = infantPrice;
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

	public LocalDate getDepartDate() {
		return departDate;
	}

	public void setDepartDate(LocalDate departDate) {
		this.departDate = departDate;
	}

	public LocalTime getDepartTime() {
		return departTime;
	}

	public void setDepartTime(LocalTime departTime) {
		this.departTime = departTime;
	}
	
	public String getDepartDateTime() {
		return Common.toLocalDateTime(departDate, departTime);
	}

	public LocalDate getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(LocalDate arriveDate) {
		this.arriveDate = arriveDate;
	}

	public LocalTime getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(LocalTime arriveTime) {
		this.arriveTime = arriveTime;
	}
	
	public String getArriveDateTime() {
		return Common.toLocalDateTime(arriveDate, arriveTime);
	}

	public double getAdultPrice() {
		return adultPrice;
	}
	
	public String getAdultPriceString() {
		DecimalFormat df2 = new DecimalFormat(Common.DECIMAL_FORMAT_DF2);
		return df2.format(Common.roundBigDecimal(adultPrice, 2));
	}

	public void setAdultPrice(double adultPrice) {
		this.adultPrice = adultPrice;
	}

	public double getChildPrice() {
		return childPrice;
	}
	
	public String getChildPriceString() {
		DecimalFormat df2 = new DecimalFormat(Common.DECIMAL_FORMAT_DF2);
		return df2.format(Common.roundBigDecimal(childPrice, 2));
	}

	public void setChildPrice(double childPrice) {
		this.childPrice = childPrice;
	}

	public double getInfantPrice() {
		return infantPrice;
	}
	
	public String getInfantPriceString() {
		DecimalFormat df2 = new DecimalFormat(Common.DECIMAL_FORMAT_DF2);
		return df2.format(Common.roundBigDecimal(infantPrice, 2));
	}

	public void setInfantPrice(double infantPrice) {
		this.infantPrice = infantPrice;
	}

	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", flightNo=" + flightNo + ", airline=" + airline + ", source=" + source
				+ ", destination=" + destination + ", departDate=" + departDate + ", departTime=" + departTime
				+ ", arriveDate=" + arriveDate + ", arriveTime=" + arriveTime + ", adultPrice=" + adultPrice
				+ ", childPrice=" + childPrice + ", infantPrice=" + infantPrice + "]";
	}
	
}
