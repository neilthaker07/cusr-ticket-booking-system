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
	private String source;
	private String destination;
	private Date departureTime;
	
	public JourneyDetails() {
		
	}
	
	public JourneyDetails(Long journeyId, Long trainId, Long ticketId, String source, String destination, Date departureTime)
	{
		this.journeyId = journeyId;
		this.trainId = trainId;
		this.ticketId = ticketId;
		this.source = source;
		this.destination = destination;
		this.departureTime = departureTime;
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Date getDepTime() {
		return departureTime;
	}
	public void setDepTime(Date departureTime) {
		this.departureTime = departureTime;
	}
	
	
}
