package org.yokekhei.fsd.p1;

import java.io.File;

public class FileValidation {
	
	public FileValidation() {
	}
	
	public boolean isValidAdd(String fileName) throws FileHandlerException {
		File file = new File(CommonUtils.getFilePath(fileName));
		
		if (file.exists() && !file.isDirectory()) {
			throw new FileHandlerException(fileName + " exists in root directory. Please try again.");
		}
		
		return true;
	}
	
	
}
