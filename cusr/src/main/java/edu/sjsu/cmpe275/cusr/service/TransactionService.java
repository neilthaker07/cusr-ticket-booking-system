package edu.sjsu.cmpe275.cusr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.sjsu.cmpe275.cusr.model.Transaction;
import edu.sjsu.cmpe275.cusr.repository.TransactionRepository;

@Component
public class TransactionService {
	
	@Autowired 
	TransactionRepository transactionRepository;
	
	/**
	 * To save the ticket
	 * @param ticket
	 */
	public void saveTicketTransaction(Transaction transaction)
	{
		transactionRepository.save(transaction);
	}
}
