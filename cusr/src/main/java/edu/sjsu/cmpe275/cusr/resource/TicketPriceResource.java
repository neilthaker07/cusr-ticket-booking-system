package edu.sjsu.cmpe275.cusr.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.cusr.model.JourneyDetails;
import edu.sjsu.cmpe275.cusr.service.TicketPriceService;

@CrossOrigin
@RestController
public class TicketPriceResource {

	@Autowired
	TicketPriceService ticketPriceService;
	
	@RequestMapping(method = RequestMethod.GET, value="/tickets")
	public List<JourneyDetails> getTickets()
	{
		return ticketPriceService.getTickets();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/dbQuery")
	public boolean dbQuery()
	{
		return ticketPriceService.isTicketAvailable((long)1000, "15-12-17", 'C', 'F',3, 50);
	}
}
