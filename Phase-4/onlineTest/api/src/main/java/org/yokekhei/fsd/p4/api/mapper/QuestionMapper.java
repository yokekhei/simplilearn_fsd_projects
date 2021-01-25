package org.yokekhei.fsd.p4.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { QuizMapperService.class, AnswerMapperService.class })
public interface QuestionMapper extends
	EntityDtoMapper<org.yokekhei.fsd.p4.api.dto.Question, org.yokekhei.fsd.p4.api.entity.Question> {
	
	@Mapping(expression = "java(QuizMapperService.findById(quizId))", target = "quiz")
	org.yokekhei.fsd.p4.api.entity.Question toEntity(org.yokekhei.fsd.p4.api.dto.Question dto, Long quizId);
	
	@Mapping(source = "answer", target = "answerIndex")
	org.yokekhei.fsd.p4.api.dto.Question toDto(org.yokekhei.fsd.p4.api.entity.Question entity);
	
}
