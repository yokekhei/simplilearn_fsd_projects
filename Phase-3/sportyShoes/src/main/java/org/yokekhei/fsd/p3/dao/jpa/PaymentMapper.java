package org.yokekhei.fsd.p3.dao.jpa;

import org.mapstruct.Mapper;

@Mapper
public interface PaymentMapper {
	
	org.yokekhei.fsd.p3.entity.Payment toEntity(org.yokekhei.fsd.p3.dto.Payment dto);
	
	org.yokekhei.fsd.p3.dto.Payment toDto(org.yokekhei.fsd.p3.entity.Payment entity);
	
}
