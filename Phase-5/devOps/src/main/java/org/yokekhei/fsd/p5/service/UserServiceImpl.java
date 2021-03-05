package org.yokekhei.fsd.p5.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.yokekhei.fsd.p5.dao.UserDao;
import org.yokekhei.fsd.p5.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public User login(String email, String password, String role) throws DevOpsServiceException {
		User user = null;

		try {
			user = userDao.getUser(email, password);

			if (user == null) {
				throw new DevOpsServiceException("Invalid credentials");
			} else if (!user.getRole().equals(role)) {
				throw new DevOpsServiceException("Invalid user privileges");
			} else if (!user.getEnabled()) {
				throw new DevOpsServiceException("User permission is disabled");
			}
		} catch (Exception e) {
			throw new DevOpsServiceException(e.getMessage());
		}

		return user;
	}

	@Override
	public User register(User user) throws DevOpsServiceException {
		User savedUser = null;

		try {
			User registeredUser = userDao.getUser(user.getEmail());

			if (registeredUser != null) {
				throw new DevOpsServiceException("User already exists");
			}

			savedUser = userDao.save(user);
		} catch (Exception e) {
			throw new DevOpsServiceException(e.getMessage());
		}

		return savedUser;
	}

	@Override
	public User changePassword(User user, String newPassword) throws DevOpsServiceException {
		User savedUser = null;

		try {
			user.setPassword(newPassword);
			savedUser = userDao.update(user);
		} catch (Exception e) {
			throw new DevOpsServiceException(e.getMessage());
		}

		return savedUser;
	}

}
