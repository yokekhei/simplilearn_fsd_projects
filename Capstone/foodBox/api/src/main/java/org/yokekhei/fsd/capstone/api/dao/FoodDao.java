package org.yokekhei.fsd.capstone.api.dao;

import java.math.BigDecimal;

import org.yokekhei.fsd.capstone.api.dto.Category;
import org.yokekhei.fsd.capstone.api.dto.Food;
import org.yokekhei.fsd.capstone.api.dto.Foods;
import org.yokekhei.fsd.capstone.api.dto.Offer;
import org.yokekhei.fsd.capstone.api.dto.PageInfo;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxDaoException;

public interface FoodDao {

	Food getFood(Long id) throws FoodBoxDaoException;

	Foods getFoods(PageInfo pageInfo) throws FoodBoxDaoException;

	Foods getFoodsByName(String name, PageInfo pageInfo) throws FoodBoxDaoException;

	Foods getFoodsByCategory(Category category, PageInfo pageInfo) throws FoodBoxDaoException;

	Foods getFoodsByPrice(BigDecimal price, PageInfo pageInfo) throws FoodBoxDaoException;

	Foods getFoodsByOffer(Offer offer, PageInfo pageInfo) throws FoodBoxDaoException;

	Foods getFoodsByCategoryAndOffer(Category category, Offer offer, PageInfo pageInfo) throws FoodBoxDaoException;

	Foods search(String keyword, PageInfo pageInfo) throws FoodBoxDaoException;

	Food save(Food food) throws FoodBoxDaoException;

	Food update(Food food) throws FoodBoxDaoException;

	void remove(Long id) throws FoodBoxDaoException;

	void setEnabled(Long id, Boolean enabled) throws FoodBoxDaoException;

	byte[] getFoodImage(Long id) throws FoodBoxDaoException;

}
