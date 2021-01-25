package org.yokekhei.fsd.p4.api.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Question {

	private Long id;
	
	@NotNull
	@NotEmpty(message = "Question must not empty")
	@Size(max = 255, message = "Question character length must be less than or equal to 255")
	private String desc;
	
	@NotNull
	private List<Choice> choices = new ArrayList<>();
	
	@NotNull
	private Integer answerIndex;
	
	public Question() {
	}
	
	public Question(String desc) {
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

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	public Integer getAnswerIndex() {
		return answerIndex;
	}

	public void setAnswerIndex(Integer answerIndex) {
		this.answerIndex = answerIndex;
	}

	public void clearChoices() {
		choices.clear();
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", desc=" + desc + ", choices=" + choices + ", answerIndex=" + answerIndex + "]";
	}
	
}
