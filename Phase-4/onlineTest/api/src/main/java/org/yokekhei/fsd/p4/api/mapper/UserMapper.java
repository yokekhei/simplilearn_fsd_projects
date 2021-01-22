package org.yokekhei.fsd.p4.api.mapper;

import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
	
	org.yokekhei.fsd.p4.api.entity.User toEntity(org.yokekhei.fsd.p4.api.dto.User dto);
	
	org.yokekhei.fsd.p4.api.dto.User toDto(org.yokekhei.fsd.p4.api.entity.User entity);
	
}
