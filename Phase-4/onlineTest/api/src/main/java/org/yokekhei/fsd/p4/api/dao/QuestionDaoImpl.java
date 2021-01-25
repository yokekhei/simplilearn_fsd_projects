package org.yokekhei.fsd.p4.api.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.yokekhei.fsd.p4.api.dto.Question;
import org.yokekhei.fsd.p4.api.exception.OnlineTestDaoException;
import org.yokekhei.fsd.p4.api.mapper.QuestionMapper;

@Repository
public class QuestionDaoImpl implements QuestionDao {

	@Resource
	private QuestionRepository repository;
	
	@Resource
	private QuestionMapper mapper;
	
	@Override
	public Question getQuestion(Long id) throws OnlineTestDaoException {
		Question question = null;
		
		try {
			question = repository.findById(id)
					.map(entity -> mapper.toDto(entity))
					.orElse(null);
		} catch (Exception e) {
			throw new OnlineTestDaoException(e.getMessage());
		}
		
		return question;
	}

	@Override
	@Transactional
	public Question save(Question question, Long quizId) throws OnlineTestDaoException {
		Question savedQuestion = null;
		
		try {
			savedQuestion = mapper.toDto(repository.save(mapper.toEntity(question, quizId)));
		} catch (Exception e) {
			throw new OnlineTestDaoException(e.getMessage());
		}
		
		return savedQuestion;
	}

	@Override
	public void remove(List<Long> questionIds) throws OnlineTestDaoException {
		try {
			repository.deleteByIds(questionIds);
		} catch (Exception e) {
			throw new OnlineTestDaoException(e.getMessage());
		}
	}

}
