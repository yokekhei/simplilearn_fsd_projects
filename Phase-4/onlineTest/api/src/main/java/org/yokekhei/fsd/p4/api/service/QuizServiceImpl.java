package org.yokekhei.fsd.p4.api.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yokekhei.fsd.p4.api.dao.AnswerDao;
import org.yokekhei.fsd.p4.api.dao.ChoiceDao;
import org.yokekhei.fsd.p4.api.dao.QuestionDao;
import org.yokekhei.fsd.p4.api.dao.QuizDao;
import org.yokekhei.fsd.p4.api.dto.Category;
import org.yokekhei.fsd.p4.api.dto.Choice;
import org.yokekhei.fsd.p4.api.dto.Question;
import org.yokekhei.fsd.p4.api.dto.Quiz;
import org.yokekhei.fsd.p4.api.dto.User;
import org.yokekhei.fsd.p4.api.exception.OnlineTestServiceException;

@Service
public class QuizServiceImpl implements QuizService {
	
	@Resource
	private QuizDao quizDao;
	
	@Resource
	private QuestionDao questionDao;
	
	@Resource
	private ChoiceDao choiceDao;
	
	@Resource
	private AnswerDao answerDao;

	@Override
	@Transactional
	public Quiz getQuiz(Long id) throws OnlineTestServiceException {
		Quiz quiz = null;
		
		try {
			quiz = quizDao.getQuiz(id);
		} catch (Exception e) {
			throw new OnlineTestServiceException(e.getMessage());
		}
		
		return quiz;
	}

	@Override
	@Transactional
	public List<Quiz> getQuizzesByCategoryId(Long id) throws OnlineTestServiceException {
		List<Quiz> quizzes = null;
		
		try {
			Category category = new Category();
			category.setId(id);
			quizzes = quizDao.getQuizzesByCategory(category);
		} catch (Exception e) {
			throw new OnlineTestServiceException(e.getMessage());
		}
		
		return quizzes;
	}

	@Override
	@Transactional
	public List<Quiz> getQuizzesByTesterEmail(String email) throws OnlineTestServiceException {
		List<Quiz> quizzes = null;
		
		try {
			User user = new User();
			user.setEmail(email);
			quizzes = quizDao.getQuizzesByTester(user);
		} catch (Exception e) {
			throw new OnlineTestServiceException(e.getMessage());
		}
		
		return quizzes;
	}
	
	@Override
	@Transactional
	public Quiz createQuiz(Quiz quiz) throws OnlineTestServiceException {
		Quiz savedQuiz = null;
		
		try {
			savedQuiz = quizDao.save(quiz);
			savedQuiz.clearQuestions();
			
			for (Question question : quiz.getQuestions()) {
				Question savedQuestion = questionDao.save(question, savedQuiz.getId());
				savedQuestion.clearChoices();
				
				for (Choice choice : question.getChoices()) {
					Choice savedChoice = choiceDao.save(choice, savedQuestion.getId());
					savedQuestion.getChoices().add(savedChoice);
				}
				
				answerDao.save(question.getAnswerIndex(), savedQuestion);
				savedQuestion.setAnswerIndex(question.getAnswerIndex());
				
				savedQuiz.getQuestions().add(savedQuestion);
			}
		} catch (Exception e) {
			throw new OnlineTestServiceException(e.getMessage());
		}
		
		return savedQuiz;
	}
	
	@Override
	@Transactional
	public Quiz deleteQuiz(Long id) throws OnlineTestServiceException {
		Quiz quiz = null;
		
		try {
			quiz = getQuiz(id);
			List<Long> questionIds = quiz.getQuestions()
					.stream()
					.map(question -> question.getId())
					.collect(Collectors.toList());
			answerDao.remove(questionIds);
			choiceDao.remove(questionIds);
			questionDao.remove(questionIds);
			quizDao.remove(id);
		} catch (Exception e) {
			throw new OnlineTestServiceException(e.getMessage());
		}
		
		return quiz;
	}
	
	@Override
	@Transactional
	public Quiz updateQuiz(Quiz quiz) throws OnlineTestServiceException {
		Quiz savedQuiz = null;
		
		try {
			deleteQuiz(quiz.getId());
			
			// Save
			quiz.setId(null);
			savedQuiz = quizDao.save(quiz);
			savedQuiz.clearQuestions();
			
			for (Question question : quiz.getQuestions()) {
				question.setId(null);;
				Question savedQuestion = questionDao.save(question, savedQuiz.getId());
				savedQuestion.clearChoices();
				
				for (Choice choice : question.getChoices()) {
					choice.setId(null);
					Choice savedChoice = choiceDao.save(choice, savedQuestion.getId());
					savedQuestion.getChoices().add(savedChoice);
				}
				
				answerDao.save(question.getAnswerIndex(), savedQuestion);
				savedQuestion.setAnswerIndex(question.getAnswerIndex());
				
				savedQuiz.getQuestions().add(savedQuestion);
			}
		} catch (Exception e) {
			throw new OnlineTestServiceException(e.getMessage());
		}
		
		return savedQuiz;
	}

}
