package org.yokekhei.fsd.p4.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yokekhei.fsd.p4.api.entity.Category;
import org.yokekhei.fsd.p4.api.exception.OnlineTestServiceException;
import org.yokekhei.fsd.p4.api.service.CommonService;

@RestController
@RequestMapping(value = "/api",
				produces = { MediaType.APPLICATION_JSON_VALUE })
public class CategoryController {

	@Autowired
	private CommonService commonService;
	
	@GetMapping("/category")
	public List<Category> getCategories() throws OnlineTestServiceException {
		return commonService.getCategories();
	}
	
}
