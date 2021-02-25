package org.yokekhei.fsd.capstone.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.yokekhei.fsd.capstone.api.dto.User;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxServiceException;
import org.yokekhei.fsd.capstone.api.service.AuthenticationService;

@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
public class AuthenticationController {

	@Autowired
	private AuthenticationService service;

	@PostMapping("/login")
	@ResponseBody
	public User login(@Validated @RequestBody User user) throws FoodBoxServiceException {
		return service.login(user.getEmail(), user.getPassword(), user.getRole());
	}

	@PostMapping("/register")
	@ResponseBody
	public User register(@Validated @RequestBody User user) throws FoodBoxServiceException {
		user.setEnabled(true);
		return service.register(user);
	}

}
