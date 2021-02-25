package org.yokekhei.fsd.capstone.api.mapper;

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

}
