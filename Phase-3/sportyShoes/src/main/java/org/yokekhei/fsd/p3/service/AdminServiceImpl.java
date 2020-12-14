package org.yokekhei.fsd.p3.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.yokekhei.fsd.p3.Common;
import org.yokekhei.fsd.p3.dao.UserDao;
import org.yokekhei.fsd.p3.dto.User;

@Service
public class AdminServiceImpl implements AdminService {

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
			} else if (!user.getRole().equals(Common.ROLE_ADMIN)) {
				throw new SportyShoesServiceException("Insufficient administrator privileges");
			} else if (!user.getEnabled()) {
				throw new SportyShoesServiceException("Administrator permission is disabled");
			}
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return user;
	}

	@Override
	public User changePassword(User user, String newPassword) throws SportyShoesServiceException {
		User savedUser = null;
		
		try {
			user.setPassword(newPassword);
			savedUser = userDao.save(user);
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return savedUser;
	}

}