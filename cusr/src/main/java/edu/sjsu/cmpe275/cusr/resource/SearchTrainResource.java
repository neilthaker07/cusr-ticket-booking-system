package edu.sjsu.cmpe275.cusr.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Object> searchTrain(@RequestBody SearchTrain searchTrain)
	{
		ArrayList<HashMap<String,String>> fiveResults = searchTrainService.findTrain(searchTrain);
		
		List<JSONObject> entities = new ArrayList<JSONObject>();
        JSONObject entity = new JSONObject();
        entity.put("fiveTrains", fiveResults);
        entities.add(entity);
        
		return new ResponseEntity<Object>(entities, HttpStatus.OK);
	}
}
