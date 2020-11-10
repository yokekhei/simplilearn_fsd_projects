package org.yokekhei.fsd.p2.dao;

import java.util.List;

import org.yokekhei.fsd.p2.bean.Flight;

public interface FlightDao {

	void addFlight(Flight data) throws FlyAwayDaoException;
	List<Flight> getAllFlights() throws FlyAwayDaoException;
	List<Flight> getFlights(String srcLocationCode, String dstLocationCode,
			String departDate) throws FlyAwayDaoException;
	Flight getFlight(int flightId) throws FlyAwayDaoException;
	void updateFlight(Flight data) throws FlyAwayDaoException;
	void deleteFlight(int flightId) throws FlyAwayDaoException;
	
}
