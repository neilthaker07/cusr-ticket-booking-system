package edu.sjsu.cmpe275.cusr.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "JOURNEY_DETAILS")
public class JourneyDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long journeyId;
	private String trainNo;
	private int source;
	private int destination;
	private String departureTime;
	private int passengers;
	private String journeyDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TICKET_ID")
	@JsonBackReference
	private Ticket ticket;
	
	public JourneyDetails() {
		
	}
	
	public Long getJourneyId() {
		return journeyId;
	}
	public void setJourneyId(Long journeyId) {
		this.journeyId = journeyId;
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

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}
	
}
