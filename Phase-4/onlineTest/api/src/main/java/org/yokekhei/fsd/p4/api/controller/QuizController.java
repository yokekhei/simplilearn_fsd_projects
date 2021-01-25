package org.yokekhei.fsd.p4.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yokekhei.fsd.p4.api.dto.Quiz;
import org.yokekhei.fsd.p4.api.exception.OnlineTestServiceException;
import org.yokekhei.fsd.p4.api.service.QuizService;

@RestController
@RequestMapping(value = "/api",
produces = { MediaType.APPLICATION_JSON_VALUE })
public class QuizController {

	@Autowired
	QuizService service;
	
	@PostMapping("/quiz")
	public Quiz createQuiz(@Validated @RequestBody Quiz quiz) throws OnlineTestServiceException {
		return service.createQuiz(quiz);
	}
	
	@PutMapping("/quiz")
	public Quiz updateQuiz(@Validated @RequestBody Quiz quiz) throws OnlineTestServiceException {
		return service.updateQuiz(quiz);
	}
	
	@GetMapping("/quiz/{id}")
	public Quiz getQuizById(@PathVariable("id") Long id) throws OnlineTestServiceException {
		return service.getQuiz(id);
	}
	
	@GetMapping("/quiz/category/{id}")
	public List<Quiz> getQuizzesByCategory(@PathVariable("id") Long id) throws OnlineTestServiceException {
		return service.getQuizzesByCategoryId(id);
	}
	
	@GetMapping("/quiz/tester/{email}")
	public List<Quiz> getQuizzesByTester(@PathVariable("email") String email) throws OnlineTestServiceException {
		return service.getQuizzesByTesterEmail(email);
	}
	
	@DeleteMapping("/quiz/{id}")
	public Quiz deleteQuiz(@PathVariable("id") Long id) throws OnlineTestServiceException {
		return service.deleteQuiz(id);
	}
	
}
