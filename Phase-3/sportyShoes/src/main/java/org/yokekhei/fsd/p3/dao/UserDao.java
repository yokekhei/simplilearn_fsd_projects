package org.yokekhei.fsd.p3.dao;

import org.yokekhei.fsd.p3.dto.User;

public interface UserDao {

	User getUser(String email, String password) throws SportyShoesDaoException;
	User save(User user) throws SportyShoesDaoException;
}
