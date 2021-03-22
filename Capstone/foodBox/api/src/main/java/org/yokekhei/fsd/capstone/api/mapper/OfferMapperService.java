package org.yokekhei.fsd.capstone.api.mapper;

import org.mapstruct.Mapper;

@Mapper
public interface OfferMapperService {

	default org.yokekhei.fsd.capstone.api.entity.Offer findById(Long id) {
		if (id == null) {
			return null;
		}

		org.yokekhei.fsd.capstone.api.entity.Offer offer = new org.yokekhei.fsd.capstone.api.entity.Offer();
		offer.setId(id);

		return offer;
	}

	default Long findByOffer(org.yokekhei.fsd.capstone.api.entity.Offer offer) {
		if (offer == null) {
			return null;
		}

		return offer.getId();
	}

}
