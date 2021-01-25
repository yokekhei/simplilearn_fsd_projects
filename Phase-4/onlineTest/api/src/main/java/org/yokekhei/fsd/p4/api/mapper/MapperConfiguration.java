package org.yokekhei.fsd.p4.api.mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

	@Bean
	public CategoryMapper categoryMapper() {
		return Mappers.getMapper(CategoryMapper.class);
	}
	
	@Bean
	public UserMapper userMapper() {
		return Mappers.getMapper(UserMapper.class);
	}
	
	@Bean
	public QuizMapper quizMapper() {
		return Mappers.getMapper(QuizMapper.class);
	}
	
	@Bean
	public QuestionMapper questionMapper() {
		return Mappers.getMapper(QuestionMapper.class);
	}
	
	@Bean
	public ChoiceMapper choiceMapper() {
		return Mappers.getMapper(ChoiceMapper.class);
	}
	
	@Bean
	public AnswerMapperService answerMapperService() {
		return Mappers.getMapper(AnswerMapperService.class);
	}
	
}
