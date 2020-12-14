package org.yokekhei.fsd.p3.service;

import java.util.List;

import org.yokekhei.fsd.p3.dto.Category;
import org.yokekhei.fsd.p3.dto.User;

public interface AdminService {

	User login(String email, String password) throws SportyShoesServiceException;
	User changePassword(User user, String newPassword) throws SportyShoesServiceException;
	
	List<Category> getAllCategories() throws SportyShoesServiceException;
	void addCategory(Category data) throws SportyShoesServiceException;
	void updateCategory(Category data) throws SportyShoesServiceException;
	void deleteCategory(Long id) throws SportyShoesServiceException;
	
}
