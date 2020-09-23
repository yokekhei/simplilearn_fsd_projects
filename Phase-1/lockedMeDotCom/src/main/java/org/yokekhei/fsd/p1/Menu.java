package org.yokekhei.fsd.p1;

public class Menu {
	
	private WelcomeScreen welcomeScreen;
	
	public Menu() {
		welcomeScreen = new WelcomeScreen();
	}

	public Menu(String applicationName, String developerName, String companyName) {
		welcomeScreen = new WelcomeScreen(applicationName, developerName, companyName);
	}
	
	public void run() {
		welcomeScreen.print();
	}
	
}
