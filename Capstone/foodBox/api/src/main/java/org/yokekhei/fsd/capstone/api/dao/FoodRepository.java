package org.yokekhei.fsd.capstone.api.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.yokekhei.fsd.capstone.api.entity.Category;
import org.yokekhei.fsd.capstone.api.entity.Food;
import org.yokekhei.fsd.capstone.api.entity.Offer;

public interface FoodRepository extends PagingAndSortingRepository<Food, Long> {

	List<Food> findAllByName(String name, Pageable pageable);

	List<Food> findAllByCategory(Category category, Pageable pageable);

	List<Food> findAllByPrice(BigDecimal price, Pageable pageable);

	List<Food> findAllByOffer(Offer offer, Pageable pageable);

	@Modifying
	@Query(value = "Update Food f set enabled=?2 where id=?1")
	void setEnabled(Long id, Boolean enabled);

	@EntityGraph(attributePaths = { "image" })
	Food findWithImageAttachedById(Long id);

}
