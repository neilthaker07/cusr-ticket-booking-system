package edu.sjsu.cmpe275.cusr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.sjsu.cmpe275.cusr.model.JourneyDetails;
import edu.sjsu.cmpe275.cusr.model.Ticket;
import edu.sjsu.cmpe275.cusr.model.Transaction;
import edu.sjsu.cmpe275.cusr.repository.JourneyRepository;

@Component
public class JourneyService {
	
	@Autowired 
	JourneyRepository journeyRepository;
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	TransactionService transactionService;

	/*public void saveJourney(JourneyDetails journey)
	{
		journeyRepository.save(journey);
	}*/
	
	public JourneyDetails getJourneyById(Long journeyId)
	{
		return journeyRepository.findOne(journeyId);
	}
	
	public Long saveFinalTicket(JourneyDetails journeyDetails, Double price, Long userId)
	{
		Ticket ticket = new Ticket();
		ticket.setUserId(userId);
		ticket.setDeleted(false);
		ticket.setCancelled(false);
		
		ticketService.saveTicket(ticket);
		
		journeyDetails.setTicket(ticket);
		journeyRepository.save(journeyDetails);
		
		Transaction transaction = new Transaction();
		transaction.setPrice(price);
		transaction.setTicket(ticket);
		transactionService.saveTicketTransaction(transaction);
		
		return ticket.getTicketId(); 
	}
}
