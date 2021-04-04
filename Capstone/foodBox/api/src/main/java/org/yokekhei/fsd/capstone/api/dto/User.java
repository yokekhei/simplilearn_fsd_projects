package org.yokekhei.fsd.capstone.api.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class User {

	@NotNull
	@NotEmpty(message = "Email must not empty")
	@Email(message = "Valid email required")
	private String email;

	@NotNull
	@NotEmpty(message = "Password must not empty")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Password must have at least one upper case, one lower case, one digit and one special character, minimum eight in length")
	private String password;

	private String username;
	private String phone;
	private String role;
	private Address address;
	private Boolean enabled;
	private LocalDateTime createdDateTime;

	public User() {
	}

	public User(String email) {
		this.email = email;
	}

	public User(String email, String password, String username, String phone, String role, Address address,
			Boolean enabled) {
		this.email = email;
		this.password = password;
		this.username = username;
		this.phone = phone;
		this.role = role;
		this.address = address;
		this.enabled = enabled;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", username=" + username + ", phone=" + phone
				+ ", role=" + role + ", address=" + address + ", enabled=" + enabled + ", createdDateTime="
				+ createdDateTime + "]";
	}

}
