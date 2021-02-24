package org.yokekhei.fsd.capstone.api.mapper;

import java.util.List;

public interface EntityDtoMapper<D, E> {
	
	E toEntity(D dto);
	D toDto(E entity);
	List<E> toEntity(List<D> dtoList);
	List<D> toDto(List<E> entityList);
	
}
