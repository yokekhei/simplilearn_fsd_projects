package org.yokekhei.fsd.p4.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Category {
	
	private Long id;
	
	@NotNull
	@NotEmpty(message = "Category name must not empty")
	@Size(max = 50, message = "Category name length must be less than or equal to 50")
	private String name;
	
	public Category() {
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

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

}
