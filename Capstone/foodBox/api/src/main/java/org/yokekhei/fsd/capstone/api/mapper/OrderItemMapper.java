package org.yokekhei.fsd.capstone.api.mapper;

import org.mapstruct.Mapper;

@Mapper(uses = { FoodMapper.class })
public interface OrderItemMapper extends
		EntityDtoMapper<org.yokekhei.fsd.capstone.api.dto.OrderItem, org.yokekhei.fsd.capstone.api.entity.OrderItem> {

	org.yokekhei.fsd.capstone.api.entity.OrderItem toEntity(org.yokekhei.fsd.capstone.api.dto.OrderItem dto);

	org.yokekhei.fsd.capstone.api.dto.OrderItem toDto(org.yokekhei.fsd.capstone.api.entity.OrderItem entity);

}
