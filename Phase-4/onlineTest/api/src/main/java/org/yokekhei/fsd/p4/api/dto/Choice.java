package org.yokekhei.fsd.p4.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Choice {

	private Long id;
	
	@NotNull
	@NotEmpty(message = "Choice must not empty")
	@Size(max = 255, message = "Choice character length must be less than or equal to 255")
	private String desc;
	
	public Choice() {
	}

	public Choice(String desc) {
		this.desc = desc;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Choice [id=" + id + ", desc=" + desc + "]";
	}
	
}
