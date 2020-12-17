package org.yokekhei.fsd.p3.service;

import java.util.List;

import org.yokekhei.fsd.p3.dto.Category;
import org.yokekhei.fsd.p3.dto.Payment;
import org.yokekhei.fsd.p3.dto.Product;
import org.yokekhei.fsd.p3.dto.User;

public interface UserService {

	User login(String email, String password) throws SportyShoesServiceException;
	User register(User user) throws SportyShoesServiceException;
	
	List<Category> getAllCategories() throws SportyShoesServiceException;
	
	List<Product> getProductsByCategory(Long categoryId) throws SportyShoesServiceException;
	byte[] getProductPicture(Long productId) throws SportyShoesServiceException;
	Product getProduct(Long productId) throws SportyShoesServiceException;
	
	Payment pay(Payment payment) throws SportyShoesServiceException;
	Payment getPayment(Long paymentId) throws SportyShoesServiceException;
	
}
