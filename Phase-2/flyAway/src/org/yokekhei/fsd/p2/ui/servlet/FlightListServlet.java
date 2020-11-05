package org.yokekhei.fsd.p2.ui.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.yokekhei.fsd.p2.bean.AdminUser;

/**
 * Servlet implementation class FlightsListServlet
 */
@WebServlet("/FlightListServlet")
public class FlightListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlightListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		AdminUser adminUser = (AdminUser)session.getAttribute("adminUser");
		response.sendRedirect(View.ADMIN_FLIGHT_LIST);
	}

}
