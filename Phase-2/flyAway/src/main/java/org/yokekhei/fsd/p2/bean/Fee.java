package org.yokekhei.fsd.p2.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="Fee")
public class Fee {

	@Id
	@Column(nullable=false, name="fee_type")
	private String feeType;
	
	@Column(nullable=false, name="fee_value")
	private double feeValue;
	
	public Fee() {
	}

	public String getFeeType() {
		return feeType;
	}

	public double getFeeValue() {
		return feeValue;
	}

	@Override
	public String toString() {
		return "Fee [feeType=" + feeType + ", feeValue=" + feeValue + "]";
	}
	
}
