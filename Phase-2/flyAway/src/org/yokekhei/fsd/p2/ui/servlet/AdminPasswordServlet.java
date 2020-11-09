package org.yokekhei.fsd.p2.ui.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.yokekhei.fsd.p2.bean.AdminUser;
import org.yokekhei.fsd.p2.service.AdminService;
import org.yokekhei.fsd.p2.service.AdminServiceImpl;
import org.yokekhei.fsd.p2.service.FlyAwayServiceException;

/**
 * Servlet implementation class AdminPasswordServlet
 */
@WebServlet("/password")
public class AdminPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPasswordServlet() {
        super();
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action == null) {
			response.sendRedirect(View.ADMIN_CHANGE_PASSWORD);
			return;
		}
		
		if (action.equals("change")) {
			doPostChange(request, response);
		} else {
			response.sendRedirect(View.ADMIN_CHANGE_PASSWORD);
		}
	}
	
	private void doPostChange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		AdminUser adminUser = (AdminUser)session.getAttribute("adminUser");
		
		if (request.getParameter("password") == null ||
				request.getParameter("confirmPassword") == null) {
			session.setAttribute("changePasswordStatus", "fail");
		} else if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))) {
			session.setAttribute("changePasswordStatus", "notMatch");
		} else if (request.getParameter("password").equals(adminUser.getPassword())) {
			session.setAttribute("changePasswordStatus", "same");
		} else {
			try {
				AdminService service = new AdminServiceImpl(
						(SessionFactory) (getServletContext().getAttribute("hbmSessionFactory")));
				
				adminUser.setPassword(request.getParameter("password"));
				service.updateAdminUser(adminUser);
				session.setAttribute("changePasswordStatus", "success");
			} catch (FlyAwayServiceException | NumberFormatException e) {
				if (session != null) {
					session.setAttribute("changePasswordStatus", "fail");
				}
			}
		}
		
		response.sendRedirect(View.ADMIN_CHANGE_PASSWORD);
	}

}
