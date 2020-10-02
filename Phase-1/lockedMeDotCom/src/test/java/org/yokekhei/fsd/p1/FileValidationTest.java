package org.yokekhei.fsd.p1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FileValidationTest {
	
	@Test
	public void testNullFileName() {
		FileValidation v = new FileValidation();
		
		try {
			v.isValidAdd(null);
		} catch (FileHandlerException e) {
			assertEquals("Invalid null file name.", e.getMessage());
		}
	}
	
	@Test
	public void testEmptyFileName() {
		FileValidation v = new FileValidation();
		
		try {
			v.isValidAdd("");
		} catch (FileHandlerException e) {
			assertEquals("File name cannot be empty.", e.getMessage());
		}
	}
	
	@Test
	public void testRegexFileName() {
		FileValidation v = new FileValidation();
		
		try {
			v.isValidAdd("a*&:pug.JpG");
		} catch (FileHandlerException e) {
			assertEquals("A file name can't contain any of the following characters:"
					+ System.lineSeparator() + "\\ / : * ? \" < > |", e.getMessage());
		}
	}
	
}
