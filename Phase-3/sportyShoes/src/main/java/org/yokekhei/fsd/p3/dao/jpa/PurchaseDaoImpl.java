package org.yokekhei.fsd.p3.dao.jpa;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.yokekhei.fsd.p3.dao.PurchaseDao;
import org.yokekhei.fsd.p3.dao.SportyShoesDaoException;
import org.yokekhei.fsd.p3.dto.Category;
import org.yokekhei.fsd.p3.dto.Purchase;
import org.yokekhei.fsd.p3.entity.PurchaseItem;

@Repository
@Qualifier("jpa")
public class PurchaseDaoImpl implements PurchaseDao {
	
	@Resource
	private PurchaseRepository repository;
	
	@Resource
	private PurchaseItemRepository itemRepository;
	
	@Resource
	private PurchaseMapper mapper;

	@Resource
	private CategoryMapper categoryMapper;
	
	@Override
	public List<Purchase> getAllPurchasesCreatedBetween(LocalDateTime start, LocalDateTime end)
			throws SportyShoesDaoException {
		List<Purchase> purchases = null;
		
		try {
			purchases = repository.findAllByCreatedDateTimeBetween(start, end)
					.stream()
					.map(entity -> mapper.toDto(entity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new SportyShoesDaoException(e.getMessage());
		}
		
		return purchases;
	}

	@Override
	public List<Purchase> getPurchasesByCategory(Category category) throws SportyShoesDaoException {
		List<Purchase> purchases = null;
		
		try {
			List<PurchaseItem> items = itemRepository.findAllByCategory(
					categoryMapper.toEntity(category));
			purchases = repository.findDistinctByItemsIn(items)
					.stream()
					.map(entity -> mapper.toDto(entity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new SportyShoesDaoException(e.getMessage());
		}
		
		return purchases;
	}

	@Override
	public Purchase getPurchase(Long id) throws SportyShoesDaoException {
		Purchase purchase = null;
		
		try {
			purchase = repository.findById(id)
					.map(entity -> mapper.toDto(entity))
					.orElse(null);
		} catch (Exception e) {
			throw new SportyShoesDaoException(e.getMessage());
		}
		
		return purchase;
	}

}
