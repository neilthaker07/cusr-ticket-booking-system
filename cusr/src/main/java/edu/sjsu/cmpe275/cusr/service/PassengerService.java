package edu.sjsu.cmpe275.cusr.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.sjsu.cmpe275.cusr.model.Passenger;
import edu.sjsu.cmpe275.cusr.model.Ticket;
import edu.sjsu.cmpe275.cusr.repository.PassengerRepository;

@Component
public class PassengerService {
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	TicketService ticketService;
	
	public void insertPassenger(Passenger pax){
		passengerRepository.save(pax);
	}
	
	public void savePaxInfoForTicket(String paxJsonData, long ticketId){
		
		Ticket ticket = ticketService.getTicketById(ticketId);
		
		JSONParser parser = new JSONParser();
		
		// Converting JSON string of passenger info to JSONArray
		try{
			JSONArray passengerJSONArray = (JSONArray) parser.parse(paxJsonData); 
			
			for(int i=0; i<passengerJSONArray.size(); i++){
				JSONObject passengerJSONObj = (JSONObject) passengerJSONArray.get(i);
				
				// Setting up passenger object from the JSON data
				Passenger pax = new Passenger();
				pax.setFirstname((String)passengerJSONObj.get("firstname"));
				pax.setLastname((String)passengerJSONObj.get("lastname"));
				pax.setAge(Integer.valueOf((String)passengerJSONObj.get("age")));
				pax.setTicket(ticket);
				
				this.insertPassenger(pax);
			}
		}catch(ParseException e){
			e.printStackTrace();
		}
	}
}
