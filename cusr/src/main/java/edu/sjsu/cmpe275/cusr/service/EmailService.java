package edu.sjsu.cmpe275.cusr.service;

import org.springframework.beans.factory.annotation.Autowired;

import edu.sjsu.cmpe275.cusr.model.Ticket;
import edu.sjsu.cmpe275.cusr.repository.EmailRepository;

public class EmailService {
	
	@Autowired 
	EmailRepository emailRepository;

	public long findUserbyTicketId(Long id)
	{
		Ticket ticket= emailRepository.findOne(id);
		
		return ticket.getUserId();
	}

}
