package edu.sjsu.cmpe275.cusr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.sjsu.cmpe275.cusr.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
