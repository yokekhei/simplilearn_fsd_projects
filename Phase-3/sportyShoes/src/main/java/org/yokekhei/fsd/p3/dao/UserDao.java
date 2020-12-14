package org.yokekhei.fsd.p3.dao;

import org.yokekhei.fsd.p3.entity.User;

public interface UserDao {

	User getUser(String email, String password);
}
