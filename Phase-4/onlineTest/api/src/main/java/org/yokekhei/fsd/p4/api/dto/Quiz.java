package org.yokekhei.fsd.p4.api.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class Quiz {
	
	private Long id;
	
	@NotNull(message = "Quiz name must not be null")
	@NotEmpty(message = "Quiz name must not empty")
	@Size(max = 50, message = "Quiz name length must be less than or equal to 50")
	private String name;
	
	@NotNull(message = "Category id must not be null")
	private Long categoryId;
	
	@NotNull(message = "User id must not be null")
	private String userId;
	
	@NotNull
	private List<Question> questions = new ArrayList<>();
	
	private LocalDateTime createdDateTime;
	
	private MultipartFile image;
	
	public Quiz() {
	}
	
	public Quiz(String name, Long categoryId, String userId) {
		this.name = name;
		this.categoryId = categoryId;
		this.userId = userId;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public void clearQuestions() {
		for (Question q : questions) {
			q.clearChoices();
		}
		
		questions.clear();
	}

	@Override
	public String toString() {
		return "Quiz [id=" + id + ", name=" + name + ", categoryId=" + categoryId + ", userId=" + userId
				+ ", questions=" + questions + ", createdDateTime=" + createdDateTime + "]";
	}
	
}
