package org.yokekhei.fsd.capstone.api.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.yokekhei.fsd.capstone.api.entity.Category;
import org.yokekhei.fsd.capstone.api.entity.Food;
import org.yokekhei.fsd.capstone.api.entity.Offer;

public interface FoodRepository extends PagingAndSortingRepository<Food, Long> {

	Page<Food> findByNameIgnoreCase(String name, Pageable pageable);

	Page<Food> findByCategory(Category category, Pageable pageable);

	Page<Food> findByPrice(BigDecimal price, Pageable pageable);

	Page<Food> findByOffer(Offer offer, Pageable pageable);

	Page<Food> findByCategoryAndOffer(Category category, Offer offer, Pageable pageable);

	@Query("SELECT f FROM Food f LEFT JOIN f.offer o WHERE f.name LIKE %?1%"
			+ " OR f.desc LIKE %?1%"
			+ " OR f.category.name LIKE %?1%"
			+ " OR f.offer.name LIKE %?1%"
			+ " OR CONCAT(f.price, '') LIKE %?1%")
	Page<Food> search(String keyword, Pageable pageable);

	@Modifying
	@Query(value = "Update Food f set enabled=?2 where id=?1")
	void setEnabled(Long id, Boolean enabled);

	@EntityGraph(attributePaths = { "image" })
	Food findWithImageAttachedById(Long id);

}
