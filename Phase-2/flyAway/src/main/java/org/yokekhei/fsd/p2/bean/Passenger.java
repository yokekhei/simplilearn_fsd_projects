package org.yokekhei.fsd.p2.bean;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="Passenger")
public class Passenger {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, name="passenger_id")
	private int passengerId;
	
	@Column(nullable=false, name="passenger_fname", length=50)
	private String firstName;
	
	@Column(nullable=false, name="passenger_lname", length=50)
	private String lastName;
	
	@Column(nullable=false, name="passenger_dob")
	private LocalDate dob;
	
	@Column(nullable=false, name="passenger_gender", length=1)
	private String gender;
	
	@Column(nullable=false, name="passenger_type", length=1)
	private String type;
	
	public Passenger() {
	}

	public Passenger(String firstName, String lastName, LocalDate dob, String gender, String type) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.type = type;
	}
	
	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dob=" + dob + ", gender=" + gender + ", type=" + type + "]";
	}
	
}
