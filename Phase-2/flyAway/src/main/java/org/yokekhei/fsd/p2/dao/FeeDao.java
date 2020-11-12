package org.yokekhei.fsd.p2.dao;

public interface FeeDao {

	double getPassengerServiceCharge() throws FlyAwayDaoException;
	double getRegulatoryServiceCharge() throws FlyAwayDaoException;
	double getServiceTax() throws FlyAwayDaoException;
	
}
