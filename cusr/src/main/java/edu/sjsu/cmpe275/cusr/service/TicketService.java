package edu.sjsu.cmpe275.cusr.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
	
	public boolean cancelTicket(Ticket ticket)
	{
		ArrayList<JourneyDetails> cancelledJourney = journeyRepository.findManyByTicket(ticket.getTicketId());
		boolean isTicketAllowedToCancel = false;
		
		for(JourneyDetails jd: cancelledJourney){
			
			String journeyDate = jd.getJourneyDate();
			String deptTime = jd.getDepartureTime();
			
			String value = journeyDate+"/"+deptTime;
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd/HHmm");
			Date ticketDate = null;
			try {
				ticketDate = dateFormat.parse(value);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime ticketThen = LocalDateTime.of(ticketDate.getYear()+1900, 
					ticketDate.getMonth()+1, 
					ticketDate.getDate(), ticketDate.getHours(), ticketDate.getMinutes());
			
			Duration duration = Duration.between(ticketThen, now);
			long diff = Math.abs(duration.toMinutes());
			
			if(diff >= 60){
				isTicketAllowedToCancel = true;
			} else{
				isTicketAllowedToCancel = false;
				break;
			}
		}
		
		if(isTicketAllowedToCancel){
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
		}
		
		return isTicketAllowedToCancel;
	}
	
	public Ticket getTicketById(long id)
	{
		Ticket ticket = ticketRepository.findOne(id);
		return ticket;
	}
	
	public List<Ticket> getTicketByUserId(long id){
		return ticketRepository.getTicketsByUserId(id);
	}
	
	public List<Ticket> getAllTickets(){
		return ticketRepository.findAll();
	}
	
	public void deleteTicket(Ticket ticket){
		ticketRepository.delete(ticket);
	}
	
}
