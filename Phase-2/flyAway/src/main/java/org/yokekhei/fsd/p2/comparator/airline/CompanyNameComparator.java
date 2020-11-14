package org.yokekhei.fsd.p2.comparator.airline;

import java.util.Comparator;

import org.yokekhei.fsd.p2.bean.Airline;

public class CompanyNameComparator implements Comparator<Airline> {

	@Override
	public int compare(Airline o1, Airline o2) {
		return o1.getCompanyName().compareTo(o2.getCompanyName());
	}

}
