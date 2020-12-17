package org.yokekhei.fsd.p3.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yokekhei.fsd.p3.dto.Bag;
import org.yokekhei.fsd.p3.dto.BagItem;
import org.yokekhei.fsd.p3.dto.Payment;
import org.yokekhei.fsd.p3.dto.Purchase;
import org.yokekhei.fsd.p3.dto.PurchaseItem;
import org.yokekhei.fsd.p3.dto.User;
import org.yokekhei.fsd.p3.service.SportyShoesServiceException;
import org.yokekhei.fsd.p3.service.UserService;
import org.yokekhei.fsd.p3.ui.View;

@Controller
public class PaymentController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/payment")
	public String paymentDone(@RequestParam(name = "id") Long paymentId,
			Model model,
			HttpServletRequest request) throws SportyShoesServiceException {
		Payment payment = service.getPayment(paymentId);
		model.addAttribute(payment);
		
		return View.V_USER_CONFIRM_ORDER;
	}
	
	@PostMapping("/payment")
	public String paymentForm(@Valid @ModelAttribute("payment") Payment payment,
			BindingResult result,
			HttpServletRequest request) throws SportyShoesServiceException {
		HttpSession session = request.getSession(false);
		
		if (result.hasErrors()) {
			session.setAttribute("alert", result.getAllErrors().get(0).getDefaultMessage());
			return "redirect:/" + View.C_USER_CHECKOUT;
		}
		
		Bag bag = (Bag) session.getAttribute("bag");
		Purchase purchaseOrder = new Purchase();
		purchaseOrder.setUser((User) session.getAttribute("loginUser"));
		purchaseOrder.setTotalPrice(bag.getTotalPrice());
		
		for (BagItem bi : bag.getItems()) {
			purchaseOrder.getItems().add(new PurchaseItem(
					bi.getProduct(),
					bi.getQuantity(),
					bi.getTotalPrice()));
		}
		
		payment.setPurchaseOrder(purchaseOrder);
		
		Payment paySlip = service.pay(payment);
		session.removeAttribute("bag");
		
		return "redirect:/" + View.C_USER_PAYMENT + "?id=" + paySlip.getId();
	}
	
	@ExceptionHandler(SportyShoesServiceException.class)
	public String handlerException(SportyShoesServiceException exception,
			HttpServletRequest request) {
		request.getSession(false).setAttribute("alert", exception.getMessage());
		
		return "redirect:/" + View.C_USER_CHECKOUT;
	}
	
}
