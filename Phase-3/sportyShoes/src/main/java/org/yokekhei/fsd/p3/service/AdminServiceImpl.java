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
public class AdminServiceImpl implements AdminService {

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
			} else if (!user.getRole().equals(Common.ROLE_ADMIN)) {
				throw new SportyShoesServiceException("Insufficient administrator privileges");
			} else if (!user.getEnabled()) {
				throw new SportyShoesServiceException("Administrator permission is disabled");
			}
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return user;
	}

	@Override
	public User changePassword(User user, String newPassword) throws SportyShoesServiceException {
		User savedUser = null;
		
		try {
			user.setPassword(newPassword);
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
	public void addCategory(Category data) throws SportyShoesServiceException {
		try {
			categoryDao.save(data);
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
	}

	@Override
	public void updateCategory(Category data) throws SportyShoesServiceException {
		try {
			categoryDao.save(data);
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
	}

	@Override
	public void deleteCategory(Long id) throws SportyShoesServiceException {
		try {
			categoryDao.remove(id);
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
	}

	@Override
	public List<Product> getAllProducts() throws SportyShoesServiceException {
		List<Product> products = null;
		
		try {
			products = productDao.getAllProducts();
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return products;
	}

	@Override
	public void addProduct(Product data) throws SportyShoesServiceException {
		try {
			if (data.getCategory() == null) {
				data.setCategory(categoryDao.getCategory(data.getCategoryId()));
			}
			
			productDao.save(data);
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
	}

	@Override
	public void updateProduct(Product data) throws SportyShoesServiceException {
		try {
			if (data.getCategory() == null) {
				data.setCategory(categoryDao.getCategory(data.getCategoryId()));
			}
			
			productDao.update(data);
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
	}

	@Override
	public void deleteProduct(Long id) throws SportyShoesServiceException {
		try {
			productDao.remove(id);
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
	}

}
