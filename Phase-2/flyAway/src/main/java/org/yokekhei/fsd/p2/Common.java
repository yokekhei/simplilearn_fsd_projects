package org.yokekhei.fsd.p2;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yokekhei.fsd.p2.ui.servlet.View;

public class Common {

	public static final String DATE_FORMAT = "dd-MM-yyyy";
	public static final String DATE_FORMAT2 = "dd MMM yyyy";
	public static final String TIME_FORMAT = "HH:mm";
	public static final String DATETIME_FORMAT = "dd-MM-yyyy HH:mm";
	public static final String DECIMAL_FORMAT_DF2 = "########0.00";
	
	public static final String PASSENGER_ADULT = "A";
	public static final String PASSENGER_CHILD = "C";
	public static final String PASSENGER_INFANT = "I";
	
	public static void viewError(String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("error", message);
		RequestDispatcher rd = request.getRequestDispatcher(View.ERROR);
		rd.forward(request, response);
	}
	
	public static LocalDate toLocalDate(String strDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		return LocalDate.parse(strDate, formatter);
	}
	
	public static LocalDate toLocalDate(LocalDateTime localDateTime) {
		return localDateTime.toLocalDate();
	}
	
	public static LocalTime toLocalTime(String strTime) {
		return LocalTime.parse(strTime);
	}
	
	public static LocalTime toLocalTime(LocalDateTime localDateTime) {
		return localDateTime.toLocalTime();
	}
	
	public static LocalDateTime toLocalDateTime(String strDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
		return LocalDateTime.parse(strDateTime, formatter);
	}
	
	public static String toLocalDateTime(LocalDate date, LocalTime time) {
		LocalDateTime dt = LocalDateTime.of(date, time);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
		return dt.format(formatter);
	}
	
	public static String getDurationInHourMinute(String fromLocalDateTime, String toLocalDateTime) {
		Duration duration = Duration.between(toLocalDateTime(fromLocalDateTime),
				toLocalDateTime(toLocalDateTime));
		Long hours = duration.getSeconds()/3600;
		Long minutes = (duration.getSeconds()%3600)/60;
		
		return hours.toString() + "h " + minutes.toString() + "m"; 
	}

	public static BigDecimal roundBigDecimal(Object value, int scale) {
		return new BigDecimal(String.valueOf(value)).setScale(scale, BigDecimal.ROUND_HALF_UP);
	}
	
}
