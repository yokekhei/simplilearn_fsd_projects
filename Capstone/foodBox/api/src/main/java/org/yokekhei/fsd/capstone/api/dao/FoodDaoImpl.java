package org.yokekhei.fsd.capstone.api.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.yokekhei.fsd.capstone.api.dto.Category;
import org.yokekhei.fsd.capstone.api.dto.Food;
import org.yokekhei.fsd.capstone.api.dto.Foods;
import org.yokekhei.fsd.capstone.api.dto.Offer;
import org.yokekhei.fsd.capstone.api.dto.PageInfo;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxDaoException;
import org.yokekhei.fsd.capstone.api.mapper.CategoryMapper;
import org.yokekhei.fsd.capstone.api.mapper.FoodMapper;
import org.yokekhei.fsd.capstone.api.mapper.OfferMapper;
import org.yokekhei.fsd.capstone.api.repository.FoodRepository;

@Repository
public class FoodDaoImpl implements FoodDao {

	@Resource
	private FoodRepository repository;

	@Resource
	private FoodMapper mapper;

	@Resource
	private CategoryMapper categoryMapper;

	@Resource
	private OfferMapper offerMapper;

	@Override
	public Food getFood(Long id) throws FoodBoxDaoException {
		Food food = null;

		try {
			food = repository.findById(id).map(entity -> mapper.toDto(entity)).orElse(null);
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return food;
	}

	@Override
	public Foods getFoods(PageInfo pageInfo) throws FoodBoxDaoException {
		Foods foods = null;

		try {
			Page<org.yokekhei.fsd.capstone.api.entity.Food> page = repository
					.findAll(PageRequest.of(pageInfo.getPage(), pageInfo.getSize(), getSort(pageInfo)));
			List<Food> list = IterableUtils.toList(page.getContent()).stream().map(entity -> mapper.toDto(entity))
					.collect(Collectors.toList());

			foods = new Foods();
			PageInfo pgInfo = new PageInfo(page.getNumber(), page.getSize(), page.getNumberOfElements(),
					page.getTotalElements(), page.getTotalPages(), pageInfo.getSortBy(), pageInfo.getDirection());
			foods.setPageInfo(pgInfo);
			foods.setList(list);
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return foods;
	}

	@Override
	public Foods getFoodsByName(String name, PageInfo pageInfo) throws FoodBoxDaoException {
		Foods foods = null;

		try {
			Page<org.yokekhei.fsd.capstone.api.entity.Food> page = repository.findByNameIgnoreCase(name,
					PageRequest.of(pageInfo.getPage(), pageInfo.getSize(), getSort(pageInfo)));
			List<Food> list = IterableUtils.toList(page.getContent()).stream().map(entity -> mapper.toDto(entity))
					.collect(Collectors.toList());

			foods = new Foods();
			PageInfo pgInfo = new PageInfo(page.getNumber(), page.getSize(), page.getNumberOfElements(),
					page.getTotalElements(), page.getTotalPages(), pageInfo.getSortBy(), pageInfo.getDirection());
			foods.setPageInfo(pgInfo);
			foods.setList(list);
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return foods;
	}

	@Override
	public Foods getFoodsByCategory(Category category, PageInfo pageInfo) throws FoodBoxDaoException {
		Foods foods = null;

		try {
			org.yokekhei.fsd.capstone.api.entity.Category categoryEntity = categoryMapper.toEntity(category);
			Page<org.yokekhei.fsd.capstone.api.entity.Food> page = repository.findByCategory(categoryEntity,
					PageRequest.of(pageInfo.getPage(), pageInfo.getSize(), getSort(pageInfo)));
			List<Food> list = IterableUtils.toList(page.getContent()).stream().map(entity -> mapper.toDto(entity))
					.collect(Collectors.toList());

			foods = new Foods();
			PageInfo pgInfo = new PageInfo(page.getNumber(), page.getSize(), page.getNumberOfElements(),
					page.getTotalElements(), page.getTotalPages(), pageInfo.getSortBy(), pageInfo.getDirection());
			foods.setPageInfo(pgInfo);
			foods.setList(list);
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return foods;
	}

	@Override
	public Foods getFoodsByPrice(BigDecimal price, PageInfo pageInfo) throws FoodBoxDaoException {
		Foods foods = null;

		try {
			Page<org.yokekhei.fsd.capstone.api.entity.Food> page = repository.findByPrice(price,
					PageRequest.of(pageInfo.getPage(), pageInfo.getSize(), getSort(pageInfo)));
			List<Food> list = IterableUtils.toList(page.getContent()).stream().map(entity -> mapper.toDto(entity))
					.collect(Collectors.toList());

			foods = new Foods();
			PageInfo pgInfo = new PageInfo(page.getNumber(), page.getSize(), page.getNumberOfElements(),
					page.getTotalElements(), page.getTotalPages(), pageInfo.getSortBy(), pageInfo.getDirection());
			foods.setPageInfo(pgInfo);
			foods.setList(list);
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return foods;
	}

	@Override
	public Foods getFoodsByOffer(Offer offer, PageInfo pageInfo) throws FoodBoxDaoException {
		Foods foods = null;

		try {
			org.yokekhei.fsd.capstone.api.entity.Offer offerEntity = offerMapper.toEntity(offer);
			Page<org.yokekhei.fsd.capstone.api.entity.Food> page = repository.findByOffer(offerEntity,
					PageRequest.of(pageInfo.getPage(), pageInfo.getSize(), getSort(pageInfo)));
			List<Food> list = IterableUtils.toList(page.getContent()).stream().map(entity -> mapper.toDto(entity))
					.collect(Collectors.toList());

			foods = new Foods();
			PageInfo pgInfo = new PageInfo(page.getNumber(), page.getSize(), page.getNumberOfElements(),
					page.getTotalElements(), page.getTotalPages(), pageInfo.getSortBy(), pageInfo.getDirection());
			foods.setPageInfo(pgInfo);
			foods.setList(list);
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return foods;
	}

	@Override
	public Foods getFoodsByCategoryAndOffer(Category category, Offer offer, PageInfo pageInfo)
			throws FoodBoxDaoException {
		Foods foods = null;

		try {
			org.yokekhei.fsd.capstone.api.entity.Category categoryEntity = categoryMapper.toEntity(category);
			org.yokekhei.fsd.capstone.api.entity.Offer offerEntity = offerMapper.toEntity(offer);
			Page<org.yokekhei.fsd.capstone.api.entity.Food> page = repository.findByCategoryAndOffer(categoryEntity,
					offerEntity, PageRequest.of(pageInfo.getPage(), pageInfo.getSize(), getSort(pageInfo)));
			List<Food> list = IterableUtils.toList(page.getContent()).stream().map(entity -> mapper.toDto(entity))
					.collect(Collectors.toList());

			foods = new Foods();
			PageInfo pgInfo = new PageInfo(page.getNumber(), page.getSize(), page.getNumberOfElements(),
					page.getTotalElements(), page.getTotalPages(), pageInfo.getSortBy(), pageInfo.getDirection());
			foods.setPageInfo(pgInfo);
			foods.setList(list);
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return foods;
	}

	@Override
	public Foods search(String keyword, PageInfo pageInfo) throws FoodBoxDaoException {
		Foods foods = null;

		try {
			Page<org.yokekhei.fsd.capstone.api.entity.Food> page = repository.search(keyword,
					PageRequest.of(pageInfo.getPage(), pageInfo.getSize(), getSort(pageInfo)));
			List<Food> list = IterableUtils.toList(page.getContent()).stream().map(entity -> mapper.toDto(entity))
					.collect(Collectors.toList());

			foods = new Foods();
			PageInfo pgInfo = new PageInfo(page.getNumber(), page.getSize(), page.getNumberOfElements(),
					page.getTotalElements(), page.getTotalPages(), pageInfo.getSortBy(), pageInfo.getDirection());
			foods.setPageInfo(pgInfo);
			foods.setList(list);
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return foods;
	}

	@Override
	public Food save(Food food) throws FoodBoxDaoException {
		Food savedFood = null;

		try {
			org.yokekhei.fsd.capstone.api.entity.Food entity = mapper.toEntity(food);

			if (food.getImage() != null && !food.getImage().isEmpty()) {
				entity.setImage(IOUtils.toByteArray(food.getImage().getInputStream()));
			}

			savedFood = mapper.toDto(repository.save(entity));
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return savedFood;
	}

	@Override
	public Food update(Food food) throws FoodBoxDaoException {
		Food savedFood = null;

		try {
			org.yokekhei.fsd.capstone.api.entity.Food current = repository.findById(food.getId()).orElse(null);

			if (current == null) {
				return save(food);
			}

			org.yokekhei.fsd.capstone.api.entity.Food entity = mapper.toEntity(food);

			current.setName(entity.getName());
			current.setCategory(entity.getCategory());
			current.setPrice(entity.getPrice());
			current.setDesc(entity.getDesc());
			current.setOffer(entity.getOffer());

			if (food.getImage() != null && !food.getImage().isEmpty()) {
				current.setImage(IOUtils.toByteArray(food.getImage().getInputStream()));
			}

			savedFood = mapper.toDto(repository.save(current));
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return savedFood;
	}

	@Override
	public void remove(Long id) throws FoodBoxDaoException {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}
	}

	@Override
	public void setEnabled(Long id, Boolean enabled) throws FoodBoxDaoException {
		try {
			repository.setEnabled(id, enabled);
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}
	}

	@Override
	public byte[] getFoodImage(Long id) throws FoodBoxDaoException {
		byte[] image = null;

		try {
			org.yokekhei.fsd.capstone.api.entity.Food food = repository.findWithImageAttachedById(id);
			image = food.getImage();
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return image;
	}

	private Sort getSort(PageInfo pageInfo) {
		Direction direction = Direction.ASC;

		if (pageInfo.getDirection() != null && pageInfo.getDirection().equalsIgnoreCase("desc")) {
			direction = Direction.DESC;
		}

		return pageInfo.getSortBy() != null ? Sort.by(direction, pageInfo.getSortBy()) : Sort.unsorted();
	}

}
