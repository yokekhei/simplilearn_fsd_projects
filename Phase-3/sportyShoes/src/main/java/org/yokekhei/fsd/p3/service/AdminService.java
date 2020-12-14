package org.yokekhei.fsd.p3.service;

import org.yokekhei.fsd.p3.dto.User;

public interface AdminService {

	User login(String email, String password) throws SportyShoesServiceException;
	User changePassword(User user, String newPassword) throws SportyShoesServiceException;
	
}
