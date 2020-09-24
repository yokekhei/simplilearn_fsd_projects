package org.yokekhei.fsd.p1;

public class CommonUtils {
	
	public static final String ROOT_DIRECTORY = "root/";
	
	public static String getFilePath(String fileName) {
		return ROOT_DIRECTORY + fileName.trim();
	}
}
