package org.yokekhei.fsd.p1;

import java.util.Scanner;
import java.util.Set;

public class MainMenu extends Menu {
	
	private boolean exit;
	private FileHandler fileHandler;
	private BusinessLevelOpsMenu businessLevelOpsMenu;
	
	private enum MenuOption {
		EXIT,
		LIST_FILES,
		BUSINESS_LEVEL_OPS
	};
	
	public MainMenu() {
		super(new Scanner(System.in));
		
		fileHandler = new FileHandler();
		businessLevelOpsMenu = new BusinessLevelOpsMenu(getScanner(), fileHandler);
	}
	
	@Override
	public void run() {
		while (!exit) {
			print();
			
			int choice = getInput(MenuOption.EXIT.ordinal(), MenuOption.BUSINESS_LEVEL_OPS.ordinal());
			performAction(MenuOption.values()[choice]);
		}
	}
	
	@Override
	protected void print() {
		System.out.println(System.lineSeparator() + "Please make a selection: ");
		System.out.println("1) Retrieve file names");
		System.out.println("2) Business-level operations");
		System.out.println("0) Exit");
	}
	
	private void performAction(MenuOption choice) {
		switch (choice) {
			case EXIT:
				exit = true;
				System.out.println("Thank you for using our application.");
				break;
			
			case LIST_FILES:
				listFiles();
				break;
			
			case BUSINESS_LEVEL_OPS:
				businessLevelOpsMenu.run();
				break;
				
			default:
				System.err.println("An unknown error has occured.");
		}
	}
	
	private void listFiles() {
		try {
			Set<String> fileNames = fileHandler.list();
			
			if (!fileNames.isEmpty()) {
				System.out.println(fileNames);
			} else {
				System.out.println("No file exists in the directory.");
			}
		} catch (FileHandlerException e) {
			System.err.println(e.getMessage());
		}
	}
	
}
