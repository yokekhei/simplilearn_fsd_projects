package org.yokekhei.fsd.p3.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.yokekhei.fsd.p3.Common;

public class User {
	
	@NotNull
	@NotEmpty(message = "Email must not empty")
	@Email(message = "Valid email required")
	private String email;
	
	@NotNull
	@NotEmpty(message = "Password must not empty")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
	message = "Password must have at least one upper case, one lower case, one digit and one special character, minimum eight in length")
	private String password;
	
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
	message = "At least one upper case, one lower case, one digit and one special character, minimum eight in length")
	private String confirmPassword;
	
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private String dobString;
	private String gender;
	private String role;
	private Boolean enabled;
	private LocalDateTime createdDateTime;
	
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
		this.enabled = true;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	public String getDobString() {
		if (dobString == null && dob != null) {
			dobString = Common.toLocalDateString(dob);
		}
		
		return dobString;
	}

	public void setDobString(String dobString) {
		this.dobString = dobString;
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getCreatedDateString() {
		if (createdDateTime != null) {
			return Common.toLocalDateString(createdDateTime);
		}
		
		return "";
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", gender=" + gender
				+ ", role=" + role + ", enabled=" + enabled + ", createdDateTime=" + createdDateTime + "]";
	}
	
}
