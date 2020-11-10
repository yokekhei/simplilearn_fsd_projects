package org.yokekhei.fsd.p2.ui.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.yokekhei.fsd.p2.bean.Flight;
import org.yokekhei.fsd.p2.service.AdminService;
import org.yokekhei.fsd.p2.service.AdminServiceImpl;
import org.yokekhei.fsd.p2.service.FlyAwayServiceException;

/**
 * Servlet implementation class GuestServlet
 */
@WebServlet("/guest")
public class GuestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(View.GUEST_FLIGHT_SEARCH);
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
		
		if (action.equals("search")) {
			doPostSearch(request, response);
		} else {
			doGet(request, response);
		}
	}
	
	private void doPostSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		
		if (request.getParameter("dstLocation").equals(request.getParameter("srcLocation"))) {
			request.setAttribute("searchFlightStatus", "sameLocation");
			rd = request.getRequestDispatcher(View.GUEST_FLIGHT_SEARCH);
			rd.include(request, response);
			return;
		}
		
		try {
			AdminService service = new AdminServiceImpl(
					(SessionFactory) (getServletContext().getAttribute("hbmSessionFactory")));
			service.getAllFlights();
			List<Flight> flights = service.getFlights(request.getParameter("srcLocation"),
					request.getParameter("dstLocation"),
					request.getParameter("departDate"));
			
			if (flights.isEmpty()) {
				request.setAttribute("searchFlightStatus", "empty");
				rd = request.getRequestDispatcher(View.GUEST_FLIGHT_SEARCH);
				rd.include(request, response);
				return;
			}
			
			request.setAttribute("flightSearchResult", flights);
			request.setAttribute("adultNo", request.getParameter("adultNo"));
			request.setAttribute("childNo", request.getParameter("childNo"));
			request.setAttribute("infantNo", request.getParameter("infantNo"));
			rd = request.getRequestDispatcher(View.GUEST_FLIGHT_SELECT);
			rd.include(request, response);
		} catch (FlyAwayServiceException e) {
			request.setAttribute("searchFlightStatus", "fail");
			rd = request.getRequestDispatcher(View.GUEST_FLIGHT_SEARCH);
			rd.include(request, response);
		}
	}

}
