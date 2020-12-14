package org.yokekhei.fsd.p3.dao.jpa;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfiguration {
	
	@Bean
	public UserMapper userMapper() {
		return Mappers.getMapper(UserMapper.class);
	}
	
}
