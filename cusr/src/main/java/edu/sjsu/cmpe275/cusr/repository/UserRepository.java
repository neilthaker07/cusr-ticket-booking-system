package edu.sjsu.cmpe275.cusr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.sjsu.cmpe275.cusr.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("FROM User u WHERE u.emailAddress = :email")
    User findUserByEmailId(@Param("email") String email);
	
}
