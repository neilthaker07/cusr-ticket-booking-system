package edu.sjsu.cmpe275.cusr.resource;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.cusr.model.Ticket;

@RestController
public class TicketPriceResource {

	//@RequestMapping(method = RequestMethod.POST, value="/calculatePrice")
	//public void getPrice(@RequestBody Ticket ticket)
	public void getPrice()
	{	
		Map<String, Integer> stations = new HashMap<String, Integer>();
		stations.put("A",1);
		stations.put("B",2);
		stations.put("C",3);
		stations.put("D",4);
		stations.put("E",5);
		stations.put("F",6);
		stations.put("G",7);
		stations.put("H",8);
		stations.put("I",9);
		stations.put("J",10);
		stations.put("K",11);
		stations.put("L",12);
		stations.put("M",13);
		stations.put("N",14);
		stations.put("O",15);
		stations.put("P",16);
		stations.put("Q",17);
		stations.put("R",18);
		stations.put("S",19);
		stations.put("T",20);
		stations.put("U",21);
		stations.put("V",22);
		stations.put("W",23);
		stations.put("X",24);
		stations.put("Y",25);
		stations.put("Z",26);
		
		String fromStation = "C";
		String toStation = "P";
		
		int stationDiff = Math.abs(stations.get(toStation) - stations.get(fromStation));
		double d = stationDiff / 5.0;
	
        double finalRate = Math.ceil(d);
        
        String trainType = "reg";
		if(!trainType.equals("reg"))
		{
			finalRate*=2; // express, price double 
		}
		
		System.out.println("final rate : "+finalRate); // searching time price rate
		
		// $1 transaction fees will be shown at booking time.
		
		
		
		
		
		
		
		 
		String f1 = "G";
		String t1 = "T";
		
		//(1) Pre searching of a time
		
		// regular trains
		int u1 = stations.get(f1);
		int betweenStationTime = (u1 - 1) * 5; // 5 min from 1 station to other
		int haultTime = (u1 - 1) * 3; // 3 min hault at each station
		
		int totalBeforeTravelTime = betweenStationTime + haultTime; // in minutes
		
		int searchedTime = 7; // 7:00 AM 
		
		// train search time in database at station A
		int departureTime = searchedTime - totalBeforeTravelTime;  // 7:03 AM 
		// Search this time in DB : This is departure time at searched station.
		// SB0615 : It's near to 06:18 AM
		
		
		//(2) Total traveling Time in a train from source to destination
		departureTime = 7; // 7:03 AM, ex: 7:00 AM
		int u2 = stations.get(t1) - stations.get(f1);
		int betweenStationTime2 = (u2) * 5; // 5 min from 1 station to other
		int haultTime2 = (u2 - 1) * 3; // 3 min hault at each station
		
		int totalTravelTime = betweenStationTime2 + haultTime2; // in minutes

		
		int reachingTime = departureTime + totalTravelTime;
		
		
		
	}
	
	
}
