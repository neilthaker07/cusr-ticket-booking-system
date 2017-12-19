package edu.sjsu.cmpe275.cusr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.sjsu.cmpe275.cusr.model.Ticket;
import edu.sjsu.cmpe275.cusr.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	@Query("FROM Transaction j where j.ticket.ticketId = :ticketId")
	Transaction findOneByTicket(@Param("ticketId") Long ticketId);
}
