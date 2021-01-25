package org.yokekhei.fsd.p4.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "answers")
@DynamicUpdate
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "answer_id", nullable = false)
	private Long id;
	
	@OneToOne(targetEntity = Question.class)
	@JoinColumn(name = "answer_question")
	private Question question;
	
	@OneToOne(targetEntity = Choice.class)
	@JoinColumn(name = "answer_choice")
	private Choice choice;
	
	public Answer() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Choice getChoice() {
		return choice;
	}

	public void setChoice(Choice choice) {
		this.choice = choice;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", question=" + question + ", choice=" + choice + "]";
	}
	
}
