package edu.sjsu.cmpe275.cusr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.sjsu.cmpe275.cusr.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

}
