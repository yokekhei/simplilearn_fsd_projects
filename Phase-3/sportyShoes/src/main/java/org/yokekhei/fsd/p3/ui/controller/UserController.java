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
import org.yokekhei.fsd.p3.Common;
import org.yokekhei.fsd.p3.dto.User;
import org.yokekhei.fsd.p3.service.SportyShoesServiceException;
import org.yokekhei.fsd.p3.service.UserService;
import org.yokekhei.fsd.p3.ui.View;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("guest", new User());
		
		return View.V_USER_HOME;
	}
	
	@PostMapping("/login")
	public String loginForm(@Valid @ModelAttribute("user") User user,
			BindingResult result,
			ModelMap modelMap,
			HttpServletRequest request) throws SportyShoesServiceException {
		if (result.hasErrors()) {
			modelMap.addAttribute("user", new User(user.getEmail()));
			modelMap.addAttribute("guest", new User());
			modelMap.addAttribute("userFailMsg", result.getAllErrors().get(0).getDefaultMessage());
			return View.V_USER_HOME;
		}
		
		User loginUser = service.login(user.getEmail(), user.getPassword());
		
		HttpSession session = request.getSession(true);
		session.setAttribute("loginUser", loginUser);
		
		return "redirect:/" + View.C_USER_CATALOG;
	}
	
	@GetMapping("/logout")
	public String logout(Model model, HttpServletRequest request) {
		request.getSession(false).invalidate();
		model.addAttribute("user", new User());
		model.addAttribute("guest", new User());
		
		return View.V_USER_HOME;
	}
	
	@PostMapping("/register")
	public String registerForm(@Valid @ModelAttribute("guest") User user,
			BindingResult result,
			ModelMap modelMap) throws SportyShoesServiceException {
		if (result.hasErrors()) {
			modelMap.addAttribute("user", new User());
			modelMap.addAttribute("guest", new User());
			modelMap.addAttribute("userFailMsg", result.getAllErrors().get(0).getDefaultMessage());
			return View.V_USER_HOME;
		}
		
		user.setRole(Common.ROLE_USER);
		user.setEnabled(true);
		
		User loginUser = service.register(user);
		
		modelMap.addAttribute("user", new User(loginUser.getEmail()));
		modelMap.addAttribute("guest", new User());
		modelMap.addAttribute("success", "Congratulations! You have signed up successfully.");
		
		return View.V_USER_HOME;
	}
	
	@ExceptionHandler(SportyShoesServiceException.class)
	public String handlerException(SportyShoesServiceException exception, Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("guest", new User());
		model.addAttribute("userFailMsg", exception.getMessage());
		
		return View.V_USER_HOME;
	}
	
}
