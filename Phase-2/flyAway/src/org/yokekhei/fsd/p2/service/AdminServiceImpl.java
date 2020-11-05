package org.yokekhei.fsd.p2.service;

import org.yokekhei.fsd.p2.bean.AdminUser;
import org.yokekhei.fsd.p2.dao.AdminUserDao;
import org.yokekhei.fsd.p2.dao.AdminUserDaoImpl;
import org.yokekhei.fsd.p2.dao.FlyAwayDaoException;

public class AdminServiceImpl implements AdminService {

	public AdminServiceImpl() {
	}
	
	@Override
	public AdminUser login(String email, String password) throws FlyAwayServiceException {
		AdminUser adminUser = null;
		
		try {
			AdminUserDao dao = new AdminUserDaoImpl();
			adminUser = dao.getAdminUser(email, password);
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to get admin details - " + e.getMessage());
		}
		
		return adminUser;
	}

}
