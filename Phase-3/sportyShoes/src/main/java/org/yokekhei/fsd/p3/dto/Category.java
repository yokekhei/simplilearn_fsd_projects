package org.yokekhei.fsd.p3.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Category {

	private Long id;
	
	@NotNull
	@NotEmpty(message = "Category name must not empty")
	@Size(max = 30, message = "Category name length must be less than or equal to 30")
	private String name;
	
	@NotNull
	@NotEmpty(message = "Category description must not empty")
	private String desc;
	
	public Category() {
	}

	public Category(String name, String desc) {
		this.name = name;
		this.desc = desc;
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

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", desc=" + desc + "]";
	}
	
}
