package org.yokekhei.fsd.capstone.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { OrderItemMapper.class, UserMapperService.class })
public interface OrderMapper
		extends EntityDtoMapper<org.yokekhei.fsd.capstone.api.dto.Order, org.yokekhei.fsd.capstone.api.entity.Order> {

	@Mapping(source = "userId", target = "user")
	org.yokekhei.fsd.capstone.api.entity.Order toEntity(org.yokekhei.fsd.capstone.api.dto.Order dto);

	@Mapping(source = "user", target = "userId")
	org.yokekhei.fsd.capstone.api.dto.Order toDto(org.yokekhei.fsd.capstone.api.entity.Order entity);

}
