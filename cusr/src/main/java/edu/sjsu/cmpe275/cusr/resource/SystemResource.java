package edu.sjsu.cmpe275.cusr.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.cusr.service.SystemService;

@RestController
@CrossOrigin
public class SystemResource {
	
	@Autowired
	SystemService systemService;
	
	@RequestMapping(method=RequestMethod.POST, value="/resetSystem")
	public String resetSystem(@RequestParam int trainCapacity){
		systemService.resetSystem(trainCapacity);
		return "SUCCESS";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/systemReport")
	public double systemReport(@RequestParam String trainNo, @RequestParam String date)
	{
		

		System.out.println("::::::::::: trainNo :::::::::: " +trainNo  + "::::::::" + date) ;
		double value= systemService.systemReport(trainNo, date);
		return value;
		
	}

}
