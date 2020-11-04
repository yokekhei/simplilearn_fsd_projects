package org.yokekhei.fsd.p2.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.yokekhei.fsd.p2.bean.AdminUser;

public class AdminDaoImpl implements AdminDao {

	SessionFactory sessionFactory;
	
	public AdminDaoImpl() {
		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(org.yokekhei.fsd.p2.bean.AdminUser.class);
		StandardServiceRegistryBuilder builder =
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(builder.build());
	}
	
	@Override
	public AdminUser getAdminDetails(String email, String password) throws FlyAwayDaoException {
		Session session = null;
		AdminUser adminUser = null;
		
		try {
			session = sessionFactory.openSession();
			Query<AdminUser> query = session.createQuery("from AdminUser where admin_email=:email "
					+ "and admin_password=:password");
			query.setParameter("email", email);
			query.setParameter("password", password);
			List<AdminUser> result = query.list();
			
			if (result.isEmpty()) {
				throw new FlyAwayDaoException("No admin details found for email " + email);
			}
			
			adminUser = result.get(0);
		} catch (HibernateException e) {
			throw new FlyAwayDaoException("Failed to query admin details - " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return adminUser;
	}

}
