package org.yokekhei.fsd.p4.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "choices")
@DynamicUpdate
public class Choice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "choice_id", nullable = false)
	private Long id;
	
	@Column(name = "choice_desc", nullable = false)
	private String desc;
	
	@ManyToOne
	@JoinColumn(name="choice_question", nullable=false, updatable=false)
	private Question question;
	
	public Choice() {
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

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Choice [id=" + id + ", desc=" + desc + ", question=" + question + "]";
	}
	
	@Override
	public boolean equals(Object o) {
		Choice choice = (Choice)o;
		return choice.getId() == id;
	}
	
}
