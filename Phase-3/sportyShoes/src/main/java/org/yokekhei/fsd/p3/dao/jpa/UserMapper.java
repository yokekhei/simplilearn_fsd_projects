package org.yokekhei.fsd.p3.dao.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
	
	org.yokekhei.fsd.p3.entity.User toEntity(org.yokekhei.fsd.p3.dto.User dto);
	
	@Mapping(target = "confirmPassword", ignore = true)
	org.yokekhei.fsd.p3.dto.User toDto(org.yokekhei.fsd.p3.entity.User entity);
	
}
