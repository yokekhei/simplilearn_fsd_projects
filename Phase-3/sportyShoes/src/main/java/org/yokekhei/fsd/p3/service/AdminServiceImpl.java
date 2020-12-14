package org.yokekhei.fsd.p3.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.yokekhei.fsd.p3.Common;
import org.yokekhei.fsd.p3.dao.UserDao;
import org.yokekhei.fsd.p3.entity.User;

@Service
public class AdminServiceImpl implements AdminService {

	@Resource
	@Qualifier("jpa")
	private UserDao userDao;
	
	@Override
	public void login(String email, String password) throws SportyShoesServiceException {
		User user = userDao.getUser(email, password);
		
		if (user == null) {
			throw new SportyShoesServiceException("Invalid credentials");
		} else if (!user.getRole().equals(Common.ROLE_ADMIN)) {
			throw new SportyShoesServiceException("Insufficient administrator privileges");
		} else if (!user.isEnabled()) {
			throw new SportyShoesServiceException("Administrator permission is disabled");
		}
	}

}
