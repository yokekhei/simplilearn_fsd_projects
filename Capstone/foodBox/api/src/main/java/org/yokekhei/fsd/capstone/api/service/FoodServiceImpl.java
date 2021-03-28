package org.yokekhei.fsd.capstone.api.service;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yokekhei.fsd.capstone.api.dao.FoodDao;
import org.yokekhei.fsd.capstone.api.dto.Category;
import org.yokekhei.fsd.capstone.api.dto.Food;
import org.yokekhei.fsd.capstone.api.dto.Foods;
import org.yokekhei.fsd.capstone.api.dto.Offer;
import org.yokekhei.fsd.capstone.api.dto.PageInfo;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxServiceException;

@Service
public class FoodServiceImpl implements FoodService {

	@Resource
	private FoodDao foodDao;

	@Override
	@Transactional
	public Foods getFoods(Boolean enabled, PageInfo pageInfo) throws FoodBoxServiceException {
		Foods foods = null;

		try {
			foods = foodDao.getFoods(pageInfo);

			if (enabled != null) {
				foods.setList(
						foods.getList().stream().filter(c -> c.getEnabled() == enabled).collect(Collectors.toList()));
			}
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return foods;
	}

	@Override
	@Transactional
	public Foods getFoodsByName(String name, Boolean enabled, PageInfo pageInfo) throws FoodBoxServiceException {
		Foods foods = null;

		try {
			foods = foodDao.getFoodsByName(name, pageInfo);

			if (enabled != null) {
				foods.setList(
						foods.getList().stream().filter(c -> c.getEnabled() == enabled).collect(Collectors.toList()));
			}
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return foods;
	}

	@Override
	@Transactional
	public Foods getFoodsByCategory(Category category, Boolean enabled, PageInfo pageInfo)
			throws FoodBoxServiceException {
		Foods foods = null;

		try {
			foods = foodDao.getFoodsByCategory(category, pageInfo);

			if (enabled != null) {
				foods.setList(
						foods.getList().stream().filter(c -> c.getEnabled() == enabled).collect(Collectors.toList()));
			}
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return foods;
	}

	@Override
	@Transactional
	public Foods getFoodsByPrice(BigDecimal price, Boolean enabled, PageInfo pageInfo) throws FoodBoxServiceException {
		Foods foods = null;

		try {
			foods = foodDao.getFoodsByPrice(price, pageInfo);

			if (enabled != null) {
				foods.setList(
						foods.getList().stream().filter(c -> c.getEnabled() == enabled).collect(Collectors.toList()));
			}
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return foods;
	}

	@Override
	@Transactional
	public Foods getFoodsByOffer(Offer offer, Boolean enabled, PageInfo pageInfo) throws FoodBoxServiceException {
		Foods foods = null;

		try {
			foods = foodDao.getFoodsByOffer(offer, pageInfo);

			if (enabled != null) {
				foods.setList(
						foods.getList().stream().filter(c -> c.getEnabled() == enabled).collect(Collectors.toList()));
			}
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return foods;
	}

	@Override
	@Transactional
	public Foods getFoodsByCategoryAndOffer(Category category, Offer offer, Boolean enabled, PageInfo pageInfo)
			throws FoodBoxServiceException {
		Foods foods = null;

		try {
			foods = foodDao.getFoodsByCategoryAndOffer(category, offer, pageInfo);

			if (enabled != null) {
				foods.setList(
						foods.getList().stream().filter(c -> c.getEnabled() == enabled).collect(Collectors.toList()));
			}
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return foods;
	}

	@Override
	public Foods searchFoods(String keyword, Boolean enabled, PageInfo pageInfo) throws FoodBoxServiceException {
		Foods foods = null;

		try {
			foods = foodDao.search(keyword, pageInfo);

			if (enabled != null) {
				foods.setList(
						foods.getList().stream().filter(c -> c.getEnabled() == enabled).collect(Collectors.toList()));
			}
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return foods;
	}

	@Override
	@Transactional
	public Food getFood(Long id) throws FoodBoxServiceException {
		Food food = null;

		try {
			food = foodDao.getFood(id);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return food;
	}

	@Override
	@Transactional
	public Food createFood(Food food) throws FoodBoxServiceException {
		Food savedFood = null;

		try {
			savedFood = foodDao.save(food);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return savedFood;
	}

	@Override
	@Transactional
	public Food updateFood(Food food) throws FoodBoxServiceException {
		Food savedFood = null;

		try {
			if (food.getId() == null) {
				throw new FoodBoxServiceException("Food id cannot be null.");
			}

			if (foodDao.getFood(food.getId()) == null) {
				throw new FoodBoxServiceException("Food " + food.getId() + "not found.");
			}

			savedFood = foodDao.update(food);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return savedFood;
	}

	@Override
	@Transactional
	public Food deleteFood(Long id) throws FoodBoxServiceException {
		Food deletedFood = null;

		try {
			if (id == null) {
				throw new FoodBoxServiceException("Food id cannot be null.");
			}

			deletedFood = foodDao.getFood(id);

			if (deletedFood == null) {
				throw new FoodBoxServiceException("Food " + id + "not found.");
			}

			foodDao.remove(id);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return deletedFood;
	}

	@Override
	@Transactional
	public Food setEnabled(Long id, Boolean enabled) throws FoodBoxServiceException {
		Food updatedFood = null;

		try {
			if (id == null) {
				throw new FoodBoxServiceException("Food id cannot be null.");
			}

			updatedFood = foodDao.getFood(id);

			if (updatedFood == null) {
				throw new FoodBoxServiceException("Food " + id + "not found.");
			}

			foodDao.setEnabled(id, enabled);
			updatedFood.setEnabled(enabled);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return updatedFood;
	}

	@Override
	@Transactional
	public byte[] getFoodImage(Long id) throws FoodBoxServiceException {
		byte[] image = null;

		try {
			image = foodDao.getFoodImage(id);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return image;
	}

}
