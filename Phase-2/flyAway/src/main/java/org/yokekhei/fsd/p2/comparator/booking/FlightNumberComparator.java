package org.yokekhei.fsd.p2.comparator.booking;

import java.util.Comparator;

import org.yokekhei.fsd.p2.bean.Booking;

public class FlightNumberComparator implements Comparator<Booking> {

	@Override
	public int compare(Booking o1, Booking o2) {
		String flightNumberO1 = o1.getFlight().getAirline().getFlightCode() + o1.getFlight().getFlightNo();
		String flightNumberO2 = o2.getFlight().getAirline().getFlightCode() + o2.getFlight().getFlightNo();
		
		return flightNumberO1.compareTo(flightNumberO2);
	}

}
