package edu.sjsu.cmpe275.cusr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.sjsu.cmpe275.cusr.model.JourneyDetails;

public interface JourneyRepository extends JpaRepository<JourneyDetails, Long>{

	/*@Query("select sum(j.passengers) FROM journey_details j WHERE j.journey_train_id = :journey_train_id and j.journey_date = :journey_date and "
			+ " 1 = (case when (( :searchedFromStation >= j.source && :searchedToStation<= j.destination ) OR ( :searchedFromStation < j.source && :searchedToStation<= j.destination && :searchedToStation>j.source ) "
			+ " OR ( :searchedFromStation < j.source && :searchedToStation> j.destination ) OR ( :searchedFromStation > j.source && :searchedFromStation< j.destination && :searchedToStation>j.destination )) then 1 else 2 end) ")
	    int getTotalBookedSeats(
	    		@Param("journey_train_id") Long journey_train_id,
	    		@Param("journey_date") String journey_date,
	    		@Param("searchedFromStation") int searchedFromStation,
	    		@Param("searchedToStation") int searchedToStation,
	    		@Param("passengers") int passengers
	    		);*/
}
