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
	}
	
	
}
