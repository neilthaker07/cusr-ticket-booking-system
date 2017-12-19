package edu.sjsu.cmpe275.cusr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.sjsu.cmpe275.cusr.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

	@Query("update Ticket t set t.isCancelled=1 where t.ticketId = :ticketId ")
	int setIsCancelledFor( @Param("ticketId") Long ticketId);
	
}
