package org.yokekhei.fsd.p4.api.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.yokekhei.fsd.p4.api.Common;
import org.yokekhei.fsd.p4.api.dao.CategoryDao;
import org.yokekhei.fsd.p4.api.dao.UserDao;
import org.yokekhei.fsd.p4.api.dto.Category;
import org.yokekhei.fsd.p4.api.dto.User;
import org.yokekhei.fsd.p4.api.exception.OnlineTestServiceException;

@Service
public class CommonServiceImpl implements CommonService {

	@Resource
	private CategoryDao categoryDao;
	
	@Resource
	private UserDao userDao;
	
	@Override
	public List<Category> getCategories() throws OnlineTestServiceException {
		List<Category> categories = null;
		
		try {
			categories = categoryDao.getCategories();
		} catch (Exception e) {
			throw new OnlineTestServiceException(e.getMessage());
		}
		
		return categories;
	}

	@Override
	public User login(String email, String password, String role) throws OnlineTestServiceException {
		User user = null;
		
		try {
			user = userDao.getUser(email, password);
		} catch (Exception e) {
			throw new OnlineTestServiceException(e.getMessage());
		}
		
		if (user == null) {
			throw new OnlineTestServiceException(
					Common.SV_INVALID_CREDENTIAL,"Invalid credentials");
		} else if (!user.getEnabled()) {
			throw new OnlineTestServiceException(
					Common.SV_PERMISSION_DISABLED, "User permission is disabled");
		} else if (user.getRole().equals(Common.ROLE_TESTEE) &&
				(role.equals(Common.ROLE_ADMIN) || role.equals(Common.ROLE_TESTER))) {
			throw new OnlineTestServiceException(
					Common.SV_INVALID_PRIVILEGES, "Invalid user privileges");
		}
		
		return user;
	}

	@Override
	public User register(User user) throws OnlineTestServiceException {
		User savedUser = null;
		
		try {
			User registeredUser = userDao.getUser(user.getEmail());
			
			if (registeredUser != null) {
				throw new OnlineTestServiceException(
						Common.SV_USER_ALREADY_EXISTS, "User already exists");
			}
			
			savedUser = userDao.save(user);
		} catch (OnlineTestServiceException e) {
			throw new OnlineTestServiceException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new OnlineTestServiceException(e.getMessage());
		}
		
		return savedUser;
	}

}
