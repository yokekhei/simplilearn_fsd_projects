package org.yokekhei.fsd.p2.service;

import java.util.List;

import org.yokekhei.fsd.p2.bean.Flight;
import org.yokekhei.fsd.p2.bean.Payment;
import org.yokekhei.fsd.p2.bean.Place;

public interface GuestService {
	
	List<Place> getAllPlaces() throws FlyAwayServiceException;
	
	List<Flight> getFlights(String srcLocationCode, String dstLocationCode,
			String departDate) throws FlyAwayServiceException;
	Flight getFlight(int flightId) throws FlyAwayServiceException;
	
	double getPassengerServiceCharge() throws FlyAwayServiceException;
	double getRegulatoryServiceCharge() throws FlyAwayServiceException;
	double getServiceTax() throws FlyAwayServiceException;
	
	void addPayment(Payment data) throws FlyAwayServiceException;

}
