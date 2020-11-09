package org.yokekhei.fsd.p2.dao;

import org.yokekhei.fsd.p2.bean.AdminUser;

public interface AdminUserDao {

	AdminUser getAdminUser(String email, String password) throws FlyAwayDaoException;
	void updateAdminUser(AdminUser data) throws FlyAwayDaoException;
	
}
