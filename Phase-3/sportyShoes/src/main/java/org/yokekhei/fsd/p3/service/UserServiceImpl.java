package org.yokekhei.fsd.p3.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.yokekhei.fsd.p3.Common;
import org.yokekhei.fsd.p3.dao.UserDao;
import org.yokekhei.fsd.p3.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	@Qualifier("jpa")
	private UserDao userDao;
	
	@Override
	public User login(String email, String password) throws SportyShoesServiceException {
		User user = null;
		
		try {
			user = userDao.getUser(email, password);
			
			if (user == null) {
				throw new SportyShoesServiceException("Invalid credentials");
			} else if (!user.getRole().equals(Common.ROLE_USER)) {
				throw new SportyShoesServiceException("Invalid user privileges");
			} else if (!user.getEnabled()) {
				throw new SportyShoesServiceException("User permission is disabled");
			}
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return user;
	}
	
	@Override
	public User register(User user) throws SportyShoesServiceException {
		User savedUser = null;
		
		try {
			if (user.getDob() == null &&
					user.getDobString() != null &&
					!user.getDobString().isEmpty()) {
				user.setDob(Common.toLocalDate(user.getDobString()));
			}
			
			savedUser = userDao.save(user);
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return savedUser;
	}

}
