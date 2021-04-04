package org.yokekhei.fsd.capstone.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Common {

	public static final String ROLE_ADMIN = "A";
	public static final String ROLE_USER = "U";

	public static final String DB_ERROR_CODE = "DB0001";

	public static final String SB_VALIDATION_FAIL = "SB0001";

	public static final String SV_INVALID_CREDENTIAL = "SV0001";
	public static final String SV_PERMISSION_DISABLED = "SV0002";
	public static final String SV_INVALID_PRIVILEGES = "SV0003";
	public static final String SV_USER_ALREADY_EXISTS = "SV0004";

	public static final String DEFAULT_IMAGE = "static/images/default-image.png";

	public static final String CURRENCY_USD = "usd";
	public static final String CURRENCY_EUR = "eur";
	public static final String CURRENCY_MYR = "myr";

	public static final String DATETIME_FORMAT = "dd-MM-yyyy HH:mm:ss";

	public static LocalDateTime toLocalDateTime(String strDateTime) {
		return Common.toLocalDateTime(strDateTime, DATETIME_FORMAT);
	}

	public static LocalDateTime toLocalDateTime(String strDateTime, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return LocalDateTime.parse(strDateTime, formatter);
	}

}
