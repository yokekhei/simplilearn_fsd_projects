package org.yokekhei.fsd.p2.comparator.flight;

import java.util.Comparator;

import org.yokekhei.fsd.p2.bean.Flight;

public class InfantPriceComparator implements Comparator<Flight> {

	@Override
	public int compare(Flight o1, Flight o2) {
		if (o1.getInfantPrice() < o2.getInfantPrice()) {
			return -1;
		} else if (o1.getInfantPrice() > o2.getInfantPrice()) {
			return 1;
		}
		
		return 0;
	}

}
