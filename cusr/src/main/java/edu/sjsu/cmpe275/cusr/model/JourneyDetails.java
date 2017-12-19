package edu.sjsu.cmpe275.cusr.model;

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
	private Long journeyTrainId;
	private Long ticketId;
	private int source;
	private int destination;
	private String departureTime;
	private int passengers;
	private String journeyDate;
	
	public JourneyDetails() {
		
	}
	
	public JourneyDetails(Long journeyTrainId, Long ticketId, int source, int destination, int passengers, String departureTime, String journeyDate)
	{
		super();
		this.journeyTrainId = journeyTrainId;
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
	public Long getJourneyTrainId() {
		return journeyTrainId;
	}
	public void setJourneyTrainId(Long journeyTrainId) {
		this.journeyTrainId = journeyTrainId;
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
	public String getDepTime() {
		return departureTime;
	}
	public void setDepTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	public String getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}
	
}
