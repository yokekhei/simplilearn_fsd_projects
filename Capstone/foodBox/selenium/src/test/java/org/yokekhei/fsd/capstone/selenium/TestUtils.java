package org.yokekhei.fsd.capstone.selenium;

import org.apache.commons.lang3.SystemUtils;

public class TestUtils {

	public static final String TEST_DATA_FILE_PATH = "test-data.xlsx";

	public static boolean isWindows() {
		return SystemUtils.IS_OS_WINDOWS;
	}

	public static boolean isLinux() {
		return SystemUtils.IS_OS_LINUX;
	}

}
