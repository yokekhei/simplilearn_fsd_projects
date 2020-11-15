package org.yokekhei.fsd.p2.dao;

import java.util.List;

import org.yokekhei.fsd.p2.bean.Booking;

public interface BookingDao {

	void addBooking(Booking data) throws FlyAwayDaoException;
	List<Booking> getAllBookings() throws FlyAwayDaoException;
	Booking getBooking(int bookingId) throws FlyAwayDaoException;
	List<Booking> getBookingsByFlightId(int flightId) throws FlyAwayDaoException;
	void updateBooking(Booking data) throws FlyAwayDaoException;
	void deleteBooking(int bookingId) throws FlyAwayDaoException;
	
}
