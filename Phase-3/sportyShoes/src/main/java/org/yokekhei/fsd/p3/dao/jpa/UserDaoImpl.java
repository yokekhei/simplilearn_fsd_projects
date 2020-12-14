package org.yokekhei.fsd.p3.dao.jpa;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
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
	public User getUser(String email, String password) {
		return repository.findByEmailAndPassword(email, password)
				.map(entity -> mapper.toDto(entity))
				.orElse(null);
	}

}
