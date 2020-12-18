package org.yokekhei.fsd.p3.ui.controller;

import java.time.LocalDateTime;
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
import org.yokekhei.fsd.p3.Common;
import org.yokekhei.fsd.p3.dto.User;
import org.yokekhei.fsd.p3.dto.UserSearchForm;
import org.yokekhei.fsd.p3.service.AdminService;
import org.yokekhei.fsd.p3.service.SportyShoesServiceException;
import org.yokekhei.fsd.p3.ui.View;

@Controller
public class UserReportController {

	@Autowired
	AdminService service;
	
	@GetMapping("/admin/report/user")
	public String userList(Model model, HttpServletRequest request) throws SportyShoesServiceException {
		LocalDateTime end = LocalDateTime.now();
		LocalDateTime start = end.minusDays(7);
		
		List<User> userList = service.getAllUsersWithUserRoleCreatedBetween(start, end);
		model.addAttribute("userList", userList);
		model.addAttribute("userSearchForm", new UserSearchForm());
		
		try {
			model.addAttribute("startDate", Common.toLocalDateString(start));
			model.addAttribute("endDate", Common.toLocalDateString(end));
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return View.V_ADMIN_USER_REPORT;
	}
	
	@PostMapping("/admin/report/user")
	public String userReportForm(@RequestParam(name = "action") String action,
			@Valid @ModelAttribute("userSearchForm") UserSearchForm userSearchForm,
			BindingResult result,
			ModelMap modelMap,
			HttpServletRequest request) throws SportyShoesServiceException {
		HttpSession session = request.getSession(false);
		
		if (!isValidAction(action) || result.hasErrors()) {
			modelMap.addAttribute("userSearchForm", new UserSearchForm());
			
			if (result.hasErrors()) {
				session.setAttribute("alert", result.getAllErrors().get(0).getDefaultMessage());
			} else {
				session.setAttribute("alert", "User report action not supported");
			}
			
			return "redirect:/" + View.C_ADMIN_USER_REPORT;
		}
		
		if (!userSearchForm.getFirstName().isEmpty()) {
			List<User> userList = service.getUsersByFirstName(userSearchForm.getFirstName());
			modelMap.addAttribute("userList", userList);
			modelMap.addAttribute("startDate", "");
			modelMap.addAttribute("endDate", "");
		} else {
			try {
				LocalDateTime start = Common.toLocalDateTime(userSearchForm.getFromDate()  + " 00:00:00");
				LocalDateTime end = Common.toLocalDateTime(userSearchForm.getToDate() + " 23:59:59");
				
				List<User> userList = service.getAllUsersWithUserRoleCreatedBetween(start, end);
				modelMap.addAttribute("userList", userList);
			} catch (Exception e) {
				throw new SportyShoesServiceException(e.getMessage());
			}
			
			modelMap.addAttribute("startDate", userSearchForm.getFromDate());
			modelMap.addAttribute("endDate", userSearchForm.getToDate());
		}
		
		
		modelMap.addAttribute("userSearchForm", new UserSearchForm());
		
		return View.V_ADMIN_USER_REPORT;
	}
	
	@ExceptionHandler(SportyShoesServiceException.class)
	public String handlerException(SportyShoesServiceException exception,
			HttpServletRequest request) {
		if (exception.getMessage().contains("parse")) {
			request.getSession(false).setAttribute("alert", "Date could not be parsed");
		} else {
			request.getSession(false).setAttribute("alert", exception.getMessage());
		}
		
		return "redirect:/" + View.C_ADMIN_USER_REPORT;
	}
	
	private boolean isValidAction(String action) {
		return action != null &&
				(action.equals("search"));
	}
	
}
