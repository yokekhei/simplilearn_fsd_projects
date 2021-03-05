package org.yokekhei.fsd.p5.ui.controller;

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
import org.yokekhei.fsd.p5.Common;
import org.yokekhei.fsd.p5.dto.User;
import org.yokekhei.fsd.p5.service.DevOpsServiceException;
import org.yokekhei.fsd.p5.service.UserService;
import org.yokekhei.fsd.p5.ui.View;

@Controller
public class AdminController {

	@Autowired
	private UserService service;

	@GetMapping("/admin")
	public String home(Model model) {
		model.addAttribute("user", new User());

		return View.V_ADMIN_SIGNIN;
	}

	@PostMapping("/admin/login")
	public String loginForm(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap modelMap,
			HttpServletRequest request) throws DevOpsServiceException {
		if (result.hasErrors()) {
			modelMap.addAttribute("user", new User(user.getEmail()));
			modelMap.addAttribute("authMsg", result.getAllErrors().get(0).getDefaultMessage());
			return View.V_ADMIN_SIGNIN;
		}

		User loginUser = service.login(user.getEmail(), user.getPassword(), Common.ROLE_ADMIN);

		HttpSession session = request.getSession(true);
		session.setAttribute("loginUser", loginUser);

		return "redirect:/" + View.C_ADMIN_COURSES;
	}

	@GetMapping("/admin/logout")
	public String logout(Model model, HttpServletRequest request) {
		request.getSession(false).invalidate();
		model.addAttribute("user", new User());

		return View.V_ADMIN_SIGNIN;
	}

	@ExceptionHandler(DevOpsServiceException.class)
	public String handlerException(DevOpsServiceException exception, Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("authMsg", exception.getMessage());

		return View.V_ADMIN_SIGNIN;
	}

}
