package org.yokekhei.fsd.p2.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.yokekhei.fsd.p2.bean.Airline;

public class AirlineDaoImpl implements AirlineDao {

	private SessionFactory sessionFactory;
	
	public AirlineDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addAirline(Airline data) throws FlyAwayDaoException {
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
			
			throw new FlyAwayDaoException("Failed to add airline - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<Airline> getAllAirlines() throws FlyAwayDaoException {
		Session session = null;
		List<Airline> airlines = null;
		
		try {
			session = sessionFactory.openSession();
			airlines = session.createQuery("from Airline").getResultList();
		} catch (Exception e) {
			throw new FlyAwayDaoException("Failed to retrieve airlines - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return airlines;
	}

	@Override
	public Airline getAirline(int airlineCode) throws FlyAwayDaoException {
		Session session = null;
		Airline airline = null;
		
		try {
			session = sessionFactory.openSession();
			airline = session.get(Airline.class, airlineCode);
			
			if (airline == null) {
				throw new FlyAwayDaoException("No airline found for airline code " + airlineCode);
			}
		} catch (Exception e) {
			throw new FlyAwayDaoException("Failed to query airline details - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return airline;
	}

	@Override
	public void updateAirline(Airline data) throws FlyAwayDaoException {
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
			
			throw new FlyAwayDaoException("Failed to update airline - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void deleteAirline(int airlineCode) throws FlyAwayDaoException {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete Airline where airline_code=:airlineCode");
			query.setParameter("airlineCode", airlineCode);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			
			throw new FlyAwayDaoException("Failed to delete airline for airline code " +
					airlineCode + " - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
