package org.yokekhei.fsd.p2.ui.servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.yokekhei.fsd.p2.bean.Flight;
import org.yokekhei.fsd.p2.bean.Guest;
import org.yokekhei.fsd.p2.bean.Passenger;
import org.yokekhei.fsd.p2.bean.Payment;
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
		String action = request.getParameter("action");
		
		if (action == null) {
			response.sendRedirect(View.GUEST_FLIGHT_SEARCH);
		} else if (action.equals("book")) {
			doGetBook(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action == null) {
			doGet(request, response);
		} else if (action.equals("register")) {
			doPostRegister(request, response);
		} else if (action.equals("search")) {
			doPostSearch(request, response);
		} else if (action.equals("pay")) {
			doPostPay(request, response);
		} else if (action.equals("paid")) {
			doPostPaid(request, response);
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
	
	private void doGetBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		
		try {
			Integer adultNo = Integer.parseInt(request.getParameter("adult"));
			Integer childNo = Integer.parseInt(request.getParameter("child"));
			Integer infantNo = Integer.parseInt(request.getParameter("infant"));
			request.setAttribute("adultNo", adultNo);
			request.setAttribute("childNo", childNo);
			request.setAttribute("infantNo", infantNo);
			request.setAttribute("totalPerson", (adultNo + childNo + infantNo));
			request.setAttribute("flightId", request.getParameter("id"));
			rd = request.getRequestDispatcher(View.GUEST_DETAILS);
			rd.include(request, response);
		} catch (NumberFormatException e) {
			Common.viewError(e.getMessage(), request, response);
		}
	}
	
	private void doPostRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AdminService service = new AdminServiceImpl(
					(SessionFactory) (getServletContext().getAttribute("hbmSessionFactory")));
			Flight flight = service.getFlight(Integer.parseInt(request.getParameter("id")));
			
			Guest guest = new Guest(request.getParameter("email"),
					request.getParameter("firstName"),
					request.getParameter("lastName"),
					request.getParameter("phone"));
			
			int adultNo = Integer.parseInt(request.getParameter("adult"));
			int childNo = Integer.parseInt(request.getParameter("child"));
			int infantNo = Integer.parseInt(request.getParameter("infant"));
			List<Passenger> passengers = new ArrayList<>();
			
			for (int i=1; i<=adultNo; i++) {
				Passenger p = new Passenger(request.getParameter("adultFirstName" + i),
						request.getParameter("adultLastName" + i),
						Common.toLocalDate(request.getParameter("adultDob" + i)),
						request.getParameter("adultGender" + i),
						Common.PASSENGER_ADULT);
				passengers.add(p);
			}
			
			for (int i=1; i<=childNo; i++) {
				Passenger p = new Passenger(request.getParameter("childFirstName" + i),
						request.getParameter("childLastName" + i),
						Common.toLocalDate(request.getParameter("childDob" + i)),
						request.getParameter("childGender" + i),
						Common.PASSENGER_CHILD);
				passengers.add(p);
			}
			
			for (int i=1; i<=infantNo; i++) {
				Passenger p = new Passenger(request.getParameter("infantFirstName" + i),
						request.getParameter("infantLastName" + i),
						Common.toLocalDate(request.getParameter("infantDob" + i)),
						request.getParameter("infantGender" + i),
						Common.PASSENGER_INFANT);
				passengers.add(p);
			}
			
			Booking b = new Booking(flight, guest, passengers,
					adultNo * flight.getAdultPrice(),
					childNo * flight.getChildPrice(),
					infantNo * flight.getInfantPrice(),
					(adultNo + childNo) * service.getPassengerServiceCharge(),
					(adultNo + childNo) * service.getServiceTax(),
					(adultNo + childNo) * service.getRegulatoryServiceCharge());
			
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(1800);  // session expired in 30 minutes
			session.setAttribute("bookingDetails", b);
			
			DecimalFormat df2 = new DecimalFormat(Common.DECIMAL_FORMAT_DF2);
			request.setAttribute("totalCharge", df2.format(Common.roundBigDecimal(
					b.getTotalAdultFare() + b.getTotalChildFare() + b.getTotalInfantFare() +
					b.getTotalPassengerServiceCharge() + b.getTotalServiceTax() +
					b.getTotalRegulatoryServiceCharge(), 2)));
			request.setAttribute("flightDuration", Common.getDurationInHourMinute(
					b.getFlight().getDepartDateTime(), b.getFlight().getArriveDateTime()));
			request.setAttribute("adultNo", adultNo);
			request.setAttribute("childNo", childNo);
			request.setAttribute("infantNo", infantNo);
			
			RequestDispatcher rd = request.getRequestDispatcher(View.BOOKING_DETAILS);
			rd.include(request, response);
		} catch (FlyAwayServiceException | NumberFormatException e) {
			Common.viewError(e.getMessage(), request, response);
		}
	}
	
	private void doPostPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Booking b = null;
		
		try {
			b = (Booking)session.getAttribute("bookingDetails");
			
			DecimalFormat df2 = new DecimalFormat(Common.DECIMAL_FORMAT_DF2);
			request.setAttribute("totalCharge", df2.format(Common.roundBigDecimal(
					b.getTotalAdultFare() + b.getTotalChildFare() + b.getTotalInfantFare() +
					b.getTotalPassengerServiceCharge() + b.getTotalServiceTax() +
					b.getTotalRegulatoryServiceCharge(), 2)));
			request.setAttribute("currentYear", Integer.parseInt(Common.getCurrentYear(Common.YEAR_FORMAT)));
			
			RequestDispatcher rd = request.getRequestDispatcher(View.PAYMENT);
			rd.include(request, response);
		} catch (Exception e) {
			if (session == null || b == null) {
				Common.viewSessionExpired(request, response, View.GUEST_FLIGHT_SEARCH);
				return;
			}
			
			Common.viewError(e.getMessage(), request, response);
		}
	}
	
	private void doPostPaid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Booking b = null;
		
		try {
			b = (Booking)session.getAttribute("bookingDetails");
			Payment p = new Payment(b, request.getParameter("nameOnCard"),
					b.getTotalAdultFare() + b.getTotalChildFare() + b.getTotalInfantFare() +
					b.getTotalPassengerServiceCharge() + b.getTotalServiceTax() +
					b.getTotalRegulatoryServiceCharge());
			AdminService service = new AdminServiceImpl(
					(SessionFactory) (getServletContext().getAttribute("hbmSessionFactory")));
			service.addPayment(p);
			
			session.invalidate();
			
			request.setAttribute("bookingDetails", b);
			
			LocalDateTime boardingDateTime = Common.toLocalDateTime(
					Common.toLocalDateTime(b.getFlight().getDepartDate(),
							b.getFlight().getDepartTime())).minusHours(1);
			
			request.setAttribute("boardingDate",
					Common.toLocalDateString(boardingDateTime.toLocalDate(), Common.DATE_FORMAT2));
			request.setAttribute("boardingTime",
					Common.toLocalTimeString(boardingDateTime.toLocalTime(), Common.TIME_FORMAT));
			
			RequestDispatcher rd = request.getRequestDispatcher(View.BOARDING_PASS);
			rd.include(request, response);
		} catch (Exception e) {
			if (session == null || b == null) {
				Common.viewSessionExpired(request, response, View.GUEST_FLIGHT_SEARCH);
				return;
			}
			
			Common.viewError(e.getMessage(), request, response);
		}
	}

}
