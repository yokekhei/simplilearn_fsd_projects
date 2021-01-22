package org.yokekhei.fsd.p4.api.dto;

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
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
	message = "Password must have at least one upper case, one lower case, one digit and one special character, minimum eight in length")
	private String password;
	
	private String username;
	private String role;
	private Boolean enabled;
	
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

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", username=" + username + ", role=" + role
				+ ", enabled=" + enabled + "]";
	}
	
}
