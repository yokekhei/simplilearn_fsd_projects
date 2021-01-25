package org.yokekhei.fsd.p4.api.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yokekhei.fsd.p4.api.dto.Choice;
import org.yokekhei.fsd.p4.api.dto.Question;
import org.yokekhei.fsd.p4.api.dto.Quiz;
import org.yokekhei.fsd.p4.api.exception.OnlineTestServiceException;

@SpringBootTest
public class QuizServiceTests {

	@Autowired
	QuizService service;
	
	@Test
	void testSave() {
		Quiz quiz = new Quiz("1st Grade Subtraction", 1L, "johndoe@gmail.com");
		
		List<Question> questions = quiz.getQuestions();
		
		Question q1 = new Question("5 - 1 = ?");
		List<Choice> cs1 = q1.getChoices();
		Choice c1_1 = new Choice("4");
		cs1.add(c1_1);
		Choice c1_2 = new Choice("1");
		cs1.add(c1_2);
		Choice c1_3 = new Choice("3");
		cs1.add(c1_3);
		Choice c1_4 = new Choice("0");
		cs1.add(c1_4);
		q1.setAnswerIndex(0);
		questions.add(q1);
		
		Question q2 = new Question("9 - 3 = ?");
		List<Choice> cs2 = q2.getChoices();
		Choice c2_1 = new Choice("2");
		cs2.add(c2_1);
		Choice c2_2 = new Choice("1");
		cs2.add(c2_2);
		Choice c2_3 = new Choice("3");
		cs2.add(c2_3);
		Choice c2_4 = new Choice("6");
		cs2.add(c2_4);
		q2.setAnswerIndex(3);
		questions.add(q2);
		
		try {
			Quiz savedQuiz = service.createQuiz(quiz);
			System.out.println(savedQuiz);
		} catch (OnlineTestServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	void testGet() {
		try {
			Quiz quiz = service.getQuiz(1L);
			System.out.println(quiz);
		} catch (OnlineTestServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	void testGetByCategoryId() {
		try {
			List<Quiz> quizzes = service.getQuizzesByCategoryId(1L);
			System.out.println(quizzes);
		} catch (OnlineTestServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	void testGetByTesterEmail() {
		try {
			List<Quiz> quizzes = service.getQuizzesByTesterEmail("johndoe@gmail.com");
			System.out.println(quizzes);
		} catch (OnlineTestServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	void testDelete() {
		try {
			Quiz quiz = service.deleteQuiz(38L);
			System.out.println(quiz);
		} catch (OnlineTestServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	void testUpdate() {
		Quiz quiz = new Quiz("1st Grade Subtraction (Modified)", 1L, "johndoe@gmail.com");
		quiz.setId(6L);
		
		List<Question> questions = quiz.getQuestions();
		
		Question q1 = new Question("5 - 1 = ?");
		List<Choice> cs1 = q1.getChoices();
		Choice c1_1 = new Choice("4");
		cs1.add(c1_1);
		Choice c1_2 = new Choice("1");
		cs1.add(c1_2);
		Choice c1_3 = new Choice("3");
		cs1.add(c1_3);
		Choice c1_4 = new Choice("0");
		cs1.add(c1_4);
		q1.setAnswerIndex(0);
		questions.add(q1);
		
		Question q2 = new Question("9 - 4 = ?");
		List<Choice> cs2 = q2.getChoices();
		Choice c2_1 = new Choice("2");
		cs2.add(c2_1);
		Choice c2_2 = new Choice("1");
		cs2.add(c2_2);
		Choice c2_3 = new Choice("3");
		cs2.add(c2_3);
		Choice c2_4 = new Choice("5");
		cs2.add(c2_4);
		q2.setAnswerIndex(3);
		questions.add(q2);
		
		Question q3 = new Question("3 - 3 = ?");
		List<Choice> cs3 = q3.getChoices();
		Choice c3_1 = new Choice("9");
		cs3.add(c3_1);
		Choice c3_2 = new Choice("0");
		cs3.add(c3_2);
		Choice c3_3 = new Choice("10");
		cs3.add(c3_3);
		Choice c3_4 = new Choice("45");
		cs3.add(c3_4);
		q3.setAnswerIndex(1);
		questions.add(q3);
		
		try {
			Quiz updatedQuiz = service.updateQuiz(quiz);
			System.out.println(updatedQuiz);
		} catch (OnlineTestServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
}
