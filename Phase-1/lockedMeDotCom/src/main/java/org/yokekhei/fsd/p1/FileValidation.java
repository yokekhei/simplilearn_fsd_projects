package org.yokekhei.fsd.p1;

import java.io.File;

public class FileValidation {
	
	public FileValidation() {
	}
	
	public boolean isValidAdd(String fileName) throws FileHandlerException {
		if (fileName.isEmpty()) {
			throw new FileHandlerException("File name cannot be empty.");
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
	
	
}
