package org.yokekhei.fsd.capstone.api.service;

import java.util.List;

import org.yokekhei.fsd.capstone.api.dto.Order;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxServiceException;

public interface OrderService {

	List<Order> getOrders() throws FoodBoxServiceException;

	Order getOrder(Long id) throws FoodBoxServiceException;

	Order createOrder(Order order) throws FoodBoxServiceException;

	Order updateOrder(Order order) throws FoodBoxServiceException;

	Order deleteOrder(Long id) throws FoodBoxServiceException;

}
