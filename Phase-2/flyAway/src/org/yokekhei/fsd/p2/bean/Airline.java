package org.yokekhei.fsd.p2.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="Airline")
public class Airline {

	@Id
	@Column(nullable=false, name="airline_code")
	private int airlineCode;
	
	@Column(nullable=false, name="flight_code", length=2)
	private String flightCode;
	
	@Column(nullable=false, name="company_name", length=100)
	private String companyName;
	
	@Column(nullable=false, name="country", length=100)
	private String country;
	
	public Airline() {
	}

	public int getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(int airlineCode) {
		this.airlineCode = airlineCode;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Airline [airlineCode=" + airlineCode + ", flightCode=" + flightCode + ", companyName=" + companyName
				+ ", country=" + country + "]";
	}
	
}
