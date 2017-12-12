package edu.sjsu.cmpe275.cusr.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JOURNEY_DETAILS")
public class JourneyDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long journeyId;
	private Long trainId;
	private Long ticketId;
	private int source;
	private int destination;
	private Date departureTime;
	private int passengers;
	private Date journeyDate;
	
	public JourneyDetails() {
		
	}
	
	public JourneyDetails(Long journeyId, Long trainId, Long ticketId, int source, int destination, int passengers, Date departureTime, Date journeyDate)
	{
		super();
		this.journeyId = journeyId;
		this.trainId = trainId;
		this.ticketId = ticketId;
		this.source = source;
		this.destination = destination;
		this.departureTime = departureTime;
		this.passengers = passengers;
		this.journeyDate = journeyDate;
	}
	
	public Long getJourneyId() {
		return journeyId;
	}
	public void setJourneyId(Long journeyId) {
		this.journeyId = journeyId;
	}
	public Long getTrainId() {
		return trainId;
	}
	public void setTrainId(Long trainId) {
		this.trainId = trainId;
	}
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public int getDestination() {
		return destination;
	}
	public void setDestination(int destination) {
		this.destination = destination;
	}
	public Date getDepTime() {
		return departureTime;
	}
	public void setDepTime(Date departureTime) {
		this.departureTime = departureTime;
	}
	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	public Date getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(Date journeyDate) {
		this.journeyDate = journeyDate;
	}
	
}
