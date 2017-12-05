package edu.sjsu.cmpe275.cusr.model;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;


public class SearchTrain {
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
	/*
	to get index of express trains in ExpressTrainsSchedule array.
	*/
	private ArrayList<Character> indexmappingExpressTrains;

	public SearchTrain(int regularTrainCount,int expressTrainCount)
	{
		this.ExpressStations =  new HashSet<Character>();
		this.regularTrainSchedule = new int [regularTrainCount][regularTrainCount];
		this.expressTrainsSchedule = new int[expressTrainCount][expressTrainCount];
		this.indexmappingRegularTrains = new ArrayList<Character>();
		this.indexmappingExpressTrains =  new ArrayList<Character>();
		this.populate_IndexMapping();
	}
	public void populate_IndexMapping()
	{
		indexmappingExpressTrains.addAll(Arrays.asList('A','F','K','P','U','Z'));
		indexmappingRegularTrains.addAll(Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'));
		
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
	 public void SearchTrain(Character Departure,Character Arrival,Integer Time,String TicketType,int Connections_num, String RoundTrip,int Passanger_num)
	 {

	 }
	public void SerchTrainsHelper(Character Departure,Character Arrival,Integer Time)
	{
		HashMap<Character,ArrayList<Integer>> trainschedules =  new HashMap<Character,ArrayList<Integer>>();
		String Bound = "";
		if(Departure < Arrival)
		{
			Bound = "NB";	
		}
		else
		{
			Bound = "SB";
		}
		//Tp get express trains schedules
		if(this.ExpressStations.contains(Departure) && this.ExpressStations.contains(Arrival))
		{
			lookExpressTrains(Departure,Arrival,Time);
			
		}
		// To find closest express train station 
		else if(Math.abs(this.indexmappingRegularTrains.indexOf(Departure)-this.indexmappingRegularTrains.indexOf(Arrival)) > 5)
		{
			//if Departure station is already express station then find closest express station to arrival.
			if(this.ExpressStations.contains(Departure))
			{
				for(int i=this.indexmappingExpressTrains.size();i> this.indexmappingExpressTrains.indexOf(Departure);i--)
				{
					//check if arrival station is immediately after express station found
					if(this.indexmappingExpressTrains.get(i) <= Arrival)
					{
						//This finds the expressTrains connections
						HashMap<Character,ArrayList<Integer>> hm_e = lookExpressTrains(Departure,this.indexmappingExpressTrains.get(i),Time);
						//This finds all other connections.
						HashMap<Character,ArrayList<Integer>> hm_r = lookAllTrains(this.indexmappingExpressTrains.get(i),Arrival,Time); 
						trainschedules.putAll(hm_e);
						trainschedules.putAll(hm_r);
					}
				}
			}
			else if(this.ExpressStations.contains(Arrival))
			{
				for(int i=this.indexmappingRegularTrains.indexOf(Departure);i<this.indexmappingRegularTrains.size();i++)
				{
					if(this.indexmappingExpressTrains.contains(this.indexmappingRegularTrains.get(i)))
					{
						HashMap<Character,ArrayList<Integer>> hm_e = lookExpressTrains(this.indexmappingRegularTrains.get(i),Arrival,Time);
						HashMap<Character,ArrayList<Integer>> hm_r = lookAllTrains(Departure,this.indexmappingRegularTrains.get(i),Time); 
						trainschedules.putAll(hm_e);
						trainschedules.putAll(hm_r);
					}
				}
			}
		}
		lookAllTrains(Departure,Arrival,Time);
		
	}
	public HashMap<Character,ArrayList<Integer>> lookExpressTrains(Character Departure,Character Arrival,Integer Time)
	{
		
		HashMap<Character,ArrayList<Integer>> hm = new HashMap<Character,ArrayList<Integer>>();
		int stationIndexExpressDeparture =  this.indexmappingExpressTrains.indexOf(Departure);
		//int stationIndexRegularDeparture = this.indexmappingRegularTrains.indexOf(Departure);
		int stationIndexExpressArrival =  this.indexmappingExpressTrains.indexOf(Arrival);
		//int stationIndexRegularArrival =  this.indexmappingExpressTrains.indexOf(Arrival);
		
		for(int i=stationIndexExpressDeparture;i<=stationIndexExpressArrival;i++)
		{
			hm.put(this.indexmappingExpressTrains.get(i),new ArrayList<Integer>());
			for(int j=0;j<this.expressTrainsSchedule[i].length;j++)
			{
				if(this.expressTrainsSchedule[i][j] >= Time)
				{
				hm.get(this.indexmappingExpressTrains.get(i)).add(this.expressTrainsSchedule[i][j]);
				}
			}
		}
		return hm;
	}
	
	public HashMap<Character,ArrayList<Integer>> lookAllTrains(Character Departure,Character Arrival,Integer Time)
	{
		
		HashMap<Character,ArrayList<Integer>> hm = new HashMap<Character,ArrayList<Integer>>();
		int stationIndexRegularDeparture =  this.indexmappingRegularTrains.indexOf(Departure);
		//int stationIndexRegularDeparture = this.indexmappingRegularTrains.indexOf(Departure);
		int stationIndexRegularArrival =  this.indexmappingRegularTrains.indexOf(Arrival);
		//int stationIndexRegularArrival =  this.indexmappingExpressTrains.indexOf(Arrival);
		
		for(int i=stationIndexRegularDeparture;i<=stationIndexRegularArrival;i++)
		{
			hm.put(this.indexmappingRegularTrains.get(i),new ArrayList<Integer>());
			for(int j=0;j<this.regularTrainSchedule[i].length;j++)
			{
				if(this.regularTrainSchedule[i][j] >= Time)
				{
				hm.get(this.indexmappingRegularTrains.get(i)).add(this.regularTrainSchedule[i][j]);
				}
			}
		}
		return hm;
	}
	
	
	
	
	
	
}
