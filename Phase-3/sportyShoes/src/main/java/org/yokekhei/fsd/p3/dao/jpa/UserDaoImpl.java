package org.yokekhei.fsd.p3.dao.jpa;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.yokekhei.fsd.p3.dao.SportyShoesDaoException;
import org.yokekhei.fsd.p3.dao.UserDao;
import org.yokekhei.fsd.p3.dto.User;

@Repository
@Qualifier("jpa")
public class UserDaoImpl implements UserDao {

	@Resource
	private UserRepository repository;
	
	@Resource
	private UserMapper mapper;
	
	@Override
	public User getUser(String email, String password) throws SportyShoesDaoException {
		User user = null;
		
		try {
			user = repository.findByEmailAndPassword(email, password)
					.map(entity -> mapper.toDto(entity))
					.orElse(null);
		} catch (Exception e) {
			throw new SportyShoesDaoException(e.getMessage());
		}
		
		return user;
	}

	@Override
	@Transactional
	public User save(User user) throws SportyShoesDaoException {
		User savedUser = null;
		
		try {
			savedUser = mapper.toDto(repository.save(mapper.toEntity(user)));
		} catch (Exception e) {
			throw new SportyShoesDaoException(e.getMessage());
		}
		
		return savedUser;
	}

}
