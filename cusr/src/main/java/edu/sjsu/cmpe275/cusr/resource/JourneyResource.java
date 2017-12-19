package edu.sjsu.cmpe275.cusr.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	/*
	@RequestMapping(method=RequestMethod.POST, value="/journey")
	public String saveJourney(@RequestBody JourneyDetails journeyDetails) 
	{
		journeyService.saveJourney(journeyDetails);
		return "journey";
	}*/
	
	@RequestMapping(method = RequestMethod.GET, value="/journey/{id}")
	public JourneyDetails getJourney(@PathVariable Long id)
	{	
		return journeyService.getJourneyById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/finalTicket/{userId}")
	public Long saveFinalTicket(@PathVariable("userId") Long userId,
			@RequestBody JourneyDetails journeyDetails, @RequestParam Double price) 
	{
		// returns ticket id
		return journeyService.saveFinalTicket(journeyDetails, price, userId);
	}
	
}
