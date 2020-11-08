package org.yokekhei.fsd.p2.ui.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.yokekhei.fsd.p2.Common;
import org.yokekhei.fsd.p2.bean.Airline;
import org.yokekhei.fsd.p2.bean.Flight;
import org.yokekhei.fsd.p2.bean.Place;
import org.yokekhei.fsd.p2.service.AdminService;
import org.yokekhei.fsd.p2.service.AdminServiceImpl;
import org.yokekhei.fsd.p2.service.FlyAwayServiceException;

/**
 * Servlet implementation class FlightServlet
 */
@WebServlet("/flight")
public class FlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlightServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AdminService service = new AdminServiceImpl(
					(SessionFactory) (getServletContext().getAttribute("hbmSessionFactory")));
			List<Flight> flights = service.getAllFlights();
			List<Place> places = service.getAllPlaces();
			List<Airline> airlines = service.getAllAirlines();
			
			HttpSession session = request.getSession(false);
			session.setAttribute("flightList", flights);
			session.setAttribute("placeList", places);
			session.setAttribute("airlineList", airlines);
			response.sendRedirect(View.ADMIN_FLIGHT_LIST);
		} catch (FlyAwayServiceException e) {
			Common.viewError(e.getMessage(), request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action == null) {
			doGet(request, response);
			return;
		}
		
		if (action.equals("add")) {
			doPostAdd(request, response);
		} else if (action.equals("update")) {
			doPostUpdate(request, response);
		} else if (action.equals("delete")) {
			doPostDelete(request, response);
		} else {
			doGet(request, response);
		}
	}
	
	private void doPostAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		System.out.println("flightNo = " + request.getParameter("flightNo"));
		System.out.println("airline = " + request.getParameter("airline"));
		System.out.println("srcLocation = " + request.getParameter("srcLocation"));
		System.out.println("dstLocation = " + request.getParameter("dstLocation"));
		System.out.println("departDate = " + request.getParameter("departDate"));
		System.out.println("departTime = " + request.getParameter("departTime"));
		System.out.println("adultPrice = " + request.getParameter("adultPrice"));
		System.out.println("childPrice = " + request.getParameter("childPrice"));
		System.out.println("infantPrice" + request.getParameter("infantPrice"));
		
		try {
			AdminService service = new AdminServiceImpl(
					(SessionFactory) (getServletContext().getAttribute("hbmSessionFactory")));
			service.addFlight(Integer.parseInt(request.getParameter("flightNo")),
					Integer.parseInt(request.getParameter("airline")),
					request.getParameter("srcLocation"),
					request.getParameter("dstLocation"),
					request.getParameter("departDate"),
					request.getParameter("departTime"),
					Double.parseDouble(request.getParameter("adultPrice")),
					Double.parseDouble(request.getParameter("childPrice")),
					Double.parseDouble(request.getParameter("infantPrice")));
		} catch (FlyAwayServiceException | NumberFormatException e) {
			if (session != null) {
				session.setAttribute("alert", "Add flight failed. Please try again.");
			}
		}
		
		response.sendRedirect(View.ADMIN_FLIGHT_LIST_SERVLET);
	}
	
	private void doPostUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		try {
			AdminService service = new AdminServiceImpl(
					(SessionFactory) (getServletContext().getAttribute("hbmSessionFactory")));
			service.updateFlight(Integer.parseInt(request.getParameter("flightId")),
					Integer.parseInt(request.getParameter("flightNo")),
					Integer.parseInt(request.getParameter("airline")),
					request.getParameter("srcLocation"),
					request.getParameter("dstLocation"),
					request.getParameter("departDate"),
					request.getParameter("departTime"),
					Double.parseDouble(request.getParameter("adultPrice")),
					Double.parseDouble(request.getParameter("childPrice")),
					Double.parseDouble(request.getParameter("infantPrice")));
		} catch (FlyAwayServiceException | NumberFormatException e) {
			if (session != null) {
				session.setAttribute("alert", "Update flight failed. Please try again.");
			}
		}
		
		response.sendRedirect(View.ADMIN_FLIGHT_LIST_SERVLET);
	}
	
	private void doPostDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		try {
			AdminService service = new AdminServiceImpl(
					(SessionFactory) (getServletContext().getAttribute("hbmSessionFactory")));
			service.deleteFlight(Integer.parseInt(request.getParameter("flightId")));
		} catch (FlyAwayServiceException | NumberFormatException e) {
			if (session != null) {
				session.setAttribute("alert", "Delete flight failed. Please try again.");
			}
		}
		
		response.sendRedirect(View.ADMIN_FLIGHT_LIST_SERVLET);
	}
	
}
