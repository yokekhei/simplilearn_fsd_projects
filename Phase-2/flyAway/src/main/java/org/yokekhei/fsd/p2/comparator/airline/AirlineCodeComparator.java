package org.yokekhei.fsd.p2.comparator.airline;

import java.util.Comparator;

import org.yokekhei.fsd.p2.bean.Airline;

public class AirlineCodeComparator implements Comparator<Airline> {

	@Override
	public int compare(Airline o1, Airline o2) {
		if (o1.getAirlineCode() < o2.getAirlineCode()) {
			return -1;
		} else if (o1.getAirlineCode() > o2.getAirlineCode()) {
			return 1;
		}
		
		return 0;
	}

}
