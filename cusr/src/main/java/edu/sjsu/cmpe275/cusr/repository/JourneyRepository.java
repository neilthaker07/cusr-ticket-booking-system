package edu.sjsu.cmpe275.cusr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.sjsu.cmpe275.cusr.model.JourneyDetails;

public interface JourneyRepository extends JpaRepository<JourneyDetails, Long>{

	@Query("select sum(j.passengers) FROM JourneyDetails j WHERE j.journeyTrainId = :journeyTrainId and j.journeyDate=:journeyDate "
			+ " and 1 = (case when (( :searchedFromStation >= j.source and :searchedToStation<= j.destination ) OR ( :searchedFromStation < j.source and :searchedToStation<= j.destination and :searchedToStation>j.source )  "
			+ " OR ( :searchedFromStation < j.source and :searchedToStation> j.destination ) OR ( :searchedFromStation > j.source and :searchedFromStation< j.destination and :searchedToStation>j.destination )) then 1 else 2 end) ")      
	int findByJourneyTrainIdAndJourneyDate(
	    		@Param("journeyTrainId") Long journeyTrainId,
	    		@Param("journeyDate") String journeyDate,
	    		@Param("searchedFromStation") int searchedFromStation,
	    		@Param("searchedToStation") int searchedToStation
	    		);
	
	@Query("FROM JourneyDetails j where j.ticket.ticketId = :ticketId")
	JourneyDetails findOneByTicket(@Param("ticketId") Long ticketId);
}
