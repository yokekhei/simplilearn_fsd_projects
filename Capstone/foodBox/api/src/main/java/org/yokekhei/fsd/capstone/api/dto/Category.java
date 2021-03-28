package org.yokekhei.fsd.capstone.api.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class Category {

	private Long id;

	@NotNull
	@NotEmpty(message = "Category name must not empty")
	@Size(max = 15, message = "Category name length must be less than or equal to 15")
	private String name;

	private Boolean enabled;

	private LocalDateTime createdDateTime;

	private MultipartFile image;

	public Category() {
	}

	public Category(Long id) {
		this.id = id;
	}

	public Category(Long id, String name, Boolean enabled) {
		this.id = id;
		this.name = name;
		this.enabled = enabled;
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

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", enabled=" + enabled + ", createdDateTime=" + createdDateTime
				+ "]";
	}

}
