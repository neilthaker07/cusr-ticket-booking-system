package edu.sjsu.cmpe275.cusr.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.sjsu.cmpe275.cusr.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

	@Query("FROM Passenger j where j.ticket.ticketId = :ticketId")
	ArrayList<Passenger> findManyByTicket(@Param("ticketId") Long ticketId);
}
