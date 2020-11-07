package org.yokekhei.fsd.p2.dao;

import java.util.List;

import org.yokekhei.fsd.p2.bean.Place;

public interface PlaceDao {

	void addPlace(Place data) throws FlyAwayDaoException;
	List<Place> getAllPlaces() throws FlyAwayDaoException;
	Place getPlace(String locationCode) throws FlyAwayDaoException;
	void updatePlace(Place data) throws FlyAwayDaoException;
	void deletePlace(String locationCode) throws FlyAwayDaoException;
	
}
