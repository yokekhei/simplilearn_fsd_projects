package org.yokekhei.fsd.capstone.api.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yokekhei.fsd.capstone.api.dao.OfferDao;
import org.yokekhei.fsd.capstone.api.dto.Offer;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxServiceException;

@Service
public class OfferServiceImpl implements OfferService {

	@Resource
	private OfferDao offerDao;

	@Override
	@Transactional
	public List<Offer> getOffers() throws FoodBoxServiceException {
		List<Offer> offers = null;

		try {
			offers = offerDao.getOffers();
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return offers;
	}

	@Override
	@Transactional
	public Offer getOffer(Long id) throws FoodBoxServiceException {
		Offer offer = null;

		try {
			offer = offerDao.getOffer(id);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return offer;
	}

	@Override
	@Transactional
	public Offer createOffer(Offer offer) throws FoodBoxServiceException {
		Offer savedOffer = null;

		try {
			savedOffer = offerDao.save(offer);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return savedOffer;
	}

	@Override
	@Transactional
	public Offer updateOffer(Offer offer) throws FoodBoxServiceException {
		Offer savedOffer = null;

		try {
			if (offer.getId() == null) {
				throw new FoodBoxServiceException("Offer id cannot be null.");
			}

			if (offerDao.getOffer(offer.getId()) == null) {
				throw new FoodBoxServiceException("Offer " + offer.getId() + "not found.");
			}

			savedOffer = offerDao.save(offer);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return savedOffer;
	}

	@Override
	@Transactional
	public Offer deleteOffer(Long id) throws FoodBoxServiceException {
		Offer deletedOffer = null;

		try {
			if (id == null) {
				throw new FoodBoxServiceException("Offer id cannot be null.");
			}

			deletedOffer = offerDao.getOffer(id);

			if (deletedOffer == null) {
				throw new FoodBoxServiceException("Offer " + id + "not found.");
			}

			offerDao.remove(id);
		} catch (Exception e) {
			throw new FoodBoxServiceException(e.getMessage());
		}

		return deletedOffer;
	}

}
