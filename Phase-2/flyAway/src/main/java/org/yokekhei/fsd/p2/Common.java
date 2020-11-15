package org.yokekhei.fsd.p2;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

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
	public static final String DATETIME_FORMAT2 = "MMM dd yyyy HH:mm";
	public static final String YEAR_FORMAT = "yy";
	
	public static final String DECIMAL_FORMAT_DF2 = "########0.00";
	
	public static final String PASSENGER_ADULT = "A";
	public static final String PASSENGER_CHILD = "C";
	public static final String PASSENGER_INFANT = "I";
	
	public static void viewError(String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("error", message);
		RequestDispatcher rd = request.getRequestDispatcher(View.ERROR);
		rd.forward(request, response);
	}
	
	public static void viewSessionExpired(HttpServletRequest request, HttpServletResponse response, String nextPage) throws ServletException, IOException {
		request.setAttribute("sessionStatus", "expired");
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.include(request, response);
	}
	
	public static LocalDate toLocalDate(String strDate) {
		return Common.toLocalDate(strDate, DATE_FORMAT);
	}
	
	public static LocalDate toLocalDate(String strDate, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return LocalDate.parse(strDate, formatter);
	}
	
	public static LocalDate toLocalDate(LocalDateTime localDateTime) {
		return localDateTime.toLocalDate();
	}
	
	public static String toLocalDateString(LocalDate localDate, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return localDate.format(formatter);
	}
	
	public static LocalTime toLocalTime(String strTime) {
		return LocalTime.parse(strTime);
	}
	
	public static LocalTime toLocalTime(LocalDateTime localDateTime) {
		return localDateTime.toLocalTime();
	}
	
	public static String toLocalTimeString(LocalTime localTime, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return localTime.format(formatter);
	}
	
	public static LocalDateTime toLocalDateTime(String strDateTime) {
		return Common.toLocalDateTime(strDateTime, DATETIME_FORMAT);
	}
	
	public static LocalDateTime toLocalDateTime(String strDateTime, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return LocalDateTime.parse(strDateTime, formatter);
	}
	
	public static String toLocalDateTime(LocalDate date, LocalTime time) {
		LocalDateTime dt = LocalDateTime.of(date, time);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
		return dt.format(formatter);
	}
	
	public static String toLocalDateTime(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
		return localDateTime.format(formatter);
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
	
	public static String getCurrentYear(String format) {
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return now.format(formatter);
	}
	
	public static int getRandomSeatNo() {
		Random r = new Random();
		int low = 100;
		int high = 287;
		return r.nextInt(high-low) + low;
	}
	
}
