package org.yokekhei.fsd.p2.dao;

import java.util.List;

import org.yokekhei.fsd.p2.bean.Payment;

public interface PaymentDao {

	void addPayment(Payment data) throws FlyAwayDaoException;
	List<Payment> getAllPayments() throws FlyAwayDaoException;
	Payment getPayment(int paymentId) throws FlyAwayDaoException;
	void updatePayment(Payment data) throws FlyAwayDaoException;
	void deletePayment(int paymentId) throws FlyAwayDaoException;
	
}
