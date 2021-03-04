package org.yokekhei.fsd.p5.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper extends EntityDtoMapper<org.yokekhei.fsd.p5.dto.User, org.yokekhei.fsd.p5.entity.User> {

	@Mapping(target = "confirmPassword", ignore = true)
	org.yokekhei.fsd.p5.dto.User toDto(org.yokekhei.fsd.p5.entity.User entity);

}
