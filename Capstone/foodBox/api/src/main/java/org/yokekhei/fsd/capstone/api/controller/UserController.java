package org.yokekhei.fsd.capstone.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.yokekhei.fsd.capstone.api.Common;
import org.yokekhei.fsd.capstone.api.dto.User;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxServiceException;
import org.yokekhei.fsd.capstone.api.service.UserService;

@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/user/{email}")
	@ResponseBody
	public User getUserById(@PathVariable("email") String email) throws FoodBoxServiceException {
		return service.getUser(email);
	}

	@GetMapping("/user")
	@ResponseBody
	public List<User> getUsers(@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate) throws FoodBoxServiceException {
		if (startDate != null && endDate != null) {
			LocalDateTime start = Common.toLocalDateTime(startDate + " 00:00:00");
			LocalDateTime end = Common.toLocalDateTime(endDate + " 23:59:59");
			return service.getUsersCreatedBetween(start, end);
		}

		return service.getUsers();
	}

}
