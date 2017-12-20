package edu.sjsu.cmpe275.cusr.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transcationId;
	
	private Double price;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TICKET_ID")
	@JsonBackReference
	private Ticket ticket;
	
	public Transaction()
	{
		
	}
	
	public Long getTranscationId() {
		return transcationId;
	}
	public void setTranscationId(Long transcationId) {
		this.transcationId = transcationId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
}
