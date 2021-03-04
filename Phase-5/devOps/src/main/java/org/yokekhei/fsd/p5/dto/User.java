package org.yokekhei.fsd.p5.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.yokekhei.fsd.p5.Common;

public class User {

	@NotNull
	@NotEmpty(message = "Email must not empty")
	@Email(message = "Valid email required")
	private String email;

	@NotNull
	@NotEmpty(message = "Password must not empty")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Password must have at least one upper case, one lower case, one digit and one special character, minimum eight in length")
	private String password;

	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Password must have at least one upper case, one lower case, one digit and one special character, minimum eight in length")
	private String confirmPassword;

	private String userName;
	private String role;
	private Boolean enabled;
	private LocalDateTime createdDateTime;

	public User() {
	}

	public User(String email) {
		this.email = email;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
				+ ", userName=" + userName + ", role=" + role + ", enabled=" + enabled + ", createdDateTime="
				+ createdDateTime + "]";
	}

}
