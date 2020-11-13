package org.yokekhei.fsd.p2.ui.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.yokekhei.fsd.p2.ui.servlet.View;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter(
		dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE
		}
					, 
		servletNames = {
				"org.yokekhei.fsd.p2.ui.servlet.AdminLogoutServlet",
				"org.yokekhei.fsd.p2.ui.servlet.AdminPasswordServlet",
				"org.yokekhei.fsd.p2.ui.servlet.AirlineServlet",
				"org.yokekhei.fsd.p2.ui.servlet.FlightServlet",
				"org.yokekhei.fsd.p2.ui.servlet.PlaceServlet"
				
		})
public class AuthenticationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);
		
		if (session == null || session.getAttribute("adminUser") == null) {
			request.setAttribute("sessionStatus", "invalid");
			RequestDispatcher rd = request.getRequestDispatcher(View.ADMIN_SIGNIN);
			rd.include(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
