package org.yokekhei.fsd.p2.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="Guest")
public class Guest {

	@Id
	@Column(nullable=false, name="guest_email", length=100)
	private String email;
	
	@Column(nullable=false, name="guest_fname", length=50)
	private String firstName;
	
	@Column(nullable=false, name="guest_lname", length=50)
	private String lastName;
	
	@Column(nullable=false, name="guest_phone", length=50)
	private String phone;
	
	public Guest() {
	}
	
	public Guest(String email, String firstName, String lastName, String phone) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Guest [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
				+ "]";
	}
	
}
