package org.yokekhei.fsd.capstone.api.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.yokekhei.fsd.capstone.api.dto.Order;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxDaoException;
import org.yokekhei.fsd.capstone.api.mapper.OrderMapper;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Resource
	private OrderRepository repository;

	@Resource
	private OrderMapper mapper;

	@Override
	public Order getOrder(Long id) throws FoodBoxDaoException {
		Order order = null;

		try {
			order = repository.findById(id)
					.map(entity -> mapper.toDto(entity))
					.orElse(null);
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return order;
	}

	@Override
	public List<Order> getOrders() throws FoodBoxDaoException {
		List<Order> orders = null;

		try {
			orders = repository.findAll()
					.stream()
					.map(entity -> mapper.toDto(entity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return orders;
	}

	@Override
	public Order save(Order order) throws FoodBoxDaoException {
		Order savedOrder = null;

		try {
			savedOrder = mapper.toDto(repository.save(mapper.toEntity(order)));
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return savedOrder;
	}

	@Override
	public void remove(Long id) throws FoodBoxDaoException {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}
	}

}
