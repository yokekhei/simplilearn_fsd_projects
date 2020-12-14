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
import org.yokekhei.fsd.p3.service.AdminService;
import org.yokekhei.fsd.p3.service.SportyShoesServiceException;
import org.yokekhei.fsd.p3.ui.View;

@Controller
public class CategoryController {

	@Autowired
	private AdminService service;
	
	@GetMapping("/admin/category")
	public String categoryList(Model model,
			HttpServletRequest request) throws SportyShoesServiceException {
		model.addAttribute("category", new Category());
		
		List<Category> categoryList = service.getAllCategories();
		request.getSession(false).setAttribute("categoryList", categoryList);
		
		return View.V_ADMIN_CATEGORY_LIST;
	}
	
	@PostMapping("/admin/category")
	public String categoryForm(@RequestParam(name = "action") String action,
			@Valid @ModelAttribute("category") Category category,
			BindingResult result,
			ModelMap modelMap,
			HttpServletRequest request) throws SportyShoesServiceException {
		HttpSession session = request.getSession(false);
		
		if (!isValidAction(action) || result.hasErrors()) {
			modelMap.addAttribute("category", new Category());
			
			if (result.hasErrors()) {
				session.setAttribute("alert", result.getAllErrors().get(0).getDefaultMessage());
			} else {
				session.setAttribute("alert", "Category action not supported");
			}
			
			return View.V_ADMIN_CATEGORY_LIST;
		}
		
		if (action.equals("add")) {
			service.addCategory(category);
		} else if (action.equals("update")) {
			service.updateCategory(category);
		} else if (action.equals("delete")) {
			service.deleteCategory(category.getId());
		}
		
		return "redirect:/" + View.C_ADMIN_CATEGORY;
	}
	
	@ExceptionHandler(SportyShoesServiceException.class)
	public String handlerException(SportyShoesServiceException exception,
			Model model,
			HttpServletRequest request) {
		model.addAttribute("category", new Category());
		request.getSession(false).setAttribute("alert", exception.getMessage());
		
		return View.V_ADMIN_CATEGORY_LIST;
	}
	
	private boolean isValidAction(String action) {
		return action != null &&
				(action.equals("add") ||
						action.equals("update") ||
						action.equals("delete"));
	}
	
}
