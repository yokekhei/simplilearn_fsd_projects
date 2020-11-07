package org.yokekhei.fsd.p2.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.yokekhei.fsd.p2.bean.Place;

public class PlaceDaoImpl implements PlaceDao {

	SessionFactory sessionFactory;
	
	public PlaceDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addPlace(Place data) throws FlyAwayDaoException {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(data);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			
			throw new FlyAwayDaoException("Failed to add place - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	@Override
	public List<Place> getAllPlaces() throws FlyAwayDaoException {
		Session session = null;
		List<Place> places = null;
		
		try {
			session = sessionFactory.openSession();
			Query<Place> query = session.createQuery("from Place");
			places = query.list();
		} catch (Exception e) {
			throw new FlyAwayDaoException("Failed to retrieve places - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return places;
	}

	@Override
	public Place getPlace(String locationCode) throws FlyAwayDaoException {
		Session session = null;
		Place place = null;
		
		try {
			session = sessionFactory.openSession();
			Query<Place> query = session.createQuery("from Place where location_code=:locationCode");
			query.setParameter("locationCode", locationCode);
			List<Place> result = query.list();
			
			if (result.isEmpty()) {
				throw new FlyAwayDaoException("No place found for location code " + locationCode);
			}
			
			place = result.get(0);
		} catch (Exception e) {
			throw new FlyAwayDaoException("Failed to query place details - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return place;
	}

	@Override
	public void updatePlace(Place data) throws FlyAwayDaoException {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(data);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			
			throw new FlyAwayDaoException("Failed to update place - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void deletePlace(String locationCode) throws FlyAwayDaoException {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete Place where location_code=:locationCode");
			query.setParameter("locationCode", locationCode);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			
			throw new FlyAwayDaoException("Failed to delete place for location code " +
					locationCode + " - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
