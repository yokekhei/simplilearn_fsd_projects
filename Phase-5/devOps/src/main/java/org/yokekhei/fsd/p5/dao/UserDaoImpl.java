package org.yokekhei.fsd.p5.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.yokekhei.fsd.p5.dto.User;
import org.yokekhei.fsd.p5.mapper.UserMapper;
import org.yokekhei.fsd.p5.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao {

	@Resource
	private UserRepository repository;

	@Resource
	private UserMapper mapper;

	@Override
	public User getUser(String email, String password) throws DevOpsDaoException {
		User user = null;

		try {
			user = repository.findByEmailAndPassword(email, password)
					.map(entity -> mapper.toDto(entity))
					.orElse(null);
		} catch (Exception e) {
			throw new DevOpsDaoException(e.getMessage());
		}

		return user;
	}

	@Override
	public User save(User user) throws DevOpsDaoException {
		User savedUser = null;

		try {
			savedUser = mapper.toDto(repository.save(mapper.toEntity(user)));
		} catch (Exception e) {
			throw new DevOpsDaoException(e.getMessage());
		}

		return savedUser;
	}

	@Override
	public User update(User user) throws DevOpsDaoException {
		User savedUser = null;

		try {
			User current = repository.findById(user.getEmail())
					.map(entity -> mapper.toDto(entity))
					.orElse(null);

			if (current == null) {
				throw new DevOpsDaoException("User " + user.getEmail() + " does not exist");
			}

			current.setEmail(user.getEmail());
			current.setUserName(user.getUserName());
			current.setPassword(user.getPassword());
			current.setEnabled(user.getEnabled());

			savedUser = mapper.toDto(repository.save(mapper.toEntity(current)));
		} catch (Exception e) {
			throw new DevOpsDaoException(e.getMessage());
		}

		return savedUser;
	}

	@Override
	public User getUser(String email) throws DevOpsDaoException {
		User user = null;

		try {
			user = repository.findById(email)
					.map(entity -> mapper.toDto(entity))
					.orElse(null);
		} catch (Exception e) {
			throw new DevOpsDaoException(e.getMessage());
		}

		return user;
	}

}
