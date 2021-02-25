package org.yokekhei.fsd.capstone.api.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.yokekhei.fsd.capstone.api.dto.User;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxDaoException;
import org.yokekhei.fsd.capstone.api.mapper.UserMapper;

@Repository
public class UserDaoImpl implements UserDao {

	@Resource
	private UserRepository repository;

	@Resource
	private UserMapper mapper;

	@Override
	public User getUser(String email) throws FoodBoxDaoException {
		User user = null;

		try {
			user = repository.findById(email)
					.map(entity -> mapper.toDto(entity))
					.orElse(null);
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return user;
	}

	@Override
	public User getUser(String email, String password) throws FoodBoxDaoException {
		User user = null;

		try {
			user = repository.findByEmailAndPassword(email, password)
					.map(entity -> mapper.toDto(entity))
					.orElse(null);
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return user;
	}

	@Override
	public User save(User user) throws FoodBoxDaoException {
		User savedUser = null;

		try {
			savedUser = mapper.toDto(repository.save(mapper.toEntity(user)));
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return savedUser;
	}

}
