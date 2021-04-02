package org.yokekhei.fsd.capstone.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yokekhei.fsd.capstone.api.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findAllByCreatedDateTimeBetween(LocalDateTime createdDateTimeStart, LocalDateTime createdDateTimeEnd);

}
