package org.yokekhei.fsd.p3.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yokekhei.fsd.p3.dto.Category;
import org.yokekhei.fsd.p3.dto.Product;
import org.yokekhei.fsd.p3.service.AdminService;
import org.yokekhei.fsd.p3.service.SportyShoesServiceException;
import org.yokekhei.fsd.p3.ui.View;

@Controller
public class ProductController {

	@Autowired
	private AdminService service;
	
	@GetMapping("/admin/product")
	public String productList(Model model,
			HttpServletRequest request) throws SportyShoesServiceException {
		model.addAttribute("product", new Product());
		
		List<Product> productList = service.getAllProducts();
		request.getSession(false).setAttribute("productList", productList);
		
		List<Category> categoryList = service.getAllCategories();
		request.getSession(false).setAttribute("categoryList", categoryList);
		
		return View.V_ADMIN_PRODUCT_LIST;
	}
	
	@PostMapping("/admin/product")
	public String productForm(@RequestParam(name = "action") String action,
			@Valid @ModelAttribute("product") Product product,
			BindingResult result,
			ModelMap modelMap,
			HttpServletRequest request) throws SportyShoesServiceException {
		HttpSession session = request.getSession(false);
		
		if (!isValidAction(action) || result.hasErrors()) {
			modelMap.addAttribute("product", new Product());
			
			if (result.hasErrors()) {
				session.setAttribute("alert", result.getAllErrors().get(0).getDefaultMessage());
			} else {
				session.setAttribute("alert", "Product action not supported");
			}
			
			return View.V_ADMIN_PRODUCT_LIST;
		}
		
		if (action.equals("add")) {
			service.addProduct(product);
		} else if (action.equals("update")) {
			service.updateProduct(product);
		} else if (action.equals("delete")) {
			service.deleteProduct(product.getId());
		}
		
		return "redirect:/" + View.C_ADMIN_PRODUCT;
	}
	
	@ExceptionHandler(SportyShoesServiceException.class)
	public String handlerException(SportyShoesServiceException exception,
			Model model,
			HttpServletRequest request) {
		model.addAttribute("product", new Product());
		request.getSession(false).setAttribute("alert", exception.getMessage());
		
		return View.V_ADMIN_PRODUCT_LIST;
	}
	
	private boolean isValidAction(String action) {
		return action != null &&
				(action.equals("add") ||
						action.equals("update") ||
						action.equals("delete"));
	}
	
}
