package org.yokekhei.fsd.capstone.api.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.yokekhei.fsd.capstone.api.dto.Offer;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxDaoException;
import org.yokekhei.fsd.capstone.api.mapper.OfferMapper;
import org.yokekhei.fsd.capstone.api.repository.OfferRepository;

@Repository
public class OfferDaoImpl implements OfferDao {

	@Resource
	private OfferRepository repository;

	@Resource
	private OfferMapper mapper;

	@Override
	public Offer getOffer(Long id) throws FoodBoxDaoException {
		Offer offer = null;

		try {
			offer = repository.findById(id)
					.map(entity -> mapper.toDto(entity))
					.orElse(null);
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return offer;
	}

	@Override
	public List<Offer> getOffers() throws FoodBoxDaoException {
		List<Offer> offers = null;

		try {
			offers = repository.findAll().stream()
					.map(entity -> mapper.toDto(entity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return offers;
	}

	@Override
	public Offer save(Offer offer) throws FoodBoxDaoException {
		Offer savedOffer = null;

		try {
			savedOffer = mapper.toDto(repository.save(mapper.toEntity(offer)));
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}

		return savedOffer;
	}

	@Override
	public void remove(Long id) throws FoodBoxDaoException {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new FoodBoxDaoException(e.getMessage());
		}
	}

}
