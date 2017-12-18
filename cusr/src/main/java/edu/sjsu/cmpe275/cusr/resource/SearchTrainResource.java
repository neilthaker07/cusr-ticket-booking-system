package edu.sjsu.cmpe275.cusr.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.cusr.model.SearchTrain;
import edu.sjsu.cmpe275.cusr.service.SearchTrainService;

@RestController
@CrossOrigin
public class SearchTrainResource {
	
	@Autowired
	SearchTrainService searchTrainService;
	
	@RequestMapping(method=RequestMethod.POST, value="/search")
	public String saveTicket(@RequestBody SearchTrain searchTrain)
	{
		searchTrainService.findTrain(searchTrain.getDeparture_station(), searchTrain.getArrival_station(), searchTrain.getDep_time(), searchTrain.getTicket_type(), searchTrain.getConn_num(), searchTrain.getRound_tr() , searchTrain.getPass_num(), searchTrain.getDep_date());
		return "train";
	}
}