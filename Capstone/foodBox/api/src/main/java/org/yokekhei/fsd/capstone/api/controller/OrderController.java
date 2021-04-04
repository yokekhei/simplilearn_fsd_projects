package org.yokekhei.fsd.capstone.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.yokekhei.fsd.capstone.api.Common;
import org.yokekhei.fsd.capstone.api.dto.Order;
import org.yokekhei.fsd.capstone.api.dto.User;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxServiceException;
import org.yokekhei.fsd.capstone.api.service.OrderService;

@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
public class OrderController {

	@Autowired
	private OrderService service;

	@GetMapping("/order")
	@ResponseBody
	public List<Order> getOrders(@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate) throws FoodBoxServiceException {
		if (startDate != null && endDate != null) {
			LocalDateTime start = Common.toLocalDateTime(startDate + " 00:00:00");
			LocalDateTime end = Common.toLocalDateTime(endDate + " 23:59:59");
			return service.getOrdersCreatedBetween(start, end);
		}

		return service.getOrders();
	}

	@GetMapping("/order/user/{email}")
	@ResponseBody
	public List<Order> getOrderByUserAndDayRange(@PathVariable("email") String email,
			@RequestParam(required = false) Integer days) throws FoodBoxServiceException {
		User user = new User(email);

		if (days != null) {
			LocalDateTime end = LocalDateTime.now();
			LocalDateTime start = end.minusDays(days);
			return service.getOrdersByUserAndCreatedBetween(user, start, end);
		}

		return service.getOrdersByUser(user);
	}

	@GetMapping("/order/{id}")
	@ResponseBody
	public Order getOrderById(@PathVariable("id") Long id) throws FoodBoxServiceException {
		return service.getOrder(id);
	}

	@PostMapping("/order")
	@ResponseBody
	public Order createOrder(@Validated @RequestBody Order order) throws FoodBoxServiceException {
		return service.createOrder(order);
	}

	@PutMapping("/order")
	@ResponseBody
	public Order updateOrder(@Validated @RequestBody Order order) throws FoodBoxServiceException {
		return service.updateOrder(order);
	}

	@DeleteMapping("/order/{id}")
	@ResponseBody
	public Order deleteOrder(@PathVariable("id") Long id) throws FoodBoxServiceException {
		return service.deleteOrder(id);
	}

}
