package edu.sjsu.cmpe275.cusr.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.HashSet;

public class SearchHelper {
	 private Hashtable<Integer,String> NorthBoundRegularTrains;
	 private Hashtable<Integer,String> NorthBoundExpressTrains;
	 private Hashtable<Integer,String> SouthBoundRegularTrains;
	 private Hashtable<Integer,String> SouthBoundExpressTrains;
	 private HashSet<Character> ExpressStations;
	 
	
	 // public SearchHelper(int RegularTrains,int ExpressTrains)
	 // {
		//  this.ExpressStations =  new HashSet<Character>();
		//  this.NorthBoundRegularTrains = new Hashtable<Integer,String>(RegularTrains);
		//  this.NorthBoundExpressTrains = new Hashtable<Integer,String>(ExpressTrains);
		//  this.SouthBoundRegularTrains = new Hashtable<Integer,String>(RegularTrains);
		//  this.SouthBoundExpressTrains = new Hashtable<Integer,String>(ExpressTrains); 
	 // }
	 // public void AddExpressStations(Character c)
	 // {
		//  this.ExpressStations.add(c);
	 // }
	 // public  void AddNorthBoundRegularTrains(Integer Time,String Name)
	 // {
		//  this.NorthBoundRegularTrains.put(Time,Name);
	 // }
	 // public  void AddNorthBoundExpressTrains(Integer Time,String Name)
	 // {
		//  this.NorthBoundExpressTrains.put(Time,Name);
	 // }
	 // public  void AddSouthBoundRegularTrains(Integer Time,String Name)
	 // {
		//  this.SouthBoundRegularTrains.put(Time,Name);
	 // }
	 // public  void AddSouthBoundExpressTrains(Integer Time,String Name)
	 // {
		//  this.SouthBoundExpressTrains.put(Time,Name);
	 // }
	 
	 // public void search(Character Departure, Character Arrival,String time)
	 // {
		// if(Departure < Arrival)
		// {
		// 	NorthBoundSearch(Departure,Arrival,time);
		// }
		// else
		// {
		// 	SouthBoundSearch(Departure,Arrival);
		// }
	 // }
	 

	 // public Hashtable<String,String> NorthBoundSearch(Character Departure,Character Arrival,String time)
	 // {
		//  if(this.ExpressStations.contains(Departure) && this.ExpressStations.contains(Arrival))
		//  {
		// 	 GetExpressTrainsDetails(time);
		//  }
	 // }
	 
	 // public Hashtable<String,String> SouthBoundSearch(Character Departure,Character Arrival)
	 // {
		 
	 // }
	 // public void GetExpressTrainDetails(String Time)
	 // {
	 
	 
	 // }
}
