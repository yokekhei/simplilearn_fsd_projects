package org.yokekhei.fsd.p3.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.yokekhei.fsd.p3.dto.Category;
import org.yokekhei.fsd.p3.dto.Purchase;

public interface PurchaseDao {

	List<Purchase> getAllPurchasesCreatedBetween(LocalDateTime start, LocalDateTime end)
			throws SportyShoesDaoException;
	List<Purchase> getPurchasesByCategory(Category category) throws SportyShoesDaoException;
	Purchase getPurchase(Long id) throws SportyShoesDaoException;
	
}
