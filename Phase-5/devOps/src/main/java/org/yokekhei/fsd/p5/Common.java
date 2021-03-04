package org.yokekhei.fsd.p5;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Common {

	public static final String ROLE_ADMIN = "A";
	public static final String ROLE_USER = "U";

	public static final String DATE_FORMAT = "dd-MM-yyyy";

	public static String toLocalDateString(LocalDateTime localDateTime) {
		return Common.toLocalDateString(localDateTime, DATE_FORMAT);
	}

	public static String toLocalDateString(LocalDateTime localDateTime, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return localDateTime.format(formatter);
	}

}
