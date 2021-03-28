package org.yokekhei.fsd.capstone.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { CategoryMapperService.class, OfferMapperService.class })
public interface FoodMapper
		extends EntityDtoMapper<org.yokekhei.fsd.capstone.api.dto.Food, org.yokekhei.fsd.capstone.api.entity.Food> {

	@Mapping(source = "categoryId", target = "category")
	@Mapping(source = "offerId", target = "offer")
	@Mapping(target = "image", ignore = true)
	org.yokekhei.fsd.capstone.api.entity.Food toEntity(org.yokekhei.fsd.capstone.api.dto.Food dto);

	@Mapping(source = "category", target = "categoryId")
	@Mapping(source = "offer", target = "offerId")
	@Mapping(target = "image", ignore = true)
	org.yokekhei.fsd.capstone.api.dto.Food toDto(org.yokekhei.fsd.capstone.api.entity.Food entity);
}
