package org.yokekhei.fsd.p3.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {

	@Id
	@NotEmpty(message = "Email must not empty")
	@Column(name = "user_email")
	private String email;
	
	@NotEmpty(message = "Password must not empty")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
	message = "At least one upper case, one lower case, one digit and one special character, minimum eight in length")
	@Column(name = "user_password", nullable = false)
	private String password;
	
	@Column(name = "user_fname")
	private String firstName;
	
	@Column(name = "user_lname")
	private String lastName;
	
	@Column(name = "user_dob")
	private LocalDate dob;
	
	@Column(name = "user_gender", length = 1)
	private String gender;
	
	@Column(name = "user_role", nullable = false, length = 1)
	private String role;
	
	@Column(name = "user_enabled", nullable = false)
	private Boolean enabled;
	
	public User() {
	}
	
	public User(String email) {
		this.email = email;
	}

	public User(String email, String password, String firstName, String lastName, LocalDate dob, String gender,
			String role) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.role = role;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dob=" + dob + ", gender=" + gender + ", role=" + role + ", enabled=" + enabled + "]";
	}
	
}
