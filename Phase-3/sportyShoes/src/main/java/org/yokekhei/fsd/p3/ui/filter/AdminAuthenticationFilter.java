package org.yokekhei.fsd.p3.ui.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.yokekhei.fsd.p3.ui.View;

public class AdminAuthenticationFilter implements Filter {
	
    public AdminAuthenticationFilter() {
    }
    
	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);
		
		if (session == null || session.getAttribute("loginUser") == null) {
			request.setAttribute("authMsg", "Session expired");
			RequestDispatcher rd = request.getRequestDispatcher("/" + View.V_ADMIN_HOME);
			rd.include(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}
	
	public void destroy() {
	}

}
