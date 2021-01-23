package org.yokekhei.fsd.p4.api.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.yokekhei.fsd.p4.api.dao.CategoryDao;
import org.yokekhei.fsd.p4.api.dto.Category;
import org.yokekhei.fsd.p4.api.exception.OnlineTestServiceException;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Resource
	private CategoryDao categoryDao;
	
	@Override
	public List<Category> getCategories() throws OnlineTestServiceException {
		List<Category> categories = null;
		
		try {
			categories = categoryDao.getCategories();
		} catch (Exception e) {
			throw new OnlineTestServiceException(e.getMessage());
		}
		
		return categories;
	}
	
}
