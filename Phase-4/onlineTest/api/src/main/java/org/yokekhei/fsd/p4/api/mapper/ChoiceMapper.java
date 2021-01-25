package org.yokekhei.fsd.p4.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { QuestionMapperService.class })
public interface ChoiceMapper extends
	EntityDtoMapper<org.yokekhei.fsd.p4.api.dto.Choice, org.yokekhei.fsd.p4.api.entity.Choice> {

	@Mapping(expression = "java(QuestionMapperService.findById(questionId))", target = "question")
	org.yokekhei.fsd.p4.api.entity.Choice toEntity(org.yokekhei.fsd.p4.api.dto.Choice dto, Long questionId);
	
	org.yokekhei.fsd.p4.api.dto.Choice toDto(org.yokekhei.fsd.p4.api.entity.Choice entity);
	
}
