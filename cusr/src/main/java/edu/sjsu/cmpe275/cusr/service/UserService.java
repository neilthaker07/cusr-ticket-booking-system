package edu.sjsu.cmpe275.cusr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.sjsu.cmpe275.cusr.model.User;
import edu.sjsu.cmpe275.cusr.repository.UserRepository;

@Component
public class UserService {
	
	@Autowired 
	UserRepository userRepository;
	
	public void saveUser(User user){
		userRepository.save(user);
	}

}
