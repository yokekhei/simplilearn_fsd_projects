package org.yokekhei.fsd.p1;

import java.util.Scanner;

public class BusinessLevelOpsMenu extends Menu {

	private boolean back;
	private FileHandler fileHandler;
	private FileValidation fileValidation;
	
	private enum SubMenuOption {
		BACK,
		ADD_FILE,
		DELETE_FILE,
		SEARCH_FILE
	};
	
	public BusinessLevelOpsMenu(Scanner scanner, FileHandler fileHandler) {
		super(scanner);
		
		this.fileHandler = fileHandler;
		fileValidation = new FileValidation();
	}

	@Override
	public void run() {
		while (!back) {
			print();
			
			int selection = getInput(SubMenuOption.BACK.ordinal(), SubMenuOption.SEARCH_FILE.ordinal());
			performAction(SubMenuOption.values()[selection]);
		}
		
		back = false;
	}

	@Override
	protected void print() {
		System.out.println(System.lineSeparator() + "Please make a selection: ");
		System.out.println("1) Add file");
		System.out.println("2) Delete file");
		System.out.println("3) Search file");
		System.out.println("0) Back");
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
	
	private void addFile() {
		System.out.print(System.lineSeparator() + "Enter file name: ");
		String fileName = getScanner().nextLine();
		
		try {
			if (fileValidation.isValidAdd(fileName)) {
				System.out.print(System.lineSeparator() + "Enter file content: ");
				String content = getScanner().nextLine();
				fileHandler.add(fileName, content);
				System.out.println("'" + fileName + "' has been added successfully.");
			}
		} catch (FileHandlerException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void deleteFile() {
		System.out.print(System.lineSeparator() + "Enter file name: ");
		String fileName = getScanner().nextLine();
		
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
		String fileName = getScanner().nextLine();
		
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
