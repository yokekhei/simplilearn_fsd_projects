package org.yokekhei.fsd.p4.api.mapper;

import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {
	
	org.yokekhei.fsd.p4.api.entity.Category toEntity(org.yokekhei.fsd.p4.api.dto.Category dto);
	
	org.yokekhei.fsd.p4.api.dto.Category toDto(org.yokekhei.fsd.p4.api.entity.Category entity);
	
}
