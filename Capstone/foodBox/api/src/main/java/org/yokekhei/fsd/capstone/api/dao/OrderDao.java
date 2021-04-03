package org.yokekhei.fsd.capstone.api.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.yokekhei.fsd.capstone.api.dto.Order;
import org.yokekhei.fsd.capstone.api.dto.User;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxDaoException;

public interface OrderDao {

	Order getOrder(Long id) throws FoodBoxDaoException;

	List<Order> getOrders() throws FoodBoxDaoException;

	List<Order> getOrdersCreatedBetween(LocalDateTime start, LocalDateTime end) throws FoodBoxDaoException;

	List<Order> getOrdersByUser(User user) throws FoodBoxDaoException;

	List<Order> getOrdersByUserAndCreatedBetween(User user, LocalDateTime start, LocalDateTime end)
			throws FoodBoxDaoException;

	Order save(Order order) throws FoodBoxDaoException;

	void remove(Long id) throws FoodBoxDaoException;

}
