package org.yokekhei.fsd.p5.service;

import org.yokekhei.fsd.p5.dto.User;

public interface UserService {

	User login(String email, String password) throws DevOpsServiceException;

	User register(User user) throws DevOpsServiceException;

	User changePassword(User user, String newPassword) throws DevOpsServiceException;

}
