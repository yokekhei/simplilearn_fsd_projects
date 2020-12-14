package org.yokekhei.fsd.p3.dao.jpa;

import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {
	
	org.yokekhei.fsd.p3.entity.Category toEntity(org.yokekhei.fsd.p3.dto.Category dto);
	
	org.yokekhei.fsd.p3.dto.Category toDto(org.yokekhei.fsd.p3.entity.Category entity);
	
}
