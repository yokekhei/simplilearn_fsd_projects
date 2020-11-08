package org.yokekhei.fsd.p2;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yokekhei.fsd.p2.ui.servlet.View;

public class Common {

	public static final String DATE_FORMAT = "dd-MM-yyyy";
	public static final String TIME_FORMAT = "HH:mm";
	
	public static void viewError(String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("error", message);
		RequestDispatcher rd = request.getRequestDispatcher(View.ERROR);
		rd.forward(request, response);
	}
	
	public static LocalDate toLocalDate(String strDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		return LocalDate.parse(strDate, formatter);
	}
	
	public static LocalTime toLocalTime(String strTime) {
		return LocalTime.parse(strTime);
	}
	
}
