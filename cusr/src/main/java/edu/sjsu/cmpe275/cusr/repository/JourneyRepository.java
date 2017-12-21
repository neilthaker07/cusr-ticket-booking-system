package edu.sjsu.cmpe275.cusr.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.sjsu.cmpe275.cusr.model.JourneyDetails;

public interface JourneyRepository extends JpaRepository<JourneyDetails, Long>{

	@Query("select (case when sum(j.passengers) is null then 0 else sum(j.passengers) end) as passengers FROM JourneyDetails j WHERE j.trainNo = :trainNo and j.journeyDate=:journeyDate "
			+ " and 1 = (case when (( :searchedFromStation >= j.source and :searchedToStation<= j.destination ) OR ( :searchedFromStation < j.source and :searchedToStation<= j.destination and :searchedToStation>j.source )  "
			+ " OR ( :searchedFromStation < j.source and :searchedToStation> j.destination ) OR ( :searchedFromStation > j.source and :searchedFromStation< j.destination and :searchedToStation>j.destination )) then 1 else 2 end) ")      
	int findByJourneyTrainIdAndJourneyDate(
	    		@Param("trainNo") String trainNo,
	    		@Param("journeyDate") String journeyDate,
	    		@Param("searchedFromStation") int searchedFromStation,
	    		@Param("searchedToStation") int searchedToStation
	    		);
	
	@Query("FROM JourneyDetails j where j.ticket.ticketId = :ticketId")
	JourneyDetails findOneByTicket(@Param("ticketId") Long ticketId);
	
	@Query("FROM JourneyDetails j where j.ticket.ticketId = :ticketId")
	ArrayList<JourneyDetails> findManyByTicket(@Param("ticketId") Long ticketId);
	
	@Query("FROM JourneyDetails j where j.trainNo = :trainNo and j.journeyDate = :journeyDate ")
	ArrayList<JourneyDetails> findByTrainIdAndJourneyDate(@Param("trainNo") String trainNo, @Param("journeyDate") String journeyDate);
	
}
