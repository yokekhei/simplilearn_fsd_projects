package org.yokekhei.fsd.p4.api.dao;

import java.util.List;

import org.yokekhei.fsd.p4.api.dto.Category;
import org.yokekhei.fsd.p4.api.dto.Quiz;
import org.yokekhei.fsd.p4.api.dto.User;
import org.yokekhei.fsd.p4.api.exception.OnlineTestDaoException;

public interface QuizDao {
	
	Quiz getQuiz(Long id) throws OnlineTestDaoException;
	Quiz save(Quiz quiz) throws OnlineTestDaoException;
	List<Quiz> getQuizzesByCategory(Category category) throws OnlineTestDaoException;
	List<Quiz> getQuizzesByTester(User user) throws OnlineTestDaoException;
	void remove(Long id) throws OnlineTestDaoException;
	
}
