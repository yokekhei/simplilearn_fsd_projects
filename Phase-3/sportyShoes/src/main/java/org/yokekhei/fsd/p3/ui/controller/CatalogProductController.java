package org.yokekhei.fsd.p3.ui.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yokekhei.fsd.p3.dto.BagItem;
import org.yokekhei.fsd.p3.dto.Product;
import org.yokekhei.fsd.p3.service.SportyShoesServiceException;
import org.yokekhei.fsd.p3.service.UserService;
import org.yokekhei.fsd.p3.ui.View;

@Controller
public class CatalogProductController {

	@Autowired
	UserService service;
	
	@GetMapping("/catalog/product")
	public String catalogProduct(@RequestParam(name = "id") Long productId,
			HttpServletRequest request,
			Model model) throws SportyShoesServiceException {
		Product product = service.getProduct(productId);
		model.addAttribute("product", product);
		model.addAttribute("bagItem", new BagItem());
		
		return View.V_USER_PRODUCT;
	}
	
	@GetMapping("/catalog/product/image")
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
		
		return "redirect:/" + View.C_USER_CATALOG;
	}
	
}
