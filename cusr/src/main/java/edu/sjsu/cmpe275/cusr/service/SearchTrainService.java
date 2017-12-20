package edu.sjsu.cmpe275.cusr.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.sjsu.cmpe275.cusr.model.SearchTrain;
import edu.sjsu.cmpe275.cusr.repository.JourneyRepository;

@Component
public class SearchTrainService {

	private ArrayList<ArrayList<HashMap<String,String>>> searchedTrains = new ArrayList<ArrayList<HashMap<String,String>>>();
	int  regularTrainCount=26;
	int expressTrainCount=5;
	
	//Contains express trains stations
	private HashSet<Character> ExpressStations =  new HashSet<Character>();
	/*Contains schedule for each regular train
	eg: regularTrain[0] = [915,930,945..] where 0=A
	*/
	private int[][] regularTrainSchedule = new int [regularTrainCount][60];
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
	
	
	
	@Autowired 
	JourneyRepository journeyRepository;
	@Autowired
	TicketPriceService ticketPriceService;
	
	
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
		this.searchedTrains = new ArrayList<ArrayList<HashMap<String,String>>>();
		
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
		int start_time=615;
		int next_train = 0;
		int offset =  8;
		 int[][] regularTrainSchedule = new int[26][45];

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
	
	}
	
	public void populate_IndexMapping()
	{
		indexmappingExpressTrains.addAll(Arrays.asList('Z','U','P','K','F','A'));
		this.indexmappingExpressTrainsSB.addAll(Arrays.asList('A','F','K','P','U','Z'));
		indexmappingRegularTrains.addAll(Arrays.asList('Z','Y','X','W','V','U','T','S','R','Q','P','O','N','M','L','K','J','I','H','G','F','E','D','C','B','A'));
		this.indexmappingRegularTrainsSB.addAll(Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'));
		
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
	 
	 public ArrayList<HashMap<String,String>> findTrain(SearchTrain searchTrain)
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
		 ArrayList<HashMap<String,String>> trainsList = new ArrayList<HashMap<String,String>>();
		 //south bound trains. eg: A->B
		 if(Departure < Arrival)
		{
			if (TicketType == "Express") {

			} else if (TicketType == "Regular") {

			} else {

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
	 			trainsList = lookRegularTrainsFinal(Departure,Arrival,Time,Dep_Date,Passanger_num,this.indexmappingExpressTrains,Connections_num);	
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
						lookRegularTrainsFinal(Departure,this.indexmappingExpressTrains.get(i),Time,Dep_Date,Passanger_num,this.indexmappingExpressTrains,Connections_num);
						//Get train info for Express to Regular train connection
						//Change time to time from last reached station from above call.
						lookRegularTrainsFinal(this.indexmappingExpressTrains.get(i),Arrival,Time,Dep_Date,Passanger_num,this.indexmappingRegularTrains,Connections_num);
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
						lookRegularTrainsFinal(Departure,this.indexmappingExpressTrains.get(i),Time,Dep_Date,Passanger_num,this.indexmappingRegularTrains,Connections_num);
						//Get train from express to express
						lookRegularTrainsFinal(this.indexmappingExpressTrains.get(i),Arrival,Time,Dep_Date,Passanger_num,this.indexmappingExpressTrains,Connections_num);
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
								lookRegularTrainsFinal(Departure,this.indexmappingExpressTrains.get(i),Time,Dep_Date,Passanger_num,this.indexmappingExpressTrains,Connections_num);
								lookRegularTrainsFinal(this.indexmappingExpressTrains.get(i),this.indexmappingRegularTrains.get(j),Time,Dep_Date,Passanger_num,this.indexmappingRegularTrains,Connections_num);
								lookRegularTrainsFinal(this.indexmappingExpressTrains.get(j),Arrival,Time,Dep_Date,Passanger_num,this.indexmappingRegularTrains,Connections_num);
								
								
							}
						}
					}
					}
	 		}
	 	}
	 	else if(TicketType.equals("Regular"))
	 	{
	 		System.out.println("Step 1 "+Departure.getClass().getName());
	 		
 			ArrayList<HashMap<String,String>> temp =  new ArrayList<HashMap<String,String>>();
 			HashMap<String,String> temp_store = new HashMap<String,String>();
 			temp =  lookRegularTrainsFinal(Departure,Arrival,Time,Dep_Date,Passanger_num,this.indexmappingRegularTrains,Connections_num);
 			
 			temp_store.put("depature_station",temp.get(0).get("departure_station"));
 			temp_store.put("arrival_station",temp.get(temp.size()-1).get("arrival_station"));
 			temp_store.put("passengers",temp.get(0).get("passengers")); // null
			temp_store.put("departure_time",temp.get(0).get("departure_time")); // null
			temp_store.put("departure_date",temp.get(0).get("departure_date")); // null
			temp_store.put("arrival_time",String.valueOf(Integer.valueOf(temp.get(temp.size()-1).get("departure_time"))+5)); // Calculate arrival time: remaining
			double price =  ticketPriceService.ticketPriceBetweenStations(Departure, Arrival,TicketType);
			temp_store.put("price",String.valueOf(price)); // price calculation : remaining
			temp_store.put("journeyTime",String.valueOf(Integer.valueOf(temp.get(temp.size()-1).get("departure_time"))-Integer.valueOf(temp.get(0).get("departure_time")))); // journey time : remaining
			temp_store.put("trainType","NB");  // train type : remaining
			
			trainsList.add(temp_store);
 		 
 			
 	}
	 	else
	 	{
	 		System.out.println("Step 1 "+Departure.getClass().getName());
	 		if(Math.abs(this.indexmappingRegularTrains.indexOf(Departure)-this.indexmappingRegularTrains.indexOf(Arrival)) > 5)
	 		{
	 			System.out.println("step 2 "+Departure instanceof String);
	 			System.out.println("step 2 2 "+Departure.getClass().getName());
	 			//Both are express train
		 		if(this.indexmappingExpressTrains.indexOf(Departure)!= -1 && this.indexmappingExpressTrains.indexOf(Arrival)!= -1 )
		 		{
		 			System.out.println("step 3 ");
		 			lookRegularTrainsFinal(Departure,Arrival,Time,Dep_Date,Passanger_num,this.indexmappingExpressTrains,Connections_num);	
		 		}
		 		//Departure is express
		 		else if(this.indexmappingExpressTrains.indexOf(Departure)!= -1)
		 		{
		 			for(int i=this.indexmappingExpressTrains.size();i> this.indexmappingExpressTrains.indexOf(Departure);i--)
					{
						//check if arrival station is immediately after express station found
						if(this.indexmappingExpressTrains.get(i) <= Arrival)
						{
							//Get train info for Express train connections
							lookRegularTrainsFinal(Departure,this.indexmappingExpressTrains.get(i),Time,Dep_Date,Passanger_num,this.indexmappingExpressTrains,Connections_num);
							//Get train info for Express to Regular train connection
							lookRegularTrainsFinal(this.indexmappingExpressTrains.get(i),Arrival,Time,Dep_Date,Passanger_num,this.indexmappingRegularTrains,Connections_num);
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
							lookRegularTrainsFinal(Departure,this.indexmappingExpressTrains.get(i),Time,Dep_Date,Passanger_num,this.indexmappingRegularTrains,Connections_num);
							//Get train from express to express
							lookRegularTrainsFinal(this.indexmappingExpressTrains.get(i),Arrival,Time,Dep_Date,Passanger_num,this.indexmappingExpressTrains,Connections_num);
						}
					}
		 		}
	 		}
	 		lookRegularTrainsFinal(Departure,Arrival,Time,Dep_Date,Passanger_num,this.indexmappingRegularTrains,Connections_num);	
	 	}
		 }
		 get_json_data_for_hm( Departure, Arrival,trainSchedules,TicketType);
		 return trainsList;

	 }
	public ArrayList<HashMap<String,String>> lookRegularTrainsFinal(Character Departure,Character Arrival,Integer Time,String Dep_date,int Passanger_num,ArrayList<Character> indexMRTrains,int conn_num)
	{
		ArrayList<HashMap<String,String>> hm_arr = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> hm = null;
		HashMap<Integer,Boolean> hm_visitied_stations = new HashMap<Integer,Boolean>();
		int departure_station_current_ind =indexMRTrains.indexOf(Departure);
		int arrival_station_current_ind= departure_station_current_ind +1;
		int current_depture_time=get_available_time_from_station(departure_station_current_ind,Time);
		int stationIndexRegularDeparture =  indexMRTrains.indexOf(Departure);
		int stationIndexRegularArrival =  indexMRTrains.indexOf(Arrival);
		int counter = 0;
		
		if(conn_num == 0)
		{
				while(departure_station_current_ind < arrival_station_current_ind && arrival_station_current_ind <= stationIndexRegularArrival)
				{
					if(is_connection_between_dep_arrival_available(departure_station_current_ind,arrival_station_current_ind,current_depture_time,Dep_date))
					{
						hm = new HashMap<String,String>();
						hm.put("departure_station",String.valueOf(indexMRTrains.get(departure_station_current_ind)));
						hm.put("arrival_station",String.valueOf(indexMRTrains.get(arrival_station_current_ind)));
						hm.put("departure_time",String.valueOf(current_depture_time));
						hm.put("departure_date",Dep_date);
						hm.put("passengers",String.valueOf(Passanger_num));
						hm.put("arrival_time",String.valueOf(current_depture_time)); // Calculate arrival time: remaining
						hm.put("price",String.valueOf(Passanger_num)); // price calculation : remaining
						hm.put("journeyTime",String.valueOf(current_depture_time)); // journey time : remaining
						hm.put("trainType","NB");  // train type : remaining
						
						hm_arr.add(hm);
						departure_station_current_ind ++;
						arrival_station_current_ind ++;	
						current_depture_time = get_available_time_from_station(departure_station_current_ind,current_depture_time+5);
					}
					else
					{
						hm_arr.clear();
						departure_station_current_ind =indexMRTrains.indexOf(Departure);
						arrival_station_current_ind= departure_station_current_ind +1;
						current_depture_time =get_available_time_from_station(departure_station_current_ind,Time+15);
						
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
				if(is_connection_between_dep_arrival_available(departure_station_current_ind,arrival_station_current_ind,current_depture_time,Dep_date))
				{
					hm.clear();
					hm.put("departure_station",String.valueOf(indexMRTrains.get(departure_station_current_ind)));
					hm.put("arrival_station",String.valueOf(indexMRTrains.get(arrival_station_current_ind)));
					hm.put("departure_time",String.valueOf(current_depture_time));
					hm.put("departure_date",Dep_date);
					hm_arr.add(hm);
					departure_station_current_ind ++;
					arrival_station_current_ind ++;	
					hm_visitied_stations.put(departure_station_current_ind,false);
					current_depture_time = get_available_time_from_station(departure_station_current_ind,current_depture_time+5);
					
				}
				else
				{
					
					current_depture_time = get_available_time_from_station(departure_station_current_ind,current_depture_time+15);
					if(current_depture_time == -1)
					{
						hm_visitied_stations.replace(departure_station_current_ind, false);
					if(hm_arr.size()!=0) {
						current_depture_time = Integer.valueOf(hm_arr.get(hm_arr.size()-1).get("departure_time"));
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
	public int get_available_time_from_station(int index,int time)
	{
		for(int j=0;j<this.regularTrainSchedule[index].length;j++)
		{
			if(this.regularTrainSchedule[index][j] >= time)
			{
			 return this.regularTrainSchedule[index][j] ;
			}
		}
		return -1;
	}
	public Boolean is_connection_between_dep_arrival_available(int fromStation,int toStation,Integer time_current, String journeyDate)
	{	
		//call query to get back Boolean for connection 
		int bookedTickets = journeyRepository.findByJourneyTrainIdAndJourneyDate((long)1000, journeyDate, fromStation, toStation);
		int passengers = 3; // dynamic
		int totalTrainSeats = 50; // dynamic
		if(bookedTickets > (totalTrainSeats - passengers))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public void get_json_data_for_hm(Character Departure,Character Arrival, HashMap<Character,ArrayList<Integer>> hm,String TicketType )
	 {
		System.out.println("NEIL TESTING");
		System.out.println(hm);
		System.out.println("NEIL TESTING");
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
