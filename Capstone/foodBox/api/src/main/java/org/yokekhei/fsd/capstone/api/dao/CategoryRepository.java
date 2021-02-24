package org.yokekhei.fsd.capstone.api.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.yokekhei.fsd.capstone.api.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query(value = "Update Category c set enabled=?2 where id=?1")
	void setEnabled(Long id, Boolean enabled);

	@EntityGraph(attributePaths = { "image" })
	Category findWithImageAttachedById(Long id);

}
