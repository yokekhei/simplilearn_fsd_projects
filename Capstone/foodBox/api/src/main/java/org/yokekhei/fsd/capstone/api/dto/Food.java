package org.yokekhei.fsd.capstone.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class Food {

	private Long id;

	@NotNull
	@NotEmpty(message = "Food name must not empty")
	@Size(max = 31, message = "Food name length must be less than or equal to 31")
	private String name;

	@NotNull(message = "Category id must not be null")
	private Long categoryId;

	@DecimalMin(value = "0.01", message = "Food price must be greater than 0.00")
	private BigDecimal price;

	@NotNull
	@NotEmpty(message = "Food description must not empty")
	@Size(max = 255, message = "Food description length must be less than or equal to 255")
	private String desc;

	private Long offerId;
	private MultipartFile image;
	private Boolean enabled;
	private LocalDateTime createdDateTime;

	public Food() {
	}

	public Food(Long id, String name, Long categoryId, BigDecimal price, String desc, Long offerId, Boolean enabled) {
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.price = price;
		this.desc = desc;
		this.offerId = offerId;
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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
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
		return "Food [id=" + id + ", name=" + name + ", categoryId=" + categoryId + ", price=" + price + ", desc="
				+ desc + ", offerId=" + offerId + ", image=" + image + ", enabled=" + enabled + ", createdDateTime="
				+ createdDateTime + "]";
	}

}
