package org.yokekhei.fsd.p1;

import java.util.Scanner;

public class Menu {
	
	private static final int INVALID_CHOICE = -1;
	
	private boolean exit;
	private boolean back;
	private WelcomeScreen welcomeScreen;
	private Scanner scanner;
	
	private enum MenuOption {
		EXIT,
		LIST_FILES,
		BUSINESS_OPS
	};
	
	private enum SubMenuOption {
		BACK,
		ADD_FILE,
		DELETE_FILE,
		SEARCH_FILE
	};
	
	public Menu() {
		welcomeScreen = new WelcomeScreen();
		scanner = new Scanner(System.in);
	}

	public Menu(String applicationName, String developerName, String companyName) {
		welcomeScreen = new WelcomeScreen(applicationName, developerName, companyName);
		scanner = new Scanner(System.in);
	}
	
	public void run() {
		welcomeScreen.print();
		
		while (!exit) {
			print();
			
			int choice = getInput(MenuOption.EXIT.ordinal(), MenuOption.BUSINESS_OPS.ordinal());
			performAction(MenuOption.values()[choice]);
		}
	}
	
	private void print() {
		System.out.println(System.lineSeparator() + "Please make a selection: ");
		System.out.println("1) Retrieve file names");
		System.out.println("2) Business-level operations");
		System.out.println("0) Exit");
	}
	
	private int getInput(int min, int max) {
		int choice = INVALID_CHOICE;
		
		while (choice < min || choice > max) {
			System.out.print(System.lineSeparator() + "Enter your choice: ");
			
			try {
				choice = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.err.println("Invalid choice. Please try again.");
			}
		}
		
		return choice;
	}
	
	private void performAction(MenuOption choice) {
		switch (choice) {
			case EXIT:
				exit = true;
				System.out.println("Thank you for using our application.");
				break;
			
			case LIST_FILES:
				break;
			
			case BUSINESS_OPS:
				while (!back) {
					printSubMenu();
					
					int selection = getInput(SubMenuOption.BACK.ordinal(), SubMenuOption.SEARCH_FILE.ordinal());
					performAction(SubMenuOption.values()[selection]);
				}
				
				back = false;
				break;
				
			default:
				System.err.println("An unknown error has occured.");
		}
	}
	
	private void performAction(SubMenuOption choice) {
		switch (choice) {
			case BACK:
				back = true;
				break;
		
			case ADD_FILE:
				break;
		
			case DELETE_FILE:
				break;
		
			case SEARCH_FILE:
				break;
			
			default:
				System.err.println("An unknown error has occured.");
		}
	}
	
	private void printSubMenu() {
		System.out.println(System.lineSeparator() + "Please make a selection: ");
		System.out.println("1) Add file");
		System.out.println("2) Delete file");
		System.out.println("3) Search file");
		System.out.println("0) Back");
	}
	
}
