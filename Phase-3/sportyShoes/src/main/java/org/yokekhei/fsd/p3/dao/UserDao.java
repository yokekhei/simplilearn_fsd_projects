package org.yokekhei.fsd.p3.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.yokekhei.fsd.p3.dto.User;

public interface UserDao {

	User getUser(String email, String password) throws SportyShoesDaoException;
	User save(User user) throws SportyShoesDaoException;
	User update(User user) throws SportyShoesDaoException;
	
	User getUser(String email) throws SportyShoesDaoException;
	List<User> getAllUsers() throws SportyShoesDaoException;
	List<User> getUsersWithUserRole() throws SportyShoesDaoException;
	List<User> getAllUsersCreatedBetween(LocalDateTime start, LocalDateTime end)
			throws SportyShoesDaoException;
	List<User> getUsersByFirstName(String firstName) throws SportyShoesDaoException;
	
}
