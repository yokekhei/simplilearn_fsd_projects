package org.yokekhei.fsd.p2.ui.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Application Lifecycle Listener implementation class ApplicationListener
 *
 */
@WebListener
public class ApplicationListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ApplicationListener() {
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	ServletContext sc = sce.getServletContext();
    	SessionFactory sessionFactory = (SessionFactory)sc.getAttribute("hbmSessionFactory");
    	
    	if (sessionFactory != null) {
    		sessionFactory.close();
    	}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	Configuration configuration = new Configuration().configure();
    	configuration.addAnnotatedClass(org.yokekhei.fsd.p2.bean.AdminUser.class);
		configuration.addAnnotatedClass(org.yokekhei.fsd.p2.bean.Place.class);
		configuration.addAnnotatedClass(org.yokekhei.fsd.p2.bean.Airline.class);
		configuration.addAnnotatedClass(org.yokekhei.fsd.p2.bean.Flight.class);
		StandardServiceRegistryBuilder builder =
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		
		ServletContext sc = sce.getServletContext();
		sc.setAttribute("hbmSessionFactory", sessionFactory);
    }
	
}
