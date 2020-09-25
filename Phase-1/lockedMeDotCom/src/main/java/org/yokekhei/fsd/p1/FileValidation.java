package org.yokekhei.fsd.p1;

import java.io.File;

public class FileValidation {
	
	public FileValidation() {
	}
	
	public boolean isValidAdd(String fileName) throws FileHandlerException {
		if (fileName == null) {
			throw new FileHandlerException("Invalid null file name.");
		}
		
		if (fileName.isEmpty()) {
			throw new FileHandlerException("File name cannot be empty.");
		}
		
		if (fileName.replaceAll("[\\\\/:\\*\\?\\\"<>\\|]", "").length() != fileName.length()) {
			throw new FileHandlerException("A file name can't contain any of the following characters:"
					+ System.lineSeparator() + "\\ / : * ? \" < > |");
		}
		
		File file = new File(CommonUtils.getFilePath(fileName));
		
		if (file.exists()) {
			if (file.isDirectory()) {
				throw new FileHandlerException("There is already a folder with the same "
						+ "name as the file name you specified. Specify a different name.");
			} else {
				throw new FileHandlerException("'" + fileName + "' already exists in the directory.");
			}
		}
		
		return true;
	}
	
	public boolean isValidDelete(String fileName) throws FileHandlerException {
		if (fileName == null) {
			throw new FileHandlerException("Invalid null file name.");
		}
		
		if (fileName.isEmpty()) {
			throw new FileHandlerException("File name cannot be empty.");
		}
		
		return true;
	}
	
}
