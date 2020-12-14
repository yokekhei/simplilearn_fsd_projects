package org.yokekhei.fsd.p3.dao;

import java.util.List;

import org.yokekhei.fsd.p3.dto.Category;

public interface CategoryDao {

	List<Category> getAllCategories() throws SportyShoesDaoException;
	Category getCategory(Long id) throws SportyShoesDaoException;
	Category save(Category category) throws SportyShoesDaoException;
	void remove(Long id) throws SportyShoesDaoException;
	
}
