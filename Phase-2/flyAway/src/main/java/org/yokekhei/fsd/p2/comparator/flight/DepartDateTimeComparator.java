package org.yokekhei.fsd.p2.comparator.flight;

import java.util.Comparator;

import org.yokekhei.fsd.p2.bean.Flight;

public class DepartDateTimeComparator implements Comparator<Flight> {

	@Override
	public int compare(Flight o1, Flight o2) {
		return o1.getDepartDateTime().compareTo(o2.getDepartDateTime());
	}

}
