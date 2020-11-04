package org.yokekhei.fsd.p2.dao;

import org.yokekhei.fsd.p2.bean.AdminUser;

public interface AdminDao {

	AdminUser getAdminDetails(String email, String password) throws FlyAwayDaoException;
	
}
