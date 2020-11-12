package org.yokekhei.fsd.p2.bean;

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

@Entity

@Table(name="Booking")
public class Booking {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, name="booking_id")
	private int bookingId;
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity = Flight.class)
	@JoinColumn(name="flight_id")
	private Flight flight;
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity = Guest.class)
	@JoinColumn(name="guest_email")
	private Guest guest;
	
	@OneToMany(cascade = CascadeType.ALL, targetEntity = Passenger.class)
	@JoinTable(name="Booking_Passenger",
		joinColumns = @JoinColumn( name="booking_id"),
		inverseJoinColumns = @JoinColumn( name="passenger_id"))
	private List<Passenger> passengers = new ArrayList<>();
	
	@Column(nullable=false, name="total_adult_fare")
	private double totalAdultFare;
	
	@Column(nullable=false, name="total_child_fare")
	private double totalChildFare;
	
	@Column(nullable=false, name="total_infant_fare")
	private double totalInfantFare;
	
	@Column(nullable=false, name="total_passenger_service_charge")
	private double totalPassengerServiceCharge;
	
	@Column(nullable=false, name="total_service_tax")
	private double totalServiceTax;
	
	@Column(nullable=false, name="total_regulatory_service_charge")
	private double totalRegulatoryServiceCharge;
	
	public Booking() {
	}

	public Booking(Flight flight, Guest guest, List<Passenger> passengers, double totalAdultFare, double totalChildFare,
			double totalInfantFare, double totalPassengerServiceCharge, double totalServiceTax,
			double totalRegulatoryServiceCharge) {
		super();
		this.flight = flight;
		this.guest = guest;
		this.passengers = passengers;
		this.totalAdultFare = totalAdultFare;
		this.totalChildFare = totalChildFare;
		this.totalInfantFare = totalInfantFare;
		this.totalPassengerServiceCharge = totalPassengerServiceCharge;
		this.totalServiceTax = totalServiceTax;
		this.totalRegulatoryServiceCharge = totalRegulatoryServiceCharge;
	}
	
	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public double getTotalAdultFare() {
		return totalAdultFare;
	}

	public void setTotalAdultFare(double totalAdultFare) {
		this.totalAdultFare = totalAdultFare;
	}

	public double getTotalChildFare() {
		return totalChildFare;
	}

	public void setTotalChildFare(double totalChildFare) {
		this.totalChildFare = totalChildFare;
	}

	public double getTotalInfantFare() {
		return totalInfantFare;
	}

	public void setTotalInfantFare(double totalInfantFare) {
		this.totalInfantFare = totalInfantFare;
	}

	public double getTotalPassengerServiceCharge() {
		return totalPassengerServiceCharge;
	}

	public void setTotalPassengerServiceCharge(double totalPassengerServiceCharge) {
		this.totalPassengerServiceCharge = totalPassengerServiceCharge;
	}

	public double getTotalServiceTax() {
		return totalServiceTax;
	}

	public void setTotalServiceTax(double totalServiceTax) {
		this.totalServiceTax = totalServiceTax;
	}

	public double getTotalRegulatoryServiceCharge() {
		return totalRegulatoryServiceCharge;
	}

	public void setTotalRegulatoryServiceCharge(double totalRegulatoryServiceCharge) {
		this.totalRegulatoryServiceCharge = totalRegulatoryServiceCharge;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", flight=" + flight + ", guest=" + guest + ", passengers="
				+ passengers + ", totalAdultFare=" + totalAdultFare + ", totalChildFare=" + totalChildFare
				+ ", totalInfantFare=" + totalInfantFare + ", totalPassengerServiceCharge="
				+ totalPassengerServiceCharge + ", totalServiceTax=" + totalServiceTax
				+ ", totalRegulatoryServiceCharge=" + totalRegulatoryServiceCharge + "]";
	}
	
}

