package org.yokekhei.fsd.p2.service;

import java.util.List;

import org.yokekhei.fsd.p2.bean.AdminUser;
import org.yokekhei.fsd.p2.bean.Airline;
import org.yokekhei.fsd.p2.bean.Place;

public interface AdminService {

	AdminUser login(String email, String password) throws FlyAwayServiceException;
	
	List<Place> getAllPlaces() throws FlyAwayServiceException;
	void addPlace(Place data) throws FlyAwayServiceException;
	void updatePlace(Place data) throws FlyAwayServiceException;
	void deletePlace(String locationCode) throws FlyAwayServiceException;
	
	List<Airline> getAllAirlines() throws FlyAwayServiceException;
	void addAirline(Airline data) throws FlyAwayServiceException;
	void updateAirline(Airline data) throws FlyAwayServiceException;
	void deleteAirline(int airlineCode) throws FlyAwayServiceException;
	
}
