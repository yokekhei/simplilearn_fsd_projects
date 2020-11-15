package org.yokekhei.fsd.p2.comparator.flight;

import java.util.Comparator;

import org.yokekhei.fsd.p2.bean.Flight;

public class ToCityComparator implements Comparator<Flight> {

	@Override
	public int compare(Flight o1, Flight o2) {
		return o1.getDestination().getCityName().compareTo(o2.getDestination().getCityName());
	}

}
