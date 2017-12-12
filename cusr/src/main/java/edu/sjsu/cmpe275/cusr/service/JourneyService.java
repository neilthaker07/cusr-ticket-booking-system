package edu.sjsu.cmpe275.cusr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.sjsu.cmpe275.cusr.model.JourneyDetails;
import edu.sjsu.cmpe275.cusr.repository.JourneyRepository;

@Component
public class JourneyService {
	
	@Autowired 
	JourneyRepository journeyRepository;

	/**
	 * Save journey details 
	 * @param journey
	 */
	public void saveJourney(JourneyDetails journey)
	{
		journeyRepository.save(journey);
	}
}
