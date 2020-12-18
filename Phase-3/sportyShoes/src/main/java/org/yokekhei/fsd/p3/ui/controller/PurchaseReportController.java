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
import org.yokekhei.fsd.p3.dto.Category;
import org.yokekhei.fsd.p3.dto.Purchase;
import org.yokekhei.fsd.p3.dto.PurchaseFilterForm;
import org.yokekhei.fsd.p3.service.AdminService;
import org.yokekhei.fsd.p3.service.SportyShoesServiceException;
import org.yokekhei.fsd.p3.ui.View;

@Controller
public class PurchaseReportController {

	@Autowired
	AdminService service;
	
	@GetMapping("/admin/report/purchase")
	public String purchaseList(Model model, HttpServletRequest request) throws SportyShoesServiceException {
		if (request.getSession(false).getAttribute("categoryList") != null) {
			model.addAttribute("categoryList", request.getSession(false).getAttribute("categoryList"));
		} else {
			List<Category> categoryList = service.getAllCategories();
			model.addAttribute("categoryList", categoryList);
			request.getSession(false).getAttribute("categoryList");
		}
		
		LocalDateTime end = LocalDateTime.now();
		LocalDateTime start = end.minusDays(7);
		
		List<Purchase> orderList = service.getAllPurchasesCreatedBetween(start, end);
		model.addAttribute("orderList", orderList);
		model.addAttribute("purchaseFilterForm", new PurchaseFilterForm());
		
		try {
			model.addAttribute("startDate", Common.toLocalDateString(start));
			model.addAttribute("endDate", Common.toLocalDateString(end));
		} catch (Exception e) {
			throw new SportyShoesServiceException(e.getMessage());
		}
		
		return View.V_ADMIN_PURCHASE_REPORT;
	}
	
	@PostMapping("/admin/report/purchase")
	public String purchaseReportForm(@RequestParam(name = "action") String action,
			@Valid @ModelAttribute("purchaseFilterForm") PurchaseFilterForm purchaseFilterForm,
			BindingResult result,
			ModelMap modelMap,
			HttpServletRequest request) throws SportyShoesServiceException {
		HttpSession session = request.getSession(false);
		
		if (!isValidAction(action) || result.hasErrors()) {
			modelMap.addAttribute("purchaseFilterForm", new PurchaseFilterForm());
			
			if (result.hasErrors()) {
				session.setAttribute("alert", result.getAllErrors().get(0).getDefaultMessage());
			} else {
				session.setAttribute("alert", "Purchase report action not supported");
			}
			
			return "redirect:/" + View.C_ADMIN_PURCHASE_REPORT;
		}
		
		if (purchaseFilterForm.getCategoryId() != null) {
			List<Purchase> orderList = service.getPurchasesByCategory(purchaseFilterForm.getCategoryId());
			modelMap.addAttribute("orderList", orderList);
			modelMap.addAttribute("startDate", "");
			modelMap.addAttribute("endDate", "");
		} else {
			try {
				LocalDateTime start = Common.toLocalDateTime(purchaseFilterForm.getFromDate()  + " 00:00:00");
				LocalDateTime end = Common.toLocalDateTime(purchaseFilterForm.getToDate() + " 23:59:59");
				
				List<Purchase> orderList = service.getAllPurchasesCreatedBetween(start, end);
				modelMap.addAttribute("orderList", orderList);
			} catch (Exception e) {
				throw new SportyShoesServiceException(e.getMessage());
			}
			
			modelMap.addAttribute("startDate", purchaseFilterForm.getFromDate());
			modelMap.addAttribute("endDate", purchaseFilterForm.getToDate());
		}
		
		
		modelMap.addAttribute("purchaseFilterForm", new PurchaseFilterForm());
		modelMap.addAttribute("categoryList", session.getAttribute("categoryList"));
		
		return View.V_ADMIN_PURCHASE_REPORT;
	}
	
	@GetMapping("/admin/report/purchase/view")
	public String viewPurchase(@RequestParam(name = "id") Long id,
			Model model) throws SportyShoesServiceException {
		if (id == null) {
			throw new SportyShoesServiceException("id cannot be null to view purchase report");
		}
		
		Purchase order = service.getPurchase(id);
		model.addAttribute("order", order);
		
		return View.V_ADMIN_PURCHASE_VIEW;
	}
	
	@ExceptionHandler(SportyShoesServiceException.class)
	public String handlerException(SportyShoesServiceException exception,
			HttpServletRequest request) {
		if (exception.getMessage().contains("parse")) {
			request.getSession(false).setAttribute("alert", "Date could not be parsed");
		} else {
			request.getSession(false).setAttribute("alert", exception.getMessage());
		}
		
		return "redirect:/" + View.C_ADMIN_PURCHASE_REPORT;
	}
	
	private boolean isValidAction(String action) {
		return action != null &&
				(action.equals("filter"));
	}
	
}
