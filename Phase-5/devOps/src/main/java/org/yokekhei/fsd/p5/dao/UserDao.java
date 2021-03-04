package org.yokekhei.fsd.p5.dao;

import org.yokekhei.fsd.p5.dto.User;

public interface UserDao {

	User getUser(String email, String password) throws DevOpsDaoException;

	User save(User user) throws DevOpsDaoException;

	User update(User user) throws DevOpsDaoException;

	User getUser(String email) throws DevOpsDaoException;

}
