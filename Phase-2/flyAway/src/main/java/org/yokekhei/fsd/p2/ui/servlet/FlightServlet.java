package org.yokekhei.fsd.p2.ui.servlet;

import java.io.IOException;
import java.util.Collections;
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
import org.yokekhei.fsd.p2.comparator.flight.AdultPriceComparator;
import org.yokekhei.fsd.p2.comparator.flight.ArriveDateTimeComparator;
import org.yokekhei.fsd.p2.comparator.flight.ChildPriceComparator;
import org.yokekhei.fsd.p2.comparator.flight.DepartDateTimeComparator;
import org.yokekhei.fsd.p2.comparator.flight.FlightCodeComparator;
import org.yokekhei.fsd.p2.comparator.flight.FlightNoComparator;
import org.yokekhei.fsd.p2.comparator.flight.FromCityComparator;
import org.yokekhei.fsd.p2.comparator.flight.InfantPriceComparator;
import org.yokekhei.fsd.p2.comparator.flight.ToCityComparator;
import org.yokekhei.fsd.p2.service.AdminService;
import org.yokekhei.fsd.p2.service.AdminServiceImpl;
import org.yokekhei.fsd.p2.service.FlyAwayServiceException;

/**
 * Servlet implementation class FlightServlet
 */
@WebServlet("/flight")
public class FlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String INVALID_DATE_SELECTION = "Arrive date/time must be later than depart date/time";
	private static final String INVALID_PLACE_SELECTION = "From city must not same as To city";
       
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
			
			if (request.getParameter("sortBy") != null) {
				sort(flights, request.getParameter("sortBy"));
			}
			
			HttpSession session = request.getSession(false);
			session.setAttribute("flightList", flights);
			session.setAttribute("placeList", places);
			session.setAttribute("airlineList", airlines);
			response.sendRedirect(View.ADMIN_FLIGHT_LIST);
		} catch (Exception e) {
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
		} else if (action.equals("add")) {
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
		
		if (!isValid(session, request, response)) {
			response.sendRedirect(View.ADMIN_FLIGHT_LIST_SERVLET);
			return;
		}
		
		try {
			AdminService service = new AdminServiceImpl(
					(SessionFactory) (getServletContext().getAttribute("hbmSessionFactory")));
			service.addFlight(Integer.parseInt(request.getParameter("flightNo")),
					Integer.parseInt(request.getParameter("airline")),
					request.getParameter("srcLocation"),
					request.getParameter("dstLocation"),
					request.getParameter("departDate"),
					request.getParameter("departTime"),
					request.getParameter("arriveDate"),
					request.getParameter("arriveTime"),
					Double.parseDouble(request.getParameter("adultPrice")),
					Double.parseDouble(request.getParameter("childPrice")),
					Double.parseDouble(request.getParameter("infantPrice")));
		} catch (FlyAwayServiceException | NumberFormatException e) {
			if (session != null) {
				session.setAttribute("alert", "Add flight failed. Please try again.");
			} else {
				Common.viewError(e.getMessage(), request, response);
				return;
			}
		}
		
		response.sendRedirect(View.ADMIN_FLIGHT_LIST_SERVLET);
	}
	
	private void doPostUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (!isValid(session, request, response)) {
			response.sendRedirect(View.ADMIN_FLIGHT_LIST_SERVLET);
			return;
		}
		
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
					request.getParameter("arriveDate"),
					request.getParameter("arriveTime"),
					Double.parseDouble(request.getParameter("adultPrice")),
					Double.parseDouble(request.getParameter("childPrice")),
					Double.parseDouble(request.getParameter("infantPrice")));
		} catch (FlyAwayServiceException | NumberFormatException e) {
			if (session != null) {
				session.setAttribute("alert", "Update flight failed. Please try again.");
			} else {
				Common.viewError(e.getMessage(), request, response);
				return;
			}
		}
		
		response.sendRedirect(View.ADMIN_FLIGHT_LIST_SERVLET);
	}
	
	private void doPostDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AdminService service = new AdminServiceImpl(
					(SessionFactory) (getServletContext().getAttribute("hbmSessionFactory")));
			service.deleteFlight(Integer.parseInt(request.getParameter("flightId")));
		} catch (FlyAwayServiceException | NumberFormatException e) {
			HttpSession session = request.getSession(false);
			
			if (session != null) {
				session.setAttribute("alert", "Delete flight failed. Please try again.");
			} else {
				Common.viewError(e.getMessage(), request, response);
				return;
			}
		}
		
		response.sendRedirect(View.ADMIN_FLIGHT_LIST_SERVLET);
	}
	
	private boolean isValid(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (!isDateSelectionValid(request.getParameter("departDate"),
					request.getParameter("departTime"),
					request.getParameter("arriveDate"),
					request.getParameter("arriveTime"))) {
				session.setAttribute("alert", INVALID_DATE_SELECTION);
				return false;
			}
			
			if (request.getParameter("srcLocation").equals(request.getParameter("dstLocation"))) {
				session.setAttribute("alert", INVALID_PLACE_SELECTION);
				return false;
			}
		} catch (Exception e) {
			Common.viewError(e.getMessage(), request, response);
			return false;
		}
		
		return true;
	}
	
	private boolean isDateSelectionValid(String departDate, String departTime,
			String arriveDate, String arriveTime) {
		String departDateTime = departDate + departTime;
		String arriveDateTime = arriveDate + arriveTime;
		
		if (arriveDateTime.compareTo(departDateTime) <= 0) {
			return false;
		}
		
		return true;
	}
	
	private void sort(List<Flight> flights, String method) {
		if (flights == null || flights.isEmpty()) {
			return;
		}
		
		if (method.equals("flightNo")) {
			Collections.sort(flights, new FlightNoComparator());
		} else if (method.equals("flightCode")) {
			Collections.sort(flights, new FlightCodeComparator());
		} else if (method.equals("fromCity")) {
			Collections.sort(flights, new FromCityComparator());
		} else if (method.equals("toCity")) {
			Collections.sort(flights, new ToCityComparator());
		} else if (method.equals("departDt")) {
			Collections.sort(flights, new DepartDateTimeComparator());
		} else if (method.equals("arriveDt")) {
			Collections.sort(flights, new ArriveDateTimeComparator());
		} else if (method.equals("adultPrice")) {
			Collections.sort(flights, new AdultPriceComparator());
		} else if (method.equals("childPrice")) {
			Collections.sort(flights, new ChildPriceComparator());
		} else if (method.equals("infantPrice")) {
			Collections.sort(flights, new InfantPriceComparator());
		}
	}
	
}
