package org.yokekhei.fsd.capstone.api.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.yokekhei.fsd.capstone.api.dto.Category;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxServiceException;
import org.yokekhei.fsd.capstone.api.service.CategoryService;

@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
public class CategoryController {

	@Autowired
	CategoryService service;

	@GetMapping("/category")
	@ResponseBody
	public List<Category> getCategories(@RequestParam(required = false) String enabled) throws FoodBoxServiceException {
		if (enabled != null) {
			return service.getCategories(enabled.equalsIgnoreCase("true"));
		}
		
		return service.getCategories(null);
	}

	@GetMapping("/category/{id}")
	@ResponseBody
	public Category getCategoryById(@PathVariable("id") Long id) throws FoodBoxServiceException {
		return service.getCategory(id);
	}

	@PostMapping("/category")
	@ResponseBody
	public Category createCategory(@Validated @RequestBody Category category) throws FoodBoxServiceException {
		return service.createCategory(category);
	}

	@PostMapping(value = "/category/image", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	@ResponseBody
	public Category createCategoryWithImage(@Validated @RequestPart("category") Category category,
			@RequestPart("image") MultipartFile image) throws FoodBoxServiceException {
		category.setImage(image);
		return service.createCategory(category);
	}

	@PutMapping("/category")
	@ResponseBody
	public Category updateCategory(@Validated @RequestBody Category category) throws FoodBoxServiceException {
		return service.updateCategory(category);
	}

	@PutMapping(value = "/category/image", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	@ResponseBody
	public Category updateCategoryWithImage(@Validated @RequestPart("category") Category category,
			@RequestPart("image") MultipartFile image) throws FoodBoxServiceException {
		category.setImage(image);
		return service.updateCategory(category);
	}

	@PutMapping("/category/{id}/enabled")
	@ResponseBody
	public Category setEnabled(@PathVariable("id") Long id, @RequestBody Category category)
			throws FoodBoxServiceException {
		return service.setEnabled(id, category.getEnabled());
	}

	@DeleteMapping("/category/{id}")
	@ResponseBody
	public Category deleteCategory(@PathVariable("id") Long id) throws FoodBoxServiceException {
		return service.deleteCategory(id);
	}

	@GetMapping("/category/{id}/image")
	public void getImage(@PathVariable("id") Long id, HttpServletResponse response) throws FoodBoxServiceException {
		try {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			byte[] image = service.getCategoryImage(id);

			if (image == null) {
				Resource resource = new ClassPathResource(org.yokekhei.fsd.capstone.api.Common.DEFAULT_IMAGE);
				image = IOUtils.toByteArray(resource.getInputStream());
			}

			response.getOutputStream().write(image);
			response.getOutputStream().close();
		} catch (IOException e) {
			throw new FoodBoxServiceException(e.getMessage());
		}
	}

}
