package org.yokekhei.fsd.p4.api.mapper;

import java.util.List;

public interface EntityDtoMapper<D, E> {
	
	E toEntity(D dto);
	D toDto(E entity);
	List<E> toEntity(List<D> dtoList);
	List<D> toDto(List<E> entityList);
	
}
