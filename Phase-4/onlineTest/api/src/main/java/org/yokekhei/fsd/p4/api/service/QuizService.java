package org.yokekhei.fsd.p4.api.service;

import java.util.List;

import org.yokekhei.fsd.p4.api.dto.Quiz;
import org.yokekhei.fsd.p4.api.exception.OnlineTestServiceException;

public interface QuizService {
	
	Quiz getQuiz(Long id) throws OnlineTestServiceException;
	List<Quiz> getQuizzesByCategoryId(Long id) throws OnlineTestServiceException;
	List<Quiz> getQuizzesByTesterEmail(String email) throws OnlineTestServiceException;
	Quiz createQuiz(Quiz quiz) throws OnlineTestServiceException;
	Quiz updateQuiz(Quiz quiz) throws OnlineTestServiceException;
	Quiz deleteQuiz(Long id) throws OnlineTestServiceException;
	byte[] getQuizImage(Long quizId) throws OnlineTestServiceException;
	
}
