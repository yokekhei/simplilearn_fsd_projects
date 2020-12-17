package org.yokekhei.fsd.p3.dao;

import org.yokekhei.fsd.p3.dto.Payment;

public interface PaymentDao {

	Payment save(Payment payment) throws SportyShoesDaoException;
	Payment getPayment(Long id) throws SportyShoesDaoException;
	
}
