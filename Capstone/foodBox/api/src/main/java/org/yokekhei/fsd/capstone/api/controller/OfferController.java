package org.yokekhei.fsd.capstone.api.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.yokekhei.fsd.capstone.api.dto.Offer;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxServiceException;
import org.yokekhei.fsd.capstone.api.service.OfferService;

@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
public class OfferController {

	@Autowired
	OfferService service;

	@GetMapping("/offer")
	@ResponseBody
	public List<Offer> getOffers() throws FoodBoxServiceException {
		return service.getOffers();
	}

	@GetMapping("/offer/{id}")
	@ResponseBody
	public Offer getOfferById(@PathVariable("id") Long id) throws FoodBoxServiceException {
		return service.getOffer(id);
	}

	@PostMapping("/offer")
	@ResponseBody
	public Offer createOffer(@Validated @RequestBody Offer offer) throws FoodBoxServiceException {
		return service.createOffer(offer);
	}

	@PutMapping("/offer")
	@ResponseBody
	public Offer updateOffer(@Validated @RequestBody Offer offer) throws FoodBoxServiceException {
		return service.updateOffer(offer);
	}

	@DeleteMapping("/offer/{id}")
	@ResponseBody
	public Offer deleteOffer(@PathVariable("id") Long id) throws FoodBoxServiceException {
		return service.deleteOffer(id);
	}

}
