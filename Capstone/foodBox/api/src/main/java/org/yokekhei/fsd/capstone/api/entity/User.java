package org.yokekhei.fsd.capstone.api.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "user_email", nullable = false)
	private String email;

	@Column(name = "user_password", nullable = false)
	private String password;

	@Column(name = "user_name", nullable = false)
	private String username;

	@Column(name = "user_phone", nullable = false)
	private String phone;

	@Column(name = "user_role", nullable = false, length = 1)
	private String role;

	@Embedded
	private Address address;

	@Column(name = "user_enabled", nullable = false)
	private Boolean enabled;

	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdDateTime;

	public User() {
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
