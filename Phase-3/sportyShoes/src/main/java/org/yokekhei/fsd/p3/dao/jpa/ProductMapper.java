package org.yokekhei.fsd.p3.dao.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductMapper {
	
	@Mapping(target = "picture", ignore = true)
	org.yokekhei.fsd.p3.entity.Product toEntity(org.yokekhei.fsd.p3.dto.Product dto);
	
	@Mapping(target = "categoryId", ignore = true)
	@Mapping(target = "imageFile", ignore = true)
	org.yokekhei.fsd.p3.dto.Product toDto(org.yokekhei.fsd.p3.entity.Product entity);
	
}
