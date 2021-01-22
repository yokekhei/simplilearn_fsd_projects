package org.yokekhei.fsd.p4.api.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.yokekhei.fsd.p4.api.dto.Category;
import org.yokekhei.fsd.p4.api.exception.OnlineTestDaoException;
import org.yokekhei.fsd.p4.api.mapper.CategoryMapper;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Resource
	private CategoryRepository repository;
	
	@Resource
	private CategoryMapper mapper;
	
	@Override
	public List<Category> getCategories() throws OnlineTestDaoException {
		List<Category> categories = null;
		
		try {
			categories = repository.findAll()
					.stream()
					.map(entity -> mapper.toDto(entity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OnlineTestDaoException(e.getMessage());
		}
		
		return categories;
	}

}
