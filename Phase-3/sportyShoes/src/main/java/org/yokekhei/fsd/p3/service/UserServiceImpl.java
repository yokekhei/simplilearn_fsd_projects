package org.yokekhei.fsd.p3.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.yokekhei.fsd.p3.Common;
import org.yokekhei.fsd.p3.dao.CategoryDao;
import org.yokekhei.fsd.p3.dao.ProductDao;
import org.yokekhei.fsd.p3.dao.UserDao;
import org.yokekhei.fsd.p3.dto.Category;
import org.yokekhei.fsd.p3.dto.Product;
import org.yokekhei.fsd.p3.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	@Qualifier("jpa")
	private UserDao userDao;
	
	@Resource
	@Qualifier("jpa")
	private CategoryDao categoryDao;
	
	@Resource
	@Qualifier("jpa")
	private ProductDao productDao;
	
	@Override
	public User login(String email, String password) throws SportyShoesServiceException {
		User user = null;
		
		try {
			user = userDao.getUser(email, password);
			
			if (user == null) {
				throw new SportyShoesServiceException("Invalid credentials");
			} else if (!user.getRole().equals(Common.ROLE_USER)) {
				throw new SportyShoesServiceException("Invalid user privileges");
			} else if (!user.getEnabled()) {
				throw new SportyShoesServiceException("User permission is disabled");
			}
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return user;
	}
	
	@Override
	public User register(User user) throws SportyShoesServiceException {
		User savedUser = null;
		
		try {
			User registeredUser = userDao.getUser(user.getEmail());
			
			if (registeredUser != null) {
				throw new SportyShoesServiceException("User already exists");
			}
			
			if (user.getDob() == null &&
					user.getDobString() != null &&
					!user.getDobString().isEmpty()) {
				user.setDob(Common.toLocalDate(user.getDobString()));
			}
			
			savedUser = userDao.save(user);
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return savedUser;
	}

	@Override
	public List<Category> getAllCategories() throws SportyShoesServiceException {
		List<Category> categories = null;
		
		try {
			categories = categoryDao.getAllCategories();
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return categories;
	}

	@Override
	public List<Product> getProductsByCategory(Long categoryId) throws SportyShoesServiceException {
		List<Product> products = null;
		
		try {
			Category category = categoryDao.getCategory(categoryId);
			
			if (category == null) {
				throw new SportyShoesServiceException("Invalid category id " + categoryId);
			}
			
			products = productDao.getProductsByCategory(category);
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return products;
	}

	@Override
	public byte[] getProductPicture(Long productId) throws SportyShoesServiceException {
		byte[] picture = null;
		
		try {
			picture = productDao.getProductPicture(productId);
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return picture;
	}

	@Override
	public Product getProduct(Long productId) throws SportyShoesServiceException {
		Product product = null;
		
		try {
			product = productDao.getProduct(productId);
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return product;
	}

}
