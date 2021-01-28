package org.yokekhei.fsd.p4.api.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "quizzes")
@DynamicUpdate
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quiz_id", nullable = false)
	private Long id;
	
	@Column(name = "quiz_name", nullable = false)
	private String name;
	
	@OneToOne(targetEntity = Category.class)
	@JoinColumn(name = "quiz_category")
	private Category category;
	
	@OneToOne(targetEntity = User.class)
	@JoinColumn(name = "quiz_tester")
	private User tester;
	
	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdDateTime;
	
	@OneToMany(mappedBy="quiz")
	private List<Question> questions = new ArrayList<>();
	
	@Lob
    @Basic(fetch = FetchType.LAZY)
	@Column(name = "quiz_image")
    private byte[] image;
	
	public Quiz() {
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

	public User getTester() {
		return tester;
	}

	public void setTester(User tester) {
		this.tester = tester;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Quiz [id=" + id + ", name=" + name + ", category=" + category + ", tester=" + tester
				+ ", createdDateTime=" + createdDateTime + ", questions=" + questions + "]";
	}
	
}
