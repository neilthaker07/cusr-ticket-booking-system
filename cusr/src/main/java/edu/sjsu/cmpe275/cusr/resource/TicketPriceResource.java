package edu.sjsu.cmpe275.cusr.resource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.cusr.model.JourneyDetails;
import edu.sjsu.cmpe275.cusr.model.Ticket;
import edu.sjsu.cmpe275.cusr.service.JourneyService;
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
}
