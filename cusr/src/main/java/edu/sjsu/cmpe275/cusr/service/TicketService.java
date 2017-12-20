package edu.sjsu.cmpe275.cusr.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.sjsu.cmpe275.cusr.model.JourneyDetails;
import edu.sjsu.cmpe275.cusr.model.Passenger;
import edu.sjsu.cmpe275.cusr.model.Ticket;
import edu.sjsu.cmpe275.cusr.model.Transaction;
import edu.sjsu.cmpe275.cusr.repository.JourneyRepository;
import edu.sjsu.cmpe275.cusr.repository.PassengerRepository;
import edu.sjsu.cmpe275.cusr.repository.TicketRepository;
import edu.sjsu.cmpe275.cusr.repository.TransactionRepository;

@Component
public class TicketService {
	
	@Autowired 
	TicketRepository ticketRepository;
	@Autowired
	JourneyRepository journeyRepository;
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	PassengerRepository passengerRepository;
	
	/**
	 * To save the ticket
	 * @param ticket
	 */
	public Ticket saveTicket(Ticket ticket)
	{
		return ticketRepository.save(ticket);
	}
	
	public Ticket cancelTicket(Ticket ticket)
	{
		ArrayList<JourneyDetails> cancelledJourney = journeyRepository.findManyByTicket(ticket.getTicketId());
		for(JourneyDetails jd: cancelledJourney){
			journeyRepository.delete(jd);
		}

		Transaction cancelledTicketTransaction = transactionRepository.findOneByTicket(ticket.getTicketId());
		transactionRepository.delete(cancelledTicketTransaction);
		
		ArrayList<Passenger> cancelledTicketPassengers = passengerRepository.findManyByTicket(ticket.getTicketId());
		for(Passenger passenger : cancelledTicketPassengers)
		{
			passengerRepository.delete(passenger);
		}
		
		ticket.setCancelled(true);
		ticketRepository.save(ticket);
		
		return ticket;
	}
	
	public Ticket getTicketById(long id)
	{
		Ticket ticket = ticketRepository.findOne(id);
		return ticket;
	}
	
	public List<Ticket> getTicketByUserId(long id){
		return ticketRepository.getTicketsByUserId(id);
	}
}
