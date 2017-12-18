package edu.sjsu.cmpe275.cusr.model;

public class SearchTrain {
	
	private Character departure_station;

	private Character arrival_station;
	private Integer dep_time;
	private String ticket_type;
	private int conn_num;
	private  Boolean round_tr;
	private int pass_num;
	private Boolean exact_time;
	private String dep_date;
	
	public SearchTrain() {
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
	public Boolean getExact_time() {
		return exact_time;
	}
	public void setExact_time(Boolean exact_time) {
		this.exact_time = exact_time;
	}
	public String getDep_date() {
		return dep_date;
	}
	public void setDep_date(String dep_date) {
		this.dep_date = dep_date;
	}
	
}
