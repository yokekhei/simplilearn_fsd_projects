package org.yokekhei.fsd.p1;

public class LockedMeDotCom {

	public static void main(String[] args) {
		System.out.println(new WelcomeScreen("LockedMe.com",
				"Yoke Khei Lam",
				"Lockers Pvt. Ltd."));
		Menu menu = new MainMenu();
		menu.run();
	}

}
