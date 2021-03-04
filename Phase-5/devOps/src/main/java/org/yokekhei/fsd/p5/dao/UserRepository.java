package org.yokekhei.fsd.p5.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yokekhei.fsd.p5.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByEmailAndPassword(String email, String password);

}
