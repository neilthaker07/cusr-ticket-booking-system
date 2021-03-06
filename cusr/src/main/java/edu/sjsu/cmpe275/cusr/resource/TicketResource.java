package edu.sjsu.cmpe275.cusr.resource;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	public String saveTicket(@RequestBody Ticket ticket)
	{
		ticketService.saveTicket(ticket);
		return "ticket";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/cancelTicket")
	public String cancelTicket(@RequestParam long ticketId)
	{
		Ticket ticket = ticketService.getTicketById(ticketId);
		boolean cancelledTicket = ticketService.cancelTicket(ticket);
		
		if(cancelledTicket){
			return "SUCCESS";
		}else{
			return "ERROR";
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/tickets/{userid}")
	public List<Ticket> getTicketForUser(@PathVariable long userid){
		
		List<Ticket> ticketList = ticketService.getTicketByUserId(userid);
		return ticketList;
		
	}
}

