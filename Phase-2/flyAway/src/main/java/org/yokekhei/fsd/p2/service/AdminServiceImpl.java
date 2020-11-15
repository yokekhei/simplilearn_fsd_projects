package org.yokekhei.fsd.p2.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.yokekhei.fsd.p2.Common;
import org.yokekhei.fsd.p2.bean.AdminUser;
import org.yokekhei.fsd.p2.bean.Airline;
import org.yokekhei.fsd.p2.bean.Booking;
import org.yokekhei.fsd.p2.bean.Flight;
import org.yokekhei.fsd.p2.bean.Place;
import org.yokekhei.fsd.p2.dao.AdminUserDao;
import org.yokekhei.fsd.p2.dao.AdminUserDaoImpl;
import org.yokekhei.fsd.p2.dao.AirlineDao;
import org.yokekhei.fsd.p2.dao.AirlineDaoImpl;
import org.yokekhei.fsd.p2.dao.BookingDao;
import org.yokekhei.fsd.p2.dao.BookingDaoImpl;
import org.yokekhei.fsd.p2.dao.FlightDao;
import org.yokekhei.fsd.p2.dao.FlightDaoImpl;
import org.yokekhei.fsd.p2.dao.FlyAwayDaoException;
import org.yokekhei.fsd.p2.dao.PlaceDao;
import org.yokekhei.fsd.p2.dao.PlaceDaoImpl;

public class AdminServiceImpl implements AdminService {

	private SessionFactory sessionFactory;
	
	public AdminServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public AdminUser login(String email, String password) throws FlyAwayServiceException {
		AdminUser adminUser = null;
		
		try {
			AdminUserDao dao = new AdminUserDaoImpl(sessionFactory);
			adminUser = dao.getAdminUser(email, password);
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to get admin details - " + e.getMessage());
		}
		
		return adminUser;
	}
	
	@Override
	public void updateAdminUser(AdminUser data) throws FlyAwayServiceException {
		try {
			AdminUserDao dao = new AdminUserDaoImpl(sessionFactory);
			dao.updateAdminUser(data);
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to update admin user - " + e.getMessage());
		}
	}

	@Override
	public List<Place> getAllPlaces() throws FlyAwayServiceException {
		List<Place> places = null;
		
		try {
			PlaceDao dao = new PlaceDaoImpl(sessionFactory);
			places = dao.getAllPlaces();
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to get all places - " + e.getMessage());
		}
		
		return places;
	}
	
	@Override
	public void addPlace(Place data) throws FlyAwayServiceException {
		try {
			PlaceDao dao = new PlaceDaoImpl(sessionFactory);
			dao.addPlace(data);
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to add place - " + e.getMessage());
		}
	}
	
	@Override
	public void updatePlace(Place data) throws FlyAwayServiceException {
		try {
			PlaceDao dao = new PlaceDaoImpl(sessionFactory);
			dao.updatePlace(data);
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to update place - " + e.getMessage());
		}
	}

	@Override
	public void deletePlace(String locationCode) throws FlyAwayServiceException {
		try {
			PlaceDao dao = new PlaceDaoImpl(sessionFactory);
			dao.deletePlace(locationCode);
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to delete place - " + e.getMessage());
		}
	}

	@Override
	public List<Airline> getAllAirlines() throws FlyAwayServiceException {
		List<Airline> airlines = null;
		
		try {
			AirlineDao dao = new AirlineDaoImpl(sessionFactory);
			airlines = dao.getAllAirlines();
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to get all airlines - " + e.getMessage());
		}
		
		return airlines;
	}

	@Override
	public void addAirline(Airline data) throws FlyAwayServiceException {
		try {
			AirlineDao dao = new AirlineDaoImpl(sessionFactory);
			dao.addAirline(data);
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to add airline - " + e.getMessage());
		}
	}

	@Override
	public void updateAirline(Airline data) throws FlyAwayServiceException {
		try {
			AirlineDao dao = new AirlineDaoImpl(sessionFactory);
			dao.updateAirline(data);
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to update airline - " + e.getMessage());
		}
	}

	@Override
	public void deleteAirline(int airlineCode) throws FlyAwayServiceException {
		try {
			AirlineDao dao = new AirlineDaoImpl(sessionFactory);
			dao.deleteAirline(airlineCode);
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to delete airline - " + e.getMessage());
		}
	}

	@Override
	public List<Flight> getAllFlights() throws FlyAwayServiceException {
		List<Flight> flights = null;
		
		try {
			FlightDao dao = new FlightDaoImpl(sessionFactory);
			flights = dao.getAllFlights();
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to get all flights - " + e.getMessage());
		}
		
		return flights;
	}

