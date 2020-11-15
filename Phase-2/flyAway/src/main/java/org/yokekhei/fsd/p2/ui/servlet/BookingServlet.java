package org.yokekhei.fsd.p2.ui.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.yokekhei.fsd.p2.Common;
import org.yokekhei.fsd.p2.bean.Booking;
import org.yokekhei.fsd.p2.bean.Passenger;
import org.yokekhei.fsd.p2.comparator.booking.ArriveDateTimeComparator;
import org.yokekhei.fsd.p2.comparator.booking.BookingIdComparator;
import org.yokekhei.fsd.p2.comparator.booking.DepartDateTimeComparator;
import org.yokekhei.fsd.p2.comparator.booking.FlightNumberComparator;
import org.yokekhei.fsd.p2.comparator.booking.FromCityComparator;
import org.yokekhei.fsd.p2.comparator.booking.GuestNameComparator;
import org.yokekhei.fsd.p2.comparator.booking.ToCityComparator;
import org.yokekhei.fsd.p2.comparator.booking.TotalPriceComparator;
import org.yokekhei.fsd.p2.service.AdminService;
import org.yokekhei.fsd.p2.service.AdminServiceImpl;

/**
 * Servlet implementation class BookingServlet
 */
@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action == null) {
			doGetAll(request, response);
		} else if (action.equals("view")) {
			doGetView(request, response);
		} else {
			doGetAll(request, response);
		}
	}
	
	private void doGetView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		try {
			AdminService service = new AdminServiceImpl(
					(SessionFactory) (getServletContext().getAttribute("hbmSessionFactory")));
			Booking booking = service.getBooking(Integer.parseInt(request.getParameter("id")));
			
			request.setAttribute("booking", booking);
			
			int adultNo = 0;
			int childNo = 0;
			int infantNo = 0;
			for (Passenger p : booking.getPassengers()) {
				if (p.getType().equals(Common.PASSENGER_ADULT)) {
					adultNo++;
				} else if (p.getType().equals(Common.PASSENGER_CHILD)) {
					childNo++;
				} else if (p.getType().equals(Common.PASSENGER_INFANT)) {
					infantNo++;
				}
			}
			request.setAttribute("adultNo", adultNo);
			request.setAttribute("childNo", childNo);
			request.setAttribute("infantNo", infantNo);
			
			RequestDispatcher rd = request.getRequestDispatcher(View.ADMIN_BOOKING_VIEW);
			rd.include(request, response);
		} catch (Exception e) {
			if (session != null) {
				session.setAttribute("alert", "Get booking failed. Please try again.");
				return;
			}
			
			Common.viewError(e.getMessage(), request, response);
		}
	}
	
	private void doGetAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AdminService service = new AdminServiceImpl(
					(SessionFactory) (getServletContext().getAttribute("hbmSessionFactory")));
			List<Booking> bookings = service.getAllBookings();
			
			if (request.getParameter("sortBy") != null) {
				sort(bookings, request.getParameter("sortBy"));
			}
			
			HttpSession session = request.getSession(false);
			session.setAttribute("bookingList", bookings);
			response.sendRedirect(View.ADMIN_BOOKING_LIST);
		} catch (Exception e) {
			Common.viewError(e.getMessage(), request, response);
		}
	}
	
	private void sort(List<Booking> bookings, String method) {
		if (bookings == null || bookings.isEmpty()) {
			return;
		}
		
		if (method.equals("bookingId")) {
			Collections.sort(bookings, new BookingIdComparator());
		} else if (method.equals("guest")) {
			Collections.sort(bookings, new GuestNameComparator());
		} else if (method.equals("flightCode")) {
			Collections.sort(bookings, new FlightNumberComparator());
		} else if (method.equals("fromCity")) {
			Collections.sort(bookings, new FromCityComparator());
		} else if (method.equals("toCity")) {
			Collections.sort(bookings, new ToCityComparator());
		} else if (method.equals("departDt")) {
			Collections.sort(bookings, new DepartDateTimeComparator());
		} else if (method.equals("arriveDt")) {
			Collections.sort(bookings, new ArriveDateTimeComparator());
		} else if (method.equals("totalPrice")) {
			Collections.sort(bookings, new TotalPriceComparator());
		}
	}

}
