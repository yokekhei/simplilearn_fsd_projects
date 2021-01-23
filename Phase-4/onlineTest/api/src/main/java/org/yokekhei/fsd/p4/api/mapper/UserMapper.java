package org.yokekhei.fsd.p4.api.mapper;

import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends
	EntityDtoMapper<org.yokekhei.fsd.p4.api.dto.User, org.yokekhei.fsd.p4.api.entity.User> {
	
}
