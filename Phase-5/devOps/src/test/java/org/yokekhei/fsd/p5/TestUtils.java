package org.yokekhei.fsd.p5;

import org.apache.commons.lang3.SystemUtils;

public class TestUtils {

	public static boolean isWindows() {
		return SystemUtils.IS_OS_WINDOWS;
	}

	public static boolean isLinux() {
		return SystemUtils.IS_OS_LINUX;
	}

}
