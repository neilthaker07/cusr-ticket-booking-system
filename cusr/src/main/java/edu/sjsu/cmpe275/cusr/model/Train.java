package edu.sjsu.cmpe275.cusr.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRAIN")
public class Train {

	public Train(Long trainId, String trainNo, Date departureTime, String trainType, Boolean isNorthBound, int capacity) {
		super();
		this.trainId = trainId;
		this.trainNo = trainNo;
		this.departureTime = departureTime;
		this.trainType = trainType;
		this.isNorthBound = isNorthBound;
		this.capacity = capacity;
	}
	public Train()
	{
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long trainId;
	private String trainNo;
	private Date departureTime;
	private String trainType;
	private Boolean isNorthBound;
	private int capacity;
	public Long getTrainId() {
		return trainId;
	}
	public void setTrainId(Long trainId) {
		this.trainId = trainId;
	}
	public String getTrainNo() {
		return trainNo;
	}
	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}
	public Date getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}
	public String getTrainType() {
		return trainType;
	}
	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}
	public Boolean getIsNorthBound() {
		return isNorthBound;
	}
	public void setIsNorthBound(Boolean isNorthBound) {
		this.isNorthBound = isNorthBound;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
}

