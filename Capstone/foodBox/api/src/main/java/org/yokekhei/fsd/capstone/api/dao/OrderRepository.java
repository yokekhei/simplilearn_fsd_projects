package org.yokekhei.fsd.capstone.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yokekhei.fsd.capstone.api.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
