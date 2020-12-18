package org.yokekhei.fsd.p3.dao.jpa;

import org.mapstruct.Mapper;

@Mapper
public interface PurchaseMapper {
	
	org.yokekhei.fsd.p3.entity.Purchase toEntity(org.yokekhei.fsd.p3.dto.Purchase dto);
	
	org.yokekhei.fsd.p3.dto.Purchase toDto(org.yokekhei.fsd.p3.entity.Purchase entity);
	
}
