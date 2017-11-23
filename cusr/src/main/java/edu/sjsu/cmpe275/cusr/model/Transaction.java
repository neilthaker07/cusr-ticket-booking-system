package edu.sjsu.cmpe275.cusr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transcationId;
	private Long ticketId;
	private int price;
	
	public Transaction()
	{
		
	}
	
	public Transaction(Long transcationId, Long ticketId, int price)
	{
		super();
		this.transcationId = transcationId;
		this.ticketId = ticketId;
		this.price = price;
	}
	
	public Long getTranscationId() {
		return transcationId;
	}
	public void setTranscationId(Long transcationId) {
		this.transcationId = transcationId;
	}
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
}
