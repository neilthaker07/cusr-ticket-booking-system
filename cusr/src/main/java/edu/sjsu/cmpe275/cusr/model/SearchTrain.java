package edu.sjsu.cmpe275.cusr.model;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
//import org.java.simple.JSONArray;



public class SearchTrain {
	
	private Character departure_station;


	private ArrayList<ArrayList<HashMap<String,String>>> searchedTrains;
	private Character arrival_station;
	private Integer dep_time;
	private String ticket_type;
	private int conn_num;
	private  Boolean round_tr;
	private int pass_num;
	private Boolean exact_time;
	private String dep_date;
	
	//Contains express trains stattions
	private HashSet<Character> ExpressStations;
	/*Contains schedule for each regular train
	eg: regularTrain[0] = [915,930,945..] where 0=A
	*/
	private int[][] regularTrainSchedule;
	/* contains scheule for express trains
	eg:expressTrainsSchedule[0]=[9,10,11..] where 0=A;
	*/
	private int[][] expressTrainsSchedule;
	/*
	to get index of Regular trains in regularTrainsSchedule array.
	*/
	private ArrayList<Character> indexmappingRegularTrains;
	private ArrayList<Character> indexmappingRegularTrainsSB;
	/*
	to get index of express trains in ExpressTrainsSchedule array.
	*/
	private ArrayList<Character> indexmappingExpressTrains;
	private ArrayList<Character> indexmappingExpressTrainsSB;
	public SearchTrain()
	{
		
	}
	public SearchTrain(int regularTrainCount,int expressTrainCount)
	{
		this.ExpressStations =  new HashSet<Character>();
		this.regularTrainSchedule = new int [regularTrainCount][60];
		this.expressTrainsSchedule = new int[expressTrainCount][60];
		this.indexmappingRegularTrains = new ArrayList<Character>();
		this.indexmappingExpressTrainsSB = new ArrayList<Character>();
		this.indexmappingExpressTrains =  new ArrayList<Character>();
		this.indexmappingRegularTrainsSB = new ArrayList<Character>();
		this.searchedTrains = new ArrayList<ArrayList<HashMap<String,String>>>();
		
		this.populate_IndexMapping();
		this.regularTrainSchedule[0]= new int [] {915,930,1000};
		this.regularTrainSchedule[1] = new int[] {923,938,1008};
		this.regularTrainSchedule[2]= new int[] {931,946,1016};
		this.regularTrainSchedule[3]=new int [] {939,954,1024};
		this.expressTrainsSchedule[0]= new int [] {900,1000,1100};
		this.expressTrainsSchedule[1]=new int[] {908,1008,1108};
		this.expressTrainsSchedule[2]= new int[]{916,1016,1116};

		
	}
	public  SearchTrain(Character Departure,Character Arrival,int Time,String TicketType,int Connections_num, Boolean RoundTrip,int Passanger_num)
	{
		
	}
	public Character getDeparture_station() {
		return departure_station;
	}
	public void setDeparture_station(Character departure_station) {
		this.departure_station = departure_station;
	}
	public Character getArrival_station() {
		return arrival_station;
	}
	public void setArrival_station(Character arrival_station) {
		this.arrival_station = arrival_station;
	}
	public Integer getDep_time() {
		return dep_time;
	}
	public void setDep_time(Integer dep_time) {
		this.dep_time = dep_time;
	}
	public String getTicket_type() {
		return ticket_type;
	}
	public void setTicket_type(String ticket_type) {
		this.ticket_type = ticket_type;
	}
	public int getConn_num() {
		return conn_num;
	}
	public void setConn_num(int conn_num) {
		this.conn_num = conn_num;
	}
	public Boolean getRound_tr() {
		return round_tr;
	}
	public void setRound_tr(Boolean round_tr) {
		this.round_tr = round_tr;
	}
	public int getPass_num() {
		return pass_num;
	}
	public void setPass_num(int pass_num) {
		this.pass_num = pass_num;
	}
	
