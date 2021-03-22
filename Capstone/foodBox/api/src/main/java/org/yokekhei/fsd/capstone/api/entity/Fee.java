package org.yokekhei.fsd.capstone.api.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "fees")
@DynamicUpdate
public class Fee {

	@Id
	@Column(name = "fee_type", nullable = false)
	private String type;

	@Column(name = "fee_value", nullable = false)
	private BigDecimal value;

	public Fee() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Fee [type=" + type + ", value=" + value + "]";
	}

}
