package org.yokekhei.fsd.p2.comparator.booking;

import java.util.Comparator;

import org.yokekhei.fsd.p2.bean.Booking;

public class BookingIdComparator implements Comparator<Booking> {

	@Override
	public int compare(Booking o1, Booking o2) {
		if (o1.getBookingId() < o2.getBookingId()) {
			return -1;
		} else if (o1.getBookingId() > o2.getBookingId()) {
			return 1;
		}
		
		return 0;
	}

}
