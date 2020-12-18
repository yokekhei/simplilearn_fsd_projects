package org.yokekhei.fsd.p3.dao.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.yokekhei.fsd.p3.entity.Category;
import org.yokekhei.fsd.p3.entity.PurchaseItem;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {

	@Query(value = "Select pi From PurchaseItem pi where pi.product.category = ?1")
	List<PurchaseItem> findAllByCategory(Category category);
	
}
