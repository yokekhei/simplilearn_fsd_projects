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

	@Bean
	public OfferMapper offerMapper() {
		return Mappers.getMapper(OfferMapper.class);
	}

	@Bean
	public FeeMapper feeMapper() {
		return Mappers.getMapper(FeeMapper.class);
	}

	@Bean
	public FoodMapper foodMapper() {
		return Mappers.getMapper(FoodMapper.class);
	}

	@Bean
	public OrderMapper orderMapper() {
		return Mappers.getMapper(OrderMapper.class);
	}

	@Bean
	public OrderItemMapper orderItemMapper() {
		return Mappers.getMapper(OrderItemMapper.class);
	}

}
