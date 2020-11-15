package org.yokekhei.fsd.p2.comparator.booking;

import java.util.Comparator;

import org.yokekhei.fsd.p2.bean.Booking;

public class GuestNameComparator implements Comparator<Booking> {

	@Override
	public int compare(Booking o1, Booking o2) {
		String guestNameO1 = o1.getGuest().getFirstName() + o1.getGuest().getLastName();
		String guestNameO2 = o2.getGuest().getFirstName() + o2.getGuest().getLastName();
		
		return guestNameO1.compareTo(guestNameO2);
	}

}
