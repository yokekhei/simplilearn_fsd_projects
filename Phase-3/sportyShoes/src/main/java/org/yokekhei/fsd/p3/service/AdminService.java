package org.yokekhei.fsd.p3.service;

import java.time.LocalDateTime;
import java.util.List;

import org.yokekhei.fsd.p3.dto.Category;
import org.yokekhei.fsd.p3.dto.Product;
import org.yokekhei.fsd.p3.dto.User;

public interface AdminService {

	User login(String email, String password) throws SportyShoesServiceException;
	User changePassword(User user, String newPassword) throws SportyShoesServiceException;
	
	List<User> getAllUsers() throws SportyShoesServiceException;
	List<User> getUsersWithUserRole() throws SportyShoesServiceException;
	List<User> getAllUsersWithUserRoleCreatedBetween(LocalDateTime start, LocalDateTime end)
			throws SportyShoesServiceException;
	List<User> getUsersByFirstName(String firstName) throws SportyShoesServiceException;
	
	List<Category> getAllCategories() throws SportyShoesServiceException;
	void addCategory(Category data) throws SportyShoesServiceException;
	void updateCategory(Category data) throws SportyShoesServiceException;
	void deleteCategory(Long id) throws SportyShoesServiceException;
	
	List<Product> getAllProducts() throws SportyShoesServiceException;
	void addProduct(Product data) throws SportyShoesServiceException;
	void updateProduct(Product data) throws SportyShoesServiceException;
	void deleteProduct(Long id) throws SportyShoesServiceException;
	
}
