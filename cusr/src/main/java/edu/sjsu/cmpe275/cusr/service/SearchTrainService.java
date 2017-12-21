package edu.sjsu.cmpe275.cusr.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.sjsu.cmpe275.cusr.model.SearchTrain;
import edu.sjsu.cmpe275.cusr.repository.JourneyRepository;
import edu.sjsu.cmpe275.cusr.repository.TrainRepository;

@Component
public class SearchTrainService {

	private ArrayList<ArrayList<HashMap<String,Object>>> searchedTrains = new ArrayList<ArrayList<HashMap<String,Object>>>();
	int  regularTrainCount=26;
	int expressTrainCount=5;
	
	//Contains express trains stations
	private HashSet<Character> ExpressStations =  new HashSet<Character>();
	/*Contains schedule for each regular train
	eg: regularTrain[0] = [915,930,945..] where 0=A
	*/
	private int[][] regularTrainSchedule = new int [regularTrainCount][45];
	/* contains scheule for express trains
	eg:expressTrainsSchedule[0]=[9,10,11..] where 0=A;
	*/
	private int[][] expressTrainsSchedule = new int[expressTrainCount][16];
	/*
	to get index of Regular trains in regularTrainsSchedule array.
	*/
	private ArrayList<Character> indexmappingRegularTrains = new ArrayList<Character>();
	private ArrayList<Character> indexmappingRegularTrainsSB  = new ArrayList<Character>();
	/*
	to get index of express trains in ExpressTrainsSchedule array.
	*/
	private ArrayList<Character> indexmappingExpressTrains  = new ArrayList<Character>();
	private ArrayList<Character> indexmappingExpressTrainsSB = new ArrayList<Character>();
	private HashMap<Integer,Integer[]> map_timings_with_train_reg =  new HashMap<Integer,Integer[]>();
	private HashMap<Integer,Integer[]> map_timings_with_train_exp =  new HashMap<Integer,Integer[]>();
	
	
	
	
	@Autowired 
	JourneyRepository journeyRepository;
	@Autowired
	TicketPriceService ticketPriceService;
	@Autowired
	TrainRepository trainRepository;
	
	/*public SearchTrainService()
	{
		int regularTrainCount=26;
		int expressTrainCount=5;
		
		this.ExpressStations =  new HashSet<Character>();
		this.regularTrainSchedule = new int [regularTrainCount][60];
		this.expressTrainsSchedule = new int[expressTrainCount][16];
		this.indexmappingRegularTrains = new ArrayList<Character>();
		this.indexmappingExpressTrainsSB = new ArrayList<Character>();
		this.indexmappingExpressTrains =  new ArrayList<Character>();
		this.indexmappingRegularTrainsSB = new ArrayList<Character>();
		this.searchedTrains = new ArrayList<ArrayList<HashMap<String,Object>>>();
		
		populateRegularTrainSchedules();
		populateExpressTrainSchedule();
		
		this.populate_IndexMapping();
		
	}*/
	public void populateRegularTrainSchedules() 
	{
		int start_time=615;
		int next_train = 0;
		int offset =  8;
		for(int i=0;i<regularTrainSchedule.length;i++)
		{
			for(int j=0;j<regularTrainSchedule[i].length;j++)
			{
				if(i!=0)
				{
			            next_train =0;
			            
					start_time = regularTrainSchedule[i-1][j]+offset;
				
					if(start_time % 100 > 60 )
					{
					    //System.out.println(start_time +"inside");
					    start_time -= offset;
					    int rem = start_time % 100;
					    start_time -= rem;
					    int off =  60-rem;
					    start_time +=100;
					    start_time += offset-off;
					}
					regularTrainSchedule[i][j] = start_time;
				}
				else{
				if(next_train >= 45)
				{
				    next_train = 0;
				    start_time +=100;
				}
				
				if(start_time + next_train %100 != 0)
				{
					regularTrainSchedule[i][j] = start_time + next_train;
					next_train += 15;
				}
				}
			}
		
			next_train =0;
		}
		
		
		/*for(int i=0;i<regularTrainSchedule.length;i++)
		{
			for(int j=0;j<regularTrainSchedule[i].length;j++)
			{
				System.out.println();
				System.out.print(regularTrainSchedule[i][j]+", ");
				
			}
		}
		*/
	}
	public void populateExpressTrainSchedule()
	{
		int start_time=600;
	int next_train = 0;
	int offset =  28;
	for(int i=0;i<expressTrainsSchedule.length;i++)
	{
		for(int j=0;j<expressTrainsSchedule[i].length;j++)
		{
			if(i!=0)
			{
		            next_train =0;
		            
				start_time = expressTrainsSchedule[i-1][j]+offset;
			
				if(start_time % 100 > 60 )
				{
				    //System.out.println(start_time +"inside");
				    start_time -= offset;
				    int rem = start_time % 100;
				    start_time -= rem;
				    int off =  60-rem;
				    start_time +=100;
				    start_time += offset-off;
				}
				expressTrainsSchedule[i][j] = start_time;
			}
			else{
			if(next_train >= 45)
			{
			    next_train = 0;
			    start_time +=100;
			}
			
			if((start_time + next_train) %100 == 0)
			{
				expressTrainsSchedule[i][j] = start_time + next_train;
				next_train += 100;
			}
			}
		}
	
		next_train =0;
}
	for(int i=0;i<expressTrainsSchedule.length;i++)
	{
		for(int j=0;j<expressTrainsSchedule[i].length;j++)
		{
			System.out.print(expressTrainsSchedule[i][j]+",");
		}
		System.out.println();
		}
	}
	
