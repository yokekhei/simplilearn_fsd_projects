package org.yokekhei.fsd.p2.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.yokekhei.fsd.p2.bean.AdminUser;
import org.yokekhei.fsd.p2.bean.Airline;
import org.yokekhei.fsd.p2.bean.Place;
import org.yokekhei.fsd.p2.dao.AdminUserDao;
import org.yokekhei.fsd.p2.dao.AdminUserDaoImpl;
import org.yokekhei.fsd.p2.dao.AirlineDao;
import org.yokekhei.fsd.p2.dao.AirlineDaoImpl;
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

}
