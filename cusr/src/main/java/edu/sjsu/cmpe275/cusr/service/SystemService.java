package edu.sjsu.cmpe275.cusr.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.sjsu.cmpe275.cusr.model.Ticket;
import edu.sjsu.cmpe275.cusr.repository.TrainRepository;

@Component
public class SystemService {
	
	@Autowired
	TrainRepository trainService;
	
	@Autowired
	TicketService ticketService;
	
	@Transactional
	public void resetSystem(int trainCapacity){
		
		trainService.updateTrainCapacity(trainCapacity);
		
		List<Ticket> ticketList = ticketService.getAllTickets();
		
		for(Ticket aTicket: ticketList){
			//ticketService.cancelTicket(aTicket);
			ticketService.deleteTicket(aTicket);
		}
	}
}
