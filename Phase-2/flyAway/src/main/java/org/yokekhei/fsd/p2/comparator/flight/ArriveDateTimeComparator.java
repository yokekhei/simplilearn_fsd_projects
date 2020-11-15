package org.yokekhei.fsd.p2.comparator.flight;

import java.util.Comparator;

import org.yokekhei.fsd.p2.bean.Flight;

public class ArriveDateTimeComparator implements Comparator<Flight> {

	@Override
	public int compare(Flight o1, Flight o2) {
		return o1.getArriveDateTime().compareTo(o2.getArriveDateTime());
	}

}
