package org.yokekhei.fsd.p2.comparator.flight;

import java.util.Comparator;

import org.yokekhei.fsd.p2.bean.Flight;

public class FlightNumberComparator implements Comparator<Flight> {

	@Override
	public int compare(Flight o1, Flight o2) {
		String flightNumberO1 = o1.getAirline().getFlightCode() + o1.getFlightNo();
		String flightNumberO2 = o2.getAirline().getFlightCode() + o2.getFlightNo();
		
		return flightNumberO1.compareTo(flightNumberO2);
	}

}
