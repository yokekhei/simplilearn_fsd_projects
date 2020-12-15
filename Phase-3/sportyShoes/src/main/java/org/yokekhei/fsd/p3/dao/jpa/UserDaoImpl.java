package org.yokekhei.fsd.p3.dao.jpa;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.yokekhei.fsd.p3.Common;
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

	@Override
	public User update(User user) throws SportyShoesDaoException {
		User savedUser = null;
		
		try {
			org.yokekhei.fsd.p3.entity.User current = repository
					.findById(user.getEmail())
					.orElse(null);
			
			if (current == null) {
				return save(user);
			}
			
			if (user.getDob() == null &&
					user.getDobString() != null &&
					!user.getDobString().isEmpty()) {
				user.setDob(Common.toLocalDate(user.getDobString()));
			}
			
			org.yokekhei.fsd.p3.entity.User entity = mapper.toEntity(user);
			current.setDob(entity.getDob());
			current.setEmail(entity.getEmail());
			current.setFirstName(entity.getFirstName());
			current.setLastName(entity.getLastName());
			current.setPassword(entity.getPassword());
			
			savedUser = mapper.toDto(repository.save(current));
		} catch (Exception e) {
			throw new SportyShoesDaoException(e.getMessage());
		}
		
		return savedUser;
	}

}
