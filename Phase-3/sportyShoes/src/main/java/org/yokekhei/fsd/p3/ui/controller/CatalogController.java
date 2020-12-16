package org.yokekhei.fsd.p3.ui.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yokekhei.fsd.p3.dto.Category;
import org.yokekhei.fsd.p3.dto.Product;
import org.yokekhei.fsd.p3.service.SportyShoesServiceException;
import org.yokekhei.fsd.p3.service.UserService;
import org.yokekhei.fsd.p3.ui.View;

@Controller
public class CatalogController {

	private static final int COL_NUM = 3;
	
	@Autowired
	UserService service;
	
	@GetMapping("/catalog")
	public String catalogList(@RequestParam(required = false, name = "categoryId") Long categoryId,
			HttpServletRequest request) throws SportyShoesServiceException {
		List<Category> categoryList = service.getAllCategories();
		request.getSession(false).setAttribute("categoryList", categoryList);
		
		int rowNumber = 0;
		List<Product> products = null;
		
		if (categoryId != null) {
			Category category = categoryList
					.stream()
					.filter(c -> c.getId().equals(categoryId))
					.findFirst()
					.orElse(null);
			
			if (category == null) {
				throw new SportyShoesServiceException("Invalid category id " + categoryId);
			}
			
			request.setAttribute("catalogTitle", category.getName());
			products = service.getProductsByCategory(categoryId);
		} else if (!categoryList.isEmpty()) {
			request.setAttribute("catalogTitle", categoryList.get(0).getName());
			products = service.getProductsByCategory(categoryList.get(0).getId());
		}
		
		List<List<Product>> productList = new ArrayList<>();
		
		if (products.size() > 0) {
			rowNumber = (products.size() / COL_NUM) + (products.size() % COL_NUM) - 1;
			
			int index = 0;
			int count = products.size();
			
			for (int row=0; row<=rowNumber; row++) {
				List<Product> rowProducts = new ArrayList<>();
				
				for (int col=0; col<COL_NUM  && count > 0; col++) {
					Product product = products.get(index++);
					rowProducts.add(product);
					count--;
				}
				
				productList.add(rowProducts);
			}
		}
		
		request.setAttribute("productList", productList);
		
		return View.V_USER_CATALOG_LIST;
	}
	
	@GetMapping("/catalog/image")
	  public void showImage(@RequestParam("productId") Long productId,
			  HttpServletRequest request,
			  HttpServletResponse response) throws SportyShoesServiceException {
		try {        
		    response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		    
		    byte[] picture = service.getProductPicture(productId);
		    
		    if (picture == null) {
		    	Resource resource = new ClassPathResource(View.DEFAULT_CATALOG_PRODUCT_IMAGE);
		    	picture = IOUtils.toByteArray(resource.getInputStream());
		    }
		    
		    response.getOutputStream().write(picture);
		    response.getOutputStream().close();
		} catch (IOException e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
	}
	
	@ExceptionHandler(SportyShoesServiceException.class)
	public String handlerException(SportyShoesServiceException exception,
			HttpServletRequest request) {
		request.getSession(false).setAttribute("alert", exception.getMessage());
		
		return View.V_USER_CATALOG_LIST;
	}
	
}
