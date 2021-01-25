package org.yokekhei.fsd.p4.api.dao;

import java.util.List;

import org.yokekhei.fsd.p4.api.dto.Choice;
import org.yokekhei.fsd.p4.api.exception.OnlineTestDaoException;

public interface ChoiceDao {

	Choice getChoice(Long id) throws OnlineTestDaoException;
	Choice save(Choice choice, Long questionId) throws OnlineTestDaoException;
	void remove(List<Long> questionIds) throws OnlineTestDaoException;
	
}
