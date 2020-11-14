package org.yokekhei.fsd.p2.comparator.place;

import java.util.Comparator;

import org.yokekhei.fsd.p2.bean.Place;

public class CityNameComparator implements Comparator<Place> {

	@Override
	public int compare(Place o1, Place o2) {
		return o1.getCityName().compareTo(o2.getCityName());
	}

}
