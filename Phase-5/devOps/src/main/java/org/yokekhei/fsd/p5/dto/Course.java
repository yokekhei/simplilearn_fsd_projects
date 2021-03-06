package org.yokekhei.fsd.p5.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.yokekhei.fsd.p5.Common;
import org.yokekhei.fsd.p5.entity.User;

public class Course {

	private Long id;

	@NotNull
	@NotEmpty(message = "Course name must not empty")
	@Size(max = 50, message = "Category name length must be less than or equal to 50")
	private String name;

	@NotNull
	@NotEmpty(message = "Course description must not empty")
	private String desc;

	private Boolean enabled;
	private LocalDateTime createdDateTime;
	private List<User> students = new ArrayList<>();

	public Course() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public List<User> getStudents() {
		return students;
	}

	public void setStudents(List<User> students) {
		this.students = students;
	}

	public String getCreatedDateString() {
		if (createdDateTime != null) {
			return Common.toLocalDateString(createdDateTime);
		}

		return "";
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", desc=" + desc + ", enabled=" + enabled + ", createdDateTime="
				+ createdDateTime + "]";
	}

}
