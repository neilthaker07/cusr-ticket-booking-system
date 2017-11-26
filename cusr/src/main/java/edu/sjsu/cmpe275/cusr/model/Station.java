package edu.sjsu.cmpe275.cusr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATION")
public class Station {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long stationId;
	private String stationName;
	private Boolean isExpress;
	private int count;
	
	public Station()
	{
		
	}
	
	public Station(Long stationId, String stationName, Boolean isExpress, int count) {
		super();
		this.stationId = stationId;
		this.stationName = stationName;
		this.isExpress = isExpress;
		this.count = count;
	}
	public Long getStationId() {
		return stationId;
	}
	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public Boolean getIsExpress() {
		return isExpress;
	}
	public void setIsExpress(Boolean isExpress) {
		this.isExpress = isExpress;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
