package org.yokekhei.fsd.p4.api.mapper;

import org.mapstruct.Mapper;

@Mapper
public interface UserMapperService {

	default org.yokekhei.fsd.p4.api.entity.User findById(String id) {
		if (id == null) {
			return null;
		}
		
		org.yokekhei.fsd.p4.api.entity.User user = new org.yokekhei.fsd.p4.api.entity.User();
		user.setEmail(id);
		
		return user;
	}
	
	default String findByUser(org.yokekhei.fsd.p4.api.entity.User user) {
		if (user == null) {
			return null;
		}
		
		return user.getEmail();
	}
	
}
