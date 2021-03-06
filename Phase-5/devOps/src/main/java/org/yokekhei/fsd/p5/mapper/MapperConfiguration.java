package org.yokekhei.fsd.p5.mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

	@Bean
	public UserMapper userMapper() {
		return Mappers.getMapper(UserMapper.class);
	}

	@Bean
	public CourseMapper courseMapper() {
		return Mappers.getMapper(CourseMapper.class);
	}

}
