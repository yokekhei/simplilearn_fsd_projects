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
import org.yokekhei.fsd.p2.bean.Place;
import org.yokekhei.fsd.p2.service.AdminService;
import org.yokekhei.fsd.p2.service.AdminServiceImpl;
import org.yokekhei.fsd.p2.service.FlyAwayServiceException;

/**
 * Servlet implementation class PlaceServlet
 */
@WebServlet("/place")
public class PlaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceServlet() {
        super();
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AdminService service = new AdminServiceImpl(
					(SessionFactory) (getServletContext().getAttribute("hbmSessionFactory")));
			List<Place> places = service.getAllPlaces();
			
			HttpSession session = request.getSession(false);
			session.setAttribute("placeList", places);
			response.sendRedirect(View.ADMIN_PLACE_LIST);
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
		try {
			AdminService service = new AdminServiceImpl(
					(SessionFactory) (getServletContext().getAttribute("hbmSessionFactory")));
			service.addPlace(new Place(
					request.getParameter("locationCode"),
					request.getParameter("locationName"),
					request.getParameter("cityName")));
		} catch (FlyAwayServiceException e) {
			HttpSession session = request.getSession(false);
			
			if (session != null) {
				session.setAttribute("alert", "Add place failed. Please try again.");
			} else {
				Common.viewError(e.getMessage(), request, response);
				return;
			}
		}
		
		response.sendRedirect(View.ADMIN_PLACE_LIST_SERVLET);
	}
	
	private void doPostUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AdminService service = new AdminServiceImpl(
					(SessionFactory) (getServletContext().getAttribute("hbmSessionFactory")));
			service.updatePlace(new Place(
					request.getParameter("locationCode"),
					request.getParameter("locationName"),
					request.getParameter("cityName")));
		} catch (FlyAwayServiceException e) {
			HttpSession session = request.getSession(false);
			
			if (session != null) {
				session.setAttribute("alert", "Update place failed. Please try again.");
			} else {
				Common.viewError(e.getMessage(), request, response);
				return;
			}
		}
		
		response.sendRedirect(View.ADMIN_PLACE_LIST_SERVLET);
	}
	
	private void doPostDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AdminService service = new AdminServiceImpl(
					(SessionFactory) (getServletContext().getAttribute("hbmSessionFactory")));
			service.deletePlace(request.getParameter("locationCode"));
		} catch (FlyAwayServiceException e) {
			HttpSession session = request.getSession(false);
			
			if (session != null) {
				session.setAttribute("alert", "Delete place failed. Please try again.");
			} else {
				Common.viewError(e.getMessage(), request, response);
				return;
			}
		}
		
		response.sendRedirect(View.ADMIN_PLACE_LIST_SERVLET);
	}
	
}
