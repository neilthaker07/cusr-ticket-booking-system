package edu.sjsu.cmpe275.cusr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.sjsu.cmpe275.cusr.model.Ticket;
import edu.sjsu.cmpe275.cusr.repository.TicketRepository;

@Component
public class TicketService {
	
	@Autowired 
	TicketRepository ticketRepository;
	
	/**
	 * To save the ticket
	 * @param ticket
	 */
	public void saveTicket(Ticket ticket)
	{
		ticketRepository.save(ticket);
	}
}
