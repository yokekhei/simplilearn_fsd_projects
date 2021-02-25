package org.yokekhei.fsd.capstone.api.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yokekhei.fsd.capstone.api.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByEmailAndPassword(String email, String password);

}
