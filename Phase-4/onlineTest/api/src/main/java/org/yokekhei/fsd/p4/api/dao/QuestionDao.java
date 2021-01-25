package org.yokekhei.fsd.p4.api.dao;

import java.util.List;

import org.yokekhei.fsd.p4.api.dto.Question;
import org.yokekhei.fsd.p4.api.exception.OnlineTestDaoException;

public interface QuestionDao {

	Question getQuestion(Long id) throws OnlineTestDaoException;
	Question save(Question question, Long quizId) throws OnlineTestDaoException;
	void remove(List<Long> questionIds) throws OnlineTestDaoException;
	
}
