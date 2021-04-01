package org.yokekhei.fsd.capstone.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.yokekhei.fsd.capstone.api.dto.ChargeRequest;
import org.yokekhei.fsd.capstone.api.dto.ChargeResponse;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxServiceException;
import org.yokekhei.fsd.capstone.api.service.StripeService;

import com.stripe.model.Charge;

@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ChargeController {

	@Autowired
	StripeService service;

	@PostMapping("/charge")
	@ResponseBody
	public ChargeResponse charge(@Validated @RequestBody ChargeRequest chargeRequest) throws FoodBoxServiceException {
		Charge charge = service.charge(chargeRequest);

		return new ChargeResponse(charge.getId(), charge.getStatus(), charge.getBalanceTransaction());
	}

}
