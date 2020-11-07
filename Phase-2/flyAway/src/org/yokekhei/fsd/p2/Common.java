package org.yokekhei.fsd.p2.ui.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Common {

	public static void viewError(String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("error", message);
		RequestDispatcher rd = request.getRequestDispatcher(View.ERROR);
		rd.forward(request, response);
	}
}
