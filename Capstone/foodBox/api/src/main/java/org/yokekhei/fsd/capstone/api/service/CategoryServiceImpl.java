package org.yokekhei.fsd.capstone.api.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yokekhei.fsd.capstone.api.dao.CategoryDao;
import org.yokekhei.fsd.capstone.api.dto.Category;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxServiceException;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Resource
	private CategoryDao categoryDao;

	@Override
	@Transactional
	public List<Category> getCategories(Boolean enabled) throws FoodBoxServiceException {
		List<Category> categories = null;

		try {
			categories = categoryDao.getCategories();

			if (enabled != null) {
				categories = categories.stream()
						.filter(c -> c.getEnabled() == enabled)
						.collect(Collectors.toList());
			}
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return categories;
	}

	@Override
	@Transactional
	public Category getCategory(Long id) throws FoodBoxServiceException {
		Category category = null;

		try {
			category = categoryDao.getCategory(id);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return category;
	}

	@Override
	@Transactional
	public Category createCategory(Category category) throws FoodBoxServiceException {
		Category savedCategory = null;

		try {
			savedCategory = categoryDao.save(category);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return savedCategory;
	}

	@Override
	@Transactional
	public Category updateCategory(Category category) throws FoodBoxServiceException {
		Category savedCategory = null;

		try {
			if (category.getId() == null) {
				throw new FoodBoxServiceException("Category id cannot be null.");
			}

			if (categoryDao.getCategory(category.getId()) == null) {
				throw new FoodBoxServiceException("Category " + category.getId() + "not found.");
			}

			savedCategory = categoryDao.update(category);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return savedCategory;
	}

	@Override
	@Transactional
	public Category deleteCategory(Long id) throws FoodBoxServiceException {
		Category deletedCategory = null;

		try {
			if (id == null) {
				throw new FoodBoxServiceException("Category id cannot be null.");
			}

			deletedCategory = categoryDao.getCategory(id);

			if (deletedCategory == null) {
				throw new FoodBoxServiceException("Category " + id + "not found.");
			}

			categoryDao.remove(id);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return deletedCategory;
	}

	@Override
	@Transactional
	public Category setEnabled(Long id, Boolean enabled) throws FoodBoxServiceException {
		Category updatedCategory = null;

		try {
			if (id == null) {
				throw new FoodBoxServiceException("Category id cannot be null.");
			}

			updatedCategory = categoryDao.getCategory(id);

			if (updatedCategory == null) {
				throw new FoodBoxServiceException("Category " + id + "not found.");
			}

			categoryDao.setEnabled(id, enabled);
			updatedCategory.setEnabled(enabled);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return updatedCategory;
	}

	@Override
	@Transactional
	public byte[] getCategoryImage(Long id) throws FoodBoxServiceException {
		byte[] image = null;

		try {
			image = categoryDao.getCategoryImage(id);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return image;
	}

}
