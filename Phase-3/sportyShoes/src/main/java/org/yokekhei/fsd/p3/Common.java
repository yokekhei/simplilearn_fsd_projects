package org.yokekhei.fsd.p3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Common {

	public static final String GENDER_MALE = "M";
	public static final String GENDER_FEMALE = "F";
	public static final String ROLE_ADMIN = "A";
	public static final String ROLE_USER = "U";
	
	public static final String DATE_FORMAT = "dd-MM-yyyy";
	public static final String DATETIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
	
	public static LocalDate toLocalDate(String strDate) {
		return Common.toLocalDate(strDate, DATE_FORMAT);
	}
	
	public static LocalDate toLocalDate(String strDate, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return LocalDate.parse(strDate, formatter);
	}
	
	public static String toLocalDateString(LocalDate localDate) {
		return Common.toLocalDateString(localDate, DATE_FORMAT);
	}
	
	public static String toLocalDateString(LocalDateTime localDateTime) {
		return Common.toLocalDateString(localDateTime, DATE_FORMAT);
	}
	
	public static String toLocalDateString(LocalDate localDate, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return localDate.format(formatter);
	}
	
	public static String toLocalDateString(LocalDateTime localDateTime, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return localDateTime.format(formatter);
	}
	
	public static LocalDateTime toLocalDateTime(String strDateTime) {
		return Common.toLocalDateTime(strDateTime, DATETIME_FORMAT);
	}
	
	public static LocalDateTime toLocalDateTime(String strDateTime, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return LocalDateTime.parse(strDateTime, formatter);
	}
	
}
