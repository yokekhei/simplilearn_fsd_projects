package org.yokekhei.fsd.p2.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.yokekhei.fsd.p2.bean.Flight;
import org.yokekhei.fsd.p2.bean.Payment;
import org.yokekhei.fsd.p2.bean.Place;
import org.yokekhei.fsd.p2.dao.FeeDao;
import org.yokekhei.fsd.p2.dao.FeeDaoImpl;
import org.yokekhei.fsd.p2.dao.FlightDao;
import org.yokekhei.fsd.p2.dao.FlightDaoImpl;
import org.yokekhei.fsd.p2.dao.FlyAwayDaoException;
import org.yokekhei.fsd.p2.dao.PaymentDao;
import org.yokekhei.fsd.p2.dao.PaymentDaoImpl;
import org.yokekhei.fsd.p2.dao.PlaceDao;
import org.yokekhei.fsd.p2.dao.PlaceDaoImpl;

public class GuestServiceImpl implements GuestService {
	
	private SessionFactory sessionFactory;
	
	public GuestServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
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
	public List<Flight> getFlights(String srcLocationCode, String dstLocationCode, String departDate)
			throws FlyAwayServiceException {
		List<Flight> flights = null;
		
		try {
			FlightDao dao = new FlightDaoImpl(sessionFactory);
			flights = dao.getFlights(srcLocationCode, dstLocationCode, departDate);
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to get flights - " + e.getMessage());
		}
		
		return flights;
	}
	
	@Override
	public Flight getFlight(int flightId) throws FlyAwayServiceException {
		Flight flight = null;
		
		try {
			FlightDao dao = new FlightDaoImpl(sessionFactory);
			flight = dao.getFlight(flightId);
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to get flight - " + e.getMessage());
		}
		
		return flight;
	}
	
	@Override
	public double getPassengerServiceCharge() throws FlyAwayServiceException {
		try {
			FeeDao dao = new FeeDaoImpl(sessionFactory);
			return dao.getPassengerServiceCharge();
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to get passenger service charge - " + e.getMessage());
		}
	}

	@Override
	public double getRegulatoryServiceCharge() throws FlyAwayServiceException {
		try {
			FeeDao dao = new FeeDaoImpl(sessionFactory);
			return dao.getRegulatoryServiceCharge();
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to get regulatory service charge - " + e.getMessage());
		}
	}

	@Override
	public double getServiceTax() throws FlyAwayServiceException {
		try {
			FeeDao dao = new FeeDaoImpl(sessionFactory);
			return dao.getServiceTax();
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to get service tax - " + e.getMessage());
		}
	}
	
	@Override
	public void addPayment(Payment data) throws FlyAwayServiceException {
		try {
			PaymentDao dao = new PaymentDaoImpl(sessionFactory);
			dao.addPayment(data);
		} catch (FlyAwayDaoException e) {
			throw new FlyAwayServiceException("Failed to add payment - " + e.getMessage());
		}
	}
	
}
