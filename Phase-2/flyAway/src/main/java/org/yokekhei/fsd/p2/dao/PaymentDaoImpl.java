package org.yokekhei.fsd.p2.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.yokekhei.fsd.p2.bean.Payment;

public class PaymentDaoImpl implements PaymentDao {

	private SessionFactory sessionFactory;
	
	public PaymentDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addPayment(Payment data) throws FlyAwayDaoException {
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
			
			throw new FlyAwayDaoException("Failed to add payment - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<Payment> getAllPayments() throws FlyAwayDaoException {
		Session session = null;
		List<Payment> payments = null;
		
		try {
			session = sessionFactory.openSession();
			payments = session.createQuery("from Payment").getResultList();
		} catch (Exception e) {
			throw new FlyAwayDaoException("Failed to retrieve payments - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return payments;
	}

	@Override
	public Payment getPayment(int paymentId) throws FlyAwayDaoException {
		Session session = null;
		Payment payment = null;
		
		try {
			session = sessionFactory.openSession();
			payment = session.get(Payment.class, paymentId);
			
			if (payment == null) {
				throw new FlyAwayDaoException("No payment found for payment id " + paymentId);
			}
		} catch (Exception e) {
			throw new FlyAwayDaoException("Failed to query payment details - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return payment;
	}

	@Override
	public void updatePayment(Payment data) throws FlyAwayDaoException {
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
			
			throw new FlyAwayDaoException("Failed to update payment - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void deletePayment(int paymentId) throws FlyAwayDaoException {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete Payment where payment_id=:paymentId");
			query.setParameter("paymentId", paymentId);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			
			throw new FlyAwayDaoException("Failed to delete payment for payment id " +
					paymentId + " - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
