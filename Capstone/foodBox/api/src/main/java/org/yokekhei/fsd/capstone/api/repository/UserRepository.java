package org.yokekhei.fsd.capstone.api.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yokekhei.fsd.capstone.api.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByEmailAndPassword(String email, String password);

	List<User> findAllByCreatedDateTimeBetween(LocalDateTime createdDateTimeStart, LocalDateTime createdDateTimeEnd);

}
