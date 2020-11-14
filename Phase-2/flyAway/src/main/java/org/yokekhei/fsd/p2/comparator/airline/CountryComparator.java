package org.yokekhei.fsd.p2.comparator.airline;

import java.util.Comparator;

import org.yokekhei.fsd.p2.bean.Airline;

public class CountryComparator implements Comparator<Airline> {

	@Override
	public int compare(Airline o1, Airline o2) {
		return o1.getCountry().compareTo(o2.getCountry());
	}

}
