package org.yokekhei.fsd.p1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CommonUtilsTest {

	@Test
	public void testGetFilePath() {
		assertEquals("root/test.txt", CommonUtils.getFilePath("test.txt"));
	}
	
}
