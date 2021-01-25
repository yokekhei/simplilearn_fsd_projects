package org.yokekhei.fsd.p4.api.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.yokekhei.fsd.p4.api.dto.Category;
import org.yokekhei.fsd.p4.api.dto.Quiz;
import org.yokekhei.fsd.p4.api.dto.User;
import org.yokekhei.fsd.p4.api.exception.OnlineTestDaoException;
import org.yokekhei.fsd.p4.api.mapper.CategoryMapper;
import org.yokekhei.fsd.p4.api.mapper.QuizMapper;
import org.yokekhei.fsd.p4.api.mapper.UserMapper;

@Repository
public class QuizDaoImpl implements QuizDao {

	@Resource
	private QuizRepository repository;
	
	@Resource
	private QuizMapper mapper;
	
	@Resource
	private CategoryMapper categoryMapper;
	
	@Resource
	private UserMapper userMapper;
	
	@Override
	public Quiz getQuiz(Long id) throws OnlineTestDaoException {
		Quiz quiz = null;
		
		try {
			quiz = repository.findById(id)
					.map(entity -> mapper.toDto(entity))
					.orElse(null);
		} catch (Exception e) {
			throw new OnlineTestDaoException(e.getMessage());
		}
		
		return quiz;
	}

	@Override
	@Transactional
	public Quiz save(Quiz quiz) throws OnlineTestDaoException {
		Quiz savedQuiz = null;
		
		try {
			savedQuiz = mapper.toDto(repository.save(mapper.toEntity(quiz)));
		} catch (Exception e) {
			throw new OnlineTestDaoException(e.getMessage());
		}
		
		return savedQuiz;
	}

	@Override
	public List<Quiz> getQuizzesByCategory(Category category) throws OnlineTestDaoException {
		List<Quiz> quizzes = null;
		
		try {
			quizzes = repository.findByCategory(categoryMapper.toEntity(category))
					.stream()
					.map(entity -> mapper.toDto(entity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OnlineTestDaoException(e.getMessage());
		}
		
		return quizzes;
	}

	@Override
	public List<Quiz> getQuizzesByTester(User user) throws OnlineTestDaoException {
		List<Quiz> quizzes = null;
		
		try {
			quizzes = repository.findByTester(userMapper.toEntity(user))
					.stream()
					.map(entity -> mapper.toDto(entity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OnlineTestDaoException(e.getMessage());
		}
		
		return quizzes;
	}

	@Override
	@Transactional
	public void remove(Long id) throws OnlineTestDaoException {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new OnlineTestDaoException(e.getMessage());
		}
	}

}
