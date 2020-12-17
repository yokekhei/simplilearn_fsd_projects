package org.yokekhei.fsd.p3.ui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.yokekhei.fsd.p3.Common;
import org.yokekhei.fsd.p3.dto.Payment;
import org.yokekhei.fsd.p3.service.UserService;
import org.yokekhei.fsd.p3.ui.View;

@Controller
public class CheckoutController {

	@Autowired
	UserService service;
	
	@GetMapping("/checkout")
	public String checkout(Model model, HttpServletRequest request) {
		model.addAttribute("payment", new Payment());
		model.addAttribute("currentYear", Integer.parseInt(Common.getCurrentYear(Common.YEAR_FORMAT)));
		
		return View.V_USER_PAYMENT;
	}
	
}
