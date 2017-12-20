package edu.sjsu.cmpe275.cusr.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "TICKET")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ticketId;
	private Long userId;
	private boolean isDeleted;
	private boolean isCancelled;
	
	@OneToMany(mappedBy = "ticket")
	@JsonManagedReference
	private List<JourneyDetails> journeyList;
	
	@OneToOne(mappedBy = "ticket")
	@JsonManagedReference
	private Transaction transaction;
	
	@OneToMany(mappedBy = "ticket")
	@JsonManagedReference
	private List<Passenger> passengerList;
	
	public Ticket()
	{
		
	}
	
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public List<JourneyDetails> getJourneyList() {
		return journeyList;
	}

	public void setJourneyList(List<JourneyDetails> journeyList) {
		this.journeyList = journeyList;
	}

	public List<Passenger> getPassengerList() {
		return passengerList;
	}

	public void setPassengerList(List<Passenger> passengerList) {
		this.passengerList = passengerList;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
}
