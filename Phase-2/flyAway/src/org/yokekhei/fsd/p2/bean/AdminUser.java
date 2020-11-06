package org.yokekhei.fsd.p2.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="AdminUser")
public class AdminUser {

	@Id
	@Column(nullable=false, name="admin_email", length=100)
	private String email;
	
	@Column(nullable=false, name="admin_password", length=50)
	private String password;
	
	public AdminUser() {
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

	@Override
	public String toString() {
		return "AdminUser [email=" + email + ", password=" + password + "]";
	}
	
}
