package org.yokekhei.fsd.p2.dao;

import java.util.List;
import java.util.TimeZone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.yokekhei.fsd.p2.Common;
import org.yokekhei.fsd.p2.bean.Flight;

public class FlightDaoImpl implements FlightDao {

	private SessionFactory sessionFactory;
	
	public FlightDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addFlight(Flight data) throws FlyAwayDaoException {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.withOptions()
					.jdbcTimeZone(TimeZone.getTimeZone("UTC"))
					.openSession();
			transaction = session.beginTransaction();
			session.save(data);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			
			throw new FlyAwayDaoException("Failed to add flight - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}	
	}
	
	@Override
	public List<Flight> getAllFlights() throws FlyAwayDaoException {
		Session session = null;
		List<Flight> flights = null;
		
		try {
			session = sessionFactory.withOptions()
					.jdbcTimeZone(TimeZone.getTimeZone("UTC"))
					.openSession();
			flights = session.createQuery("from Flight").getResultList();
		} catch (Exception e) {
			throw new FlyAwayDaoException("Failed to retrieve flights - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return flights;
	}
	
	@Override
	public List<Flight> getFlights(String srcLocationCode, String dstLocationCode, String departDate)
			throws FlyAwayDaoException {
		Session session = null;
		List<Flight> flights = null;
		
		try {
			session = sessionFactory.withOptions()
					.jdbcTimeZone(TimeZone.getTimeZone("UTC"))
					.openSession();
			Query query = session.createQuery("from Flight where source.locationCode=:srcLocationCode and " +
					"destination.locationCode=:dstLocationCode and departDate=:departDate");
			query.setParameter("srcLocationCode", srcLocationCode);
			query.setParameter("dstLocationCode", dstLocationCode);
			query.setParameter("departDate", Common.toLocalDate(departDate));
			flights = query.getResultList();
		} catch (Exception e) {
			throw new FlyAwayDaoException("Failed to retrieve flights - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return flights;
	}

	@Override
	public Flight getFlight(int flightId) throws FlyAwayDaoException {
		Session session = null;
		Flight flight = null;
		
		try {
			session = sessionFactory.withOptions()
						.jdbcTimeZone(TimeZone.getTimeZone("UTC"))
						.openSession();
			flight = session.get(Flight.class, flightId);
			
			if (flight == null) {
				throw new FlyAwayDaoException("No flight found for flight id " + flightId);
			}
		} catch (Exception e) {
			throw new FlyAwayDaoException("Failed to query flight details - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return flight;
	}

	@Override
	public void updateFlight(Flight data) throws FlyAwayDaoException {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.withOptions()
					.jdbcTimeZone(TimeZone.getTimeZone("UTC"))
					.openSession();
			transaction = session.beginTransaction();
			session.update(data);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			
			throw new FlyAwayDaoException("Failed to update flight - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void deleteFlight(int flightId) throws FlyAwayDaoException {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.withOptions()
					.jdbcTimeZone(TimeZone.getTimeZone("UTC"))
					.openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete Flight where flight_id=:flightId");
			query.setParameter("flightId", flightId);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			
			throw new FlyAwayDaoException("Failed to delete flight for flight id " +
					flightId + " - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
