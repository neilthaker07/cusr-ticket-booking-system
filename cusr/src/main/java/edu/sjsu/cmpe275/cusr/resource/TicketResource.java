package edu.sjsu.cmpe275.cusr.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.cusr.model.Ticket;
import edu.sjsu.cmpe275.cusr.service.TicketService;

@RestController
@CrossOrigin
public class TicketResource {
	
	@Autowired
	TicketService ticketService;
	
	@RequestMapping(method=RequestMethod.POST, value="/ticket")
	public String saveTicket(@RequestParam Long userId, @RequestParam Boolean isDeleted){
		
		Ticket ticket = new Ticket(userId, isDeleted);
		ticketService.saveTicket(ticket);
		return "ticket";
	}
}
