package org.yokekhei.fsd.p2.service;

import org.yokekhei.fsd.p2.bean.AdminUser;

public interface AdminService {

	AdminUser login(String email, String password) throws FlyAwayServiceException;
	
}
