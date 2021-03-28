package org.yokekhei.fsd.capstone.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yokekhei.fsd.capstone.api.entity.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long> {

}
