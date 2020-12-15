package org.yokekhei.fsd.p3.service;

import org.yokekhei.fsd.p3.dto.User;

public interface UserService {

	User login(String email, String password) throws SportyShoesServiceException;
	User register(User user) throws SportyShoesServiceException;
	
}
