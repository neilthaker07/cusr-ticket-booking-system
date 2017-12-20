package edu.sjsu.cmpe275.cusr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.sjsu.cmpe275.cusr.model.Train;

public interface TrainRepository extends JpaRepository<Train, Long>{

	@Query("FROM Train j where j.departureTime = :departureTime")
	Train findByDepartureTime(@Param("departureTime") int departureTime);
}
