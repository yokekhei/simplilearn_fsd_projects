package org.yokekhei.fsd.capstone.api.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yokekhei.fsd.capstone.api.dao.OrderDao;
import org.yokekhei.fsd.capstone.api.dto.Order;
import org.yokekhei.fsd.capstone.api.dto.User;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxServiceException;

@Service
public class OrderServiceImpl implements OrderService {

	@Resource
	private OrderDao orderDao;

	@Override
	@Transactional
	public List<Order> getOrders() throws FoodBoxServiceException {
		List<Order> orders = null;

		try {
			orders = orderDao.getOrders();
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return orders;
	}

	@Override
	@Transactional
	public List<Order> getOrdersCreatedBetween(LocalDateTime start, LocalDateTime end) throws FoodBoxServiceException {
		List<Order> orders = null;

		try {
			orders = orderDao.getOrdersCreatedBetween(start, end);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return orders;
	}

	@Override
	@Transactional
	public List<Order> getOrdersByUser(User user) throws FoodBoxServiceException {
		List<Order> orders = null;

		try {
			orders = orderDao.getOrdersByUser(user);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return orders;
	}
	
	@Override
	public List<Order> getOrdersByUserAndCreatedBetween(User user, LocalDateTime start, LocalDateTime end)
			throws FoodBoxServiceException {
		List<Order> orders = null;

		try {
			orders = orderDao.getOrdersByUserAndCreatedBetween(user, start, end);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return orders;
	}

	@Override
	@Transactional
	public Order getOrder(Long id) throws FoodBoxServiceException {
		Order order = null;

		try {
			order = orderDao.getOrder(id);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return order;
	}

	@Override
	@Transactional
	public Order createOrder(Order order) throws FoodBoxServiceException {
		Order savedOrder = null;

		try {
			savedOrder = orderDao.save(order);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return savedOrder;
	}

	@Override
	@Transactional
	public Order updateOrder(Order order) throws FoodBoxServiceException {
		Order savedOrder = null;

		try {
			if (order.getId() == null) {
				throw new FoodBoxServiceException("Order id cannot be null.");
			}

			if (orderDao.getOrder(order.getId()) == null) {
				throw new FoodBoxServiceException("Order " + order.getId() + "not found.");
			}

			savedOrder = orderDao.save(order);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return savedOrder;
	}

	@Override
	@Transactional
	public Order deleteOrder(Long id) throws FoodBoxServiceException {
		Order deletedOrder = null;

		try {
			if (id == null) {
				throw new FoodBoxServiceException("Order id cannot be null.");
			}

			deletedOrder = orderDao.getOrder(id);

			if (deletedOrder == null) {
				throw new FoodBoxServiceException("Order " + id + "not found.");
			}

			orderDao.remove(id);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return deletedOrder;
	}

}
