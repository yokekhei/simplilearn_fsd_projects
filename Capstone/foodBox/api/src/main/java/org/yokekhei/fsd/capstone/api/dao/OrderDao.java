package org.yokekhei.fsd.capstone.api.dao;

import java.util.List;

import org.yokekhei.fsd.capstone.api.dto.Order;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxDaoException;

public interface OrderDao {

	Order getOrder(Long id) throws FoodBoxDaoException;

	List<Order> getOrders() throws FoodBoxDaoException;

	Order save(Order order) throws FoodBoxDaoException;

	void remove(Long id) throws FoodBoxDaoException;

}