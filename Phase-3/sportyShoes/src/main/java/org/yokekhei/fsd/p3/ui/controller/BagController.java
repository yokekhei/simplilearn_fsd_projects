package org.yokekhei.fsd.p3.ui.controller;

import java.math.BigDecimal;

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
import org.yokekhei.fsd.p3.dto.Product;
import org.yokekhei.fsd.p3.service.SportyShoesServiceException;
import org.yokekhei.fsd.p3.service.UserService;
import org.yokekhei.fsd.p3.ui.View;

@Controller
public class BagController {

	@Autowired
	UserService service;
	
	@GetMapping("/bag")
	public String bag(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Bag bag = (Bag) session.getAttribute("bag");
		
		if (bag == null) {
			Bag newBag = new Bag();
			session.setAttribute("bag", newBag);
		}
		
		model.addAttribute("bagItem", new BagItem());
		
		return View.V_USER_BAG;
	}
	
	@PostMapping("/bag")
	public String bagForm(@RequestParam(name = "action") String action,
			@RequestParam(name = "productId") Long productId,
			@Valid @ModelAttribute("bagItem") BagItem bagItem,
			BindingResult result,
			HttpServletRequest request) throws SportyShoesServiceException {
		HttpSession session = request.getSession(false);
		
		if (!isValidAction(action) || result.hasErrors()) {
			if (result.hasErrors()) {
				session.setAttribute("alert", result.getAllErrors().get(0).getDefaultMessage());
			} else {
				session.setAttribute("alert", "Bag action not supported");
			}
			
			return "redirect:/" + View.C_USER_CATALOG_PRODUCT + "?id=" + productId;
		}
		
		Bag bag = (Bag) request.getSession(false).getAttribute("bag");
		
		if (bag == null) {
			bag = new Bag();
		}
		
		if (action.equals("add")) {
			boolean exist = false;
			
			for (BagItem bi : bag.getItems()) {
				if (bi.getProduct().getId().equals(productId)) {
					bi.setQuantity(bi.getQuantity() + 1);
					bi.setTotalPrice(bi.getTotalPrice().add(bi.getProduct().getPrice()));
					exist = true;
					break;
				}
			}
			
			if (!exist) {
				Product product = service.getProduct(productId);
				bagItem.setProduct(product);
				bag.getItems().add(bagItem);
			}
		} else if (action.equals("update")) {
			for (BagItem bi : bag.getItems()) {
				if (bi.getProduct().getId().equals(productId)) {
					bi.setQuantity(bagItem.getQuantity());
					bi.setTotalPrice(bi.getProduct().getPrice().multiply(new BigDecimal(bi.getQuantity())));
					break;
				}
			}
		} else if (action.equals("delete")) {
			bag.getItems().remove(bagItem);
		}
		
		bag.resetTotalPrice();
		
		for (BagItem item : bag.getItems()) {
			bag.setTotalPrice(bag.getTotalPrice().add(item.getTotalPrice()));
		}
		
		session.setAttribute("bag", bag);
		
		return "redirect:/" + View.C_USER_BAG;
	}
	
	@ExceptionHandler(SportyShoesServiceException.class)
	public String handlerException(SportyShoesServiceException exception,
			HttpServletRequest request) {
		request.getSession(false).setAttribute("alert", exception.getMessage());
		
		return "redirect:/" + View.C_USER_BAG;
	}
	
	private boolean isValidAction(String action) {
		return action != null &&
				(action.equals("add") ||
						action.equals("update") ||
						action.equals("delete"));
	}
	
}
