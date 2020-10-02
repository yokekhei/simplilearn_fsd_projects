package org.yokekhei.fsd.p1;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AlgorithmsTest {

	@Test
	public void testSort() {
		List<File> list = new ArrayList<>();
		list.add(new File("dancer.jpg"));
		list.add(new File("bells.txt"));
		list.add(new File("angel.jpg"));
		list.add(new File("evergreen.txt"));
		list.add(new File("zoom.txt"));
		list.add(new File("queen.bmp"));
		list.add(new File("jingle.jpg"));
		list.add(new File("candle.bmp"));
		
		FileNameComparator c = new FileNameComparator();
		Algorithms.sort(list, c);
		
		assertEquals("angel.jpg", list.get(0).getName());
		assertEquals("bells.txt", list.get(1).getName());
		assertEquals("candle.bmp", list.get(2).getName());
		assertEquals("dancer.jpg", list.get(3).getName());
		assertEquals("evergreen.txt", list.get(4).getName());
		assertEquals("jingle.jpg", list.get(5).getName());
		assertEquals("queen.bmp", list.get(6).getName());
		assertEquals("zoom.txt", list.get(7).getName());
	}
	
	@Test
	public void testBinarySearchSuccess() {
		List<File> list = new ArrayList<>();
		list.add(new File("angel.jpg"));
		list.add(new File("bells.txt"));
		list.add(new File("candle.bmp"));
		list.add(new File("dancer.jpg"));
		list.add(new File("evergreen.txt"));
		list.add(new File("jingle.jpg"));
		list.add(new File("queen.bmp"));
		list.add(new File("zoom.txt"));
		
		FileNameComparator c = new FileNameComparator();
		assertEquals(6, Algorithms.binarySearch(list, new File("queen.bmp"), c));
	}
	
	@Test
	public void testBinarySearchFail() {
		List<File> list = new ArrayList<>();
		list.add(new File("angel.jpg"));
		list.add(new File("bells.txt"));
		list.add(new File("candle.bmp"));
		list.add(new File("dancer.jpg"));
		list.add(new File("evergreen.txt"));
		list.add(new File("jingle.jpg"));
		list.add(new File("queen.bmp"));
		list.add(new File("zoom.txt"));
		
		FileNameComparator c = new FileNameComparator();
		assertEquals(-8, Algorithms.binarySearch(list, new File("unknown.txt"), c));
	}
	
}
