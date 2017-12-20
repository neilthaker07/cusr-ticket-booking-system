package edu.sjsu.cmpe275.cusr.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.sjsu.cmpe275.cusr.model.JourneyDetails;
import edu.sjsu.cmpe275.cusr.repository.JourneyRepository;

@Component
public class TicketPriceService {
	
	public double getFinalPrice(int passengers)
	{
		double price = 0;
		int transactionFee = 1;
		double journeyFee = ticketPriceBetweenStations('A', 'B', "regular");
		
		price = transactionFee + (journeyFee * passengers);
		return price;
	}
	
	public double ticketPriceBetweenStations(Character fromStation, Character toStation, String ticketType)
	{
		allStations();
		int stationDiff = Math.abs(stations.get(toStation) - stations.get(fromStation));
		double d = stationDiff / 5.0;
        double finalRate = Math.ceil(d);
        
		if(ticketType.equals("express"))
		{
			finalRate*=2; // express, price double 
		}
		
		System.out.println("final rate : "+finalRate); // searching time price rate
		return finalRate;
	}
	
	public int timeToSearchForTrain(Character fromStation, Character toStation, String ticketType, int searchedDepTime)
	{
		allStations();
		fromStation = 'G';
		toStation = 'T';
		
		//(1) Pre searching of a time
		// regular trains
		int u1 = stations.get(fromStation);
		int betweenStationTime = (u1 - 1) * 5; // 5 min from 1 station to other
		int haultTime = (u1 - 1) * 3; // 3 min hault at each station
		
		int totalBeforeTravelTime = betweenStationTime + haultTime; // in minutes
		
		searchedDepTime = 7; // 7:00 AM 
		
		// train search time in database at station A
		int depTime = searchedDepTime - totalBeforeTravelTime;  // 7:03 AM 
		// Search this time in DB : This is departure time at searched station.
		// SB0615 : It's near to 06:18 AM
		return depTime;
	}
	
	public void journeyTime(Character fromStation, Character toStation, int departureTime)
	{
		allStations();
		fromStation = 'G';
		toStation = 'T';
		
		//(2) Total traveling Time in a train from source to destination
		departureTime = 7; // 7:03 AM, ex: 7:00 AM
		int u2 = stations.get(toStation) - stations.get(fromStation);
		int betweenStationTime = (u2) * 5; // 5 min from 1 station to other
		int haultTime = (u2 - 1) * 3; // 3 min hault at each station
		
		int journeyTime = betweenStationTime + haultTime; // in minutes
		int reachingTime = departureTime + journeyTime;
	}
	
	Map<Character, Integer> stations = new HashMap<Character, Integer>();
	public void allStations()
	{
		stations.put('A',1);
		stations.put('B',2);
		stations.put('C',3);
		stations.put('D',4);
		stations.put('E',5);
		stations.put('F',6);
		stations.put('G',7);
		stations.put('H',8);
		stations.put('I',9);
		stations.put('J',10);
		stations.put('K',11);
		stations.put('L',12);
		stations.put('M',13);
		stations.put('N',14);
		stations.put('O',15);
		stations.put('P',16);
		stations.put('Q',17);
		stations.put('R',18);
		stations.put('S',19);
		stations.put('T',20);
		stations.put('U',21);
		stations.put('V',22);
		stations.put('W',23);
		stations.put('X',24);
		stations.put('Y',25);
		stations.put('Z',26);
	}
	
	public boolean isTicketAvailable(Long trainId, String journeyDate, Character fromStation, Character toStation, int passengers, int totalTrainSeats)
	{
	/*	String qry = "select sum(passengers) from journey_details where journey_train_id = 1000 and journey_date = 2012-04-23 11:25:44 "
				+ "and 1 = (case when (( 3 >= source && 6<= destination ) OR (  3 < source && 6<= destination && 6>source )"
				+ " OR ( 3 < source && 6> destination ) OR ( 3 > source && 3< destination && 6>destination )) then 1 else 2 end)";
	*/
		allStations();

		int bookedTickets = journeyRepository.findByJourneyTrainIdAndJourneyDate(trainId, journeyDate, stations.get(fromStation), stations.get(toStation));
		if(bookedTickets > (totalTrainSeats - passengers))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	@Autowired 
	JourneyRepository journeyRepository;
	public List<JourneyDetails> getTickets()
	{
		return journeyRepository.findAll();
	}
}
