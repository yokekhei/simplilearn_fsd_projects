package org.yokekhei.fsd.p3.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.yokekhei.fsd.p3.Common;
import org.yokekhei.fsd.p3.dao.CategoryDao;
import org.yokekhei.fsd.p3.dao.ProductDao;
import org.yokekhei.fsd.p3.dao.PurchaseDao;
import org.yokekhei.fsd.p3.dao.UserDao;
import org.yokekhei.fsd.p3.dto.Category;
import org.yokekhei.fsd.p3.dto.Product;
import org.yokekhei.fsd.p3.dto.Purchase;
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
	
	@Resource
	@Qualifier("jpa")
	private PurchaseDao purchaseDao;
	
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
			savedUser = userDao.update(user);
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return savedUser;
	}
	
	@Override
	public List<User> getAllUsers() throws SportyShoesServiceException {
		List<User> users = null;
		
		try {
			users = userDao.getAllUsers();
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return users;
	}
	
	@Override
	public List<User> getUsersWithUserRole() throws SportyShoesServiceException {
		List<User> users = null;
		
		try {
			users = userDao.getUsersWithUserRole();
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return users;
	}
	
	@Override
	public List<User> getAllUsersWithUserRoleCreatedBetween(LocalDateTime start, LocalDateTime end)
			throws SportyShoesServiceException {
		List<User> users = null;
		
		try {
			users = userDao.getAllUsersCreatedBetween(start, end)
						.stream()
						.filter(user -> user.getRole().equals(Common.ROLE_USER))
						.collect(Collectors.toList());
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return users;
	}
	
	@Override
	public List<User> getUsersByFirstName(String firstName) throws SportyShoesServiceException {
		List<User> users = null;
		
		try {
			users = userDao.getUsersByFirstName(firstName)
						.stream()
						.filter(user -> user.getRole().equals(Common.ROLE_USER))
						.collect(Collectors.toList());
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return users;
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

	@Override
	public List<Purchase> getAllPurchasesCreatedBetween(LocalDateTime start, LocalDateTime end)
			throws SportyShoesServiceException {
		List<Purchase> purchases = null;
		
		try {
			purchases = purchaseDao.getAllPurchasesCreatedBetween(start, end);
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return purchases;
	}

	@Override
	public List<Purchase> getPurchasesByCategory(Long categoryId) throws SportyShoesServiceException {
		List<Purchase> purchases = null;
		
		try {
			Category category = categoryDao.getCategory(categoryId);
			
			if (category == null) {
				throw new SportyShoesServiceException("Invalid category chosen to query purchases");
			}
			
			purchases = purchaseDao.getPurchasesByCategory(category);
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return purchases;
	}

	@Override
	public Purchase getPurchase(Long id) throws SportyShoesServiceException {
		Purchase purchase = null;
		
		try {
			purchase = purchaseDao.getPurchase(id);
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return purchase;
	}

}