	public void populate_IndexMapping()
	{
		indexmappingExpressTrains.addAll(Arrays.asList('Z','U','P','K','F','A'));
		this.indexmappingExpressTrainsSB.addAll(Arrays.asList('A','F','K','P','U','Z'));
		indexmappingRegularTrains.addAll(Arrays.asList('Z','Y','X','W','V','U','T','S','R','Q','P','O','N','M','L','K','J','I','H','G','F','E','D','C','B','A'));
		this.indexmappingRegularTrainsSB.addAll(Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'));
		
	}
	public void populate_mapping_train_main()
	{
		Integer[] temp = {628,656,724,752};
			map_timings_with_train_reg.put(600,temp);
			Integer[] temp1 = {628,656,724,752};
			map_timings_with_train_reg.put(700,temp1);
			Integer[] temp2 = {728,756,824,852};
			map_timings_with_train_reg.put(800,temp2);
			Integer[] temp3 = {628,656,724,752};
			map_timings_with_train_reg.put(900,temp3);
			Integer[] temp4 = {628,656,724,752};
			map_timings_with_train_reg.put(1000,temp4);
			

	}
	 public void AddExpressStations(Character c)
	 {
		 this.ExpressStations.add(c);
	 }
	 public void addRegularTrainHours(int time)
	 {
	
	 }
	 public void addExpressTrainHours(int time)
	 {
		 
	 }
	 
	 /*public void findTrain(Character Departure,Character Arrival,Integer Time,String TicketType,int Connections_num, Boolean RoundTrip,int Passanger_num,String Dep_Date)
	 {
		 ticketPriceService.isTicketAvailable(10000, Dep_Date, Departure, Arrival, Passanger_num, 50);
	 }*/
	 
	 public ArrayList<HashMap<String,Object>> findTrain(SearchTrain searchTrain)
	 {
		 Character Departure= searchTrain.getDeparture_station();
		 Character Arrival = searchTrain.getArrival_station();
		 Integer Time = searchTrain.getDep_time();
		 String TicketType = searchTrain.getTicket_type();
		 int Connections_num = searchTrain.getConn_num();
		 Boolean RoundTrip = searchTrain.getRound_tr();
		 int Passanger_num =  searchTrain.getPass_num();
		 String Dep_Date = searchTrain.getDep_date();
		 
		populateRegularTrainSchedules();
		populateExpressTrainSchedule();
		populate_IndexMapping();
		 
		 HashMap<Character,ArrayList<Integer>> trainSchedules =  new HashMap<Character,ArrayList<Integer>>();
		 ArrayList<HashMap<String,Object>> trainsList = new ArrayList<HashMap<String,Object>>();
		 //south bound trains. eg: A->B
		if (Departure < Arrival) {

			 
			
		 	if(TicketType.equals("Express"))
		 	{
		 		//Both are express train
		 		if(this.indexmappingExpressTrainsSB.indexOf(Departure)!= -1 && this.indexmappingExpressTrainsSB.indexOf(Arrival)!= -1 )
		 		{
		 			int total_return =0;
		 			while(total_return < 5 )
		 			{
		 				ArrayList<HashMap<String,Object>> temp =  new ArrayList<HashMap<String,Object>>();
		 	 			HashMap<String,Object> temp_store = new HashMap<String,Object>();
		 			
		 			if(Time < 0)
		 			{
		 				break;
		 			}
		 			temp = lookAnyTrainsFinal(Departure,Arrival,Time,Dep_Date,Passanger_num,this.indexmappingExpressTrainsSB,Connections_num,this.expressTrainsSchedule);	
		 			Time = 	get_available_time_from_station(indexmappingExpressTrainsSB.indexOf(Departure),Time+15,this.expressTrainsSchedule);
		 			temp_store.put("depature_station",temp.get(0).get("departure_station"));
		 			temp_store.put("arrival_station",temp.get(temp.size()-1).get("arrival_station"));
		 			temp_store.put("passengers",temp.get(0).get("passengers")); 
					temp_store.put("departure_time",temp.get(0).get("departure_time")); 
					temp_store.put("departure_date",temp.get(0).get("departure_date")); 
					int arrivalTime =  format_time(Integer.valueOf(String.valueOf(temp.get(temp.size()-1).get("departure_time"))),25);
					temp_store.put("arrival_time",arrivalTime); 
					double price =  ticketPriceService.ticketPriceBetweenStations(Departure, Arrival,TicketType);
					temp_store.put("price",String.valueOf(price));
					temp_store.put("journeyTime",Integer.valueOf(String.valueOf(temp.get(temp.size()-1).get("departure_time")))-Integer.valueOf(String.valueOf(temp.get(0).get("departure_time")))+25); // journey time : remaining
					temp_store.put("trainType","SB");  
					int depTimeOrginial = getTrainIndex(String.valueOf(temp.get(0).get("departure_station")).charAt(0),"Express","SB",Integer.valueOf(String.valueOf(temp.get(0).get("departure_time"))),String.valueOf(temp.get(temp.size()-1).get("arrival_station")).charAt(0));
					temp_store.put("trainNo", getTrainName(indexmappingRegularTrainsSB.indexOf(String.valueOf(temp.get(0).get("departure_station")).charAt(0)),indexmappingRegularTrainsSB.indexOf(String.valueOf(temp.get(temp.size()-1).get("arrival_station")).charAt(0)),depTimeOrginial));
					
					trainsList.add(temp_store);
					total_return++;
		 			}
		 			}
		 		//Departure is express
		 		else if(this.indexmappingExpressTrainsSB.indexOf(Departure)!= -1)
		 		{
		 			for(int i=this.indexmappingExpressTrainsSB.size()-1;i> this.indexmappingExpressTrainsSB.indexOf(Departure);i--)
					{
						//check if arrival station is immediately after express station found
						if(this.indexmappingExpressTrainsSB.get(i) <= Arrival)
						{
							//Get train info for Express train connections
							lookAnyTrainsFinal(Departure,this.indexmappingExpressTrainsSB.get(i),Time,Dep_Date,Passanger_num,this.indexmappingExpressTrainsSB,Connections_num,this.expressTrainsSchedule);
							//Get train info for Express to Regular train connection
							//Change time to time from last reached station from above call.
							lookAnyTrainsFinal(this.indexmappingExpressTrainsSB.get(i),Arrival,Time,Dep_Date,Passanger_num,this.indexmappingRegularTrainsSB,Connections_num,this.regularTrainSchedule);
						}
					}
		 		}
		 		//Arrival is express
		 		else if(this.indexmappingExpressTrainsSB.indexOf(Arrival)!= -1)
		 		{//Improve by iterating through expressinedex only
		 			for(int i=this.indexmappingRegularTrainsSB.indexOf(Departure);i<this.indexmappingRegularTrainsSB.size();i++)
					{
						if(this.indexmappingExpressTrainsSB.contains(this.indexmappingRegularTrainsSB.get(i)) && this.indexmappingRegularTrainsSB.get(i)< Arrival)
						{
							//Get train from Regular to express
							lookAnyTrainsFinal(Departure,this.indexmappingExpressTrainsSB.get(i),Time,Dep_Date,Passanger_num,this.indexmappingRegularTrainsSB,Connections_num,this.regularTrainSchedule);
							//Get train from express to express
							lookAnyTrainsFinal(this.indexmappingExpressTrainsSB.get(i),Arrival,Time,Dep_Date,Passanger_num,this.indexmappingExpressTrainsSB,Connections_num,this.expressTrainsSchedule);
						}
					}
		 		}
		 		else
		 		{
		 			for(int i=this.indexmappingExpressTrainsSB.size()-1;i> this.indexmappingExpressTrainsSB.indexOf(Departure);i--)
					{
						//check if arrival station is immediately after express station found
						if(this.indexmappingExpressTrainsSB.get(i) <= Arrival)
						{
							for(int j=this.indexmappingRegularTrainsSB.indexOf(Departure);j<this.indexmappingRegularTrainsSB.size();j++)
							{
								if(this.indexmappingExpressTrainsSB.contains(this.indexmappingRegularTrainsSB.get(j)) && this.indexmappingRegularTrainsSB.get(j)< Arrival)
								{
									lookAnyTrainsFinal(Departure,this.indexmappingExpressTrainsSB.get(i),Time,Dep_Date,Passanger_num,this.indexmappingRegularTrainsSB,Connections_num,this.regularTrainSchedule);
									lookAnyTrainsFinal(this.indexmappingExpressTrainsSB.get(i),this.indexmappingExpressTrainsSB.get(j),Time,Dep_Date,Passanger_num,this.indexmappingExpressTrainsSB,Connections_num,this.expressTrainsSchedule);
									lookAnyTrainsFinal(this.indexmappingExpressTrainsSB.get(j),Arrival,Time,Dep_Date,Passanger_num,this.indexmappingRegularTrainsSB,Connections_num,this.regularTrainSchedule);
									
									
								}
							}
						}
						}
		 		}
		 	}
		 	else if(TicketType.equals("Regular"))
		 	{
		 		
	 			
	 			int total_return =0;
	 			while(total_return < 5 )
	 			{
	 				ArrayList<HashMap<String,Object>> temp =  new ArrayList<HashMap<String,Object>>();
	 	 			HashMap<String,Object> temp_store = new HashMap<String,Object>();
	 			
	 			if(Time < 0)
	 			{
	 				break;
	 			}
	 			temp =  lookAnyTrainsFinal(Departure,Arrival,Time,Dep_Date,Passanger_num,this.indexmappingRegularTrainsSB,Connections_num,this.regularTrainSchedule);
	 			Time = 	get_available_time_from_station(indexmappingRegularTrainsSB.indexOf(Departure),Time+15,this.regularTrainSchedule);
	 			temp_store.put("departure_station",temp.get(0).get("departure_station"));
	 			temp_store.put("arrival_station",temp.get(temp.size()-1).get("arrival_station"));
	 			temp_store.put("passengers",temp.get(0).get("passengers")); 
				temp_store.put("departure_time",temp.get(0).get("departure_time")); 
				temp_store.put("departure_date",temp.get(0).get("departure_date")); 
				int arrivalTime =  format_time(Integer.valueOf(String.valueOf(temp.get(temp.size()-1).get("departure_time"))),5);
				temp_store.put("arrival_time",arrivalTime); 
				double price =  ticketPriceService.ticketPriceBetweenStations(Departure, Arrival,TicketType);
				temp_store.put("price",String.valueOf(price));
				temp_store.put("journeyTime",Integer.valueOf(String.valueOf(temp.get(temp.size()-1).get("departure_time")))-Integer.valueOf(String.valueOf(temp.get(0).get("departure_time")))+5); // journey time : remaining
				temp_store.put("trainType","SB");  
				int depTimeOrginial = getTrainIndex(String.valueOf(temp.get(0).get("departure_station")).charAt(0),"Regular","SB",Integer.valueOf(String.valueOf(temp.get(0).get("departure_time"))),String.valueOf(temp.get(temp.size()-1).get("arrival_station")).charAt(0));
				temp_store.put("trainNo", getTrainName(indexmappingRegularTrainsSB.indexOf(String.valueOf(temp.get(0).get("departure_station")).charAt(0)),indexmappingRegularTrainsSB.indexOf(String.valueOf(temp.get(temp.size()-1).get("arrival_station")).charAt(0)),depTimeOrginial));
				trainsList.add(temp_store);
				total_return++;
	 			}
	 			
	 	}
		 	else
		 	{
		 		int total_return =0;
		 		int total_return_reg = 0;
		 		if(Math.abs(this.indexmappingRegularTrainsSB.indexOf(Departure)-this.indexmappingRegularTrainsSB.indexOf(Arrival)) >= 5)
		 		{
		 			
		 			//Both are express train
			 		if(this.indexmappingExpressTrainsSB.indexOf(Departure)!= -1 && this.indexmappingExpressTrainsSB.indexOf(Arrival)!= -1 )
			 		{
			 			int TimeTemp = Time;
			 			
			 			while(total_return < 5 )
			 			{
			 				ArrayList<HashMap<String,Object>> temp =  new ArrayList<HashMap<String,Object>>();
			 	 			HashMap<String,Object> temp_store = new HashMap<String,Object>();
			 			
			 			if(TimeTemp < 0)
			 			{
			 				break;
			 			}
			 			temp=lookAnyTrainsFinal(Departure,Arrival,TimeTemp,Dep_Date,Passanger_num,this.indexmappingExpressTrainsSB,Connections_num,this.expressTrainsSchedule);	
			 			
			 			TimeTemp = 	get_available_time_from_station(indexmappingExpressTrainsSB.indexOf(Departure),TimeTemp+15,this.expressTrainsSchedule);
			 			temp_store.put("depature_station",temp.get(0).get("departure_station"));
			 			temp_store.put("arrival_station",temp.get(temp.size()-1).get("arrival_station"));
			 			temp_store.put("passengers",temp.get(0).get("passengers")); 
						temp_store.put("departure_time",temp.get(0).get("departure_time")); 
						temp_store.put("departure_date",temp.get(0).get("departure_date")); 
						int arrivalTime =  format_time(Integer.valueOf(String.valueOf(temp.get(temp.size()-1).get("departure_time"))),25);
						temp_store.put("arrival_time",arrivalTime); 
						
						double price =  ticketPriceService.ticketPriceBetweenStations(Departure, Arrival,TicketType);
						temp_store.put("price",String.valueOf(price));
						temp_store.put("journeyTime",String.valueOf(Integer.valueOf(String.valueOf(temp.get(temp.size()-1).get("departure_time")))-Integer.valueOf(String.valueOf(temp.get(0).get("departure_time")))+25)); // journey time : remaining
						temp_store.put("trainType","SB"); 
						int depTimeOrginial = getTrainIndex(String.valueOf(temp.get(0).get("departure_station")).charAt(0),"Express","SB",Integer.valueOf(String.valueOf(temp.get(0).get("departure_time"))),String.valueOf(temp.get(temp.size()-1).get("arrival_station")).charAt(0));
						
						temp_store.put("trainNo", getTrainName(indexmappingRegularTrainsSB.indexOf(String.valueOf(temp.get(0).get("departure_station")).charAt(0)),indexmappingRegularTrainsSB.indexOf(String.valueOf(temp.get(temp.size()-1).get("arrival_station")).charAt(0)),depTimeOrginial));
						
						trainsList.add(temp_store);
						total_return++;
			 			}
			 			}
			 		//Departure is express
			 		/* if(this.indexmappingExpressTrainsSB.indexOf(Departure)!= -1)
			 		{
			 			for(int i=this.indexmappingExpressTrainsSB.size();i> this.indexmappingExpressTrainsSB.indexOf(Departure);i--)
						{
							//check if arrival station is immediately after express station found
							if(this.indexmappingExpressTrainsSB.get(i) <= Arrival)
							{
								//Get train info for Express train connections
								lookAnyTrainsFinal(Departure,this.indexmappingExpressTrainsSB.get(i),Time,Dep_Date,Passanger_num,this.indexmappingExpressTrainsSB,Connections_num,this.expressTrainsSchedule);
								//Get train info for Express to Regular train connection
								lookAnyTrainsFinal(this.indexmappingExpressTrainsSB.get(i),Arrival,Time,Dep_Date,Passanger_num,this.indexmappingRegularTrainsSB,Connections_num,this.regularTrainSchedule);
							}
						}
			 		}
			 		//Arrival is express
			 		 else
			 		{
			 			for(int i=this.indexmappingRegularTrainsSB.indexOf(Departure);i<this.indexmappingRegularTrainsSB.size();i++)
						{
							if(this.indexmappingExpressTrainsSB.contains(this.indexmappingRegularTrainsSB.get(i)) && this.indexmappingRegularTrainsSB.get(i)< Arrival)
							{
								//Get train from Regular to express
								lookAnyTrainsFinal(Departure,this.indexmappingExpressTrainsSB.get(i),Time,Dep_Date,Passanger_num,this.indexmappingRegularTrainsSB,Connections_num,this.regularTrainSchedule);
								//Get train from express to express
								lookAnyTrainsFinal(this.indexmappingExpressTrainsSB.get(i),Arrival,Time,Dep_Date,Passanger_num,this.indexmappingExpressTrainsSB,Connections_num,this.expressTrainsSchedule);
							}
						}
			 		}*/
		 		}
		 		int TimeTemp2 = Time+15;
		 		while(total_return_reg < 5 )
	 			{
	 				ArrayList<HashMap<String,Object>> temp =  new ArrayList<HashMap<String,Object>>();
	 	 			HashMap<String,Object> temp_store = new HashMap<String,Object>();
	 			
	 			if(TimeTemp2 < 0)
	 			{
	 				break;
	 			}
		 		
		 		temp=lookAnyTrainsFinal(Departure,Arrival,TimeTemp2,Dep_Date,Passanger_num,this.indexmappingRegularTrainsSB,Connections_num,this.regularTrainSchedule);	
		 		TimeTemp2 = 	get_available_time_from_station(indexmappingRegularTrainsSB.indexOf(Departure),TimeTemp2+15,this.regularTrainSchedule);
	 			temp_store.put("depature_station",temp.get(0).get("departure_station"));
	 			temp_store.put("arrival_station",temp.get(temp.size()-1).get("arrival_station"));
	 			temp_store.put("passengers",temp.get(0).get("passengers")); 
				temp_store.put("departure_time",temp.get(0).get("departure_time")); 
				temp_store.put("departure_date",temp.get(0).get("departure_date")); 
				int arrivalTime =  format_time(Integer.valueOf(String.valueOf(temp.get(temp.size()-1).get("departure_time"))),5);
				temp_store.put("arrival_time",arrivalTime); 
				
				temp_store.put("arrival_time",arrivalTime); 
				double price =  ticketPriceService.ticketPriceBetweenStations(Departure, Arrival,TicketType);
				temp_store.put("price",String.valueOf(price));
				temp_store.put("journeyTime",Integer.valueOf(String.valueOf(temp.get(temp.size()-1).get("departure_time")))-Integer.valueOf(String.valueOf(temp.get(0).get("departure_time")))+5); // journey time : remaining
				temp_store.put("trainType","SB"); 
				int depTimeOrginal = getTrainIndex(String.valueOf(temp.get(0).get("departure_station")).charAt(0),"Regular","SB",Integer.valueOf(String.valueOf(temp.get(0).get("departure_time"))),String.valueOf(temp.get(temp.size()-1).get("arrival_station")).charAt(0));
				temp_store.put("trainNo", getTrainName(indexmappingRegularTrainsSB.indexOf(String.valueOf(temp.get(0).get("departure_station")).charAt(0)),indexmappingRegularTrainsSB.indexOf(String.valueOf(temp.get(temp.size()-1).get("arrival_station")).charAt(0)),depTimeOrginal));
				
				trainsList.add(temp_store);
				total_return_reg++;
	 			}
		 	return  sortTrainsByJourneyTime(trainsList);
		 		
		 	}
			 
		}
		 //northBound trains B->A
		 else
		 {
			 
		
	 	if(TicketType.equals("Express"))
	 	{
	 		//Both are express train
	 		if(this.indexmappingExpressTrains.indexOf(Departure)!= -1 && this.indexmappingExpressTrains.indexOf(Arrival)!= -1 )
	 		{
	 			int total_return =0;
	 			while(total_return < 5 )
	 			{
	 				ArrayList<HashMap<String,Object>> temp =  new ArrayList<HashMap<String,Object>>();
	 	 			HashMap<String,Object> temp_store = new HashMap<String,Object>();
	 			
	 			if(Time < 0)
	 			{
	 				break;
	 			}
	 			temp = lookAnyTrainsFinal(Departure,Arrival,Time,Dep_Date,Passanger_num,this.indexmappingExpressTrains,Connections_num,this.expressTrainsSchedule);	
	 			Time = 	get_available_time_from_station(indexmappingExpressTrainsSB.indexOf(Departure),Time+15,this.expressTrainsSchedule);
	 			temp_store.put("depature_station",temp.get(0).get("departure_station"));
	 			temp_store.put("arrival_station",temp.get(temp.size()-1).get("arrival_station"));
	 			temp_store.put("passengers",temp.get(0).get("passengers")); 
				temp_store.put("departure_time",temp.get(0).get("departure_time")); 
				temp_store.put("departure_date",temp.get(0).get("departure_date")); 
				int arrivalTime =  format_time(Integer.valueOf(String.valueOf(temp.get(temp.size()-1).get("departure_time"))),25);
				temp_store.put("arrival_time",arrivalTime); 
				
				double price =  ticketPriceService.ticketPriceBetweenStations(Departure, Arrival,TicketType);
				temp_store.put("price",String.valueOf(price));
				temp_store.put("journeyTime",Integer.valueOf(String.valueOf(temp.get(temp.size()-1).get("departure_time")))-Integer.valueOf(String.valueOf(temp.get(0).get("departure_time")))+25); // journey time : remaining
				temp_store.put("trainType","NB");  
				int depTimeOrginal = getTrainIndex(String.valueOf(temp.get(0).get("departure_station")).charAt(0),"Express","NB",Integer.valueOf(String.valueOf(temp.get(0).get("departure_time"))),String.valueOf(temp.get(temp.size()-1).get("arrival_station")).charAt(0));
				
				temp_store.put("trainNo", getTrainName(indexmappingRegularTrainsSB.indexOf(String.valueOf(temp.get(0).get("departure_station")).charAt(0)),indexmappingRegularTrainsSB.indexOf(String.valueOf(temp.get(temp.size()-1).get("arrival_station")).charAt(0)),depTimeOrginal));
				
				trainsList.add(temp_store);
				total_return++;
	 			}
	 			}
	 		//Departure is express
	 		else if(this.indexmappingExpressTrains.indexOf(Departure)!= -1)
	 		{
	 			for(int i=this.indexmappingExpressTrains.size()-1;i> this.indexmappingExpressTrains.indexOf(Departure);i--)
				{
					//check if arrival station is immediately after express station found
					if(this.indexmappingExpressTrains.get(i) <= Arrival)
					{
						//Get train info for Express train connections
						lookAnyTrainsFinal(Departure,this.indexmappingExpressTrains.get(i),Time,Dep_Date,Passanger_num,this.indexmappingExpressTrains,Connections_num,this.expressTrainsSchedule);
						//Get train info for Express to Regular train connection
						//Change time to time from last reached station from above call.
						lookAnyTrainsFinal(this.indexmappingExpressTrains.get(i),Arrival,Time,Dep_Date,Passanger_num,this.indexmappingRegularTrains,Connections_num,this.regularTrainSchedule);
					}
				}
	 		}
	 		//Arrival is express
	 		else if(this.indexmappingExpressTrains.indexOf(Arrival)!= -1)
	 		{//Improve by iterating through expressinedex only
	 			for(int i=this.indexmappingRegularTrains.indexOf(Departure);i<this.indexmappingRegularTrains.size();i++)
				{
					if(this.indexmappingExpressTrains.contains(this.indexmappingRegularTrains.get(i)) && this.indexmappingRegularTrains.get(i)< Arrival)
					{
						//Get train from Regular to express
						lookAnyTrainsFinal(Departure,this.indexmappingExpressTrains.get(i),Time,Dep_Date,Passanger_num,this.indexmappingRegularTrains,Connections_num,this.regularTrainSchedule);
						//Get train from express to express
						lookAnyTrainsFinal(this.indexmappingExpressTrains.get(i),Arrival,Time,Dep_Date,Passanger_num,this.indexmappingExpressTrains,Connections_num,this.expressTrainsSchedule);
					}
				}
	 		}
	 		else
	 		{
	 			for(int i=this.indexmappingExpressTrains.size()-1;i> this.indexmappingExpressTrains.indexOf(Departure);i--)
				{
					//check if arrival station is immediately after express station found
					if(this.indexmappingExpressTrains.get(i) <= Arrival)
					{
						for(int j=this.indexmappingRegularTrains.indexOf(Departure);j<this.indexmappingRegularTrains.size();j++)
						{
							if(this.indexmappingExpressTrains.contains(this.indexmappingRegularTrains.get(j)) && this.indexmappingRegularTrains.get(j)< Arrival)
							{
								lookAnyTrainsFinal(Departure,this.indexmappingExpressTrains.get(i),Time,Dep_Date,Passanger_num,this.indexmappingRegularTrains,Connections_num,this.regularTrainSchedule);
								lookAnyTrainsFinal(this.indexmappingExpressTrains.get(i),this.indexmappingExpressTrains.get(j),Time,Dep_Date,Passanger_num,this.indexmappingExpressTrains,Connections_num,this.expressTrainsSchedule);
								lookAnyTrainsFinal(this.indexmappingExpressTrains.get(j),Arrival,Time,Dep_Date,Passanger_num,this.indexmappingRegularTrains,Connections_num,this.regularTrainSchedule);
								
								
							}
						}
					}
					}
	 		}
	 	}
	 	else if(TicketType.equals("Regular"))
	 	{
	 		
 			
 			int total_return =0;
 			while(total_return < 5 )
 			{
 				ArrayList<HashMap<String,Object>> temp =  new ArrayList<HashMap<String,Object>>();
 	 			HashMap<String,Object> temp_store = new HashMap<String,Object>();
 			
 			if(Time < 0)
 			{
 				break;
 			}
 			temp =  lookAnyTrainsFinal(Departure,Arrival,Time,Dep_Date,Passanger_num,this.indexmappingRegularTrains,Connections_num,this.regularTrainSchedule);
 			Time = 	get_available_time_from_station(indexmappingRegularTrains.indexOf(Departure),Time+15,this.regularTrainSchedule);
 			temp_store.put("depature_station",temp.get(0).get("departure_station"));
 			temp_store.put("arrival_station",temp.get(temp.size()-1).get("arrival_station"));
 			temp_store.put("passengers",temp.get(0).get("passengers")); 
			temp_store.put("departure_time",temp.get(0).get("departure_time")); 
			temp_store.put("departure_date",temp.get(0).get("departure_date")); 
			int arrivalTime =  format_time(Integer.valueOf(String.valueOf(temp.get(temp.size()-1).get("departure_time"))),5);
			temp_store.put("arrival_time",arrivalTime);  
			double price =  ticketPriceService.ticketPriceBetweenStations(Departure, Arrival,TicketType);
			temp_store.put("price",String.valueOf(price)); 
			temp_store.put("journeyTime",Integer.valueOf(String.valueOf(temp.get(temp.size()-1).get("departure_time")))-Integer.valueOf(String.valueOf(temp.get(0).get("departure_time")))+5); // journey time : remaining
			temp_store.put("trainType","NB"); 
			int depTimeOriginal = getTrainIndex(String.valueOf(temp.get(0).get("departure_station")).charAt(0),"Regular","NB",Integer.valueOf(String.valueOf(temp.get(0).get("departure_time"))),String.valueOf(temp.get(temp.size()-1).get("arrival_station")).charAt(0));
			
			temp_store.put("trainNo", getTrainName(indexmappingRegularTrainsSB.indexOf(String.valueOf(temp.get(0).get("departure_station")).charAt(0)),indexmappingRegularTrainsSB.indexOf(String.valueOf(temp.get(temp.size()-1).get("arrival_station")).charAt(0)),depTimeOriginal));
			
			trainsList.add(temp_store);
			total_return++;
 			}
 			
 	}
	 	else
	 	{
	 		int total_return =0;
	 		int total_return_reg = 0;
	 	
	 		if(Math.abs(this.indexmappingRegularTrains.indexOf(Departure)-this.indexmappingRegularTrains.indexOf(Arrival)) >= 5)
	 		{
	 			
	 			//Both are express train
		 		if(this.indexmappingExpressTrains.indexOf(Departure)!= -1 && this.indexmappingExpressTrains.indexOf(Arrival)!= -1 )
		 		{
		 			int TimeTemp = Time;
		 			while(total_return < 5 )
		 			{
		 				ArrayList<HashMap<String,Object>> temp =  new ArrayList<HashMap<String,Object>>();
		 	 			HashMap<String,Object> temp_store = new HashMap<String,Object>();
		 			
		 			if(TimeTemp < 0)
		 			{
		 				break;
		 			}
		 			temp=lookAnyTrainsFinal(Departure,Arrival,TimeTemp,Dep_Date,Passanger_num,this.indexmappingExpressTrains,Connections_num,this.expressTrainsSchedule);
		 			TimeTemp = 	get_available_time_from_station(indexmappingExpressTrains.indexOf(Departure),TimeTemp+15,this.expressTrainsSchedule);
		 			temp_store.put("depature_station",temp.get(0).get("departure_station"));
		 			temp_store.put("arrival_station",temp.get(temp.size()-1).get("arrival_station"));
		 			temp_store.put("passengers",temp.get(0).get("passengers")); 
					temp_store.put("departure_time",temp.get(0).get("departure_time")); 
					temp_store.put("departure_date",temp.get(0).get("departure_date")); 
					int arrivalTime =  format_time(Integer.valueOf(String.valueOf(temp.get(temp.size()-1).get("departure_time"))),25);
					temp_store.put("arrival_time",arrivalTime); 
					double price =  ticketPriceService.ticketPriceBetweenStations(Departure, Arrival,TicketType);
					temp_store.put("price",String.valueOf(price));
					temp_store.put("journeyTime",Integer.valueOf(String.valueOf(temp.get(temp.size()-1).get("departure_time")))-Integer.valueOf(String.valueOf(temp.get(0).get("departure_time")))+25); // journey time : remaining
					temp_store.put("trainType","NB"); 
					int depTimeOriginal = getTrainIndex(String.valueOf(temp.get(0).get("departure_station")).charAt(0),"Express","NB",Integer.valueOf(String.valueOf(temp.get(0).get("departure_time"))),String.valueOf(temp.get(temp.size()-1).get("arrival_station")).charAt(0));
					
					temp_store.put("trainNo", getTrainName(indexmappingRegularTrainsSB.indexOf(String.valueOf(temp.get(0).get("departure_station")).charAt(0)),indexmappingRegularTrainsSB.indexOf(String.valueOf(temp.get(temp.size()-1).get("arrival_station")).charAt(0)),depTimeOriginal));
					
					trainsList.add(temp_store);
					total_return++;
		 			}
		 		
		 			}
		 		//Departure is express
		 		/*else if(this.indexmappingExpressTrains.indexOf(Departure)!= -1)
		 		{
		 			for(int i=this.indexmappingExpressTrains.size();i> this.indexmappingExpressTrains.indexOf(Departure);i--)
					{
						//check if arrival station is immediately after express station found
						if(this.indexmappingExpressTrains.get(i) <= Arrival)
						{
							//Get train info for Express train connections
							lookAnyTrainsFinal(Departure,this.indexmappingExpressTrains.get(i),Time,Dep_Date,Passanger_num,this.indexmappingExpressTrains,Connections_num,this.expressTrainsSchedule);
							//Get train info for Express to Regular train connection
							lookAnyTrainsFinal(this.indexmappingExpressTrains.get(i),Arrival,Time,Dep_Date,Passanger_num,this.indexmappingRegularTrains,Connections_num,this.regularTrainSchedule);
						}
					}
		 		}
		 		//Arrival is express
		 		else
		 		{
		 			for(int i=this.indexmappingRegularTrains.indexOf(Departure);i<this.indexmappingRegularTrains.size();i++)
					{
						if(this.indexmappingExpressTrains.contains(this.indexmappingRegularTrains.get(i)) && this.indexmappingRegularTrains.get(i)< Arrival)
						{
							//Get train from Regular to express
							lookAnyTrainsFinal(Departure,this.indexmappingExpressTrains.get(i),Time,Dep_Date,Passanger_num,this.indexmappingRegularTrains,Connections_num,this.regularTrainSchedule);
							//Get train from express to express
							lookAnyTrainsFinal(this.indexmappingExpressTrains.get(i),Arrival,Time,Dep_Date,Passanger_num,this.indexmappingExpressTrains,Connections_num,this.expressTrainsSchedule);
						}
					}
		 		}*/
	 		}
	 		int TimeTemp2 = Time+15;
	 		while(total_return_reg < 5 )
 			{
 				ArrayList<HashMap<String,Object>> temp =  new ArrayList<HashMap<String,Object>>();
 	 			HashMap<String,Object> temp_store = new HashMap<String,Object>();
 			
	 			if(TimeTemp2 < 0)
	 			{
	 				break;
	 			}
		 		temp=lookAnyTrainsFinal(Departure,Arrival,TimeTemp2,Dep_Date,Passanger_num,this.indexmappingRegularTrains,Connections_num,this.regularTrainSchedule);
		 		TimeTemp2 = 	get_available_time_from_station(indexmappingRegularTrains.indexOf(Departure),TimeTemp2+15,this.regularTrainSchedule);
	 			temp_store.put("depature_station",temp.get(0).get("departure_station"));
	 			temp_store.put("arrival_station",temp.get(temp.size()-1).get("arrival_station"));
	 			temp_store.put("passengers",temp.get(0).get("passengers")); 
				temp_store.put("departure_time",temp.get(0).get("departure_time")); 
				temp_store.put("departure_date",temp.get(0).get("departure_date")); 
				int arrivalTime =  format_time(Integer.valueOf(String.valueOf(temp.get(temp.size()-1).get("departure_time"))),25);
				temp_store.put("arrival_time",arrivalTime); 
				double price =  ticketPriceService.ticketPriceBetweenStations(Departure, Arrival,TicketType);
				temp_store.put("price",String.valueOf(price));
				temp_store.put("journeyTime",Integer.valueOf(String.valueOf(temp.get(temp.size()-1).get("departure_time")))-Integer.valueOf(String.valueOf(temp.get(0).get("departure_time")))+25); // journey time : remaining
				temp_store.put("trainType","NB");  
				int depTimeOriginal = getTrainIndex(String.valueOf(temp.get(0).get("departure_station")).charAt(0),"Regular","NB",Integer.valueOf(String.valueOf(temp.get(0).get("departure_time"))),String.valueOf(temp.get(temp.size()-1).get("arrival_station")).charAt(0));
				temp_store.put("trainNo", getTrainName(indexmappingRegularTrainsSB.indexOf(String.valueOf(temp.get(0).get("departure_station")).charAt(0)),indexmappingRegularTrainsSB.indexOf(String.valueOf(temp.get(temp.size()-1).get("arrival_station")).charAt(0)),depTimeOriginal));
				trainsList.add(temp_store);
				total_return_reg++;
 			}
	 	}
	 	trainsList = sortTrainsByJourneyTime(trainsList);
		 }
		// get_json_data_for_hm( Departure, Arrival,trainSchedules,TicketType);
		 return trainsList;

	 }
	private int getTrainIndex(Character Departure, String ticketType,String bound,int time,Character arrival) {
		// TODO Auto-generated method stub
		if(ticketType.equals("Express"))
		{
			if(bound.equals("NB"))
			{
				int stationIndex =  this.indexmappingExpressTrains.indexOf(Departure);
				int finalStationIndex = this.indexmappingExpressTrains.indexOf(arrival);
				int diff = finalStationIndex - stationIndex;
				int departureTime = diff>1 ? (time - (diff*(25+3))) : (time - (diff*25));
				
				return departureTime;
			}
			else
			{
				int stationIndex =  this.indexmappingExpressTrainsSB.indexOf(Departure);
		
				int finalStationIndex = this.indexmappingExpressTrainsSB.indexOf(arrival);
				int diff = finalStationIndex - stationIndex;
				int departureTime = diff>1 ? (time - (diff*(25+3))) : (time - (diff*25));
				
				return departureTime;
			}
			
		}
		else
		{
			if(bound.equals("NB"))
			{
				int stationIndex =  this.indexmappingRegularTrains.indexOf(Departure);
				int finalStationIndex = this.indexmappingRegularTrains.indexOf(arrival);
				int diff = finalStationIndex - stationIndex;
				int departureTime = diff>1 ? (time - (diff*(5+3))) : (time - (diff*8));
				
				
				return departureTime;
			}
			else
			{
				int stationIndex =  this.indexmappingRegularTrainsSB.indexOf(Departure);
				int finalStationIndex = this.indexmappingRegularTrainsSB.indexOf(arrival);
				int diff = finalStationIndex - stationIndex;
				int departureTime = diff>1 ? (time - (diff*(5+3))) : (time - (diff*8));
				return departureTime;
			}
		}
		
	}
	public ArrayList<HashMap<String,Object>> lookAnyTrainsFinal(Character Departure,Character Arrival,Integer Time,String Dep_date,int Passanger_num,ArrayList<Character> indexMRTrains,int conn_num,int[][] schedular)
	{
		ArrayList<HashMap<String,Object>> hm_arr = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> hm = null;
		HashMap<Integer,Boolean> hm_visitied_stations = new HashMap<Integer,Boolean>();
		int departure_station_current_ind =indexMRTrains.indexOf(Departure);
		int arrival_station_current_ind= departure_station_current_ind +1;
		int current_depture_time=get_available_time_from_station(departure_station_current_ind,Time,schedular);
		int stationIndexRegularDeparture =  indexMRTrains.indexOf(Departure);
		int stationIndexRegularArrival =  indexMRTrains.indexOf(Arrival);
		int counter = 0;
		
		if(conn_num == 0)
		{
				while(departure_station_current_ind < arrival_station_current_ind && arrival_station_current_ind <= stationIndexRegularArrival)
				{
					
					if(is_connection_between_dep_arrival_available(departure_station_current_ind,arrival_station_current_ind,current_depture_time,Dep_date,Passanger_num))
					{
						hm = new HashMap<String,Object>();
						hm.put("departure_station",String.valueOf(indexMRTrains.get(departure_station_current_ind)));
						hm.put("arrival_station",String.valueOf(indexMRTrains.get(arrival_station_current_ind)));
						hm.put("departure_time",String.valueOf(current_depture_time));
						hm.put("departure_date",Dep_date);
						hm.put("passengers",String.valueOf(Passanger_num) );
						hm_arr.add(hm);
						departure_station_current_ind ++;
						arrival_station_current_ind ++;	
						if(arrival_station_current_ind >  stationIndexRegularArrival)
						{
							break;
						}
						current_depture_time = get_available_time_from_station(departure_station_current_ind,current_depture_time+5,schedular);
					}
					else
					{
						hm_arr.clear();
						departure_station_current_ind =indexMRTrains.indexOf(Departure);
						arrival_station_current_ind= departure_station_current_ind +1;
						current_depture_time =get_available_time_from_station(departure_station_current_ind,Time+15,schedular);
						
						if(current_depture_time == -1)
						{
							break;
						}		
					}
				}
				return hm_arr;
		}
		//upto 2 connection
		else
		{
			while( counter <= conn_num && departure_station_current_ind < arrival_station_current_ind && arrival_station_current_ind <= stationIndexRegularArrival && (departure_station_current_ind >= 0 && departure_station_current_ind >= stationIndexRegularDeparture) )
			{
				if(is_connection_between_dep_arrival_available(departure_station_current_ind,arrival_station_current_ind,current_depture_time,Dep_date, Passanger_num))
				{
					hm = new HashMap<String,Object>();
					hm.put("departure_station",String.valueOf(indexMRTrains.get(departure_station_current_ind)));
					hm.put("arrival_station",String.valueOf(indexMRTrains.get(arrival_station_current_ind)));
					hm.put("departure_time",String.valueOf(current_depture_time));
					hm.put("departure_date",Dep_date);
					hm.put("passengers",String.valueOf(Passanger_num));
					hm_arr.add(hm);
					departure_station_current_ind ++;
					arrival_station_current_ind ++;	
					
					hm_visitied_stations.put(departure_station_current_ind,false);
					if(arrival_station_current_ind >  stationIndexRegularArrival)
					{
						break;
					}
					current_depture_time = get_available_time_from_station(departure_station_current_ind,current_depture_time+5,schedular);
					
				}
				else
				{
					
					current_depture_time = get_available_time_from_station(departure_station_current_ind,current_depture_time+15,schedular);
					if(current_depture_time == -1)
					{
						hm_visitied_stations.replace(departure_station_current_ind, false);
					if(hm_arr.size()!=0) {
						current_depture_time = Integer.valueOf(String.valueOf(hm_arr.get(hm_arr.size()-1).get("departure_time")));
						hm_arr.remove(hm_arr.size()-1);
						}
						departure_station_current_ind --;
						arrival_station_current_ind --;
						counter --;
					}
					if(departure_station_current_ind != stationIndexRegularDeparture  && !hm_visitied_stations.get(departure_station_current_ind))
					{
						hm_visitied_stations.put(departure_station_current_ind,true);
						counter ++;
					}
					
				}
					
			}
			
		}
		return hm_arr;
	}
	public int get_available_time_from_station(int index,int time,int[][] schedule)
	{
		for(int j=0;j<schedule[index].length;j++)
		{
			if(schedule[index][j] >= time)
			{
			 return schedule[index][j] ;
			}
		}
		return -1;
	}
	
	
	public Boolean is_connection_between_dep_arrival_available(int fromStation,int toStation,Integer time_current, String journeyDate, int passengers)
	{
		String trainNo = getTrainName(fromStation, toStation, time_current);

		// call query to get back Boolean for connection
		int bookedTickets = journeyRepository.findByJourneyTrainIdAndJourneyDate(trainNo, journeyDate, fromStation,
				toStation);
		int totalTrainSeats = trainRepository.findTrainByTrainNo(trainNo).getCapacity();
		if (bookedTickets > (totalTrainSeats - passengers)) {
			return false;
		} else {
			return true;
		}
	}
	private String getTrainName(int fromStation, int toStation, Integer time_current) {
		
		return (fromStation > toStation ? "NB" : "SB").concat(time_current < 1000 ? ("0".concat(String.valueOf(time_current)))
				: String.valueOf(time_current));
	}
	public ArrayList<HashMap<String,Object>> sortTrainsByJourneyTime(ArrayList<HashMap<String,Object>> trainsList)
	{
		Collections.sort(trainsList, new MyComparator("arrival_time"));
		return  new ArrayList<HashMap<String,Object>>(trainsList.subList(0,5));	

	}
	public int format_time(int time,int offset)
	{
		int total_time = time+offset;
		
		if(total_time % 100 > 60 )
		{
			total_time -= offset;
		    int rem = total_time % 100;
		    total_time -= rem;
		    int off =  60-rem;
		    total_time +=100;
		    total_time += offset-off;
		}
		return total_time;
	}
	
	
	
//	public HashMap<Character,ArrayList<Integer>> lookExpressTrains(Character Departure,Character Arrival,Integer Time,String Dep_date,int Passanger_num,ArrayList<Character> indexMExTrains)
//	{
//		
//		HashMap<Character,ArrayList<Integer>> hm = new HashMap<Character,ArrayList<Integer>>();
//		
//		int stationIndexExpressDeparture =  indexMExTrains.indexOf(Departure);
//		//int stationIndexRegularDeparture = this.indexmappingRegularTrains.indexOf(Departure);
//		int stationIndexExpressArrival =  indexMExTrains.indexOf(Arrival);
//		//int stationIndexRegularArrival =  this.indexmappingExpressTrains.indexOf(Arrival);
//		
//		for(int i=stationIndexExpressDeparture;i<stationIndexExpressArrival;i++)
//		{
//			hm.put(indexMExTrains.get(i),new ArrayList<Integer>());
//			for(int j=0;j<this.expressTrainsSchedule[i].length;j++)
//			{
//				if(this.expressTrainsSchedule[i][j] >= Time)
//				{
//					//check if the seat is available in particular train.if yes then add it to the map. keep counter for 5 
//				hm.get(indexMExTrains.get(i)).add(this.expressTrainsSchedule[i][j]);
//				}
//			}
//			
//		}
//		//returns the hm for key as departure station and intermediate stations and value as depature timings.
//		return hm;
//	}
//	
//	public HashMap<Character,ArrayList<Integer>> lookRegularTrains(Character Departure,Character Arrival,Integer Time,String Dep_date,int Passanger_num,ArrayList<Character> indexMRTrains,int conn_num)
//	{
//		
//		HashMap<Character,ArrayList<Integer>> hm = new HashMap<Character,ArrayList<Integer>>();
//		int stationIndexRegularDeparture =  indexMRTrains.indexOf(Departure);
//		Character arrival_station_current= indexMRTrains.get(stationIndexRegularDeparture +1);
//		int stationIndexRegularArrival =  indexMRTrains.indexOf(Arrival);
//		Boolean check=true;
//		
//		for(int i=stationIndexRegularDeparture;i<stationIndexRegularArrival;i++)
//		{
//			hm.put(indexMRTrains.get(i),new ArrayList<Integer>());
//			for(int j=0;j<this.regularTrainSchedule[i].length;j++)
//			{
//				if(this.regularTrainSchedule[i][j] >= Time)
//				{
//					while(check || arrival_station_current != Arrival )
//					{
//						if(check == false)
//						{
//							
//						}
//					}
//					//check if the seat is available in particular train.if yet then add it to the map. keep counter for 5
//					//Query should return a station nearest to the arrival station uptil which same trian is available 
//					
//				hm.get(indexMRTrains.get(i)).add(this.regularTrainSchedule[i][j]);
//				}
//			}
//		}
//		return hm;
//	}
//	public void serchTrainsHelper(Character Departure,Character Arrival,Integer Time)
	//{
//		HashMap<Character,ArrayList<Integer>> trainschedules =  new HashMap<Character,ArrayList<Integer>>();
//	
//		//Tp get express trains schedules
//		if(this.ExpressStations.contains(Departure) && this.ExpressStations.contains(Arrival))
//		{
//			lookExpressTrains(Departure,Arrival,Time);
//			
//		}
//		// To find closest express train station 
//		else if(Math.abs(this.indexmappingRegularTrains.indexOf(Departure)-this.indexmappingRegularTrains.indexOf(Arrival)) > 5)
//		{
//			//if Departure station is already express station then find closest express station to arrival.
//			if(this.ExpressStations.contains(Departure))
//			{
//				for(int i=this.indexmappingExpressTrains.size();i> this.indexmappingExpressTrains.indexOf(Departure);i--)
//				{
//					//check if arrival station is immediately after express station found
//					if(this.indexmappingExpressTrains.get(i) <= Arrival)
//					{
//						//This finds the expressTrains connections
//						HashMap<Character,ArrayList<Integer>> hm_e = lookExpressTrains(Departure,this.indexmappingExpressTrains.get(i),Time);
//						//This finds all other connections.
//						HashMap<Character,ArrayList<Integer>> hm_r = lookAllTrains(this.indexmappingExpressTrains.get(i),Arrival,Time); 
//						trainschedules.putAll(hm_e);
//						trainschedules.putAll(hm_r);
//					}
//				}
//			}
//			else if(this.ExpressStations.contains(Arrival))
//			{
//				for(int i=this.indexmappingRegularTrains.indexOf(Departure);i<this.indexmappingRegularTrains.size();i++)
//				{
//					if(this.indexmappingExpressTrains.contains(this.indexmappingRegularTrains.get(i)))
//					{
//						HashMap<Character,ArrayList<Integer>> hm_e = lookExpressTrains(this.indexmappingRegularTrains.get(i),Arrival,Time);
//						HashMap<Character,ArrayList<Integer>> hm_r = lookAllTrains(Departure,this.indexmappingRegularTrains.get(i),Time); 
//						trainschedules.putAll(hm_e);
//						trainschedules.putAll(hm_r);
//					}
//				}
//			}
//		}
//		lookAllTrains(Departure,Arrival,Time,);
//		
//	}
	
	

}
