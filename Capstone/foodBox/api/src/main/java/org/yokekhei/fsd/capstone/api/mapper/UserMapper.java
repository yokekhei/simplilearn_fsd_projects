package org.yokekhei.fsd.capstone.api.mapper;

import org.mapstruct.Mapper;

@Mapper
public interface UserMapper
		extends EntityDtoMapper<org.yokekhei.fsd.capstone.api.dto.User, org.yokekhei.fsd.capstone.api.entity.User> {

}
