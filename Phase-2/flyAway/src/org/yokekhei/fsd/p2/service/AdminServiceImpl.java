package org.yokekhei.fsd.p2.service;

import org.yokekhei.fsd.p2.bean.AdminUser;
import org.yokekhei.fsd.p2.dao.AdminDao;
import org.yokekhei.fsd.p2.dao.AdminDaoImpl;
import org.yokekhei.fsd.p2.dao.FlyAwayDaoException;

public class AdminServiceImpl implements AdminService {

	public AdminServiceImpl() {
	}
	
	@Override
	public AdminUser login(String email, String password) throws FlyAwayServiceException {
		AdminUser adminUser = null;
		
		try {
			AdminDao dao = new AdminDaoImpl();
			adminUser = dao.getAdminDetails(email, password);
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to get admin details - " + e.getMessage());
		}
		
		return adminUser;
	}

}
