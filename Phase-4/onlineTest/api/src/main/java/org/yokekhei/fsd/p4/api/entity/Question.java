package org.yokekhei.fsd.p4.api.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "questions")
@DynamicUpdate
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id", nullable = false)
	private Long id;
	
	@Column(name = "question_desc", nullable = false)
	private String desc;
	
	@OneToMany(mappedBy="question")
	private List<Choice> choices = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="question_quiz", nullable=false, updatable=false)
	private Quiz quiz;
	
	@OneToOne(mappedBy="question")
	private Answer answer;
	
	public Question() {
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

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", desc=" + desc + ", choices=" + choices + ", quiz=" + quiz + ", answer="
				+ answer + "]";
	}
	
}
