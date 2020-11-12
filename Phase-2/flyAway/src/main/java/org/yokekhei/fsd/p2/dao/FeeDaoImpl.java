package org.yokekhei.fsd.p2.dao;

import java.util.List;
import java.util.TimeZone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.yokekhei.fsd.p2.bean.Fee;

public class FeeDaoImpl implements FeeDao {
	
	private SessionFactory sessionFactory;
	
	public FeeDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public double getPassengerServiceCharge() throws FlyAwayDaoException {
		Session session = null;
		
		try {
			session = sessionFactory.withOptions()
					.jdbcTimeZone(TimeZone.getTimeZone("UTC"))
					.openSession();
			Query query = session.createQuery("from Fee where feeType='passenger_service_change'");
			List<Fee> fees = query.getResultList();
			
			if (fees != null && !fees.isEmpty()) {
				return fees.get(0).getFeeValue();
			}
		} catch (Exception e) {
			throw new FlyAwayDaoException("Failed to retrieve passenger service charge - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return 0.0;
	}

	@Override
	public double getRegulatoryServiceCharge() throws FlyAwayDaoException {
		Session session = null;
		
		try {
			session = sessionFactory.withOptions()
					.jdbcTimeZone(TimeZone.getTimeZone("UTC"))
					.openSession();
			Query query = session.createQuery("from Fee where feeType='regulatory_service_charge'");
			List<Fee> fees = query.getResultList();
			
			if (fees != null && !fees.isEmpty()) {
				return fees.get(0).getFeeValue();
			}
		} catch (Exception e) {
			throw new FlyAwayDaoException("Failed to retrieve regulatory service charge - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return 0.0;
	}

	@Override
	public double getServiceTax() throws FlyAwayDaoException {
		Session session = null;
		
		try {
			session = sessionFactory.withOptions()
					.jdbcTimeZone(TimeZone.getTimeZone("UTC"))
					.openSession();
			Query query = session.createQuery("from Fee where feeType='service_tax'");
			List<Fee> fees = query.getResultList();
			
			if (fees != null && !fees.isEmpty()) {
				return fees.get(0).getFeeValue();
			}
		} catch (Exception e) {
			throw new FlyAwayDaoException("Failed to retrieve service tax - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return 0.0;
	}

}
