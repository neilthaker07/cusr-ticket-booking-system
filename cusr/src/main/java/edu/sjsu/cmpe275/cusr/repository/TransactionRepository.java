package edu.sjsu.cmpe275.cusr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.sjsu.cmpe275.cusr.model.Ticket;
import edu.sjsu.cmpe275.cusr.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
