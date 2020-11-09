package org.yokekhei.fsd.p2.dao;

import java.util.List;

import org.yokekhei.fsd.p2.bean.Airline;

public interface AirlineDao {

	void addAirline(Airline data) throws FlyAwayDaoException;
	List<Airline> getAllAirlines() throws FlyAwayDaoException;
	Airline getAirline(int airlineCode) throws FlyAwayDaoException;
	void updateAirline(Airline data) throws FlyAwayDaoException;
	void deleteAirline(int airlineCode) throws FlyAwayDaoException;
	
}
