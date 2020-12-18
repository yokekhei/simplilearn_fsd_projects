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
	
	@Bean
	public CategoryMapper categoryMapper() {
		return Mappers.getMapper(CategoryMapper.class);
	}
	
	@Bean
	public ProductMapper productMapper() {
		return Mappers.getMapper(ProductMapper.class);
	}
	
	@Bean
	public PaymentMapper paymentMapper() {
		return Mappers.getMapper(PaymentMapper.class);
	}
	
	@Bean
	public PurchaseMapper purchaseMapper() {
		return Mappers.getMapper(PurchaseMapper.class);
	}
	
}
