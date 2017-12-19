package edu.sjsu.cmpe275.cusr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.sjsu.cmpe275.cusr.model.JourneyDetails;
import edu.sjsu.cmpe275.cusr.model.Ticket;
import edu.sjsu.cmpe275.cusr.model.Transaction;
import edu.sjsu.cmpe275.cusr.repository.JourneyRepository;
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
		JourneyDetails cancelledJourney = journeyRepository.findOneByTicket(ticket.getTicketId());
		journeyRepository.delete(cancelledJourney);

		Transaction cancelledTicketTransaction = transactionRepository.findOneByTicket(ticket.getTicketId());
		transactionRepository.delete(cancelledTicketTransaction);
		
		ticket.setCancelled(true);
		ticketRepository.save(ticket);
		return ticket;
	}
}
