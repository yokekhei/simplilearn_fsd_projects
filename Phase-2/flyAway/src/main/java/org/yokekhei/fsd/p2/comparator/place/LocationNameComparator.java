package org.yokekhei.fsd.p2.comparator.place;

import java.util.Comparator;

import org.yokekhei.fsd.p2.bean.Place;

public class LocationNameComparator implements Comparator<Place> {

	@Override
	public int compare(Place o1, Place o2) {
		return o1.getLocationName().compareTo(o2.getLocationName());
	}

}
