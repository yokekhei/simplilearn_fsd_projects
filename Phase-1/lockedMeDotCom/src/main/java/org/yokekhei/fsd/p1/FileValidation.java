package org.yokekhei.fsd.p1;

import java.io.File;

public class FileValidation {
	
	public FileValidation() {
	}
	
	public boolean isValidAdd(String fileName) throws FileHandlerException {
		if (isValid(fileName)) {
			File file = new File(CommonUtils.getFilePath(fileName));
			
			// ignore file name case sensitivity check
			if (file.exists()) {
				if (file.isDirectory()) {
					throw new FileHandlerException("There is already a folder with the same "
							+ "name as the file name you specified. Specify a different name.");
				} else {
					throw new FileHandlerException("'" + fileName + "' already exists in the directory.");
				}
			}
		}
		
		return true;
	}
	
	public boolean isValidDelete(String fileName) throws FileHandlerException {
		if (isValid(fileName)) {
			File file = new File(CommonUtils.getFilePath(fileName));
			
			if (file.exists() && file.isDirectory()) {
				throw new FileHandlerException("'" + fileName + "' is a directory. "
						+ "Delete directory is not supported.");
			}
		}
		
		return true;
	}
	
	public boolean isValidSearch(String fileName) throws FileHandlerException {
		if (isValid(fileName)) {
			File file = new File(CommonUtils.getFilePath(fileName));
			
			if (file.exists() && file.isDirectory()) {
				throw new FileHandlerException("'" + fileName + "' is a directory. "
						+ "Directory search is not supported.");
			}
		}
		
		return true;
	}
	
	private boolean isValid(String fileName) throws FileHandlerException {
		try {
			if (fileName.isEmpty()) {
				throw new FileHandlerException("File name cannot be empty.");
			}
		} catch (NullPointerException e) {
			throw new FileHandlerException("Invalid null file name.");
		}
		
		if (fileName.replaceAll("[\\\\/:\\*\\?\\\"<>\\|]", "").length() != fileName.length()) {
			throw new FileHandlerException("A file name can't contain any of the following characters:"
					+ System.lineSeparator() + "\\ / : * ? \" < > |");
		}
		
		return true;
	}
	
}
