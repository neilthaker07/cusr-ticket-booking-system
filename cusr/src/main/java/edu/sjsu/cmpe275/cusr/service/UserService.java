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
		// Only insert the user if it is not available in database
		if(!isUserWithEmailAddressExists(user.getEmailAddress()))
			 userRepository.save(user);	
	}
	
	public User getUserById(long userId){
		// TODO: Change the logic of fetching the userID by its Firebase ID instead of Primary Key
		return userRepository.getOne(userId);
	}
	
	public boolean isUserWithEmailAddressExists(String emailAddress){
		
		User user = userRepository.findUserByEmailId(emailAddress);
		
		if(user != null){
			return true;
		} else{
			return false;
		}
	}
	
	public long getUserId(String emailAddress){
		
		User user = userRepository.findUserByEmailId(emailAddress);
		
		if(user != null){
			return user.getId();
		} else{
			return -1;
		}
	}
}
