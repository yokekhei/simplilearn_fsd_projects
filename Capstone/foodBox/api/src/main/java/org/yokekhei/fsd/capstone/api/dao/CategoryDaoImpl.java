package org.yokekhei.fsd.capstone.api.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Repository;
import org.yokekhei.fsd.capstone.api.dto.Category;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxDaoException;
import org.yokekhei.fsd.capstone.api.mapper.CategoryMapper;
import org.yokekhei.fsd.capstone.api.repository.CategoryRepository;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Resource
	private CategoryRepository repository;

	@Resource
	private CategoryMapper mapper;

	@Override
	public Category getCategory(Long id) throws FoodBoxDaoException {
		Category category = null;

		try {
			category = repository.findById(id)
					.map(entity -> mapper.toDto(entity))
					.orElse(null);
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return category;
	}

	@Override
	public List<Category> getCategories() throws FoodBoxDaoException {
		List<Category> categories = null;

		try {
			categories = repository.findAll().stream()
					.map(entity -> mapper.toDto(entity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return categories;
	}

	@Override
	public Category save(Category category) throws FoodBoxDaoException {
		Category savedCategory = null;

		try {
			org.yokekhei.fsd.capstone.api.entity.Category entity = mapper.toEntity(category);

			if (category.getImage() != null && !category.getImage().isEmpty()) {
				entity.setImage(IOUtils.toByteArray(category.getImage().getInputStream()));
			}

			savedCategory = mapper.toDto(repository.save(entity));
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return savedCategory;
	}
	
	@Override
	public Category update(Category category) throws FoodBoxDaoException {
		Category savedCategory = null;
		
		try {
			org.yokekhei.fsd.capstone.api.entity.Category current =
					repository.findById(category.getId()).orElse(null);
			
			if (current == null) {
				return save(category);
			}
			
			org.yokekhei.fsd.capstone.api.entity.Category entity = mapper.toEntity(category);
			
			current.setName(entity.getName());
			current.setEnabled(entity.getEnabled());
			
			if (category.getImage() != null && !category.getImage().isEmpty()) {
				current.setImage(IOUtils.toByteArray(category.getImage().getInputStream()));
			}
			
			savedCategory = mapper.toDto(repository.save(current));
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}
		
		return savedCategory;
	}

	@Override
	public void remove(Long id) throws FoodBoxDaoException {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}
	}

	@Override
	public void setEnabled(Long id, Boolean enabled) throws FoodBoxDaoException {
		try {
			repository.setEnabled(id, enabled);
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}
	}

	@Override
	public byte[] getCategoryImage(Long id) throws FoodBoxDaoException {
		byte[] image = null;

		try {
			org.yokekhei.fsd.capstone.api.entity.Category category =
					repository.findWithImageAttachedById(id);
			image = category.getImage();
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return image;
	}

}
