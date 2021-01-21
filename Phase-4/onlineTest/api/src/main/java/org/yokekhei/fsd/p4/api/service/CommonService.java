package org.yokekhei.fsd.p4.api.service;

import java.util.List;

import org.yokekhei.fsd.p4.api.entity.Category;
import org.yokekhei.fsd.p4.api.exception.OnlineTestServiceException;

public interface CommonService {

	List<Category> getCategories() throws OnlineTestServiceException;
	
}
