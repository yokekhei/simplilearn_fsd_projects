package org.yokekhei.fsd.p3.ui.controller;

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
import org.yokekhei.fsd.p3.dto.User;
import org.yokekhei.fsd.p3.service.AdminService;
import org.yokekhei.fsd.p3.service.SportyShoesServiceException;
import org.yokekhei.fsd.p3.ui.View;

@Controller
public class PasswordController {
	
	@Autowired
	private AdminService service;

	@GetMapping("/admin/password")
	public String changePassword(Model model,
			HttpServletRequest request) {
		User loginUser = (User) request.getSession(false).getAttribute("loginUser");
		model.addAttribute("user", new User(loginUser.getEmail()));
		
		return View.V_ADMIN_CHANGE_PASSWORD;
	}
	
	@PostMapping("/admin/password")
	public String changePasswordForm(@RequestParam(name = "action") String action,
			@Valid @ModelAttribute("user") User user,
			BindingResult result,
			ModelMap modelMap,
			HttpServletRequest request) throws SportyShoesServiceException {
		HttpSession session = request.getSession(false);
		User loginUser = (User) session.getAttribute("loginUser");
		
		if (!action.equals("change") || result.hasErrors()) {
			modelMap.addAttribute("user", new User(loginUser.getEmail()));
			
			if (result.hasErrors()) {
				session.setAttribute("passwordFailure", result.getAllErrors().get(0).getDefaultMessage());
			} else {
				session.setAttribute("passwordFailure", "Password action not supported");
			}
			
			return View.V_ADMIN_CHANGE_PASSWORD;
		}
		
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			session.setAttribute("changePasswordStatus", "notMatch");
		} else if (user.getPassword().equals(loginUser.getPassword())) {
			session.setAttribute("changePasswordStatus", "same");
		} else {
			loginUser = service.changePassword(loginUser, user.getPassword());
			session.setAttribute("loginUser", loginUser);
			session.setAttribute("changePasswordStatus", "success");
		}
		
		return "redirect:/" + View.C_ADMIN_PASSWORD;
	}
	
	@ExceptionHandler(SportyShoesServiceException.class)
	public String handlerException(SportyShoesServiceException exception, HttpServletRequest request) {
		request.getSession(false).setAttribute("passwordFailure", exception.getMessage());
		return "redirect:/" + View.C_ADMIN_PASSWORD;
	}
	
}
