package org.yokekhei.fsd.p5.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "users")
@DynamicUpdate
public class User {

	@Id
	@Column(name = "user_email")
	private String email;

	@Column(name = "user_password", nullable = false)
	private String password;

	@Column(name = "user_username")
	private String userName;

	@Column(name = "user_role", nullable = false, length = 1)
	private String role;

	@Column(name = "user_enabled", nullable = false)
	private Boolean enabled;

	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdDateTime;

	@ManyToMany
	@JoinTable(name = "student_course",
		joinColumns = @JoinColumn(name = "student_id"),
		inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses = new ArrayList<>();

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

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", userName=" + userName + ", role=" + role
				+ ", enabled=" + enabled + ", createdDateTime=" + createdDateTime + ", courses=" + courses + "]";
	}

}
