package org.yokekhei.fsd.p4.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yokekhei.fsd.p4.api.dto.User;
import org.yokekhei.fsd.p4.api.exception.OnlineTestServiceException;
import org.yokekhei.fsd.p4.api.service.CommonService;

@RestController
@RequestMapping(value = "/api",
				produces = { MediaType.APPLICATION_JSON_VALUE })
public class UserController {
	
	@Autowired
	private CommonService commonService;
	
	@PostMapping("/login")
	public User login(@Validated @RequestBody User user) throws OnlineTestServiceException {
		return commonService.login(user.getEmail(), user.getPassword(), user.getRole());
	}
	
	@PostMapping("/register")
	public User register(@Validated @RequestBody User user) throws OnlineTestServiceException {
		user.setEnabled(true);
		return commonService.register(user);
	}

}
