package org.yokekhei.fsd.capstone.api.dao;

import java.util.List;

import org.yokekhei.fsd.capstone.api.dto.Offer;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxDaoException;

public interface OfferDao {

	Offer getOffer(Long id) throws FoodBoxDaoException;

	List<Offer> getOffers() throws FoodBoxDaoException;

	Offer save(Offer offer) throws FoodBoxDaoException;

	void remove(Long id) throws FoodBoxDaoException;

}
