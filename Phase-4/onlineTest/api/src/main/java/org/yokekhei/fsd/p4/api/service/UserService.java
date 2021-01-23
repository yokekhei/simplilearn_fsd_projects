package org.yokekhei.fsd.p4.api.service;

import org.yokekhei.fsd.p4.api.dto.User;
import org.yokekhei.fsd.p4.api.exception.OnlineTestServiceException;

public interface UserService {

	User login(String email, String password, String role) throws OnlineTestServiceException;
	User register(User user) throws OnlineTestServiceException;
	
}
