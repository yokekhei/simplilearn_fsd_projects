package org.yokekhei.fsd.p2.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.yokekhei.fsd.p2.bean.Booking;

public class BookingDaoImpl implements BookingDao {
	
	private SessionFactory sessionFactory;
	
	public BookingDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addBooking(Booking data) throws FlyAwayDaoException {
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
			
			throw new FlyAwayDaoException("Failed to add booking - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<Booking> getAllBookings() throws FlyAwayDaoException {
		Session session = null;
		List<Booking> bookings = null;
		
		try {
			session = sessionFactory.openSession();
			bookings = session.createQuery("from Booking").getResultList();
		} catch (Exception e) {
			throw new FlyAwayDaoException("Failed to retrieve bookings - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return bookings;
	}

	@Override
	public Booking getBooking(int bookingId) throws FlyAwayDaoException {
		Session session = null;
		Booking booking = null;
		
		try {
			session = sessionFactory.openSession();
			booking = session.get(Booking.class, bookingId);
			
			if (booking == null) {
				throw new FlyAwayDaoException("No booking found for booking id " + bookingId);
			}
		} catch (Exception e) {
			throw new FlyAwayDaoException("Failed to query booking details - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return booking;
	}

	@Override
	public void updateBooking(Booking data) throws FlyAwayDaoException {
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
			
			throw new FlyAwayDaoException("Failed to update booking - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void deleteBooking(int bookingId) throws FlyAwayDaoException {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete Booking where booking_id=:bookingId");
			query.setParameter("bookingId", bookingId);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			
			throw new FlyAwayDaoException("Failed to delete booking for booking id " +
					bookingId + " - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
