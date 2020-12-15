package org.yokekhei.fsd.p3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Common {

	public static final String GENDER_MALE = "M";
	public static final String GENDER_FEMALE = "F";
	public static final String ROLE_ADMIN = "A";
	public static final String ROLE_USER = "U";
	
	public static final String DATE_FORMAT = "dd-MM-yyyy";
	
	public static LocalDate toLocalDate(String strDate) {
		return Common.toLocalDate(strDate, DATE_FORMAT);
	}
	
	public static LocalDate toLocalDate(String strDate, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return LocalDate.parse(strDate, formatter);
	}
	
}
