package edu.sjsu.cmpe275.cusr.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.cusr.service.PassengerService;

@RestController
@CrossOrigin
public class PassengerResource {
	
	@Autowired
	PassengerService paxService;
	
	/** To save the passenger information in the database for given ticket id
	 */
	@RequestMapping(method=RequestMethod.POST, value="/passengers")
	public void addPassengersForTicket(@RequestParam String paxData, @RequestParam long ticketId){
		paxService.savePaxInfoForTicket(paxData, ticketId);
	}
}
