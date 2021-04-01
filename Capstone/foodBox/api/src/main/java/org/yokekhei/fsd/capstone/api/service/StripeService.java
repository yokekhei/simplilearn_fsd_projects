package org.yokekhei.fsd.capstone.api.service;

import org.yokekhei.fsd.capstone.api.dto.ChargeRequest;
import org.yokekhei.fsd.capstone.api.exception.FoodBoxServiceException;

import com.stripe.model.Charge;

public interface StripeService {

	Charge charge(ChargeRequest chargeRequest) throws FoodBoxServiceException;

}
