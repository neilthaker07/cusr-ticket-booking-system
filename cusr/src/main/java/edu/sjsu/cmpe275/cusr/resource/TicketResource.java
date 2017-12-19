package edu.sjsu.cmpe275.cusr.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(method=RequestMethod.PUT, value="/cancelTicket")
	public ResponseEntity<Object> cancelTicket(@RequestBody Ticket ticket)
	{
		Ticket cancelledTicket = ticketService.cancelTicket(ticket);
		
		List<JSONObject> entities = new ArrayList<JSONObject>();
	    JSONObject entity = new JSONObject();
	    entity.put("cancelledTicket", cancelledTicket);
	    entities.add(entity);
	    
		return new ResponseEntity<Object>(entities, HttpStatus.OK);
	}
	
}

