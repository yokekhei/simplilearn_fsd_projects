package org.yokekhei.fsd.capstone.api.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yokekhei.fsd.capstone.api.Common;
import org.yokekhei.fsd.capstone.api.dao.UserDao;
import org.yokekhei.fsd.capstone.api.dto.User;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxServiceException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Resource
	private UserDao userDao;

	@Override
	@Transactional
	public User login(String email, String password, String role) throws FoodBoxServiceException {
		User user = null;

		try {
			user = userDao.getUser(email, password);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		if (user == null) {
			throw new FoodBoxServiceException(Common.SV_INVALID_CREDENTIAL, "Invalid credentials");
		} else if (!user.getEnabled()) {
			throw new FoodBoxServiceException(Common.SV_PERMISSION_DISABLED, "User permission is disabled");
		} else if (user.getRole().equals(Common.ROLE_USER) && role.equals(Common.ROLE_ADMIN)) {
			throw new FoodBoxServiceException(Common.SV_INVALID_PRIVILEGES, "Invalid admin privileges");
		} else if (user.getRole().equals(Common.ROLE_ADMIN) && role.equals(Common.ROLE_USER)) {
			throw new FoodBoxServiceException(Common.SV_INVALID_PRIVILEGES, "Invalid user privileges");
		}

		return user;
	}

	@Override
	@Transactional
	public User register(User user) throws FoodBoxServiceException {
		User savedUser = null;

		try {
			User registeredUser = userDao.getUser(user.getEmail());

			if (registeredUser != null) {
				throw new FoodBoxServiceException(Common.SV_USER_ALREADY_EXISTS, "User already exists");
			}

			savedUser = userDao.save(user);
		} catch (FoodBoxServiceException e) {
			throw new FoodBoxServiceException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return savedUser;
	}

}
