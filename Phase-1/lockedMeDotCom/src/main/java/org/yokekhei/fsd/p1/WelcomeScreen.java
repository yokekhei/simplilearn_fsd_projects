package org.yokekhei.fsd.p1;

import org.apache.commons.lang3.StringUtils;

public class WelcomeScreen {

	private static final String RELEASE_VERSION = "0.0.1";
	private static final String RELEASE_DATE = "04-October-2020";
	private static final String RELEASE_YEAR = "2020";
	private static final int SCREEN_WIDTH = 80;
	
	private String applicationName;
	private String developerName;
	private String companyName;
	
	public WelcomeScreen() {
		applicationName = "";
		developerName = "";
	}

	public WelcomeScreen(String applicationName, String developerName, String companyName) {
		this.applicationName = applicationName;
		this.developerName = developerName;
		this.companyName = companyName;
	}
	
	public void print() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(getTopBottomLine());
		sb.append(getEmptyLine());
		sb.append(getOutput("Welcome to"));
		
		if (applicationName.isEmpty()) {
			sb.append(getOutput("our Application"));
		} else {
			sb.append(getOutput(applicationName));
		}
		
		sb.append(getEmptyLine());
		sb.append(getOutput("Developer: " + developerName));
		sb.append(getOutput("Version: " + RELEASE_VERSION));
		sb.append(getOutput("Date: " + RELEASE_DATE));
		
		if (!companyName.isEmpty()) {
			sb.append(getEmptyLine());
			sb.append(getOutput("Copyright " + RELEASE_YEAR + " " + companyName + ". All Rights Reserved"));
		}
		
		sb.append(getEmptyLine());
		sb.append(getTopBottomLine());
		
		System.out.println(sb.toString());
	}
	
	private String getEmptyLine() {
		return StringUtils.center(StringUtils.center(" ", SCREEN_WIDTH - 2), SCREEN_WIDTH, "|")
				+ System.lineSeparator();
	}
	
	private String getTopBottomLine() {
		return StringUtils.rightPad("+", SCREEN_WIDTH - 1, "-") + "+"
				+ System.lineSeparator();
	}
	
	private String getOutput(String output) {
		return StringUtils.center(StringUtils.center(output, SCREEN_WIDTH - 2), SCREEN_WIDTH, "|")
				+ System.lineSeparator();
	}
	
}
