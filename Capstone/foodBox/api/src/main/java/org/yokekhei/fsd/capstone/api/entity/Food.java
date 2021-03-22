package org.yokekhei.fsd.capstone.api.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "foods")
@DynamicUpdate
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "food_id")
	private Long id;

	@Column(name = "food_name", nullable = false)
	private String name;

	@OneToOne(targetEntity = Category.class)
	@JoinColumn(name = "food_category")
	private Category category;

	@Column(name = "food_price", nullable = false)
	private BigDecimal price;

	@Column(name = "food_desc", nullable = false)
	private String desc;

	@OneToOne(targetEntity = Offer.class)
	@JoinColumn(name = "food_offer")
	private Offer offer;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "food_image")
	private byte[] image;

	@Column(name = "food_enabled", nullable = false)
	private Boolean enabled;

	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdDateTime;

	public Food() {
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
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
		return "Food [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", desc=" + desc
				+ ", offer=" + offer + ", enabled=" + enabled + ", createdDateTime=" + createdDateTime + "]";
	}

}
