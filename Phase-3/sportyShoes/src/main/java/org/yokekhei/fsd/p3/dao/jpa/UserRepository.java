package org.yokekhei.fsd.p3.dao.jpa;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.yokekhei.fsd.p3.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByEmailAndPassword(String email, String password);
	
	@Query(name = "query_users_with_user_role")
	List<User> usersWithUserRole();
	
	List<User> findAllByCreatedDateTimeBetween(
		      LocalDateTime createdDateTimeStart,
		      LocalDateTime createdDateTimeEnd);
	
	List<User> findByFirstNameIgnoreCaseStartingWith(String firstName);
	
}