	public void populate_IndexMapping()
	{
		indexmappingExpressTrains.addAll(Arrays.asList('A','F','K','P','U','Z'));
		this.indexmappingExpressTrainsSB.addAll(Arrays.asList('Z','U','P','K','F','A'));
		indexmappingRegularTrains.addAll(Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'));
		this.indexmappingRegularTrainsSB.addAll(Arrays.asList('Z','Y','X','W','V','U','T','S','R','Q','P','O','N','M','L','K','J','I','H','G','F','E','D','C','B','A'));
		
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
	 public void findTrain(Character Departure,Character Arrival,Integer Time,String TicketType,int Connections_num, Boolean RoundTrip,int Passanger_num,String Dep_Date)
	 {
		 HashMap<Character,ArrayList<Integer>> trainSchedules =  new HashMap<Character,ArrayList<Integer>>();
		 //north bound trains. eg: A->B
		 if(Departure < Arrival)
			{
			 if(TicketType == "Express")
			 	{
			 		
			 	}
			 	else if(TicketType == "Regular")
			 	{
			 		
			 	}
			 	else
			 	{
			
			 	}
			}
		 //southBound trains B->A
		 else
		 {
			 
		 
	 	if(TicketType == "Express")
	 	{
	 		//Both are express train
	 		if(this.indexmappingExpressTrainsSB.indexOf(Departure)!= -1 && this.indexmappingExpressTrainsSB.indexOf(Arrival)!= -1 )
	 		{
	 			
	 			lookRegularTrainsFinal(Departure,Arrival,Time,Dep_Date,Passanger_num,this.indexmappingExpressTrainsSB,Connections_num);	
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
						lookRegularTrainsFinal(Departure,this.indexmappingExpressTrainsSB.get(i),Time,Dep_Date,Passanger_num,this.indexmappingExpressTrainsSB,Connections_num);
						//Get train info for Express to Regular train connection
						//Change time to time from last reached station from above call.
						lookRegularTrainsFinal(this.indexmappingExpressTrainsSB.get(i),Arrival,Time,Dep_Date,Passanger_num,this.indexmappingRegularTrainsSB,Connections_num);
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
						lookRegularTrainsFinal(Departure,this.indexmappingExpressTrainsSB.get(i),Time,Dep_Date,Passanger_num,this.indexmappingRegularTrainsSB,Connections_num);
						//Get train from express to express
						lookRegularTrainsFinal(this.indexmappingExpressTrainsSB.get(i),Arrival,Time,Dep_Date,Passanger_num,this.indexmappingExpressTrainsSB,Connections_num);
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
								lookRegularTrainsFinal(Departure,this.indexmappingExpressTrainsSB.get(i),Time,Dep_Date,Passanger_num,this.indexmappingExpressTrainsSB,Connections_num);
								lookRegularTrainsFinal(this.indexmappingExpressTrainsSB.get(i),this.indexmappingRegularTrainsSB.get(j),Time,Dep_Date,Passanger_num,this.indexmappingRegularTrainsSB,Connections_num);
								lookRegularTrainsFinal(this.indexmappingExpressTrainsSB.get(j),Arrival,Time,Dep_Date,Passanger_num,this.indexmappingRegularTrainsSB,Connections_num);
								
								
							}
						}
					}
					}
	 		}
	 	}
	 	else if(TicketType == "Regular")
	 	{
	 		lookRegularTrainsFinal(Departure,Arrival,Time,Dep_Date,Passanger_num,this.indexmappingRegularTrainsSB,Connections_num);
	 	}
	 	else
	 	{
	 		if(Math.abs(this.indexmappingRegularTrains.indexOf(Departure)-this.indexmappingRegularTrains.indexOf(Arrival)) > 5)
	 		{
	 			//Both are express train
		 		if(this.indexmappingExpressTrainsSB.indexOf(Departure)!= -1 && this.indexmappingExpressTrainsSB.indexOf(Arrival)!= -1 )
		 		{
		 			lookRegularTrainsFinal(Departure,Arrival,Time,Dep_Date,Passanger_num,this.indexmappingExpressTrainsSB,Connections_num);	
		 		}
		 		//Departure is express
		 		else if(this.indexmappingExpressTrainsSB.indexOf(Departure)!= -1)
		 		{
		 			for(int i=this.indexmappingExpressTrainsSB.size();i> this.indexmappingExpressTrainsSB.indexOf(Departure);i--)
					{
						//check if arrival station is immediately after express station found
						if(this.indexmappingExpressTrainsSB.get(i) <= Arrival)
						{
							//Get train info for Express train connections
							lookRegularTrainsFinal(Departure,this.indexmappingExpressTrainsSB.get(i),Time,Dep_Date,Passanger_num,this.indexmappingExpressTrainsSB,Connections_num);
							//Get train info for Express to Regular train connection
							lookRegularTrainsFinal(this.indexmappingExpressTrainsSB.get(i),Arrival,Time,Dep_Date,Passanger_num,this.indexmappingRegularTrainsSB,Connections_num);
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
							lookRegularTrainsFinal(Departure,this.indexmappingExpressTrainsSB.get(i),Time,Dep_Date,Passanger_num,this.indexmappingRegularTrainsSB,Connections_num);
							//Get train from express to express
							lookRegularTrainsFinal(this.indexmappingExpressTrainsSB.get(i),Arrival,Time,Dep_Date,Passanger_num,this.indexmappingExpressTrainsSB,Connections_num);
						}
					}
		 		}
	 		}
	 		lookRegularTrainsFinal(Departure,Arrival,Time,Dep_Date,Passanger_num,this.indexmappingRegularTrainsSB,Connections_num);	
	 	}
		 }
		 get_json_data_for_hm( Departure, Arrival,trainSchedules,TicketType);

	 }
	public ArrayList<HashMap<String,String>> lookRegularTrainsFinal(Character Departure,Character Arrival,Integer Time,String Dep_date,int Passanger_num,ArrayList<Character> indexMRTrains,int conn_num)
	{
		ArrayList<HashMap<String,String>> hm_arr = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> hm = new HashMap<String,String>();
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
					if(is_connection_between_dep_arrival_available(indexMRTrains.get(departure_station_current_ind),indexMRTrains.get(arrival_station_current_ind),current_depture_time,Dep_date))
					{
						hm.clear();
						hm.put("departure_station",String.valueOf(indexMRTrains.get(departure_station_current_ind)));
						hm.put("arrival_station",String.valueOf(indexMRTrains.get(arrival_station_current_ind)));
						hm.put("departure_time",String.valueOf(current_depture_time));
						hm.put("departure_date",Dep_date);
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
				if(is_connection_between_dep_arrival_available(indexMRTrains.get(departure_station_current_ind),indexMRTrains.get(arrival_station_current_ind),current_depture_time,Dep_date))
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
	public Boolean is_connection_between_dep_arrival_available(Character departure_station_current_ind,Character arrival_station_current_ind,Integer time_current, String Dep_date)
{
		return true;
	//call query to get back Boolean for connection 
}
	
	public void get_json_data_for_hm(Character Departure,Character Arrival, HashMap<Character,ArrayList<Integer>> hm,String TicketType )
	 {
		
		System.out.println(hm);
		
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
