package org.yokekhei.fsd.p1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
	
	public FileHandler() {
		File directory = new File(CommonUtils.ROOT_DIRECTORY);
		
		if (!directory.exists()) {
			directory.mkdir();
		}
	}
	
	public void list() {
		System.out.println("list files");
	}
	
	public void add(String fileName, String content) throws FileHandlerException {
		try (FileWriter fw = new FileWriter(CommonUtils.getFilePath(fileName));
				BufferedWriter bw = new BufferedWriter(fw);
				) {
			bw.write(content);
			bw.newLine();
		} catch (IOException e) {
			if (e.getMessage().contains("Access is denied") &&
					(new File(CommonUtils.getFilePath(fileName))).isDirectory()) {
				throw new FileHandlerException("There is already a folder with the same "
						+ "name as the file name you specified. Specify a different name.");
			} else {
				throw new FileHandlerException(e.getMessage());
			}
		}
	}
	
	public void delete(String fileName) {
		System.out.println("delete " + fileName);
	}
	
	public void search(String fileName) {
		System.out.println("search " + fileName);
	}
	
}
