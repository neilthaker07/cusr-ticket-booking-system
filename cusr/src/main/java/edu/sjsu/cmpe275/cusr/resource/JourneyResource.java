package edu.sjsu.cmpe275.cusr.resource;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.cusr.model.JourneyDetails;
import edu.sjsu.cmpe275.cusr.service.JourneyService;

@RestController
@CrossOrigin
public class JourneyResource {
	
	@Autowired
	JourneyService journeyService;
	
	@RequestMapping(method=RequestMethod.POST, value="/journey")
	public String saveJourney(@RequestParam Long trainId, @RequestParam Long ticketId, @RequestParam int source, @RequestParam int destination, @RequestParam int passengers, @RequestParam Date departureTime, @RequestParam Date journeyDate) 
	{
		JourneyDetails journey = new JourneyDetails(trainId, ticketId, source, destination, passengers, departureTime, journeyDate);
		journeyService.saveJourney(journey);
		return "journey";
	}
}