	@Override
	public void addFlight(Flight data) throws FlyAwayServiceException {
		try {
			FlightDao dao = new FlightDaoImpl(sessionFactory);
			dao.addFlight(data);
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to add flight - " + e.getMessage());
		}
	}
	
	@Override
	public void addFlight(int flightNo, int airlineCode, String srcLocationCode, String dstLocationCode,
			String departDate, String departTime, String arriveDate, String arriveTime, double adultPrice,
			double childPrice, double infantPrice) throws FlyAwayServiceException {
		try {
			AirlineDao airlineDao = new AirlineDaoImpl(sessionFactory);
			Airline airline = airlineDao.getAirline(airlineCode);
			
			PlaceDao placeDao = new PlaceDaoImpl(sessionFactory);
			Place srcLocation = placeDao.getPlace(srcLocationCode);
			Place dstLocation = placeDao.getPlace(dstLocationCode);
			
			addFlight(new Flight(flightNo, airline, srcLocation, dstLocation,
					Common.toLocalDate(departDate), Common.toLocalTime(departTime),
					Common.toLocalDate(arriveDate), Common.toLocalTime(arriveTime),
					adultPrice, childPrice, infantPrice));
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to add flight - " + e.getMessage());
		}
	}

	@Override
	public void updateFlight(Flight data) throws FlyAwayServiceException {
		try {
			FlightDao dao = new FlightDaoImpl(sessionFactory);
			dao.updateFlight(data);
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to update flight - " + e.getMessage());
		}
	}
	
	@Override
	public void updateFlight(int flightId, int flightNo, int airlineCode, String srcLocationCode,
			String dstLocationCode, String departDate, String departTime, String arriveDate, String arriveTime,
			double adultPrice, double childPrice, double infantPrice) throws FlyAwayServiceException {
		try {
			BookingDao bookingDao = new BookingDaoImpl(sessionFactory);
			List<Booking> bookings = bookingDao.getBookingsByFlightId(flightId);
			
			if (!bookings.isEmpty()) {
				throw new FlyAwayServiceException("Flight is booked");
			}
			
			AirlineDao airlineDao = new AirlineDaoImpl(sessionFactory);
			Airline airline = airlineDao.getAirline(airlineCode);
			
			PlaceDao placeDao = new PlaceDaoImpl(sessionFactory);
			Place srcLocation = placeDao.getPlace(srcLocationCode);
			Place dstLocation = placeDao.getPlace(dstLocationCode);
			
			FlightDao flightDao = new FlightDaoImpl(sessionFactory);
			Flight flight = flightDao.getFlight(flightId);
			flight.setFlightNo(flightNo);
			flight.setAirline(airline);
			flight.setSource(srcLocation);
			flight.setDestination(dstLocation);
			flight.setDepartDate(Common.toLocalDate(departDate));
			flight.setDepartTime(Common.toLocalTime(departTime));
			flight.setArriveDate(Common.toLocalDate(arriveDate));
			flight.setArriveTime(Common.toLocalTime(arriveTime));
			flight.setAdultPrice(adultPrice);
			flight.setChildPrice(childPrice);
			flight.setInfantPrice(infantPrice);
			
			flightDao.updateFlight(flight);
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to update flight - " + e.getMessage());
		}
	}

	@Override
	public void deleteFlight(int flightId) throws FlyAwayServiceException {
		try {
			BookingDao bookingDao = new BookingDaoImpl(sessionFactory);
			List<Booking> bookings = bookingDao.getBookingsByFlightId(flightId);
			
			if (!bookings.isEmpty()) {
				throw new FlyAwayServiceException("Flight is booked");
			}
			
			FlightDao dao = new FlightDaoImpl(sessionFactory);
			dao.deleteFlight(flightId);
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to delete flight - " + e.getMessage());
		}
	}

	@Override
	public List<Booking> getAllBookings() throws FlyAwayServiceException {
		List<Booking> bookings = null;
		
		try {
			BookingDao dao = new BookingDaoImpl(sessionFactory);
			bookings = dao.getAllBookings();
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to get bookings - " + e.getMessage());
		}
		
		return bookings;
	}
	
	@Override
	public Booking getBooking(int bookingId) throws FlyAwayServiceException {
		Booking booking = null;
		
		try {
			BookingDao dao = new BookingDaoImpl(sessionFactory);
			booking = dao.getBooking(bookingId);
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to get booking - " + e.getMessage());
		}
		
		return booking;
	}
	
}
