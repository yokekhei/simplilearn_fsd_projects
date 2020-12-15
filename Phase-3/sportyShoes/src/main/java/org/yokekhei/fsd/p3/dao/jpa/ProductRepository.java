package org.yokekhei.fsd.p3.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yokekhei.fsd.p3.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
