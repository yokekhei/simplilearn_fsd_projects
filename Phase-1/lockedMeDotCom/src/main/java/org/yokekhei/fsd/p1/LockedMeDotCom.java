package org.yokekhei.fsd.p1;

import java.util.Scanner;

public class LockedMeDotCom {

	public static void main(String[] args) {
		System.out.println(new WelcomeScreen("LockedMe.com",
				"Yoke Khei Lam",
				"Lockers Pvt. Ltd."));
		Scanner sc = new Scanner(System.in);
		Menu menu = new MainMenu(sc);
		menu.run();
		sc.close();
	}

}
