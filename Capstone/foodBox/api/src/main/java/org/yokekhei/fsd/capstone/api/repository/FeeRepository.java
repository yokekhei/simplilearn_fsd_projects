package org.yokekhei.fsd.capstone.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yokekhei.fsd.capstone.api.entity.Fee;

public interface FeeRepository extends JpaRepository<Fee, String> {

}
