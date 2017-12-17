package edu.sjsu.cmpe275.cusr.resource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import edu.sjsu.cmpe275.cusr.model.SearchTrain;

@RestController
@CrossOrigin
public class SearchResource {

	@RequestMapping(method=RequestMethod.POST, value="/search")
	//
	public void search(@RequestBody SearchTrain search)
	{
		
//	public void search(@RequestParam String departure_station, @RequestParam String arrival_station,@RequestParam int dep_time, @RequestParam String ticket_type, @RequestParam int conn_num,  @RequestParam Boolean round_tr, @RequestParam int pass_num,@RequestParam bool exact_time,@RequestParam String dep_date){
//		SearchTrain train =  new SearchTrain(26,5);
				//train.searchTrain(departure_station.charAt(0),arrival_station.charAt(0),dep_time,ticket_type,conn_num,round_tr,pass_num);
	
	}
}
