package org.yokekhei.fsd.capstone.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CategoryMapper extends
		EntityDtoMapper<org.yokekhei.fsd.capstone.api.dto.Category, org.yokekhei.fsd.capstone.api.entity.Category> {

	@Mapping(target = "image", ignore = true)
	org.yokekhei.fsd.capstone.api.entity.Category toEntity(org.yokekhei.fsd.capstone.api.dto.Category dto);

	@Mapping(target = "image", ignore = true)
	org.yokekhei.fsd.capstone.api.dto.Category toDto(org.yokekhei.fsd.capstone.api.entity.Category entity);
	
}
