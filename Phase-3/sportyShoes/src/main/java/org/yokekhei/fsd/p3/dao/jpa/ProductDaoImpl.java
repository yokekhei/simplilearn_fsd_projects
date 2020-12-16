package org.yokekhei.fsd.p3.dao.jpa;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.yokekhei.fsd.p3.dao.ProductDao;
import org.yokekhei.fsd.p3.dao.SportyShoesDaoException;
import org.yokekhei.fsd.p3.dto.Category;
import org.yokekhei.fsd.p3.dto.Product;

@Repository
@Qualifier("jpa")
public class ProductDaoImpl implements ProductDao {

	@Resource
	private ProductRepository repository;
	
	@Resource
	private ProductMapper mapper;
	
	@Resource
	private CategoryMapper categoryMapper;
	
	@Override
	public List<Product> getAllProducts() throws SportyShoesDaoException {
		List<Product> products = null;
		
		try {
			products = repository.findAll()
					.stream()
					.map(entity -> mapper.toDto(entity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new SportyShoesDaoException(e.getMessage());
		}
		
		return products;
	}

	@Override
	public Product getProduct(Long id) throws SportyShoesDaoException {
		Product product = null;
		
		try {
			product = repository.findById(id)
					.map(entity -> mapper.toDto(entity))
					.orElse(null);
		} catch (Exception e) {
			throw new SportyShoesDaoException(e.getMessage());
		}
		
		return product;
	}

	@Override
	@Transactional
	public Product save(Product product) throws SportyShoesDaoException {
		Product savedProduct = null;
		
		try {
			org.yokekhei.fsd.p3.entity.Product entity = mapper.toEntity(product);
			
			if (!product.getImageFile().isEmpty()) {
				entity.setPicture(IOUtils.toByteArray(product.getImageFile().getInputStream()));
			}
			
			savedProduct = mapper.toDto(repository.save(entity));
		} catch (Exception e) {
			throw new SportyShoesDaoException(e.getMessage());
		}
		
		return savedProduct;
	}
	
	@Override
	@Transactional
	public Product update(Product product) throws SportyShoesDaoException {
		Product savedProduct = null;
		
		try {
			org.yokekhei.fsd.p3.entity.Product current = repository
					.findById(product.getId()).orElse(null);
			
			if (current == null) {
				return save(product);
			}
			
			org.yokekhei.fsd.p3.entity.Product entity = mapper.toEntity(product);
			
			current.setName(entity.getName());
			current.setCategory(entity.getCategory());
			current.setShortDesc(entity.getShortDesc());
			current.setLongDesc(entity.getLongDesc());
			current.setPrice(entity.getPrice());
			
			if (!product.getImageFile().isEmpty()) {
				current.setPicture(IOUtils.toByteArray(product.getImageFile().getInputStream()));
			}
			
			savedProduct = mapper.toDto(repository.save(current));
		} catch (Exception e) {
			throw new SportyShoesDaoException(e.getMessage());
		}
		
		return savedProduct;
	}

	@Override
	@Transactional
	public void remove(Long id) throws SportyShoesDaoException {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new SportyShoesDaoException(e.getMessage());
		}
	}

	@Override
	public List<Product> getProductsByCategory(Category category) throws SportyShoesDaoException {
		List<Product> products = null;
		
		try {
			org.yokekhei.fsd.p3.entity.Category categoryEntity = categoryMapper.toEntity(category);
			
			products = repository.findByCategory(categoryEntity)
					.stream()
					.map(entity -> mapper.toDto(entity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new SportyShoesDaoException(e.getMessage());
		}
		
		return products;
	}

	@Override
	public byte[] getProductPicture(Long id) throws SportyShoesDaoException {
		byte[] picture = null;
		
		try {
			org.yokekhei.fsd.p3.entity.Product product =
					repository.findWithPictureAttachedById(id);
			picture = product.getPicture();
		} catch (Exception e) {
			throw new SportyShoesDaoException(e.getMessage());
		}
		
		return picture;
	}

}
