package org.yokekhei.fsd.p1;

import java.io.File;

public class FileHandler {

	private static final String ROOT_DIRECTORY = "root/";
	
	public FileHandler() {
		File directory = new File(ROOT_DIRECTORY);
		
		if (!directory.exists()) {
			directory.mkdir();
		} else {
			System.out.println("directory already exists");
		}
	}
	
	public void list() {
		System.out.println("list files");
	}
	
	public void add(String fileName) throws FileHandlerException {
		File file = new File(getFilePath(fileName));
		
		if (file.exists() && !file.isDirectory()) {
			throw new FileHandlerException(fileName + " exists in root directory. Please try again.");
		}
	}
	
	public void delete(String fileName) {
		System.out.println("delete " + fileName);
	}
	
	public void search(String fileName) {
		System.out.println("search " + fileName);
	}
	
	private String getFilePath(String fileName) {
		return ROOT_DIRECTORY + fileName.trim();
	}
	
}
