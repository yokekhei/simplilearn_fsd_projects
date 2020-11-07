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
import org.yokekhei.fsd.p2.service.AdminService;
import org.yokekhei.fsd.p2.service.AdminServiceImpl;
import org.yokekhei.fsd.p2.service.FlyAwayServiceException;

/**
 * Servlet implementation class AirlineServlet
 */
@WebServlet("/airline")
public class AirlineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AirlineServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AdminService service = new AdminServiceImpl(
					(SessionFactory) (getServletContext().getAttribute("hbmSessionFactory")));
			List<Airline> airlines = service.getAllAirlines();
			
			HttpSession session = request.getSession(false);
			session.setAttribute("airlineList", airlines);
			response.sendRedirect(View.ADMIN_AIRLINE_LIST);
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
		
		try {
			AdminService service = new AdminServiceImpl(
					(SessionFactory) (getServletContext().getAttribute("hbmSessionFactory")));
			service.addAirline(new Airline(
					Integer.parseInt(request.getParameter("airlineCode")),
					request.getParameter("flightCode"),
					request.getParameter("companyName"),
					request.getParameter("country")));
		} catch (FlyAwayServiceException | NumberFormatException e) {
			if (session != null) {
				session.setAttribute("alert", "Add airline failed. Please try again.");
			}
		}
		
		response.sendRedirect(View.ADMIN_AIRLINE_LIST_SERVLET);
	}
	
	private void doPostUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		try {
			AdminService service = new AdminServiceImpl(
					(SessionFactory) (getServletContext().getAttribute("hbmSessionFactory")));
			service.updateAirline(new Airline(
					Integer.parseInt(request.getParameter("airlineCode")),
					request.getParameter("flightCode"),
					request.getParameter("companyName"),
					request.getParameter("country")));
		} catch (FlyAwayServiceException | NumberFormatException e) {
			if (session != null) {
				session.setAttribute("alert", "Update airline failed. Please try again.");
			}
		}
		
		response.sendRedirect(View.ADMIN_AIRLINE_LIST_SERVLET);
	}
	
	private void doPostDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		try {
			AdminService service = new AdminServiceImpl(
					(SessionFactory) (getServletContext().getAttribute("hbmSessionFactory")));
			service.deleteAirline(Integer.parseInt(request.getParameter("airlineCode")));
		} catch (FlyAwayServiceException | NumberFormatException e) {
			if (session != null) {
				session.setAttribute("alert", "Delete airline failed. Please try again.");
			}
		}
		
		response.sendRedirect(View.ADMIN_AIRLINE_LIST_SERVLET);
	}

}
