package org.yokekhei.fsd.p2.comparator.booking;

import java.util.Comparator;

import org.yokekhei.fsd.p2.bean.Booking;

public class DepartDateTimeComparator implements Comparator<Booking> {

	@Override
	public int compare(Booking o1, Booking o2) {
		return o1.getFlight().getDepartDateTime().compareTo(
				o2.getFlight().getDepartDateTime());
	}

}
