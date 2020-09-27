package org.yokekhei.fsd.p1;

import java.util.Scanner;
import java.util.Set;

public class Menu {
	
	private static final int INVALID_CHOICE = -1;
	
	private boolean exit;
	private boolean back;
	private FileHandler fileHandler;
	private FileValidation fileValidation;
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
		fileHandler = new FileHandler();
		fileValidation = new FileValidation();
		scanner = new Scanner(System.in);
	}
	
	public void run() {
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
				listFiles();
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
				addFile();
				break;
		
			case DELETE_FILE:
				deleteFile();
				break;
		
			case SEARCH_FILE:
				searchFile();
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
	
	private void addFile() {
		System.out.print(System.lineSeparator() + "Enter file name: ");
		String fileName = scanner.nextLine();
		
		try {
			if (fileValidation.isValidAdd(fileName)) {
				System.out.print(System.lineSeparator() + "Enter file content: ");
				String content = scanner.nextLine();
				fileHandler.add(fileName, content);
				System.out.println("'" + fileName + "' has been added successfully.");
			}
		} catch (FileHandlerException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void deleteFile() {
		System.out.print(System.lineSeparator() + "Enter file name: ");
		String fileName = scanner.nextLine();
		
		try {
			if (fileValidation.isValidDelete(fileName)) {
				fileHandler.delete(fileName);
				System.out.println("'" + fileName + "' has been deleted successfully.");
			}
		} catch (FileHandlerException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void searchFile() {
		System.out.print(System.lineSeparator() + "Enter file name: ");
		String fileName = scanner.nextLine();
		
		try {
			if (fileValidation.isValidSearch(fileName)) {
				fileHandler.search(fileName);
				System.out.println("'" + fileName + "' is found in the directory.");
			}
		} catch (FileHandlerException e) {
			System.err.println(e.getMessage());
		}
	}
	
}
