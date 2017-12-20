package edu.sjsu.cmpe275.cusr.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.sjsu.cmpe275.cusr.model.JourneyDetails;
import edu.sjsu.cmpe275.cusr.model.Ticket;
import edu.sjsu.cmpe275.cusr.repository.JourneyRepository;
import edu.sjsu.cmpe275.cusr.repository.TrainRepository;

@Component
public class SystemService {
	
	@Autowired
	TrainRepository trainService;
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	JourneyRepository journeyService;
	
	@Transactional
	public void resetSystem(int trainCapacity){
		
		trainService.updateTrainCapacity(trainCapacity);
		
		List<Ticket> ticketList = ticketService.getAllTickets();
		
		for(Ticket aTicket: ticketList){
			//ticketService.cancelTicket(aTicket);
			ticketService.deleteTicket(aTicket);
		}
	}

	public double systemReport(long trainId, String date) {
		
		ArrayList<JourneyDetails> journeyDetails=journeyService.findByTrainIdAndJourneyDate(trainId, date);
		
		double finalStat=0;
		int trainCapacity=1000;
		
		for(JourneyDetails jDetails : journeyDetails )
		{
			int segments= Math.abs(jDetails.getDestination()-jDetails.getSource());
			int passangerNo= jDetails.getPassengers();
			System.out.println(":::::::::::Segments ::::: Passanger" +segments  + "::::" +passangerNo) ;
			
			
			int intermediateStat=((passangerNo/trainCapacity)*segments);
			System.out.println(":::::::::::intermediateStat ::::: " +intermediateStat  ) ;
			
			finalStat += intermediateStat;
			
			
		}
		double result = (finalStat/25)*100;
		return result;
		
	}
}
