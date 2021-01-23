package org.yokekhei.fsd.p4.api.service;

import java.util.List;

import org.yokekhei.fsd.p4.api.dto.Category;
import org.yokekhei.fsd.p4.api.dto.User;
import org.yokekhei.fsd.p4.api.exception.OnlineTestServiceException;

public interface CommonService {

	List<Category> getCategories() throws OnlineTestServiceException;
	
	User login(String email, String password, String role) throws OnlineTestServiceException;
	User register(User user) throws OnlineTestServiceException;
	
}
