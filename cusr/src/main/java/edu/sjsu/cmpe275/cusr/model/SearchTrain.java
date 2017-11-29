package edu.sjsu.cmpe275.cusr.model;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;


public class SearchTrain {
	private HashSet<Character> ExpressStations;
	private int[][] regularTrainSchedule;
	private int[][] expressTrainsSchedule;
	private ArrayList<Character> indexmappingRegularTrains;
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
	public void SerchTrains(Character Departure,Character Arrival,Integer Time)
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
		if(this.ExpressStations.contains(Departure) && this.ExpressStations.contains(Arrival))
		{
			lookExpressTrains(Departure,Arrival,Time);
			
		}
		else if(Math.abs(this.indexmappingRegularTrains.indexOf(Departure)-this.indexmappingRegularTrains.indexOf(Arrival)) > 5)
		{
			if(this.ExpressStations.contains(Departure))
			{
				for(int i=this.indexmappingExpressTrains.size();i> this.indexmappingExpressTrains.indexOf(Departure);i--)
				{
					if(this.indexmappingExpressTrains.get(i) <= Arrival)
					{
						HashMap<Character,ArrayList<Integer>> hm_e = lookExpressTrains(Departure,this.indexmappingExpressTrains.get(i),Time);
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
