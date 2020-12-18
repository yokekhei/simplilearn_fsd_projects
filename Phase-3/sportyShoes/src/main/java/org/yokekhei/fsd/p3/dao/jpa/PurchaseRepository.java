package org.yokekhei.fsd.p3.dao.jpa;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yokekhei.fsd.p3.entity.Purchase;
import org.yokekhei.fsd.p3.entity.PurchaseItem;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	List<Purchase> findAllByCreatedDateTimeBetween(
		      LocalDateTime createdDateTimeStart,
		      LocalDateTime createdDateTimeEnd);
	
	List<Purchase> findDistinctByItemsIn(List<PurchaseItem> items);
	
}
