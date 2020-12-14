package org.yokekhei.fsd.p3.dao.jpa;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.yokekhei.fsd.p3.dao.UserDao;
import org.yokekhei.fsd.p3.entity.User;

@Repository
@Qualifier("jpa")
public class UserDaoImpl implements UserDao {

	@Resource
	private UserRepository repository;
	
	@Override
	public User getUser(String email, String password) {
		return repository.findByEmailAndPassword(email, password)
				.orElse(null);
	}

}
