package org.yokekhei.fsd.p2.comparator.booking;

import java.util.Comparator;

import org.yokekhei.fsd.p2.bean.Booking;

public class TotalPriceComparator implements Comparator<Booking> {

	@Override
	public int compare(Booking o1, Booking o2) {
		if (o1.getTotalCharge() < o2.getTotalCharge()) {
			return -1;
		} else if (o1.getTotalCharge() > o2.getTotalCharge()) {
			return 1;
		}
		
		return 0;
	}

}
