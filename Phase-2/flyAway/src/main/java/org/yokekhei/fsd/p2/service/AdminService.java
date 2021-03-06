package org.yokekhei.fsd.p2.service;

import java.util.List;

import org.yokekhei.fsd.p2.bean.AdminUser;
import org.yokekhei.fsd.p2.bean.Airline;
import org.yokekhei.fsd.p2.bean.Booking;
import org.yokekhei.fsd.p2.bean.Flight;
import org.yokekhei.fsd.p2.bean.Place;

public interface AdminService {

	AdminUser login(String email, String password) throws FlyAwayServiceException;
	void updateAdminUser(AdminUser data) throws FlyAwayServiceException;
	
	List<Place> getAllPlaces() throws FlyAwayServiceException;
	void addPlace(Place data) throws FlyAwayServiceException;
	void updatePlace(Place data) throws FlyAwayServiceException;
	void deletePlace(String locationCode) throws FlyAwayServiceException;
	
	List<Airline> getAllAirlines() throws FlyAwayServiceException;
	void addAirline(Airline data) throws FlyAwayServiceException;
	void updateAirline(Airline data) throws FlyAwayServiceException;
	void deleteAirline(int airlineCode) throws FlyAwayServiceException;
	
	List<Flight> getAllFlights() throws FlyAwayServiceException;
	void addFlight(Flight data) throws FlyAwayServiceException;
	void addFlight(int flightNo, int airlineCode,
			String srcLocationCode, String dstLocationCode,
			String departDate, String departTime,
			String arriveDate, String arriveTime,
			double adultPrice, double childPrice, double infantPrice) throws FlyAwayServiceException;
	void updateFlight(Flight data) throws FlyAwayServiceException;
	void updateFlight(int flightId, int flightNo, int airlineCode,
			String srcLocationCode, String dstLocationCode,
			String departDate, String departTime,
			String arriveDate, String arriveTime,
			double adultPrice, double childPrice, double infantPrice) throws FlyAwayServiceException;
	void deleteFlight(int flightId) throws FlyAwayServiceException;
	
	List<Booking> getAllBookings() throws FlyAwayServiceException;
	Booking getBooking(int bookingId) throws FlyAwayServiceException;
	
}
