package org.yokekhei.fsd.p3.dao.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yokekhei.fsd.p3.entity.Category;
import org.yokekhei.fsd.p3.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByCategory(Category category);
	
	@EntityGraph(attributePaths={"picture"})
    Product findWithPictureAttachedById(Long id);
	
}
