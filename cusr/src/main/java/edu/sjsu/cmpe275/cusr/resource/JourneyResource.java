package edu.sjsu.cmpe275.cusr.resource;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
			@RequestParam String journeyDetailString, @RequestParam Double price) 
	{
		Map<Character, Integer> stations = new HashMap<Character, Integer>();
		stations.put('A',0);
		stations.put('B',1);
		stations.put('C',2);
		stations.put('D',3);
		stations.put('E',4);
		stations.put('F',5);
		stations.put('G',6);
		stations.put('H',7);
		stations.put('I',8);
		stations.put('J',9);
		stations.put('K',10);
		stations.put('L',11);
		stations.put('M',12);
		stations.put('N',13);
		stations.put('O',14);
		stations.put('P',15);
		stations.put('Q',16);
		stations.put('R',17);
		stations.put('S',18);
		stations.put('T',19);
		stations.put('U',20);
		stations.put('V',21);
		stations.put('W',22);
		stations.put('X',23);
		stations.put('Y',24);
		stations.put('Z',25);
		
		JSONParser parser = new JSONParser();
		
		JSONObject jouneyDetailJSONObj;
		JourneyDetails journeyDetails = new JourneyDetails();
		try {
			jouneyDetailJSONObj = (JSONObject) parser.parse(journeyDetailString);
			
			String src = (String)jouneyDetailJSONObj.get("source");
			int stationSrcValue = stations.get(src.charAt(0));
			
			String dest = (String)jouneyDetailJSONObj.get("destination");
			int stationDestValue = stations.get(dest.charAt(0));
			
			journeyDetails.setSource(stationSrcValue);
			journeyDetails.setDestination(stationDestValue);
			journeyDetails.setPassengers(Integer.valueOf((String)jouneyDetailJSONObj.get("passengers")));
			journeyDetails.setJourneyDate((String)jouneyDetailJSONObj.get("journeyDate"));
			journeyDetails.setDepartureTime((String)jouneyDetailJSONObj.get("departureTime"));
			journeyDetails.setTrainNo((String)jouneyDetailJSONObj.get("trainNo"));
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		};
		
			// returns ticket id
			return journeyService.saveFinalTicket(journeyDetails, price, userId);
		
	}
	
}
