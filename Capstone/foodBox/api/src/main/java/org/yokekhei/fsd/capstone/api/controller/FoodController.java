package org.yokekhei.fsd.capstone.api.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.yokekhei.fsd.capstone.api.dto.Category;
import org.yokekhei.fsd.capstone.api.dto.Food;
import org.yokekhei.fsd.capstone.api.dto.Foods;
import org.yokekhei.fsd.capstone.api.dto.Offer;
import org.yokekhei.fsd.capstone.api.dto.PageInfo;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxServiceException;
import org.yokekhei.fsd.capstone.api.service.FoodService;

@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
public class FoodController {

	@Autowired
	FoodService service;

	@GetMapping("/food")
	@ResponseBody
	public Foods getFoods(@RequestParam(required = false) String enabled, @RequestParam int page,
			@RequestParam int size, @RequestParam(required = false) String sortBy,
			@RequestParam(required = false) String direction) throws FoodBoxServiceException {
		PageInfo pageInfo = new PageInfo(page, size, sortBy, direction);

		if (enabled != null) {
			return service.getFoods(enabled.equalsIgnoreCase("true"), pageInfo);
		}

		return service.getFoods(null, pageInfo);
	}

	@GetMapping("/food/name/{name}")
	@ResponseBody
	public Foods getFoodsByName(@PathVariable("name") String name, @RequestParam(required = false) String enabled,
			@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String sortBy,
			@RequestParam(required = false) String direction) throws FoodBoxServiceException {
		PageInfo pageInfo = new PageInfo(page, size, sortBy, direction);

		if (enabled != null) {
			return service.getFoodsByName(name, enabled.equalsIgnoreCase("true"), pageInfo);
		}

		return service.getFoodsByName(name, null, pageInfo);
	}

	@GetMapping("/food/category/{id}")
	@ResponseBody
	public Foods getFoodsByCategory(@PathVariable("id") Long id, @RequestParam(required = false) String enabled,
			@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String sortBy,
			@RequestParam(required = false) String direction) throws FoodBoxServiceException {
		PageInfo pageInfo = new PageInfo(page, size, sortBy, direction);
		Category category = new Category(id);

		if (enabled != null) {
			return service.getFoodsByCategory(category, enabled.equalsIgnoreCase("true"), pageInfo);
		}

		return service.getFoodsByCategory(category, null, pageInfo);
	}

	@GetMapping("/food/price/{price}")
	@ResponseBody
	public Foods getFoodsByPrice(@PathVariable("price") BigDecimal price,
			@RequestParam(required = false) String enabled, @RequestParam int page, @RequestParam int size,
			@RequestParam(required = false) String sortBy, @RequestParam(required = false) String direction)
			throws FoodBoxServiceException {
		PageInfo pageInfo = new PageInfo(page, size, sortBy, direction);

		if (enabled != null) {
			return service.getFoodsByPrice(price, enabled.equalsIgnoreCase("true"), pageInfo);
		}

		return service.getFoodsByPrice(price, null, pageInfo);
	}

	@GetMapping("/food/offer/{id}")
	@ResponseBody
	public Foods getFoodsByOffer(@PathVariable("id") Long id, @RequestParam(required = false) String enabled,
			@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String sortBy,
			@RequestParam(required = false) String direction) throws FoodBoxServiceException {
		PageInfo pageInfo = new PageInfo(page, size, sortBy, direction);
		Offer offer = new Offer(id);

		if (enabled != null) {
			return service.getFoodsByOffer(offer, enabled.equalsIgnoreCase("true"), pageInfo);
		}

		return service.getFoodsByOffer(offer, null, pageInfo);
	}

	@GetMapping("/food/category/{categoryId}/offer/{offerId}")
	@ResponseBody
	public Foods getFoodsByCategoryAndOffer(@PathVariable("categoryId") Long categoryId,
			@PathVariable("offerId") Long offerId, @RequestParam(required = false) String enabled,
			@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String sortBy,
			@RequestParam(required = false) String direction) throws FoodBoxServiceException {
		PageInfo pageInfo = new PageInfo(page, size, sortBy, direction);
		Category category = new Category(categoryId);
		Offer offer = new Offer(offerId);

		if (enabled != null) {
			return service.getFoodsByCategoryAndOffer(category, offer, enabled.equalsIgnoreCase("true"), pageInfo);
		}

		return service.getFoodsByOffer(offer, null, pageInfo);
	}
	
	@GetMapping("/food/search/{keyword}")
	@ResponseBody
	public Foods searchFoods(@PathVariable("keyword") String keyword,
			@RequestParam(required = false) String enabled, @RequestParam int page, @RequestParam int size,
			@RequestParam(required = false) String sortBy, @RequestParam(required = false) String direction)
			throws FoodBoxServiceException {
		PageInfo pageInfo = new PageInfo(page, size, sortBy, direction);

		if (enabled != null) {
			return service.searchFoods(keyword, enabled.equalsIgnoreCase("true"), pageInfo);
		}

		return service.searchFoods(keyword, null, pageInfo);
	}

	@GetMapping("/food/{id}")
	@ResponseBody
	public Food getFoodById(@PathVariable("id") Long id) throws FoodBoxServiceException {
		return service.getFood(id);
	}

	@PostMapping("/food")
	@ResponseBody
	public Food createFood(@Validated @RequestBody Food food) throws FoodBoxServiceException {
		return service.createFood(food);
	}

	@PostMapping(value = "/food/image", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	@ResponseBody
	public Food createFoodWithImage(@Validated @RequestPart("food") Food food,
			@RequestPart("image") MultipartFile image) throws FoodBoxServiceException {
		food.setImage(image);
		return service.createFood(food);
	}

	@PutMapping("/food")
	@ResponseBody
	public Food updateFood(@Validated @RequestBody Food food) throws FoodBoxServiceException {
		return service.updateFood(food);
	}

	@PutMapping(value = "/food/image", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	@ResponseBody
	public Food updateFoodWithImage(@Validated @RequestPart("food") Food food,
			@RequestPart("image") MultipartFile image) throws FoodBoxServiceException {
		food.setImage(image);
		return service.updateFood(food);
	}

	@PutMapping("/food/{id}/enabled")
	@ResponseBody
	public Food setEnabled(@PathVariable("id") Long id, @RequestBody Food food) throws FoodBoxServiceException {
		return service.setEnabled(id, food.getEnabled());
	}

	@DeleteMapping("/food/{id}")
	@ResponseBody
	public Food deleteFood(@PathVariable("id") Long id) throws FoodBoxServiceException {
		return service.deleteFood(id);
	}

	@GetMapping("/food/{id}/image")
	public void getImage(@PathVariable("id") Long id, HttpServletResponse response) throws FoodBoxServiceException {
		try {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			byte[] image = service.getFoodImage(id);

			if (image == null) {
				Resource resource = new ClassPathResource(org.yokekhei.fsd.capstone.api.Common.DEFAULT_IMAGE);
				image = IOUtils.toByteArray(resource.getInputStream());
			}

			response.getOutputStream().write(image);
			response.getOutputStream().close();
		} catch (IOException e) {
			throw new FoodBoxServiceException(e.getMessage());
		}
	}

}
