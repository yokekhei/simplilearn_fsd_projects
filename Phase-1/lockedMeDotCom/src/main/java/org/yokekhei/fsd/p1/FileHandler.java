package org.yokekhei.fsd.p1;

public class FileHandler {
	
	public FileHandler() {
	}
	
	public void list() {
		System.out.println("list files");
	}
	
	public void add(String fileName) {
		System.out.println("add " + fileName);
	}
	
	public void delete(String fileName) {
		System.out.println("delete " + fileName);
	}
	
	public void search(String fileName) {
		System.out.println("search " + fileName);
	}
	
}
