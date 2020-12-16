package org.yokekhei.fsd.p3.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class Product {
	
	private Long id;
	private Category category;
	private Long categoryId;
	
	@NotNull
	@NotEmpty(message = "Product name must not empty")
	@Size(max = 50, message = "Product name length must be less than or equal to 50")
	private String name;
	
	@NotNull
	@NotEmpty
	private String shortDesc;
	
	@NotNull
	@NotEmpty
	private String longDesc;
	
	@DecimalMin(value = "0.01", message = "Product price must be greater than 0.00")
	private BigDecimal price;
	
	private MultipartFile imageFile;
	
	public Product() {
	}

	public Product(Category category, String name, String shortDesc, String longDesc, BigDecimal price) {
		this.category = category;
		this.name = name;
		this.shortDesc = shortDesc;
		this.longDesc = longDesc;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", category=" + category + ", name=" + name + ", shortDesc=" + shortDesc
				+ ", longDesc=" + longDesc + ", price=" + price + "]";
	}
	
}
