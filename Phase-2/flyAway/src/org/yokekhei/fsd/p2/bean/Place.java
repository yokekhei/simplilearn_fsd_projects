package org.yokekhei.fsd.p2.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="Place")
public class Place {

	@Id
	@Column(nullable=false, name="location_code", length=3)
	private String locationCode;
	
	@Column(nullable=false, name="location_name", length=100)
	private String locationName;
	
	@Column(nullable=false, name="city_name", length=100)
	private String cityName;
	
	public Place() {
	}

	public Place(String locationCode, String locationName, String cityName) {
		super();
		this.locationCode = locationCode;
		this.locationName = locationName;
		this.cityName = cityName;
	}
	
	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "Place [locationCode=" + locationCode + ", locationName=" + locationName + ", cityName=" + cityName
				+ "]";
	}
	
}
