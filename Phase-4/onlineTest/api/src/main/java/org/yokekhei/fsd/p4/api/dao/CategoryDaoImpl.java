package org.yokekhei.fsd.p4.api.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.yokekhei.fsd.p4.api.entity.Category;
import org.yokekhei.fsd.p4.api.exception.OnlineTestDaoException;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Resource
	private CategoryRepository repository;
	
	@Override
	public List<Category> getCategories() throws OnlineTestDaoException {
		List<Category> categories = null;
		
		try {
			categories = repository.findAll();
		} catch (Exception e) {
			throw new OnlineTestDaoException(e.getMessage());
		}
		
		return categories;
	}

}
